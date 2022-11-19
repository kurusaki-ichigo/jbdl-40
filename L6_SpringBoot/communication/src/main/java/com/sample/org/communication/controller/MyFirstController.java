package com.sample.org.communication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 *
 *  2 ways to create a bean
 *      --> @Component
 *              --- class level
 *              create instance of a class
 *
 *
 *
 *      --> @Bean
 *              ---- method level
 *          create instance of custom object -- and also that of dependency included
 *                                                          (when we are playing with redis and kafka)
 *
 * @Component -
 *              Directly or indirectly
 *
 *
 */
@RestController
//@Repository
//@Service
@Slf4j
public class MyFirstController implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Post bean construction !!!");
    }
    /**
     *
     *  Types of loggers
     *      1) Trace
     *      2) Debug
     *      3) Info
     *      4)warn
     *      5) error
     *
     */


    /**
     *
     *  CRUD -
     *  Interview Question - which http method is not idempotence
     *
     *  POST    ---     Is used to create  or modify a resource  (partial update)
     *  GET     ---     Is used to fetch a resource
     *  PUT     ---     replaces the entire resource
     *  DELETE  ---     Is used to delete a resource
     *
     *      What is safe and idempotent
     *       --
     *       safe -- if it does not alter the server state
     *       idempotence - multiple identical requests having same outcome
     *
     *  GET ---->       ------->    check DB    -------> DB
     *                          if (resource exists)
     *                                  return resource with 200 OK
     *                          return 404 (not found)
     *
     *                      Idempotency - yes
     *
     *  PUT ---->           ----> check DB      --------DB
     *                                      <-----------
     *                          (resource exist, modify if completly)   -----> DB
     *                                      <-------- (404 not found)
     *
     *                      Idempotency - yes
     *
     *  DELETE --->    Is used to delete a resource ?
     *                      if (resource exist)
     *                              ---> delete 200 ok
     *
     *                      if does not exist
     *                              ---> nothing to delete (200 ok)
     *
     *                      Idempotency - yes
     *
     *
     *  POST -->  what would be the kind of flow
     *
     *      {
     *          "userIdFrom" : "2222",
     *          "userIdTo" : "1111",
     *          "shot" : 1
     *      }
     *
     *                  Idempotent - No
     *                                  (5)
     *          ---> post (add -- 2)
     *
     *
     *   How to make this idempotent
     *
     *              ---> first solve it with single instance application
     *                  (-->                )
     *
     *
     *             -----> Multi instance of the application being deployed.
     *
     *
     */

    /**
     *  Request Param
     *  Path Variable
     *      How is the internal data structure for Set implementation
     *                  ---> HashMap
     *
     *    Service ---> distributed HashMap
     *                  --> redis       (preffered is with a time lock)
     *                  --> zookeeper
     *
     *
     *
     *
     *      two people having same name --->
     *    ----->
     *
     *
     */

    Set<String> identifierSet = new HashSet<>();
    int value = 5;
    @PostMapping(value = "/add")
    public String doSomething(@RequestParam(name = "delta") Integer delta,
                              @RequestParam(name = "identifier") String identifier){
        if(!identifierSet.contains(identifier)){
            value+= delta;
            identifierSet.add(identifier);
        }
        log.info(" ------------------->  value is : {} ", value);
        return String.valueOf(value);
    }

    /**
     * Query param vs path variable
     *              ---->
     *
     *       * Request param
     *      *              when you do not have certainintly and querying things
     *
     *     *  Path Variable --
     *      *          when you do have certaininty about the operation being performed
     *
     *
     * @param variable
     * @return
     */

    @GetMapping(value = "/hey/{baby}")
    public String doSomething3(@PathVariable(name = "baby") String variable){
        return variable;
    }

    @GetMapping(value = "/hey")
    public String doSomething(@RequestParam("queryParam") String queryParam){
        return "I was doing just fine before I met you" + queryParam;
    }


    @RequestMapping(value = "/hey2", method = RequestMethod.GET)
    public String doSomething2(){
        return "I was doing just fine before I met you .. drink too much and that an issue";
    }


    /**
     *  IOC
     *          --> Inversion of Control
     *                      --->      layman terms
     *                                  ----> you need not need to take care of bean (lifecycle)
     *                                  Spring is your buddy here to take care of it .!!!
     *
     *
     *    Bean identification would be done -- basis
     *                                  of @Component Directly or indirectly
     *              --> method identification based on request mapping
     *
     *               (())
     *               |  |
     *               |__|
     *
     *               (name , Bean)
     *
     *  Dependency Inject
     *
     *
     *  OnboardingController
     *
     *  MyFirstController                   ---> (Onboarding Controller)
     *                                                          MyFirstController
     *
     *
     *      https://stackoverflow.com/questions/19884295/soap-vs-rest-differences
     */



}
