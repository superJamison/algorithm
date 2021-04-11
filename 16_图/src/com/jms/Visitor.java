package com.jms;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/11 22:17
 */
public abstract class Visitor<T> {
    public boolean stop;
    public abstract boolean visit(T t);
}
