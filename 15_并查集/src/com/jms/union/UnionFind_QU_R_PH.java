package com.jms.union;

/**
 * quick union  基于Rank(树的高度)的优化  路径减半(path halving)
 * @author Jamison
 * @version 1.0
 * @date 2021/4/8 20:09
 */
public class UnionFind_QU_R_PH extends UnionFind_QU_R{

    public UnionFind_QU_R_PH(int capacity) {
        super(capacity);
    }

    @Override
    protected int find(int v) {
        checkRange(v);
        while (parents[v] != v){
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return parents[v];
    }
}
