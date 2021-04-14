package com.jms;

import com.jms.graph.Graph;
import com.jms.graph.ListGraph;

import java.util.Set;

/**
 * @author  Jamison
 * @date  2021/4/10 21:15
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
//        test();
//        testUnDfs();
//        testTopo();
        testMst();
    }

    public static Graph.WeightManager<Double> weightManager = new Graph.WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }
    };

    private static void testMst() {
        Graph<Double, Object> graph = undirectedGraph(Data.MST_01);
        Set<Graph.EdgeInfo<Double, Object>> mst =
                graph.mst();
        for (Graph.EdgeInfo<Double, Object> info : mst) {
            System.out.println(info);
        }
    }

    private static void testTopo() {
        Graph<Double, Object> graph = directedGraph(Data.TOPO);
        for (Object o : graph.topologicalSort()) {
            System.out.println(o);
        }
    }


    private static void testUnDfs() {
        Graph<Double, Object> graph = directedGraph(Data.DFS_02);
        graph.dfs2("a", new Visitor<Object>() {
            @Override
            public boolean visit(Object o) {
                System.out.print(o + "_");
                return false;
            }
        });
        System.out.println();
        graph.dfs("a", new Visitor<Object>() {
            @Override
            public boolean visit(Object o) {
                System.out.print(o + "_");
                return false;
            }
        });

        /*Graph<Double, Object> graph = directedGraph(Data.DFS_02);
        graph.dfs("a", new Visitor<Object>() {
            @Override
            public boolean visit(Object o) {
                System.out.println(o);
                return false;
            }
        });*/
    }

    private static void test() {
//        Graph<Integer, String> graph = new ListGraph<>();
//
//        graph.addEdge("V1", "V0", 9);
//        graph.addEdge("V1", "V2", 3);
//        graph.addEdge("V2", "V0", 2);
//        graph.addEdge("V2", "V3", 5);
//        graph.addEdge("V3", "V4", 1);
//        graph.addEdge("V0", "V4", 6);
//
////        graph.removeEdge("V0", "V4");
////        graph.removeVertex("V0");
////        graph.bfs("V1");
//        graph.dfs2("V1", new Visitor<String>() {
//            @Override
//            public boolean visit(String s) {
//                System.out.println(s);
//                return false;
//            }
//        });

//        graph.print();
    }

    /**
     * 有向图
     */
    private static Graph<Double, Object> directedGraph(Object[][] data) {
        Graph<Double, Object> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     * @param data
     * @return
     */
    private static Graph<Double, Object> undirectedGraph(Object[][] data) {
        Graph<Double, Object> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
