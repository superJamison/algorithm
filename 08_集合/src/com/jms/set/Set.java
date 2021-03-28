package com.jms.set;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/28 16:32
 */
public interface Set<T> {

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(T element);

    void add(T element);

    void remove(T element);

    void traversal(Visitor<T> visitor);

    public static abstract class Visitor<T>{
        boolean stop;
        public abstract boolean visit(T element);
    }

}
