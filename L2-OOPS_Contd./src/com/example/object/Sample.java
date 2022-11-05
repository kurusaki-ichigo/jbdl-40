package com.example.object;

/**
 * Object class is parent of every class in java
 *
 * Every class that we create inherits from object.class
 */
public class Sample {

    Integer integer = 10;

    @Override
    public String toString() {
        return "Sample{}";
    }

    /**
     * is java platform dependent or independent ?
     *      -- platform independent --
     *      (class ---> JVM)
     *
     *
     *      Can we inject code written in other languages into JVM to run ?
     */

    /**
     *
     *  Memory Mapping
     *
     *
     *      Heap Memory and Stack memory
     *
     *      Heap Memory is
     *          ----> JVm kick in
     *              and all objects are initializde , they are
     *              in Heap memory
     *
     *
     *      Stack memory
     *          ---> method references
     *          --> variables of primitive and references to the heap objects
     *
     *          CallbyValue / CallbyReference
     *          call by value for primitive , reference for objects (provided you modify the heap space allocated)
     *
     *
     *
     */


    public static class Memory {                    //          Heap                     Stack -s1
        public static void main(String[] args) {
            int primitiveDataType = 1;              //                                    primitive = 1
            Object referenceDataType = new Object();//  (--OBJECT--)            ---------->> referenceDataType
            Memory memory = new Memory();           //   (--MEMORY--)           --------->> memory
            memory.print(referenceDataType);
        }
                                                    //                                  Stack s2
        public void print(Object referenceDataType){//      (--OBJECT--)              referenceDataType
            System.out.println(referenceDataType.toString());;
        }
    }



    public static class Ballon {
        String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Ballon{" +
                    "color='" + color + '\'' +
                    '}';
        }
    }

                                                //          Heap                     Stack -s1
    public static void main(String[] args) {
        Ballon red = new Ballon();              //        (-----)             ---> red
        red.setColor("RED");                    //       (m1- RED)           -------> red
        Ballon blue = new Ballon();              //        (-----)             ---> blue
        blue.setColor("BLUE");                  //        (m2 -BLUE)             ---> blue
        System.out.println(red + " " + blue);   // red   Blue
        swap(red, blue);  //        (m1- RED , m2 -BLUE)             ---> red, blue
        System.out.println(red + " " + blue);   //
    }

//    1) Red BLUE         6
//    2) Blue red         9
                                                //          Heap                     Stack -s2
    public static void swap(Ballon red , Ballon blue){//  (m1- RED, m2 -BLUE)       red2 , blue2
        Ballon temp = blue;                     //        (m2 - BLUE)                      temp
        temp.setColor("VOILET");                //          (m2 -- VOILET)
        blue = red;                             //         (m1-RED)                           blue2
        red = temp;                             //          (m2 -VOILET)                      red2
    }

}
