package com.sample.org.communication;


/**
 *
 * Whats a master
 *              --- slave ?
 *
 *              Design tiny url
 *
 *
 *              --->    MySQL
 *
 *
 *
 *            Master Slave architecture
 *
 *              --> Relation Database -- where in
 *
 *              rows and column
 *                                            employee {
 *                                                "name" :
 *                                                "department"
 *                                            }
 *
 *                      DR --> Disaster Recovery
 *
 *          Question
 *                  ---> Just in real time
 *
 *
 *      Master Slave
 *                                                  (same region)        -- Ireland / Mumbai
 *         Application     --------------> MySql       master (insert)         ---------?> Master 2
 *                                                              |
 *                                                              |
 *                                                              |
 *                                                              |       (CDC)
 *            Fetch              --------------> MySql       slave            --------> Slave2
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