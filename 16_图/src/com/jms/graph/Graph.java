package com.jms.graph;

import com.jms.Visitor;
import lombok.*;
import sun.security.provider.certpath.Vertex;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/10 21:16
 */
public abstract class Graph<K, T> {
    protected WeightManager<K> weightManager;

    public Graph(WeightManager<K> weightManager) {
        this.weightManager = weightManager;
    }

    //返回图的所有节点的数量
    public abstract int verticesSize();
    //返回图的边总数
    public abstract int edgesSize();

    //添加一个节点
    public abstract void addVertex(T t);
    //添加一条有权重的边
    public abstract void addEdge(T from, T to, K weight);
    //添加一个无权重的边
    public abstract void addEdge(T from, T to);

    //删除一个节点
    public abstract void removeVertex(T t);
    //删除一条边
    public abstract void removeEdge(T from, T to);
    //广度优先搜索
    public abstract void bfs(T t, Visitor<T> visitor);
    //深度优先搜索
    public abstract void dfs2(T t, Visitor<T> visitor);
    public abstract void dfs(T t, Visitor<T> visitor);

    //拓扑排序
    public abstract List<T> topologicalSort();

    //最小生成树，K 代表权重
    public abstract Set<EdgeInfo<K, T>> mst();

    //单源最短路径 Dijkstra算法 | bellmanFord算法
    public abstract Map<T, PathInfo<K, T>> shortestPath(T begin);

    //多源最短路径 Floyd算法
    public abstract Map<T, Map<T, PathInfo<K, T>>> shortestPath();

    //K 权值
    public interface WeightManager<K>{
        //比较权值大小
        int compare(K w1, K w2);
        //权值相加
        K add(K w1, K w2);
        K zero();
    }

    @Getter @Setter @ToString
    public static class PathInfo<K, T>{
        protected K weight;
        protected List<EdgeInfo<K, T>> paths = new LinkedList<>();
    }

    @Setter@Getter@AllArgsConstructor@ToString
    public static class EdgeInfo<K, T>{
        private T from;
        private T to;
        private K weight;
    }
}
