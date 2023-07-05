package com.Graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetectCycleUndirectedGraph {
    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(5,6);
        graph.addEdge(6,7);
        graph.addEdge(7,6);

        DetectCycle detectCycle = new DetectCycle(graph);
        System.out.println("Cycle present: "+detectCycle.detectCycle());
    }

    // if there is back edge i.e if we encounter a neighbor node which is already visited - there is a cicle
    // Note: because edges are bidirectional, a node will always have a neighbor will always have a visited node in form of its immediate parent - exclude it while checking
    static class DetectCycle {
        Graph graph;
        Set<Integer> visited;
        DetectCycle(Graph graph) {
            this.graph = graph;
            visited = new HashSet<>();
        }

        public boolean detectCycle() {
            return hasCycle(1, null);
        }

        private boolean hasCycle(Integer vertex, Integer parent) {
            visited.add(vertex);

            List<Integer> neighbors = graph.getAdjList(vertex);
            for(Integer n : neighbors) {
                if(parent != null && n.compareTo(parent) == 0)   // skip parent
                    continue;

                if(visited.contains(n)) {
                    return true;
                } else if(hasCycle(n, vertex))
                    return true;
            }

            return false;
        }
    }


    static class Graph{
        List<List<Integer>> adjList;
        int numVertices;

        Graph(int numVertices) {
            this.numVertices = numVertices;
            adjList = new ArrayList<>();

            for(int i = 0; i <= numVertices; i++) { // Vertices range is [1, numVertices]
                adjList.add(new ArrayList<>());
            }
        }

        public List<Integer> getAdjList(Integer vertex) {
            return adjList.get(vertex);
        }

        public void addEdge(Integer source, Integer dest) {
            adjList.get(source).add(dest);
            adjList.get(dest).add(source);
        }
    }
}
