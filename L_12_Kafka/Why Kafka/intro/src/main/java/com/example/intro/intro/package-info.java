package com.example.intro.intro;


/**
 *
 *      Bookmyshow (ticket booking)
 *      TicketMaster (IRCTC / Airline / hotel / bus where seats are allotted at random)
 *
 *
 *      Book My Show
 *          ---->
 *          HLD
 *
 *             pick a region
 *                      ----> list of movies
 *                                      ---------> list of cinema halls + list of timings
 *                                                                  --------> Seat availability -----> even if you do not pay
 *                                                                                                  ---> your seat is on hold
 *
 *
 *     IRCTC
 *          HLD
 *              Pick from station
 *              Pick to Station
 *                              ------------> list of trains + availability
 *                                                          ---------> reservation options
 *                                                                          -----------------> collect payment
 *                                                                                      ------------> post payment is success
 *                                                                                                  ---------> alot a seat
 *                                                                                                      (if seat is not available)
 *                                                                                                      refund
 *
 *            Book My Show --
 *            HLD of IRCTC
 *
 *               Approach a problem --- ?
 *               Flow / functionality interviewer is requesting
 *               - search trains and stations (routes)
 *               - {
 *                   source -
 *                   destination -
 *                   Date of journey -
 *               }
 *
 *               List - of trains
 *
 *               Services
 *               User Service -----> Api Gateway --> Authentication and Authorization
 *               Search Microservice ---> Not only the list of trains but also seat availability
 *               Order Microservice ---> OrderId attached to Payment Details....
 *               Payment Microservice
 *               Notification Microservice --> SMS , EMAIL , PUSH
 *
 *
 *
 *
 *               Listing down entities
 *               User Entity
 *                  (username , pwd , name , mobile no, email, age )            --- User Preferences (food)
 *                                                                                          May mandatory . optional
 *                                                                                       (mandaotry , non mandatory , mandatory)
 *                  Go for Mongo... in this case
 *
 *                                            vertical vs horizontal
 *                                            scaling
 *                                            Vertical --- increase more horsepower (increase ram , CPu)
 *                                            horizontal -- no of instance ...
 *
 *
 *
 *               Trains - static
 *              Journey (dynamic) - with latest station that the train left
 *              Book/ Order table
 *                              -- Prefer MySQL
 *              Payment Details
 *                              -- Prefer MySQL
 *
 *
 *
 *             What kind of DB you would use and why ... ?
 *                      User Service  and why ?
 *                              what    -------------------> Consistenty and Availability
 *
 *
 *
 *
 *
 *
 *
 *              Availability
 *                          ---> availbility count design
 *
 *
 *
 *       Request flow       (netflix OSS , eureka zuul ribbon)
 *
 *                                                                           /------> Eureka (service discovery)
 *                                                                          /           (userSerrvice --- its LB url)
 *              -----> LB (ribbon)                  -----------------> Zuul (api gateway / proxy service)
 *                                                                          (/user/authenticate)
 *                  -----> (pax information)                                            \
 *                                                                                       \
 *                                                                                        \--------> User LB ------_> instance 1
 *                                                                                                         \------->   instance 2
 *
 *
 *
 *            /book
 *            {
 *                "Source"
 *                "Destination"
 *                "DOJ"
 *                PAXINFO {
 *                      ""
 *                      user_preferences {
 *
 *                      }
 *                }
 *                user_id:
 *            }
 *
 *
 *                              ----------> ORDER SERVICE
 *                                          ORDER -> INITIATED
 *                                                  -----------------------> USER SERVICE (CREATE NEW USERS** T&C Applied )
 *                                                  <----------------------
 *                                               ---> PENDING ,
 *                                               -----------------------------------------PAYMENT_MICROSERVICE------------------------------> REDIRECT REQUEST TO EXTERNAL
 *                                                                                                                                              (ORDER_ID)
 *
 *                                                                           /------> Eureka (service discovery)
 *                                                                          /
 *              -----> LB (ribbon)                  -----------------> Zuul (api gateway / proxy service)
 *                                                                          (/payment/callback)
 *                                                                          \
 *                                                                           \
 *                                                                            \------------ PAYMENT MICROSERVICE
 *                                                                                              (SUCCESS)
 *                                          ORDER_SERVICE<--------------------------------------
 *
 *
 *
 *                                                          ----------------> SEAT ALLOCATION ** T&C Applied
 *                                             ORDER SUCCESS <--------------
 *                                              ---------------------------------------------------------------> DECREASE AVAILABILITY COUNT
 *                                              ---------------------------------------------------------------> NOTIFICATION
 *                                              --------------------------------------------------------------> STATEMENT GENERATION
 *
 *
 *             How do you call external services
 *                          ---> Rest Template and OKHttpBuidler (Postman)
 *                          ---> Netflix OSS --> OPen FEIGN CLIENT as well..
 *
 *
 *
 *          Queue as a DataStructure
 *
 *
 *
 *
 *          Kafka as a distributed QUEUE
 *              All the communication take place over a topic
 *              (messages that are being sent may or may not be distributed)
 *
 *
 *              [5--4----3---2---1]
 *                                  -----> per queue there would be a consumer associated
 *                                  each consumer belong to a consumer group
 *
 *
 *              [4, 1] per partition there would be one consumer belong to a consumer group
 *              [2]
 *              [5, 3]
 *
 *
 *
 *              Joey Chandler Monika --- character of FRIENDS
 *              Mike , Harvey , Donna , Rachel -- characters of SUITS
 *
 *
 *
 *              sequnetialization is left...
 *
 *
 *                                                       Zookeeper
 *
 *          Application --------> send a message        kafka Broker           <-------  consumer (applications)
 *
 *                                                      (brokers in odd numbers)
 *
 *  10 messages
 *
 *  3 partitions
 *
 *                                                                  Q and A
 *                                                                  no of max consumers we should have
 *                                                                      and why ?
 *                                                                      for max parallelization
 *
 *                                                                      3   + 1
 *                                                                     ( no of partitions + 1 ) consumers
 *
 *
 *                     3  2  1  0
 *                   [10, 7, 4, 1]      -0th
 *
 *                   [    8, 5, 2]        1
 *
 *                   [    9, 6, 3]       2nd
 *
 *
 *
 *                   Different TOPIC
 *                   TOPIC_OFFSET_CONSUEMR_GROUP
 *
 *                   Our 0 th consumer has processed messages till 2d offset
 *                   and crashed ...
 *                   we do have 3rd consumer as a backup ... now from where 3rd consumer should start ?
 *                   it should start from 3rd offset --->
 *
 *
 *
 *                    *              sequnetialization is left...
 *
 *                   Producer                        BROKER
 *                                  -------> [topic      ------->]  <------ consumer would read messages
 *
 *                                ----->   TOPIC (Key , Value )
 *                                  Sending a message (K , value )
 *
 *                 How would you feel
 *                              ---> $ 10  , $ 100000 , $30 , $15
 *
 *
 *                              --->$ 15, $100000, $30, $10  --- happy or sad
 *                              why --- ?
 *
 *
 *              notification
 *                      []
 *                      []
 *                          []
 *                      []
 *                      []
 *
 *
 *            Internal working of a HASHMAP
 *                  ------>
 *
 *
 *                              Hashing Fucntion (key) ----- find the array index
 *
 *
 *                                                   (array)
 *
 *                                    0               |     ---- (100, 2)  , (16, 4)  (linked list - jdk < 8 , Btree jdk>=8 )
 *                                    1               |
 *                                    2               |     ---- (50, 3)
 *                                    3               |
 *
 *                       hash function
 *                       % 4
 *
 *                       HashMap .put (100, 2)
 *                       Hash of the key is done (100 % 4 = 0)
 *
 *
 *                       HashMap .put (50, 3)
 *                       50 % 4 = 2
 *
 *                       HashMap.put(16, 5)
 *                       16 % 4  = 0
 *
 *
 *
 *
 */