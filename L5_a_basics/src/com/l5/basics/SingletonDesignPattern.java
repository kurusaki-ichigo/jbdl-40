package com.l5.basics;


/**
 *
 *
 *
 *
 *  Singleton
 *          ------> Single instance of the class per application server
 *
 *
 *
 */
public class SingletonDesignPattern {

    /**
     *  Lamest way
     *      What the drawback
     *
     */
    static class SingletonLamest {
        private static SingletonLamest lamest;
        /**
         * constructor should be private
         */
        private SingletonLamest(){

        }

        public static SingletonLamest getInstance(){
            if(lamest == null){
                lamest = new SingletonLamest();
            }
            return lamest;
        }
    }


    /**
     * adding synchronization over method is costly operation
     */
    static class SingletonImprovement_1 {
        private static SingletonImprovement_1 first;

        private SingletonImprovement_1(){

        }

        public static synchronized SingletonImprovement_1 getInstance(){
            if(first == null){
                first = new SingletonImprovement_1();
            }
            return first;
        }
    }

    /**
     *  Eager binding
     *  problem -- even when no usage of this singleton is being done - there exists one object occupying space
     */
    static class Singleton_2 {
        private static Singleton_2 singleton2 = new Singleton_2();
        private Singleton_2(){

        }
        public static Singleton_2 getInstance(){
            return singleton2;
        }
    }

    static class Singleton_3 {
        private Singleton_3(){
        }

        private static Singleton_3 singleton_3 = null;

        /**
         *
         * @return
         */
        public static Singleton_3 getInstance(){
           if(singleton_3 == null){
               synchronized (Singleton_3.class){
                   if(singleton_3 == null){
                       singleton_3 = new Singleton_3();
                   }
               }
           }
        return singleton_3;
        }
    }


    /**
     * Joey Chandler Monika
     */
    static class Singleton_4 {
        private static volatile Singleton_4 singleton_4 = null;
        private Singleton_4(){}

        public static Singleton_4 getInstance(){
            if(singleton_4 == null){
                synchronized (Singleton_4.class){
                    if(singleton_4 == null){
                        singleton_4 = new Singleton_4();
                    }
                }
            }
            return singleton_4;
        }
    }


    /**
     *
     *  Singleton with enum -- H.W.
     *
     */


}
