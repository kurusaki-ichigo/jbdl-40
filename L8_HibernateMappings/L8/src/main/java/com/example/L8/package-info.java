package com.example.L8;


/**
 *
 *  Disadvantage of the older way
 *      -   more prone to error
 *      -   difficult to maintain
 *      -   conversion of java object from resultset
 *
 *  (abstract layer out)
 *  Domain object ----> Presistence layer
 *  -> Spring Data JPA
 *
 *      JPA
 *          -> Java Persistent API
 *          --> 2019 , java 17+...
 *          javax
 *              ---> jakarta
 *
 *          Jakarta Persistence api
 *
 *
 *  	 * 		-	Books			 * 		-	Authors
 *
 *  	 Every book has one author (hypothesis)
 *
 *  	 Book               Author
 *  	 1                  1
 *  	 N                   1
 *  	------------------------
 *      N                   1       Effective relationship
 *      -----------------------
 *      Relationship
 *
 *      OneAuthorToManyBooks
 *      and reverse would be ManyBooksToOneAuthor
 *
 *      DB schema
 *      Books                                                   Authors
 *      (b1, name1, isbn1 , Author1 , AuthorName1, AuthorEmail1)
 *      (b2, name2, isbn2 , Author1 , AuthorName1, AuthorEmail1)
 *      (b3, name3, isbn3 , Author2 , AuthorName2, AuthorEmail2)
 *      (b4, name4, isbn4 , Author1 , AuthorName1, AuthorEmail1)
 *
 *      Normalizations
 *          1 NF , 2 NF , 3 NF , BCNF <---->
 *
 *
 *      Scenario 1
 *                           (foreign--key)
 *                                  -------------> ??
 * @JoinColumn
 *       (b1, name1, isbn1 , A1)                            ( Author1 , AuthorName1, AuthorEmail1)
 *       (b2, name2, isbn2 , A1)                            (Author2 , AuthorName2, AuthorEmail2)
 *      (b3, name3, isbn3, A2 )
 *       (b4, name4, isbn4 , A1)
 *
 *
 *      Scenario 2
 *       (b1, name1, isbn1 )                            ( Author1 , AuthorName1, AuthorEmail1, [B1, B2, B4])
 *       (b2, name2, isbn2 )                            (Author2 , AuthorName2, AuthorEmail2, [B3])
 *      (b3, name3, isbn3 )
 *       (b4, name4, isbn4 )
 *
 *
 *  Why ?????
 *              --> Scenario 1
 *
 *
 *        User                           Order
 *          1                           N
 *          1                            1
 *-------------------------------------------------
 *      1                               N
 *-------------------------------------------------
 *
 *
 *
 *          OneUserManyOrders
 *
 *
 *          OneOrderManyUsers
 *
 *
 *
 *
 */