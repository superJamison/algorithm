package com.jms.union;

/**
 * quick union  基于Rank(树的高度)的优化  路径分裂(path spliting)
 * @author Jamison
 * @version 1.0
 * @date 2021/4/8 20:09
 */
public class UnionFind_QU_R_PS extends UnionFind_QU_R{

    public UnionFind_QU_R_PS(int capacity) {
        super(capacity);
    }

    @Override
    protected int find(int v) {
        checkRange(v);
        while (parents[v] != v){
            int p = parents[v];
            parents[v] = parents[p];
            v = p;
        }
        return parents[v];
    }
}
