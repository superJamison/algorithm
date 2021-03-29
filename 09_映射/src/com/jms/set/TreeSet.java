package com.jms.set;

import com.jms.map.Map;
import com.jms.map.TreeMap;

import javax.xml.soap.Node;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/28 22:35
 */
public class TreeSet<K> implements Set<K>{

    Map<K, Object> map = new TreeMap<>();


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(K element) {
        return map.containKey(element);
    }

    @Override
    public void add(K element) {
        map.put(element, null);
    }

    @Override
    public void remove(K element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor<K> visitor) {
        if (visitor == null){
            return;
        }
        map.traversal(new Map.Visitor<K, Object>() {
            @Override
            public boolean visit(K key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
