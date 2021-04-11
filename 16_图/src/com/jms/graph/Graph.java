package com.jms.graph;

import com.jms.Visitor;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/10 21:16
 */
public interface Graph<K, T> {
    //返回图的所有节点的数量
    int verticesSize();
    //返回图的边总数
    int edgesSize();

    //添加一个节点
    void addVertex(T t);
    //添加一条有权重的边
    void addEdge(T from, T to, K weight);
    //添加一个无权重的边
    void addEdge(T from, T to);

    //删除一个节点
    void removeVertex(T t);
    //删除一条边
    void removeEdge(T from, T to);
    //广度优先搜索
    void bfs(T t, Visitor<T> visitor);
    //深度优先搜索
    void dfs(T t, Visitor<T> visitor);

}
