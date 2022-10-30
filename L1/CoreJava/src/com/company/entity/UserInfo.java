package com.company.entity;

import java.time.LocalDate;



public class UserInfo {


    public String sample = "sample";




    /**
     *
     *
     *  Synchronized
     *  Semaphore and re entrant lock
     *              -----> Singleton
     *              --> Singleton Design Pattern
     *  T1
     *  T2
     *
     */


    /**
     *
     *  access modifier
     *
     *      private
     *          |
     *          only with in the class its accessible
     *          (wont allow access to the same thing)
     *          where
     *
     *
     *      protected
     *          |
     *          Visible in all classes of same package
     *          + child class of different package
     *
     *          when protected to be used ?
     *          - methods --
     *          - variable - protected
     *          List<Roles>
     *
     *
     *
     *
     *
     *
     *      public
     *          |
     *              accessible everywhere
     *              (any can modify)
     *              methods public -- access of it ..
     *              certain variables to be public -- why and when - application variables
     *              where
     *                  - methods -- it makes sense
     *                  - variables -- it does not -- but app contants , it makes sense
     *              --  Mutable  and immutable --
     *              Mutable - something which can change   (layman ok)
     *              Immutable - something which cannot change (layman ok)
     *
     *
     *
     *
     *      default
     *          |
     *          within the package
     *          enhances security layer -- (disagree)
     *          Why default variables --- what is need of it ..
     *
     *
     *
     *         4 pillars
     *
     *          1) Abstractions     -
     *          (Hiding implementation)
     *
     *
     *
     *
     *
     *          2) Encapsulation     - to combine methods and data of the same class
     *              |
     *               (hiding information)
     *
     *
     *          3) Inheritance      -
     *              parent child relations / 'is a' relationship
     *
     *           1)   Multiple              ------- Single Child , Multiple Parent
     *           2)   Multi level           A -- B --- C --- D
     *           3)   Single level          A --- B
     *           4)   Heriarchial               A
     *                                      B   C   D
     *
     *
     *              (User print("i am user") )  (Cook print("i am a cook") )
     *                  |                            |
     *                               (Employee )
     *
     *
     *              Supported
     *                       d) 2 3 4   - 11
     *
     *
     *
     *
     *          4) Polymorphism     -
     *                  Poly - many
     *                  morph - form
     *        -  Compile                   -  Runtime
     *             - method overload        - method overriding
     *
     *
     *
     *       It tomorrow - call by value ()
     *          42 - votes
     *          Is java call by value (tomorrow) - 10
     *          Is java call by reference  -
     *
     *
     *
     * Note         java does not provide similar methods - name as well and params identical in the order
     *          What do you mean by similar here()
     *
     *
     *
     *      abstract class vs interface when to use which one -- ?
     *
     *      method -- dont want implementation in current context/ base class - abstract
     *      abstract method in class -- that class is abstract.
     *
     *
     */




    private Long id;
    protected String name;
    public LocalDate dob;
    String email;


    // by default  empty constructor is provided

    /**
     *
     *  what happens if I provide a constructor with certains params
     *
     *
     *
     *  - if there is no constructor
     *      -- by default compiler would inject a default contructor
     *
     */

    private void validate(){
        // implemented
    }

    private void addUserToDB(){
        // implemented

        // add to db
    }

    public  void add(){
        validate();
        // check if the same user exists

        addUserToDB();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
