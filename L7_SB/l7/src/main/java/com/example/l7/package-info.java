package com.example.l7;


/**
 *
 *  POST Idempotent
 *          AWS regions
 *
 *                                              (requestId - dnkjshdfkjs - Joey)
 *                                -------> Joey (instance 2)                                      ---------
 *                              /                                                                             \
 *              Load balancer   -------> Chandler (Instance 1)                                                    --------- DB
 *                              \                                                                              /
 *                               --------> Monika (Instance 3) Monika(requestId - dnkjshdfkjs  ---------
 *
 *
 *
 *                                      Distributed lock        -- Type 2
 *
 *  OPtimistic lock
 *      @Version -- we would be covering this
 *  Pessimistic
 *                                              --->      DB    --->        DB Schema   (Relational DB)
 *                                                                          Transactions
 *                                                                         {
 *                                                                             "from_user_Id" :  "1242"
 *                                                                             "from_wallet_id" : "12"
 *                                                                             "to_user_id" : "5454"
 *                                                                             "to_wallet_id" : "54545"
 *                                                                             "created_at" :
 *                                                                             "updated_at" "
 *                                                                             "id" : 3232
 *                                                                             "amout" : 10
 *                                                                             "version" : 1
 *                                                                         }
 *
 *           Operation      Monika              insert into transaction values (from_user_Id, ...., version=version+1 ) where version  = 1
 *
 *                                                                           {
 *  *                                                                             "from_user_Id" :  "1242"
 *  *                                                                             "from_wallet_id" : "12"
 *  *                                                                             "to_user_id" : "5454"
 *  *                                                                             "to_wallet_id" : "54545"
 *  *                                                                             "created_at" :
 *  *                                                                             "updated_at" "
 *  *                                                                             "id" : 3232
 *  *                                                                             "amout" : 10
 *  *                                                                             "version" : 2
 *  *                                                                         }
 *
 *           Operation       Joey                 insert into transaction values (from_user_Id, ...., version=version+1 ) where version  = 1
 *
 *                                                      Exception would be thrown Optimistic lock exception
 *
    https://vladmihalcea.com/optimistic-vs-pessimistic-locking/
    https://www.baeldung.com/jpa-optimistic-locking#:~:text=On%20the%20contrary%2C%20the%20pessimistic,locking%20may%20result%20in%20deadlocks. *
 *  https://www.baeldung.com/jpa-pessimistic-locking
 *
 *
 *
 *    Is Redis Multi Threaded                     -=======       Zookeeper
 *      or Single Thread
 *                    (62)
 *
 *
 *
 *
 *        Master and Slave
 *
 *
 *
 *
 *  Database -- storage unit
 *    Relational vs Non Relational
 *
 *
 *      Relational -- whereever you can think of    --- is a / has a dependnecy
 *      Employee            -----> of School / college
 *       (Teacher)               ----> a lot of departments - computerscience
 *                                                          - Cultural activities
 *                                                          - treasury head
 *              id                  foreign key in Department_employe
 *                                      (department_id_1, employeeId)
 *                                      (department_id_2, employeeId)
 *                                      (department_id_3, employeeId)
 *
 *
 *
 *        Examples
 *              ----> Mysql , Oracle Postgress , Coachroach (no sql but with help of Postgress driver -- you can execute sql queries and patterns)
 *
 *
 *
 *          ACID
 *          (Atomocity , Consistency , Durability and Isolation)
 *
 *
 *
 *       Non relation
 *          -- there do not exists relations
 *              Example
 *                  Mongo , Cassandra , Dynamo , Redis, firebase --
 *
 *
 *
 *          - Mongo - 4.3 + exhibit/ support transactions
 *          - Dynamo db -- support transactions within the same region across one or multiple tables
 *
 *
 *          CAP theorem
 *
 *          Consistenecy
 *          Availabiltiy
 *          Partial Tolerance
 *
 *          MySQL - Master Slave
 *          Non Relational - Cluster (Primary , secondary )
 *
 *                       CA - mysql / postgress
 *                       CP - consistency  and PT - Mongo
 *                       AP - casaandra
 *
 *
 *   Quorem Read and Quorem Write   -- CASSANDRA
 *                                  ()        -- USERID % 3     --- 0   (INSTANCE 0)
 *                                                              --- 1   (INSTANCE 1)
 *                                                              --- 2   (INSTANCE 2)
 *
 *
 *                                  CONSISTENT HASH (NODES)
 *
 *
 *
 *          UserProfile
 *              - may have optional attributes
 *              --  address , Veg Non Veg , email**
 *                  (a)     , a  ,  a
 *                          , a ,   a
 *                     a ,     ,    a
 *
 *
 *          -   Master Slave
 *
 *
 *
 *          Writes ------------------------->    Reads
 *          Master                              (Slave)
 *         Mysql                        0----> Mysql instance 2
 *
 *
 *                                              Producer
 *           CDC -- very import
 *                  -- Book my show ,
 *          Change Data Capture
 *
 *         Instace of Mysql                                                                     Queue datastructure
 *         (your own laptop)  -----> Agent on it    / an extra application running on it
 *                                      (Maxwell , Debezium)
 *
 *
 *         Mysql (insert happens)
 *         -- on a log statement
 *          ----> bin log                                   -----------> send events to kafka
 *        (insert 1, insert 2, update1, update 2)--                   Queue [insert 1,  insert 2,  update1, update 2]           Slave DB
 *
 *         Postgres                                                         a consumer application  (insert 1, insert 2,  update1, update 2)
 *          --> wall log
 *          Mongo
 *         ---> op log
 *
 *
 *
 *          JPA
 *              ------>
 *
 *      SpringBoot Application                              Mysql DB
 *          UserEntity                                            User
 *       id                                                       id
 *       email                                                       email
 *
 *
 *          pojo ----> serialization and deserialization takes place
 *
 *          ORM -- Abstract layer which constitues the mapping of the entity with the server
 *          Implementation - Hibernate
 *                          - Open jpa
 *           SpringDataJPA has  a default implementation of HIbernate as ORM.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */