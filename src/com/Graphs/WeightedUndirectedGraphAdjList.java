package com.Graphs;

import java.util.ArrayList;
import java.util.List;

public class WeightedUndirectedGraphAdjList {
    int numVertices;
    List<List<Edge>> adjEdgeList;
    WeightedUndirectedGraphAdjList(int n) {
        numVertices = n;
        adjEdgeList = new ArrayList<>();
        for(int i = 0; i < numVertices; i++) {
            adjEdgeList.add(new ArrayList<Edge>());
        }
    }

    public void addEdge(int source, int dest, int weight) {
        adjEdgeList.get(source).add(new Edge(weight, dest));
        adjEdgeList.get(dest).add(new Edge(weight, source));
    }

    // edge contains neighbors
    public List<Edge> getEdges(int vertex) {
        return adjEdgeList.get(vertex);
    }

    class Edge{
        int weight;
        int destination;
        Edge(int w, int d) {
            weight = w;
            destination = d;
        }
    }
}

