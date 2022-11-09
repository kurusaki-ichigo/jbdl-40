package com.stream.init.c_lamdas;

import com.stream.init.c_lamdas.crud.User;
import com.stream.init.c_lamdas.crud.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaWays {


    /**
     * Sort them on basis of roll numbers
     */
    public static void main(String[] args) {
        List<Students> studentsList = Arrays.asList(
                new Students(),
                new Students(),
                new Students(),
                new Students(),
                new Students(),
                new Students()
        );

        /**
         * Sort them on basis of roll numbers
         */
        Collections.sort(studentsList, new Comparator<Students>() {
            @Override
            public int compare(Students o1, Students o2) {
                return o1.rollNo.compareTo(o2.rollNo);
            }
        });

        /**
         *  whats basically a lamda
         *
         *  (args) --> {body associated}
         *
         */
        Collections.sort(studentsList, (o1, o2) -> o1.rollNo.compareTo(o2.rollNo));


        /**
         * method reference
         *      lamda expression making call to an existing method
         *
         *      Static Method reference
         *          (args) -> class.staticMethod(args)
         *          class::staticMethod
         *
         *           (o1, o2) -> Students.compareById(o1, o2)
         *            Students::compareById
         */

        Collections.sort(studentsList, (o1, o2) -> Students.compareById(o1, o2));
        Collections.sort(studentsList, Students::compareById);

        /**
         *
         * instance method of a particular class
         *
         *
         */
        SortingStudents sortingStudents = new SortingStudents();
        Collections.sort(studentsList, (o1, o2) -> sortingStudents.compareById(o1, o2));
        Collections.sort(studentsList,sortingStudents::compareById);
        /**
         * Constructor reference
         *      - Springboot
         *
         *      CRUD
         *         C - Create
         *         R - READ
         *         U - Update
         *         D - Delete
         */

        UserRepository userRepository = new UserRepository();
        User joey = new User("Joey");
        User monika = new User("Monika");
        User chandler = new User("Chandler");
        User rachel = new User("Rachel");

        userRepository.persist(joey);
        userRepository.persist(monika);
        userRepository.persist(chandler);
        userRepository.persist(rachel);


        /**
         * find the following user if exists
         *
         *      chandler
         *      ross
         *      pheobe
         *      monika
         *
         */

        User existingChandler = userRepository.findUserByName("Chandler").orElseThrow(RuntimeException::new);
        User existingRoss = userRepository.findUserByName("Ross").orElseThrow(() -> new RuntimeException("User Not Found"));
        User existingPheobe = userRepository.findUserByName("Phoebe").orElseThrow(() -> new RuntimeException("User Not Found"));
        User existingMonika = userRepository.findUserByName("Monika").orElseThrow(() -> new RuntimeException("User Not Found"));


    }

}
