package com.example.mappings.mapping.history.oneToOne;


/**
 *
 *
 *  Bus Booking / IRCTC
 *
 *  User                            UserProfile
 *                                  orientation ,  seat preference (aisle , middle , lower , side middle , sidelower) , meal preference (veg/nonveg)
 *
 *                                  Foreign key -- ?
 *                                                  a primary key in other table
 *  {u_id1, name1, email1}          {up_id1 , or1, sp1 , mp1 , u_id1}
 *  {u_id2, name2, email2}          {up_id2 , or2, sp2 , mp2,   u_id2}
 *  {u_id3, name3, email3}          {up_id3 , or3, sp3 , mp3 , u_id3}
 *
 *
 *
 * ---> request flow
 *
 *                  ----> Authentication        --> user is valid or not
 *                  and
 *                      Authorization           --> resource is allowed or not
 *
 *
 *                          /create-booking         ---> customer (User -- user_role - end_user)
 *                          /accept-booking         ---> driver (User -- user_role - driver)
 *
 *
 *       Same microservice
 *                  --> what do you think would have a higher tps or    qps
 *                              (transactions per seconds) -- queries per seconds
 *
 *                              User vs UserProfile which would have more traffic
 *                              (Jam Tara) <--> netflix series
 *                              --> login , authorization   ( -- you would blacklist such accounts)
 *
 *
 *
 *
 *                              Origination of scam
 *                              (your own account)  ---> acc1 ---> acc2 ---> acct 3----------> scammerAccount
 *                                                  ---> acc8 ---> acc9 ---> acct 10 ---------->
 *
 *
 *
 *                              Authrization
 *                                  ----> your account / userId would be tagged as blocked / unblocked
 *                                  --> every microservice would have that check
 *
 *                                  ---> your user DB would be having mroe traffic as compared to userProfile
 *                                  User
 *
 *
 *
 *
 */