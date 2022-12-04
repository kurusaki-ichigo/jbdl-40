package com.example.mappings.mapping.history.oneToMany;


/**
 *
 *  Many to One or One to Many Mappings
 *
 *
 *
 *  What is the relation between entites
 *
 *
 *      User                        Order
 *      1 unique   --------------->  N
 *       1 user    <---------------  1 unique order
 *
 * -------------------------------------------
 *   1          -----------------> N                Effective relationship
 * -------------------------------------------
 *
 *
 *
 *      User                                                        Order
 *      (u_pk1, email1)                                             (ordr_pk1 , amount1)
 *      (u_pk2, email2)                                             (ordr_pk2 , amount2)
 *                                                                  (ordr_pk3 , amount3)
 *                                                                  (ordr_pk4 , amount4)
 *                                                                  (ordr_pk5 , amount5)
 *
 *
 *  1 representation                                                order table as it is
 *
 *  Foregin key
 *  Alter table User add Foreign key ()
 *  (u_pk1, email1, {ordr_pk1, ordr_pk3, ordr_pk5})
 *  (u_pk2, email2, {ordr_pk2, ordr_pk4})
 *
 *
 *  2 representation
 *  User table as it is                                              Order
 *                                                                  @JoinColumn
 *                                                                  (ordr_pk1 , amount1, u_pk1)
 *                                                                  (ordr_pk2 , amount2, u_pk2)
 *                                                                  (ordr_pk3 , amount3, u_pk1)
 *                                                                  (ordr_pk4 , amount4, u_pk2)
 *                                                                  (ordr_pk5 , amount5, u_pk1))
 *
 *
 * User1 -- Orders 1, 3, 5
 * User2 - Order 2, 4
 *
 *   1 NF , 2 NF , 3NF , BCNF -- read around these concepts
 *
 * which one would you prefer and why ?
 *      1 --- 3   -- why ? ------->
 *      2 --- 1 --
 *
 *      following queries
 *          1) customer support would need to check for order status when requested by customer to check the delivery status
 *          2) Get all orders by user
 *
 */