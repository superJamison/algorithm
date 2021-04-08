package com.jms.union;

/**
 * quick union  基于Rank(树的高度)的优化  路径压缩(path compression)
 * @author Jamison
 * @version 1.0
 * @date 2021/4/8 20:09
 */
public class UnionFind_QU_R_PC extends UnionFind_QU_R{

    public UnionFind_QU_R_PC(int capacity) {
        super(capacity);
    }

    @Override
    protected int find(int v) {
        checkRange(v);
        if (parents[v] != v){
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
