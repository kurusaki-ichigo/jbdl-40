package com.l5.basics;

public class Main {


    /**
     *
     *  Synchronized keywords refer to --> add around method vs add around instance
     *
     *  Race Condition
     *      - CR critical section
     *          - synchronization
     *          - keyword ------------> volatile
     *
     *  Race Condition
     *      Multiple threads were updating the same resource or where dependent on each other
     *          so as to provide a updated value --> in deadlock (A -----> B -----> A)
     *
     *     Critical Sections
     *      - Section of code where the manipulation is happening ...
     *
     *
     *      synchronized : whats the use of synchronized
     *          - add a locks (lock on a single instance)
     *
     *
     *
     *
     *
     *   In Distributed Environment -   there wont be a single pod running (k8 mesh)
     *                                  or multiple instances
     *
     *      User ----->         FE ------> Backend
     *      [client ---> application] --- request / flow of request
     *                                          -----> Api Gateway -----> rate limiter / throttler (akamai) --
     *                                                                      (IP blocking :: )
     *           - ngnix (route / proxy)
     *
     *                                                          server1             DB
     *                        - (Load balancer)                 server2
     *                                                          server3
     *
     *                        [-- Consistent Hashing]
     *
     *
     *  https://www.livemint.com/technology/apps/zomato-paytm-disney-hotstar-other-websites-hit-by-major-internet-outage-details-here-11626972055053.html
     * @param args
     */
    public static void main(String[] args) {

    }
}
