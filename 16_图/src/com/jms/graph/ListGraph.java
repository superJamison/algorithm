package com.jms.graph;

import com.jms.MinHeap;
import com.jms.UnionFind;
import com.jms.Visitor;

import java.util.*;

/**
 * @param <K> 边的权重
 * @param <T> 节点的元素
 * @author Jamison
 * @version 1.0
 * @date 2021/4/10 23:06
 */
public class ListGraph<K, T> extends Graph<K, T> {
    private Map<T, Vertex<K, T>> vertices = new HashMap<>();
    private Set<Edge<K, T>> edges = new HashSet<>();
    private Visitor<Vertex<K, T>> visitor;

    public ListGraph(WeightManager<K> weightManager) {
        super(weightManager);
    }

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

        Stack<Vertex<K, T>> stack = new Stack<>();
        Set<Vertex<K, T>> visitedSet = new HashSet<>();

        //首先访问起点
        stack.push(vertex);
        visitedSet.add(vertex);
        visitor.visit(vertex.value);

        while (!stack.isEmpty()){
            //弹出栈顶元素
            Vertex<K, T> ktVertex = stack.pop();

            //遍历元素的outEdges出度
            for (Edge<K, T> outEdge : ktVertex.outEdges) {

                //检查记录已经访问的节点的栈中是否已经有这个to了
                if (visitedSet.contains(outEdge.to)) continue;

                //将from和to入栈，并且将to添加到记录已经访问的节点的栈中，以及访问to的节点内容
                stack.push(outEdge.from);
                stack.push(outEdge.to);
                visitedSet.add(outEdge.to);
                visitor.visit(outEdge.to.value);

                //遍历出一个就退出循环
                break;
            }
        }
    }

    @Override
    public List<T> topologicalSort() {
        ArrayList<T> list = new ArrayList<>();
        Queue<Vertex<K, T>> queue = new LinkedList<>();
        Map<Vertex<K, T>, Integer> ins = new HashMap<>();

        //初始化参数
        vertices.forEach((T t, Vertex<K, T> vertex) -> {
            int size = vertex.inEdges.size();
            if (size == 0){
                queue.offer(vertex);
            }else {
                ins.put(vertex, size);
            }
        });

        while (!queue.isEmpty()){
            Vertex<K, T> vertex = queue.poll();

            //将取出的节点放入的返回结果中
            list.add(vertex.value);

            //遍历出度，查看to的入度是否为0
            for (Edge<K, T> outEdge : vertex.outEdges) {
                int toIn = ins.get(outEdge.to) - 1;
                if (toIn == 0) {
                    queue.offer(outEdge.to);
                }else {
                    ins.put(outEdge.to, toIn);
                }
            }

        }

        return list;
    }

    @Override
    public Set<EdgeInfo<K, T>> mst() {
        return kruskal();
    }

    @Override
    public Map<T, PathInfo<K, T>> shortestPath(T begin) {
        Vertex<K, T> beginVertex = vertices.get(begin);
        if (beginVertex == null){
            return null;
        }

        //初始化最短路径map
        Map<T, PathInfo<K, T>> selectedPaths = new HashMap<>();

        // <要计算的其他节点， begin到其他节点的最短路径>
        Map<Vertex<K, T>, PathInfo<K, T>> paths = new HashMap<>();
        for (Edge<K, T> outEdge : beginVertex.outEdges) {
            PathInfo<K, T> ktPathInfo = new PathInfo<>();
            ktPathInfo.paths.add(outEdge.info());
            ktPathInfo.setWeight(outEdge.weight);
            paths.put(outEdge.to, ktPathInfo);
        }

        while (!paths.isEmpty()){
            Map.Entry<Vertex<K, T>, PathInfo<K, T>> minVertex = getMinPath(paths);
            //minVertex离开桌面
            selectedPaths.put(minVertex.getKey().value, minVertex.getValue());
            paths.remove(minVertex.getKey());
            //对minVertex的outEdges进行松弛操作
            for (Edge<K, T> outEdge : minVertex.getKey().outEdges) {
                //如果outEdge.to已经离开桌面了，就没必要更新路径了
                if (selectedPaths.containsKey(outEdge.to.value) || outEdge.to.equals(beginVertex)) continue; // || outEdge.to.value.equals(beginVertex.value)
                //旧的最短路径
                PathInfo<K, T> oldPathInfo = paths.get(outEdge.to);
//                K oldWeight = oldPathInfo.weight;
                //新的可选路径，minVertex.getValue() + 边的路径
                K newWeight = weightManager.add(minVertex.getValue().weight, outEdge.weight);
                if (oldPathInfo == null || weightManager.compare(newWeight, oldPathInfo.weight) < 0){
                    PathInfo<K, T> ktPathInfo = new PathInfo<>();
                    ktPathInfo.weight = newWeight;
                    //此处不能直接这样赋值，它所赋值的是数组的引用，但是这里需要创建一个新的数组，ktPathInfo.paths = minVertex.getValue().paths
                    ktPathInfo.paths.addAll(minVertex.getValue().paths);
                    ktPathInfo.paths.add(outEdge.info());
                    paths.put(outEdge.to, ktPathInfo);
                }
            }
        }
//        selectedPaths.remove(begin);

        return selectedPaths;
    }

