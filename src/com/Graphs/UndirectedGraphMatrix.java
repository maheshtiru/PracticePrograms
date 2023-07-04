package com.Graphs;

public class UndirectedGraphMatrix {
    private int[][] adjacencyMatrix;
    private String[] vertices;

    public UndirectedGraphMatrix(int numVertices) {
        adjacencyMatrix = new int[numVertices][numVertices];
        vertices = new String[numVertices];
    }

    public void addVertex(int index, String vertex) {
        vertices[index] = vertex;
    }

    public void addEdge(int sourceIndex, int destinationIndex) {
        adjacencyMatrix[sourceIndex][destinationIndex] = 1;
        adjacencyMatrix[destinationIndex][sourceIndex] = 1;   // comment this for directed graph
    }

    public void printGraph() {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.print(vertices[i] + " -> ");
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    System.out.print(vertices[j] + " ");
                }
            }
            System.out.println();
        }
    }
}
