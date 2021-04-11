package com.jms;

import com.jms.graph.Graph;
import com.jms.graph.ListGraph;

/**
 * @author  Jamison
 * @date  2021/4/10 21:15
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
//        test();
        testUnDfs();
    }

    private static void testUnDfs() {
        Graph<Double, Object> graph = undirectedGraph(Data.DFS_01);
        graph.dfs(1, new Visitor<Object>() {
            @Override
            public boolean visit(Object o) {
                System.out.println(o);
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
        Graph<Integer, String> graph = new ListGraph<>();

        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);

//        graph.removeEdge("V0", "V4");
//        graph.removeVertex("V0");
//        graph.bfs("V1");
        graph.dfs("V1", new Visitor<String>() {
            @Override
            public boolean visit(String s) {
                System.out.println(s);
                return false;
            }
        });

//        graph.print();
    }

    /**
     * 有向图
     */
    private static Graph<Double, Object> directedGraph(Object[][] data) {
        Graph<Double, Object> graph = new ListGraph<>();
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
        Graph<Double, Object> graph = new ListGraph<>();
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
