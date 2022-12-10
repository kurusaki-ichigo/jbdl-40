package com.example.cache.cache;


/**
 *
 *
 *
 *      Caching
 *          --> technique to fetch data faster
 *          --> Redis , Aerospike
 *
 *         Caching
 *                  --------------_> JVM cache --> (non distributed) example - EHcache
 *                  ---------------> Non JVM cache ---> distributed example -- Redis (Aerospike)
 *
 *
 *        Fetching data from memory is faster
 *          -- fetching data from disk is faster -- ?
 *
 *          memory is faster
 *
 *
 *          Use case    :
 *
 *          Tinyurl ---> Not heard of it --- ?
 *
 *          Tiny url is -- ? shorten url , alias ,for longer urls
 *
 *          tinyurl.com/y3x7vw2h
 *          https://www.primevideo.com/detail/0P0U1PZWS8UIIPT8O070YFH5CL/ref=atv_hm_hom_1_c_cjm7wb_2_2
 *          100 characters in length
 *
 *
 *          What's the usecase of it -- ?
 *          benefits
 *          whenever websites has a character limit
 *              Twitter -- tweets are majorly 180 chars
 *              no way to share video
 *                      ----> video snip ---> external hosting
 *                      --> make it shorten and then tweet.
 *
 *              length of 180 chars -- ? any reason -- ?
 *              Mysql - a default column , small text , long text, blob
 *
 *              why 150 - 180 -----?
 *                          SMS ---> implementation -- 1 SMS - 150-180 chars (to reduce the bandwidth of each of tweet)
 *                          10MB of data to be displayed vs 100 MB of data to be displayed
 *                          (displayed faster --> 10 MB)
 *
 *                         H.W. - size require to store a tweet .
 *
 *                         how much bits of data per char * 180 --> total bits --> go kb --- < 10KB
 *                         150 Bytes. < 0.2KB of data per tweet .
 *
 *
 *                Tying to share more info / relevant info in FB posts too.. then also --> prefer using tinyurl
 *
 *
 *
 *       What would be the DB schema for it
 *          {
 *              "tiny-url":   -- is our primary key -- as by default every primary key is unique
 *                              - VARCHAR(6)
 *              "redirect-url" - Small Text
 *              "created_at": - OffsetDateTime
 *              "user_id": - UUID of the user
 *          }
 *
 *
 *        Assumptions for system design
 *          - Store data for an year -------------
 *          - Read to Write distribution
 *          (Read : write -- 100    :   1) ratio
 *
 *          -- Max writes for a heavy load system
 *              Transactions per hour --> 1,000,000 writes
 *
 *              urls per day - 1 mill * 24 = 24m
 *              how many urls per year  = 24m * 365 =   8760  000 000
 *
 *
 *
 *              now in 1 char how much information can we store
 *
 *              [0-9][a-z][A-Z]
 *              10+ 26 +26 = 62 combinations
 *
 *              2 characters = 62*62
 *              62*62 combinations are possible out of  = 2 characters
 *
 *              8760  000 000 combinations -- how many charactes
 *
 *              6 characters are required to store the complete information
 *
 * 2 = 2.28
 * 3
 * 4 = 593
 * 5 = 9.56
 * 6
 *
 *
 *              Reads --> 100,000,000 per hour
 *              per sec = 28 K
 *                   --- A very good MYSQL instance
 *               read -- powered by slave + cache
 *
 *
 *  REquest flow
 *      ---> Client ---> api gateway --> rate limitter / throttler --> LB ---> instance     ------- DB
 *                                                                       \                         /
 *                                                                        \ ----> instance 2 ----/
 *
 *
 *                                                                      more than one instance
 *
 *
 *                                   instance required for an application
 *                                      ----> distribution for a traffic
 *                                                          (general) -- payment service
 *                                                      ---> traffic high at 1 AM
 *                                                      ---> traffic high at 1 PM
 *
 *                                                      Do you think the resource required at 1PM would be
 *                                                      same as resources required at 1 AM  ?
 *
 *
 *                                                      Resources required would be less
 *
 *
 *                       HOw this scaling or increase in the instances of the applications
 *                       takes place -->
 *                                          consistent hashing - mechanism to route traffic
 *                                          no of hits per transaction      ----> define -- life traffic
 *
 *                                  auto scaling
 *                                          ----> automatically resources getting scalled up
 *
 *
 *                      LB -- is responsible to distribute traffic  ---- between instances
 *                          -- different algo for LB to decide which instance to route traffic to
 *
 *
 *                                                                   LB         --->  Joey (instance1)    ------- DB
 *  *                                                                       \                                   /
 *  *                                                                        \ ---->  Rachel (instance2)    ----/
 *
 *                                  ---> Round Robin
 *                                  ---> Based on the health of the instance
 *                                  ---> Based on the time to perform a similar query
 *                                  --> UserId hash
 *                                  ---> CPU utlilization
 *                                  --> Consistent Hashing
 *
 *                                     4 _______
 *                                   / 2v    3v    1 \
 *                                  ( 3          )
 *                                   \         4v /
 *                                     1v ______2
 *
 *
 *
 *                                  rules -- define autoscaling
 *                                      ---> CPU utlilization    (60 % of CPU utilization)
 *
 *
 *          Tiny URL example
 *
 *                      implement this by JVM Cache
 *                      (algo for load balancer)
 *
 *
 *                      {
 *  *              "tiny-url":   -- is our primary key -- as by default every primary key is unique
 *  *                              - VARCHAR(6)
 *  *              "redirect-url" - Small Text
 *  *              "created_at": - OffsetDateTime
 *  *              "user_id": - UUID of the user
 *  *          }
 *
 *
 *                             "user_id": - UUID of the user
 *
 *                   Joey -     1
 *                   Rachel-    2
 *                   Monika-    3
 *                  Chandler-   4
 *
 *
 *                   {
 *  *  *              "tiny-url":   --
 *  *  *              "user_id": -      1
 *  *  *          }
 *
 *                         -->USE ALGO :  UserId hash
 *
 *
 *
 *                                      --> LB ---> instance  (1,3)   ------- DB
 *                                             \                             /
 *                                              \ ----> instance (2,4) ----/
 *
 *
 *
 *      JVM cache -- (java virtual machine)             HashMap = new HashMap  (data should be small)
 *                                     (1)              (tiny_url , big_url)
 *                                     (3)
 *
 *                                     (2)              (tiny , Big)
 *                                    (4)
 *
 *                                                 LRU Cache
 *                                                 Least Recently Used      (Eviction)
 *                                                 Least Frequently Used
 *
 *
 *                                   how does 'something is getting viral' is being captured
 *                                          youtube
 *                                          how much time you think
 *                                          would be required
 *                                          Time required for 1 vs 2
 *                                          for a youtube video to reach 1 million  views vs next 1 million views
 *                                          which activity would be faster ----> ? 2nd one -- why is that so ---?????
 *
 *
 *                                          Time required
 *                                                  for a song to remain a hit
 *                                                              max duration (exception cases)      - 41 weeks
 *                                                              average duration (most of the cases) < 10 weeks
 *
 *
 *                                          Cache for 10 weeks but not 41 weeks
 *                                          ---->
 *
 *                                          (Youtube recommendation system works)
 *                                          no of likes -- 50K benchmarkk ---> get popular with people with similar interst
 *                                              ----> showing the same to different users too (less)
 *                                                  -----> video --- trending
 *                                                      ----> reginal trending (in state , in country)
 *                                                      ---> international hit / trending
 *
 *
 *                                              Cache Hit
 *                                                              (when you requested a resource, it was present in cache)
 *
 *
 *
 *                                                 Cache Miss
 *                                                              (when you request a resource , it was not available)
 *
 *
 *
 *
 *      Cache per instance
 *      ie is it is not distributed in nature
 *
 *
 *
 *
 *
 */