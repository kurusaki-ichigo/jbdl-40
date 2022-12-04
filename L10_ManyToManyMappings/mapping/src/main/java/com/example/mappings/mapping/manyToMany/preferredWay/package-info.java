package com.example.mappings.mapping.manyToMany.preferredWay;


/**
 *
 *  User (you all are)                          Course (JBDL)
 *                          give rating
 *
 *
 *                  user to course
 *                  1 ----  N
 *                   N <---- 1
 *                   -----------
 *
 *                   N        N             Many to Many
 *                   ____________
 *
 *
 *      User                                                Course
 *  (u_1 , name1, email1)                                            (c_1 , b_name1)
 *  (u_2 , name2, email2)                                            (c_2 , b_name2)
 *  (u_3 , name3, email3)                                            (c_3 , b_name3)
 *
 *
 *      U1 --- (c1, c3)
 *      U2 --- (c2)
 *      U3 --- (c1, c3)
 *                          (course rating)
 *                         u_1,   c_1, rating_1, review1
 *                         u_1,   c_3, rating_2, review2
 *                         u_2,   c_2, rating_3, review3
 *                         u_3,   c_1, rating_4, review4
 *                         u_3,   c_3, rating_5, review5
 *
 *
 *
 *
 *  User to Course Rating
 *
 *  1 unique user -----> course rating rows N
 *   1             <----- 1 course rating row
 * ----------------------------------
 * 1                        N               One User To Many Course Ratings
 * -------------------------------          Many Course Ratings to One user
 *
 *
 *  Course to Course Rating
 *
 *  1 unique course -----> course rating rows N
 *   1             <----- 1 course rating row
 * ----------------------------------
 * 1                        N               One Course To Many Course Ratings
 * -------------------------------          Many Course Ratings to One Course
 *
 *
 */