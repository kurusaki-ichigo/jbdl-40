package com.l5.basics;

public class Basics {

    /**
     *
     *
     *
     *
     *
     *  IP vs DNS
     *
     *  unique address to a machine
     *  DNS -- domain name server --- giving a name to the machine
     *
     *
     *
     *
     * https://www.google.com
     *  (base url)
     *
     *
     * /search
     * (api end point)
     *
     * ?q=amazon.in&sxsrf=ALiCzsZpa0L2b4SBJsgNp8Eh8z7_atHWOw%3A1668531687765&source=hp&ei=58VzY_CALL_sjuMPgbKE0As&iflsig=AJiK0e8AAAAAY3PT91V2Tgmxg1n4oZOihJjiZ6Px1Vgp&ved=0ahUKEwiwkKz01LD7AhU_tmMGHQEZAboQ4dUDCAg&uact=5&oq=amazon.in&gs_lcp=Cgdnd3Mtd2l6EAMyCwgAEIAEELEDEIMBMggIABCABBCxAzIFCAAQgAQyCwgAEIAEELEDEIMBMgsIABCABBCxAxCDATILCAAQgAQQsQMQgwEyCAgAEIAEEMkDMgUIABCABDIFCAAQgAQyCwgAEIAEELEDEIMBOgcIIxDqAhAnOhQILhCABBCxAxCDARDHARDRAxDUAjoFCC4QgAQ6BAgAEAM6EQguEIAEELEDEIMBEMcBENEDOgUIABCxAzoHCCMQJxCdAjoECCMQJzoUCC4QsQMQgwEQxwEQ0QMQ1AIQkQI6CAgAELEDEJECOggIABCxAxCDAToICC4QsQMQgwE6DAgjECcQnQIQRhD6AVDeOFi6XWDNX2gDcAB4AYABxQOIAeIMkgEHMC44LjQtMZgBAKABAbABCg&sclient=gws-wiz
     *  (query params)
     *
     *
     *
     *
     *      Server  -
     *      Webserver vs Application server -
     *      webserver (generally desinged to render static content)
     *
     *
     *      User ----> FrontEnd -----> backend
     *
     *  FrontEnd ---> UI --- React native app , web app , androind , ios
     *      Responsibilities of FE -- UI/ UX desing
     *                              --- validate the input request that needs to be triggered.
     *      Responsibilities of Backend
     *                  -- receiving the request from client and processing it
     *                  -- Supporting persistence layer (-- DB, Mysql , mongo , casandra... )
     *                  -- Triggering events -->
     *                  -- returning the response to the client.
     *
     *     Springboot
     *              is a framework
     *                      -- is it only for java ?    java , kotlin , groovy..
     *
     *
     *  Spring (tomcat / a server deployed eternally)       SpringBoot (internal server)
     *
     *      Server
     *          - tomcat ------ (60-70 %)   -- apache
     *          - jetty ------  (10-13%)    -- eclipse
     *          - undertow and other    -- redhat community
     *
     *          Jenkins -- ????
     *
     *  SpringBoot
     *
     *      Client ---> Server (tomcat) -- connection poll ---> (application/ jar) ----> DB
     *
     *
     *     Client ---> Application (embedded tomcat) ---> DB
     *
     *     Spring                       vs      SpringBoot
     *      --> standalone server           --> embedded tomcat
     *      --> xml notations             ---> @Annotations
     *                                      ---> auto configurations
     *      ---> does not have these priv.      --> H2 (in memory database)
     *
     *
     *
     *
     *      sample cab manangement
     *          ----> Phonepe and other companies too..
     *
     *
     *      onboarding a user is common to every one
     *
     *      Get     -- is actually used to fetch the data from our application which would query the DB
     *      Post    -- is to create a new resource / update partially
     *      Put     -- replacing the entire resource ()
     *      Delete     -- it would help in deleting the resource
     *      Patch     --  update partially
     *      Options     -- This is generally the api call made to the server to check if the api is valid / accessible or not
     *
     *
     *      StatusCode
     *          {
     *          200 -- ok
     *          201 -- created     (a new resource is create)
     *          202 -- accepted (we )
     *
     *          302 - redirected
     *
     *          400 - Bad request
     *          401 - Unauthrorized
     *          403 - Forbidden
     *          404 - Not found
     *           500 - interval server error
     *          }
     *
     *          2xx is a kind of success
     *          4xx is a kind of error (client)
     *          5xx is a kind of error (server)
     *
     *
     *      200 ok
     *      {
     *          eeror message
     *      }
     *
     *      How status codes fucks things out  - Uber eats free food paytm
     *      https://twitter.com/gergelyorosz/status/1502947315279187979?lang=en
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
