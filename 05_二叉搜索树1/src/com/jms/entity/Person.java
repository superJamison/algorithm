package com.jms.entity;


/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 22:05
 */
public class Person implements Comparable<Person>{
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person person) {
        return age - person.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
