package com.jms.union;

/**
 * quick find   find复杂度：O(1)  union复杂度：O(n)
 * @author Jamison
 * @version 1.0
 * @date 2021/4/8 19:40
 */
public class UnionFind_QF extends UnionFind{
    public UnionFind_QF(int capacity) {
        super(capacity);
    }

    /**
     * 父节点就是根节点
     */
    @Override
    protected int find(int v) {
        checkRange(v);
        return parents[v];
    }

    /**
     * 将v1所在集合的所有元素都嫁接到v2上父节点上
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1){
                parents[i] = p2;
            }
        }
    }


}
