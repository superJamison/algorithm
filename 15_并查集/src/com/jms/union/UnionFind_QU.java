package com.jms.union;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/8 19:48
 */
public class UnionFind_QU extends UnionFind{
    public UnionFind_QU(int capacity) {
        super(capacity);
    }

    /**
     * 不断的往上找根节点，当父节点是自己本身的话就是找到了根节点
     */
    @Override
    protected int find(int v) {
        checkRange(v);
        while (v != parents[v]){
            v = parents[v];
        }
        return v;
    }

    /**
     * 将v2的跟节点嫁接到v1的根节点
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        parents[p1] = p2;
    }
}
