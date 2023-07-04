package com.Graphs;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndirectedGraphAdjList {
    private Map<String, List<String>> adjacencyList;  // can also use list

    public UndirectedGraphAdjList() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(String source, String destination) {
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);   //comment out for directed graph
    }

    public List<String> getNeighbors(String vertex) {
        return adjacencyList.get(vertex);
    }

    public void printGraph() {
        for (String vertex : adjacencyList.keySet()) {
            System.out.print(vertex + " -> ");
            for (String neighbor : adjacencyList.get(vertex)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}

