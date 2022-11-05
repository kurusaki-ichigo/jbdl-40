package com.example.exceptions;

import com.example.exceptions.example.classCastException.Employee;
import com.example.exceptions.example.classCastException.Student;
import com.example.exceptions.example.classCastException.user.UserInfo;

//@ControllerAdvice
//@RestControllerAdvice
public class ExceptionHandling {

    /**
     //    @ExceptionHandler(Exception.class)

     Exceptions -- >
     Checked                            Uncheck
     () CompileTime                     () Runtime
     I/O                                ArithematicException (Divide by 0)
     FileHandling                       Class Cast Exception
     DatabaseException                  ArrayIndexOutOfBound

     * @param args
     */

    /**
     *      When to prefer checked exceptions
     *          (Best practices)
     *          --- whenever there is a business logic and you add contraint because of that
     *           - throw checked exception
     *
     *
     * class cast exception
     * @param args
     */
    public static void main(String[] args) {
        UserInfo userInfo = new Employee();
        performCheck(userInfo);
    }


    public static void performCheck(UserInfo userInfo){
        if(userInfo instanceof Student){
            System.out.println(((Student) userInfo).isStudent());
        }
    }

    public static class SampleUnchekedException extends RuntimeException{
        SampleUnchekedException(String message){
            super(message);
        }
    }

    public static class SampleCheckedException extends Exception {
        SampleCheckedException(String message){
            super(message);
        }
    }



}
