package com.multi.threading;

public class Sample {

    /**
     *
     *
     *  Async vs Parallel vs Concurrent Programming
     *
     *
     *
     *      Parallel =
     *          Without depending on each other processes are running
     *          - Can I eat and watch movies / listen to songs ? together -- ?
     *          Parallel
     *
     *
     *
     *      Concurrent =
     *          One after other
     *           - Can I eat and sing simultaneously
     *
     *
     *      Async Programming / Callbacks -
     *              -   Threadpool = Executors = ForkJoinPool() -- Traceable Executors
     *              CallBack --
     *      Processing occrs in background and normal executoion is not interrupted
     *
     *      -- Microservice Architecture
     *      -- Monolith architecture
     *
     *              Monolith
     *      -- every service or every feature that we provide is wrapped up as a single service
     *
     *      FaceBook
     *          - (features --> {Adding Friends})
     *              - user service (--> Authentication (verify a user) vs Authorization (user has those priviledges / not) )
     *              - Facebook marketplace ->
     *              - Facebook Pay -
     *              - Occulus
     *              - Chats
     *              - Notification
     *
     *      Server
     *          (Cloud) -
     *              Microservice
     *                      --> every feature based upon scalability is broken into individual service
     *
     *
     *          user to order
     *          Amazon / flipkart / walmart -- anything
     *
     *
     *          Inventory associated with product (itemsIds are passed)
     *
     *          ---> request would flow --->APi Gateway <--->  (akamai) -----> ngnix ---> tomcat --> SBoot application  --> Database
     *                                      (RateLimiting and throttling)
     *
     *
     *
     *
     *      SBoot application  --> Database                 SB2 -----> DB2
     *              ------------------------------------------>
     *              <--------------------------------------
     *
     *        UserService
     *              --> authorized ---> (-- whether user is allowed to purchase a product)
     *              -----------------> Inventory (to check if that item is available)
     *              <--------- Go Ahead
     *              -----------(common requestId)----------------> Order Service (order)   ---(requestId)----> Payment (lets pay)
     *                                                                     Order Succes         <------ Pay success
     *                              inventory (reduce)<-----------------
     *                  traceId , spanId (per microservice basis)
     *
     *
     *  Process - Vs Thread
     *
     *      Processor - Computing power .. it has cores to compute ...
     *      One processor
     *          -T1   -T2   -T3   -T4   - T1
     *          (concurrent)    - context switching between the tasks -- that happens in milli / nanos
     *
     *
     *          Thread
     *              -- a lightweight program
     *
     *          Process and Threads
     *              Process - tasks which are sent to CPU for execution
     *              The processes can have the following states -
     *                          new  , ready, running , waiting , terminated and suspended.
     *
     *              Threads
     *                  A segment of Process. A process can have multiple threads and multiple threads are contained within
     *                  process.
     *                  The thread has following states :
     *                      Running Ready Blocked.
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *      yeild vs sleep
     *
     *      [Context -- >> running a thread -- a small task which is being perfomed / executed --> current would be slept]
     *
     *
     *
     *
     *
     *
     *      Process
     *      [tasks]
     *          -T1     -T2     -T3     -T4
     *
     *          Sleep
     *          Pool of threads -- one thread has picked - Thread 1 - task1 , sleep     { ..... sleep is[task would be / thread would be paused here] called ....}
     *
     *        Yeild     -- your task --- would return to task queue and might be picked up by the different thread.
     *
     *
     *
     *
     *
     * @param args
     */

    public static void main(String[] args) {

    }
}
