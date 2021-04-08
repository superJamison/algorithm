package com.jms.union;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/8 19:25
 */
public abstract class UnionFind {
    protected int[] parents;

    public UnionFind(int capacity){
        if (capacity <= 0){
            throw new IllegalArgumentException("capacity must be > 0");
        }

        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     * 查找v所属的集合
     * @param v
     * @return
     */
    protected abstract int find(int v);

    public abstract void union(int v1, int v2);
    /**
     * 检查v1和v2是否属于同一个集合
     */
    public boolean isSame(int v1, int v2){
        return find(v1) == find(v2);
    }

    protected void checkRange(int v) {
        if (v < 0 || v >= parents.length){
            throw new IllegalArgumentException("v is out of bounds!");
        }
    }
}
