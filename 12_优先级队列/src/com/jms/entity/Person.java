package com.jms.entity;

import java.util.PriorityQueue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/1 20:22
 */
public class Person implements Comparable<Person>{
    private String name;
    private Integer boneBreak;

    public Person(String name, Integer boneBreak) {
        this.name = name;
        this.boneBreak = boneBreak;
    }

    @Override
    public int compareTo(Person o) {
        PriorityQueue queue;
        return this.boneBreak - o.boneBreak;
    }

    @Override
    public String toString() {
        return "Name:" + name + ", BoneBreak:" +boneBreak;
    }
}
