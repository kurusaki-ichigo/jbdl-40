package com.l5.basics;

public class Revision {


    /**




     https://www.google.com
     (base url)


     /search
     (api end point)



     ?


     q=amazon.in	&
     sxsrf=ALiCzsZXBAniHcB0vf8l9v0RTBhxE4fcRQ%3A1668870836660
     source=hp&ei=tPJ4Y5jyJKvLseMPs-yJgAo&iflsig=AJiK0e8AAAAAY3kAxK3bWQR8uwPtYp4BCpYbb_L9VDjI
     oq=ama
     gs_lcp=Cgdnd3Mtd2l6EAEYADIECCMQJzIECCMQJzIECCMQJzIRCC4QsQMQgwEQxwEQ0QMQkQIyCggAELEDEIMBEEMyBAgAEEMyBAgAEEMyCggAELEDEIMBEEMyDQguEMcBENEDENQCEEMyCggAELEDEIMBEEM6BwgjEOoCECdQtgFYiANgvBBoAXAAeACAAbkBiAHQA5IBAzAuM5gBAKABAbABCg&sclient=gws-wiz

     (query params)


     pass information from request url - path variable
     lets get an order information
     {
     Get -- /order/{orderId}						--- (Path variable)
     /order/bjksbfds-3nm23n--23n23n3j
     }


        Complete request flow

        Client ---> server
                                                                                    /---> instance 2
                ------> Api gateway --> rate limitter --> throttler --> loadbalancer ---> instance 1 ---> Db
                                                                                   \-----> instance 3


                --> IP vs DNS
     216.58.196.100



                Please create a static page and host your own resume online
                (you can purchase a domain or not -----> )





     */
}
