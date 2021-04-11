package com.jms.graph;

import com.jms.Visitor;

import java.util.*;

/**
 * @param <K> 边的权重
 * @param <T> 节点的元素
 * @author Jamison
 * @version 1.0
 * @date 2021/4/10 23:06
 */
public class ListGraph<K, T> implements Graph<K, T> {
    private Map<T, Vertex<K, T>> vertices = new HashMap<>();
    private Set<Edge<K, T>> edges = new HashSet<>();
    private Visitor<Vertex<K, T>> visitor;

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    /**
     * 添加节点
     */
    @Override
    public void addVertex(T t) {
        if (vertices.containsKey(t)) return;
        vertices.put(t, new Vertex<>(t));
    }

    @Override
    public void addEdge(T from, T to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(T from, T to, K weight) {
        Vertex<K, T> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<K, T> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        //创建一条新的边
        Edge<K, T> edge = new Edge<>(fromVertex, toVertex);
        //先将边从set中删除
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

        //赋值新的权重
        edge.weight = weight;

        //重新设置边
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);

    }

    @Override
    public void removeVertex(T t) {
        Vertex<K, T> vertex = vertices.remove(t);
        if (vertex == null) return;

        Iterator<Edge<K, T>> iterator = vertex.outEdges.iterator();
        while (iterator.hasNext()) {
            Edge<K, T> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            //将当前遍历到的edge从集合edge.outEdge中删除
            iterator.remove();
            edges.remove(edge);
        }

        Iterator<Edge<K, T>> inIterator = vertex.inEdges.iterator();
        while (inIterator.hasNext()) {
            Edge<K, T> edge = inIterator.next();
            edge.from.outEdges.remove(edge);
            //将当前遍历到的edge从集合edge.outEdge中删除
            inIterator.remove();
            edges.remove(edge);
        }

    }

    @Override
    public void removeEdge(T from, T to) {
        Vertex<K, T> fromVertex = vertices.get(from);
        if (fromVertex == null) return;

        Vertex<K, T> toVertex = vertices.get(to);
        if (toVertex == null) return;

        Edge<K, T> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    public void print() {
        vertices.forEach((T t, Vertex<K, T> vertex) -> {
            System.out.println(t);
            System.out.println(vertex.inEdges);
            System.out.println(vertex.outEdges);
            System.out.println("===================");
        });

        edges.forEach((Edge<K, T> edge) -> {
            System.out.println(edge);
        });
    }

    /**
     * 广度优先搜索
     *
     * @param t
     */
    @Override
    public void bfs(T t, Visitor<T> visitor) {
        if (visitor == null) return;
        Vertex<K, T> startVertex = vertices.get(t);
        if (startVertex == null) return;

        //保存被访问过的节点
        Set<Vertex<K, T>> visitorVertex = new HashSet<>();
        Queue<Vertex<K, T>> queue = new LinkedList<>();
        queue.offer(startVertex);
        //代表这个节点被访问过了
        visitorVertex.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<K, T> vertex = queue.poll();
//            System.out.println(vertex.value);
            if (visitor.visit(vertex.value)) return;

            for (Edge<K, T> outEdge : vertex.outEdges) {
                //判断这个to节点是否已经访问过了
                if (visitorVertex.contains(outEdge.to)) continue;
                queue.offer(outEdge.to);
                //代表这个节点被访问过了
                visitorVertex.add(outEdge.to);
            }
        }
    }

    @Override
    public void dfs(T t, Visitor<T> visitor) {
        if (visitor == null) return;
        Vertex<K, T> vertex = vertices.get(t);
        if (vertex == null) return;

        dfs(vertex, new HashSet<Vertex<K, T>>(), visitor);
    }

    private void dfs(Vertex<K, T> vertex, Set<Vertex<K, T>> visitorVertex, Visitor<T> visitor) {
//        System.out.println(vertex.value);
        if (visitor.visit(vertex.value)) return;
        visitorVertex.add(vertex);

        for (Edge<K, T> outEdge : vertex.outEdges) {
            if (visitorVertex.contains(outEdge.to)) continue;
            dfs(outEdge.to, visitorVertex, visitor);
        }
    }

    /**
     * 节点信息
     */
    private static class Vertex<K, T> {
        T value;
        Set<Edge<K, T>> inEdges = new HashSet<>();
        Set<Edge<K, T>> outEdges = new HashSet<>();

        public Vertex(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * 边信息
     */
    private static class Edge<K, T> {
        Vertex<K, T> from;
        Vertex<K, T> to;
        K weight;

        public Edge(Vertex<K, T> from, Vertex<K, T> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

}
