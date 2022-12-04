package com.example.mappings.mapping.manyToMany;


/**
 *
 *
 *
 *
 *
 *
 *  Author              to                              Book
 *  1                                               N
 *  N                                                1
 *  -------------------------------------------------------
 * N                                                    N       MANY TO MANY
 * --------------------------------------------------------
 *
 *
 *      (Author)                                                              Book
 *  (author_id1 , name1, email1)                                            (book_id1 , b_name1)
 *  (author_id2 , name2, email2)                                            (book_id2 , b_name2)
 *  (author_id3 , name3, email3)                                            (book_id3 , b_name3)
 *                                                                          (book_id4 , b_name4)
 *
 *
 *
 *  Representation 1                                                        table as it is.
 *  (author_id1 , name1, email1, {book_id1, book_id3})
 *  (author_id2 , name1, email1, {book_id2})
 *  (author_id3 , name1, email1, {book_id1, book_id3, book_id4})
 *
 *
 *  Representation  2
 *    as it is                                                              (book_id1 , b_name1, {author_id1, author_id3})
 *                                                                          (book_id2 , b_name2, {author_id2})
 *                                                                          (book_id3 , b_name3, {author_id1, author_id3})
 *                                                                          (book_id4 , b_name4, {author_id3})
 *
 *
 *           Options
 *       a)    1
 *       b)    2
 *       c)    Both
 *       d)    None           ------>
 *
 *      (Author)                                                              Book
 *  (author_id1 , name1, email1)                                            (book_id1 , b_name1)
 *  (author_id2 , name2, email2)                                            (book_id2 , b_name2)
 *  (author_id3 , name3, email3)                                            (book_id3 , b_name3)
 *                                                                          (book_id4 , b_name4)
 *
 *
 *                                          inventory
 *                                  id_1, author_id1, book_id1
 *                                  id_2, author_id1, book_id3
 *                                  id_3, author_id2, book_id2
 *                                  id_4, author_id3, book_id1
 *                                  id_5, author_id3, book_id3
 *                                  id_6, author_id3, book_id4
 *
 *
 *
 *                                          inventory
 *                                  (author_id1, book_id1) -- unique primary
 *                                  author_id1, book_id3
 *                                  author_id2, book_id2
 *                                  author_id3, book_id1
 *                                  author_id3, book_id3
 *                                  author_id3, book_id4
 *
 *
 *
 *      Author                              Inventory Table
 *  1 unique author has how many rows in inventory
 *
 *          1                               N (many)
 *           1                                 1 unique entry in inventory table can be mapped to how many authors
 *   ------------------------------------------------------
 *      1                                  N            OneToMany
 *  ------------------------------------------------
 *
 *         Book 1 has authors (1, 3)
 *         Book 2 has author (2)
 *         Book 3 has author (1, 3)
 *         book 4 has author (3)
 *
 *
 */