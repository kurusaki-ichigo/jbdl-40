package com.example.mappings.mapping.compositeKey;


/**
 *
 * Composite key
 *
 *      (key which has more than one column)
 *
 *
 *  Primary composite key
 *      (primary key which has more than one column)
 *
 *
 *
 *      Primary Index vs Unique Index  (HW yesterday)
 *
 mysql> show indexes from user_info;
 +-----------+------------+------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+---------+------------+
 | Table     | Non_unique | Key_name   | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment | Visible | Expression |
 +-----------+------------+------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+---------+------------+
 | user_info |          0 | PRIMARY    |            1 | id          | A         |           0 |     NULL |   NULL |      | BTREE      |         |               | YES     | NULL       |
 | user_info |          0 | identifier |            1  | identifier   | A         |           0 |     NULL |   NULL | YES  | BTREE      |         |               | YES     | NULL       |
 +-----------+------------+------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+---------+------------+
 *
 *
 *      what is the difference between the two ?
 *
 *
 *      Question
 *          If there are 3 columns over which an index is added
 *              (A,B,C) -- as a composite index
 *                  ----> how many indexes would it have --- and why ?? --- thing you all would need to remember
 *                  (assist you to write queries better)
 *
 *                  if the search is done by
 *                  (A ,B , C)
 *                  (B, A, C)
 *                  (A , C , B)
 *                  (C , A , B)
 *
 *
 *              ---    (email , mobile , dob)
 *                      A, B, C
 *
 *          select * from users where A  , B  C,            select * from users where email = ?     , mobile = ?    , dob = ?
 *          select * from users where C  , A  B,            select * from users where dob = ?       , email = ?     , mobile = ?
 *          select * from users where A  , C  B,            select * from users where email = ?       , dob = ?     , mobile = ?
 *
 *
 * Answer 1 >>>> 2
 *
 *
 *      Payment Application
 *
 *      ----> Notification Service
 *
 *              Design LLD
 *
 *              Identify actors / entities
 *              {
 *
 *                  "identifier" : "transactionId",
 *                  "to" : "user_id",
 *  *               "channel" : SMS , PUSH , EMAIL
 *                   "channelValue" : "sample@yopmail.com"
 *                  "dynamic_fields" :  {
 *  *  *                      name : "Monika" ,
 *  *  *                      amount : "10"
 *  *  *              }
 *                  "template_Id" : "1"
 *                  "created_at" :
 *                  "updated_at" :
 *              }
 *
 *
 *
 *          User Microserice which is also sending the message
 *
 *              Payment wallet ----> message ---> Notification
 *              m1 , m2 , m3 , m4 , m1 ------ prevent duplicate messages that are being sent -- ?
 *
 *              payload would be dynamic
 *              Template :              Hey  @name !, your wallet has been credited by @amount.
 *              Dynamic Variables : {
 *                      name : "Monika" ,
 *                      amount : "10"
 *              }
 *
 *
 *                 Template :              Hey  @name !, your wallet has been debited by @amount.
 *  *              Dynamic Variables : {
 *  *                      name : "Monika" ,
 *  *                      amount : "10"
 *  *              }
 *
 *
                    Template :             Hey..@name,  you have been successfully onboarded !!! .
 *  *              Dynamic Variables : {
 *  *                      name : "Joey"
 *  *
 *  *              } *
 *
 *
 *
 * TRansaction Microservice
 *
 *              {
 *
 *                  "identifier" : "1234567",
 *                  "source" : transaction_microservice
 *                  "to" : "user_id",
 *  *               "channel" : SMS , PUSH , EMAIL
 *                   "channelValue" : "sample@yopmail.com"
 *                  "dynamic_fields" :  {
 *  *  *                      name : "Monika" ,
 *  *  *                      amount : "10"
 *  *  *              }
 *                  "template_Id" : "1"
 *                  "created_at" :
 *                  "updated_at" :
 *              } *
 *
 *
 *  USer Microservice
 *  THERE WOULD BE A USER WITH ID - 1234567
 *
 *              {
 *
 *                  "identifier" : "1234567",
 *                  "source" : user_microservice
 *                  "to" : "user_id",
 *  *               "channel" : SMS , PUSH , EMAIL
 *                   "channelValue" : "sample@yopmail.com"
 *                  "dynamic_fields" :  {
 *  *  *                      name : "Monika" ,
 *  *  *                      amount : "10"
 *  *  *              }
 *                  "template_Id" : "1"
 *                  "created_at" :
 *                  "updated_at" :
 *              } *
 *
 *      ---        {"identifier" : "1234567", "source" : user_microservice }
 *
 *
 *
 */