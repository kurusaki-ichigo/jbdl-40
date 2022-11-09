package com.stream.init.c_lamdas.crud;

public class User {
    String name;
    Double id;

    public User(String name) {
        this.name = name;
        this.id = Math.random();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getId() {
        return id;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
