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
 *              Two different Group of Theives
 *              Theives ONE
 *              ( Joey Chandler Monika)
 *
 *              Theives TWO
 *  *              (Mike , Harvey , Donna , Rachel)
 *
 *
 *              Two different services consuming events -- different group name;
 *              ZOMATO , FOOD DELIVERY
 *              ---> ORDER ----> SUCCESS (PAYMENT DONE)             ----------------> (NOTIFICATION SENT TO USER)
 *                                                                  ----------------> REQUEST TO MERCHANT (RESTRAUNT)
 *
 *
 *
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
 *      Question
 *          -->
 *          Kafka ----------> what is it ? what it is used for
 *                          (Event bus)
 *                          Distributed Queue
 *                                                          Problems that it solves
 *                                                                      ---> highly resillent in nature
 *                                                                      --->    A ----> B ----> C
 *                                                                                                  (c has bad query and timeout)
 *
 *                                                                                  Prefer using Hysterix / Resillince 4j Circuit Breaker
 *                                                                                   Netflix - Hysterix
 *                                                                                   (Eureka , Zuul Ribbon , OpenFeign)
 *                                ---> what is the semantics over which kafka has been built
 *                                --> exactly once , atleast once , atmost once
 *
 *
 *                                Producer          Kafka Broker                Consumer
 *                                A -------------->      B       <------------- C
 *
 *                                                          Would consumer a message only once
 *                                     Consumer may read the message again (Atleast once)
 *                                                          At most once -- the message may or may not be consumed by consumer
 *                                                          but the max chance is it will be consumed once
 *
 *
 *                                   acknowledgement = with respect to broker
 *                                   spring.kafka.producer =---- acks =
 *                                                  -1 (all) , 0 , 1
 *
 *
 *
 *
 *                                   acknowledgement = with respect to consumer (auto ack)
 *                                                                              (manual acks)
 *                                                                           4   3   2    1    0
 *                                              (partition)         0   [    5, 4, 3, 2,  1]           <----------- Consumer
 *                                                                  1   [10,  9,   8,   7,  6]
 *                                               (offset)
 *
 *
 *              Question
 *                  Assume Consumer has read messages (1, 2, 3)
 *                  and after 3 , the consumer pod crashed ()
 *                  what do you think, the new consumer / a different consumer would read message from the 0th partition itself.
 *
 *              Answer
 *                  -- It starts from 4... how come ---------?
 *
 *                             topic ---> is --> ? EventName / EventType
 *                             // created by us
 *
 *                              topic_ offset_group     2
 *                              // created by kafka
 *
 *                                                                               3  2    1     0
 *                                              (partition)         0   [    5, 4, 3, 2,  1]           <----------- Consumer
 *                                                                  1   [10,  9,   8,   7,  6]
 *                                               (offset)
 *
 *                        Question - batch of records  (lets say batch size is 5) -- 5
 *                              assume there is an issue with message at offset 3
 *
 *                              How should kafka behave -- ?
 *                              --- Few such things ---> rollback entirely and retry
 *                                                  ---> retry specific
 *                                                  --> push the under desirable record to a different queue and retry there
 *
 *
 *
 *                          Question
 *                                  -----> What is the key based approach ----> ?
 *                                  in  terms of kafka
 *                                  HINT - internal implementation of HASHMAP
 *                                     hash of the key is done
 *
 *
 *                          ---> how would feel , 7 transactions
 *                          1000, 20, 40, 20000, 100, 32, 12345
 *
 *
 *               UserId -- hash -----------> Unique partition
 *                                                 6   5   4    3     2    1     0
 *                                             (1000, 20, 40, 20000, 100, 32, 12345) <------ Can more than one consumer read from
 *                                                                                          one partition
 *                                                                                          Why ?
 *                                                                           Consumer [2    1     0]
 *                                                                           Consumer [6   5]
 *                                      Queue ---> Sequential flow within a partition  but parallel in terms of no of partitions
 *
 *
 *
 *
 *
 *
 *
 *
 */