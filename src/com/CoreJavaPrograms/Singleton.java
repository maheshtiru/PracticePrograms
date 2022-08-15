package com.CoreJavaPrograms;

public class Singleton {
    public static void main(String[] args) {
        SingletonClass sc1 = SingletonClass.getInstance();
        System.out.println(sc1.s);

        SingletonClass sc2 = SingletonClass.getInstance();
        System.out.println(sc2.s);
    }
}

class SingletonClass {
    public String s;

    private SingletonClass() {
        s = "This is Singleton instance";
    }

    public static SingletonClass singletonInstance = null;

    public static SingletonClass getInstance() {
        if(singletonInstance == null){
            singletonInstance = new SingletonClass();
        }
        return singletonInstance;
    }
}
