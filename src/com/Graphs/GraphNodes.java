package com.Graphs;

import java.util.ArrayList;
import java.util.List;


public class GraphNodes {
    private List<Node> nodes;

    public GraphNodes() {
        nodes = new ArrayList<>();
    }

    public void addEdge(Node source, Node destination) {
        source.adjList.add(destination);
        destination.adjList.add(source);
    }

    public void addDirectedEdges(Node source, Node... destinations) {
        for(Node destination : destinations)
            source.adjList.add(destination);
//        destination.adjEdgeList.add(source);
    }

    public List<Node> getNeighbors(Node vertex) {
        return vertex.adjList;
    }

    public Node addVertex(String value) {
        Node n = new Node(value);
        nodes.add(n);
        return n;
    }

    public List<Node> getNodesList() {
        return nodes;
    }

    public void printGraph() {
        for (Node node : nodes) {
            System.out.print(node.value + ": ");
            for (Node neighbor : node.adjList) {
                System.out.print(neighbor.value + ", ");
            }
            System.out.println();
        }
    }

    static class Node {
        String value;
        List<Node> adjList;

        Node(String value) {
            this.value = value;
            adjList = new ArrayList<>();
        }
    }
}

