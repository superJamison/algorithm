package com.jms.set;

import com.jms.tree.BinaryTree;
import com.jms.tree.RBTree;

/**
 * @author Jamison
 * @version 1.0
 */
public class TreeSet<T> implements Set<T>{

    RBTree<T> tree = new RBTree<>();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(T element) {
        return tree.contains(element);
    }

    @Override
    public void add(T element) {
        tree.add(element);
    }

    @Override
    public void remove(T element) {
        tree.contains(element);
    }

    @Override
    public void traversal(Visitor<T> visitor) {
        if (visitor == null){
            return;
        }
        tree.inOrderTraversal(new BinaryTree.Visitor<T>() {
            @Override
            protected boolean visit(T element) {
                return visitor.visit(element);
            }
        });
    }
}