//    @Override
//    public Map<T, K> shortestPath(T begin) {
//        Vertex<K, T> beginVertex = vertices.get(begin);
//        if (beginVertex == null){
//            return null;
//        }
//
//        //初始化最短路径map
//        Map<T, K> selectedPaths = new HashMap<>();
//        // <要计算的其他节点， begin到其他节点的最短路径>
//        Map<Vertex<K, T>, K> paths = new HashMap<>();
//        for (Edge<K, T> outEdge : beginVertex.outEdges) {
//            paths.put(outEdge.to, outEdge.weight);
//        }
//
//        while (!paths.isEmpty()){
//            Map.Entry<Vertex<K, T>, K> minVertex = getMinPath(paths);
//            //minVertex离开桌面
//            selectedPaths.put(minVertex.getKey().value, minVertex.getValue());
//            paths.remove(minVertex.getKey());
//            //对minVertex的outEdges进行松弛操作
//            for (Edge<K, T> outEdge : minVertex.getKey().outEdges) {
//                //如果outEdge.to已经离开桌面了，就没必要更新路径了
//                if (selectedPaths.containsKey(outEdge.to.value)) continue; // || outEdge.to.value.equals(beginVertex.value)
//                //旧的最短路径
//                K oldWeight = paths.get(outEdge.to);
//                //新的可选路径，minVertex.getValue() + 边的路径
//                K newWeight = weightManager.add(minVertex.getValue(), outEdge.weight);
//                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0){
//                    paths.put(outEdge.to, newWeight);
//                }
//            }
//        }
//        selectedPaths.remove(begin);
//
//        return selectedPaths;
//    }

    /**
     * 从paths中挑选一个最短路径出来
     */
    private Map.Entry<Vertex<K, T>, PathInfo<K, T>> getMinPath(Map<Vertex<K, T>, PathInfo<K, T>> paths) {
        Iterator<Map.Entry<Vertex<K, T>, PathInfo<K, T>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<K, T>, PathInfo<K, T>> min = iterator.next();

        while (iterator.hasNext()){
            Map.Entry<Vertex<K, T>, PathInfo<K, T>> next = iterator.next();
            if (weightManager.compare(next.getValue().weight, min.getValue().weight) < 0){
                min = next;
            }
        }
        return min;
    }

    private Comparator<Edge<K, T>> edgeComparator = (Edge<K, T> e1, Edge<K, T> e2) -> {

        return weightManager.compare(e1.weight, e2.weight);
    };

    private Set<EdgeInfo<K, T>> prim() {
        Iterator<Vertex<K, T>> vertexIterator = vertices.values().iterator();
        if (!vertexIterator.hasNext()) return null;

        Set<EdgeInfo<K, T>> edgeInfos = new HashSet<>();
        Set<Vertex<K, T>> visitedVertex = new HashSet<>();

        //初始化数据
        Vertex<K, T> vertex = vertexIterator.next();
        visitedVertex.add(vertex);
        MinHeap<Edge<K, T>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);

        //取出堆顶元素，即是权重最小的边
        int verticesSize = vertices.size() - 1;
        while (!heap.isEmpty() && edgeInfos.size() < verticesSize){
            Edge<K, T> edge = heap.remove();
            if (visitedVertex.contains(edge.to)) continue;
            edgeInfos.add(edge.info());
            visitedVertex.add(edge.to);
            heap.addAll(edge.to.outEdges);
        }

        return edgeInfos;
    }

    private Set<EdgeInfo<K, T>> kruskal() {
        int edgesSize = vertices.size() - 1;
        if (edgesSize < 0){
            return null;
        }

        //初始化并查集，将每一个节点看成不同的集合
        UnionFind<Vertex<K, T>> vertexUnionFind = new UnionFind<>();
        vertices.forEach((T t, Vertex<K, T> vertex) -> {
            vertexUnionFind.makeSet(vertex);
        });

        //将所有的边放入小顶堆中，不断的取出权值最小的边，判断是否会构成环，若构成则放弃这条边，否则就加入结果集中
        Set<EdgeInfo<K, T>> edgeInfos = new HashSet<>();
        MinHeap<Edge<K, T>> heap = new MinHeap<>(edges, edgeComparator);
        while (!heap.isEmpty() && edgeInfos.size() < edgesSize){
            Edge<K, T> edge = heap.remove();
            if (vertexUnionFind.isSame(edge.from, edge.to)) continue;
            edgeInfos.add(edge.info());
            vertexUnionFind.union(edge.from, edge.to);
        }

        return edgeInfos;
    }


    @Override
    public void dfs2(T t, Visitor<T> visitor) {
        if (visitor == null) return;
        Vertex<K, T> vertex = vertices.get(t);
        if (vertex == null) return;

        dfs2(vertex, new HashSet<Vertex<K, T>>(), visitor);
    }

    private void dfs2(Vertex<K, T> vertex, Set<Vertex<K, T>> visitorVertex, Visitor<T> visitor) {
//        System.out.println(vertex.value);
        if (visitor.visit(vertex.value)) return;
        visitorVertex.add(vertex);

        for (Edge<K, T> outEdge : vertex.outEdges) {
            if (visitorVertex.contains(outEdge.to)) continue;
            dfs2(outEdge.to, visitorVertex, visitor);
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

        public EdgeInfo<K, T> info() {
            return new EdgeInfo<K, T>(from.value, to.value, weight);
        }
    }

}
