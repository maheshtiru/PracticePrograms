package com.Graphs;

import java.util.ArrayList;
import java.util.List;

public class WeightedDirectedGraphAdjList {
    int numVertices;
    int numEdges;
    List<List<WeightedDirectedGraphAdjList.Edge>> adjEdgeList;
    WeightedDirectedGraphAdjList(int n) {
        numVertices = n;
        adjEdgeList = new ArrayList<>();
        for(int i = 0; i < numVertices; i++) {
            adjEdgeList.add(new ArrayList<WeightedDirectedGraphAdjList.Edge>());
        }
    }

    public void addEdge(int source, int dest, int weight) {
        adjEdgeList.get(source).add(new WeightedDirectedGraphAdjList.Edge(weight, source, dest));
        numEdges++;
    }

    // edge contains neighbors
    public List<WeightedDirectedGraphAdjList.Edge> getEdges(int vertex) {
        return adjEdgeList.get(vertex);
    }

    class Edge{
        int weight;
        int source;
        int destination;
        Edge(int w, int s, int d) {
            weight = w;
            source = s;
            destination = d;
        }
    }
}
