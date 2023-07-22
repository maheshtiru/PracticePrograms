package com.Graphs;

import java.util.ArrayList;
import java.util.List;

/*
BellmanFord Algorithm calculates the shortest distance of all vertices from a SINGLE source vertex. But it can also work with negative edge weights
Limitations:
      - Does not work  when graph has negative weight cycle(i.e.sum of all edge weights in a cycle is negative)
      - This is because, the algorithm  goes into infinite loop
      - FOR this SAME REASON, this cant be applied to undirected graph with negative edge weight. Coz in undirected graph, even 1 -ve weight causes -ve weight cycle
*/
public class BellmanFord {
    public static void main(String... args) {
        //build graph
        WeightedDirectedGraphAdjList graph = new WeightedDirectedGraphAdjList(5);
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3,2,5);
        graph.addEdge(3,1,1);
        graph.addEdge(4,3,-3);


        shortestPath(graph, 0);
    }

    /*
    Relaxation: This process means updating shortest distance of a vertex from source when we find a new distance which is less than current
    ALGORITHM:
        - Mark distance array. Source index distance is 0, remaining all are infinity.
        - Process ALL EDGES in a loop. Relax the edge when possible
        - Repeat the loop for maximum of V-1 times( shortest path from the source to any other vertex can have at most V-1 edges ).
          The result is shortest distances of all vertices from source
        - ** Note: We can end loop if we have 0 relaxations possible. And if there are relaxations even after V-1 loops, it indicates -ve weight cycle.
        - TIME COMPLEXITY: O(V.E)
    */
    public static void shortestPath(WeightedDirectedGraphAdjList graph, int source) {
        int V = graph.numVertices;
        List<WeightedDirectedGraphAdjList.Edge> edges = new ArrayList<>();

        // get all edges & initialize distances
        int[] dist = new int[V];
        for(int v = 0; v < V; v++) {
            if(v == source) dist[v] = 0;
            else dist[v] = Integer.MAX_VALUE;

            //get edges
            for(WeightedDirectedGraphAdjList.Edge edge : graph.getEdges(v)) {
                edges.add(edge);
            }
        }

        for(int i = 1; i < V; i++) {        // V-1 times
            for(int j = 0; j < edges.size(); j++) {
                WeightedDirectedGraphAdjList.Edge edge = edges.get(j);
                if(dist[edge.source] != Integer.MAX_VALUE) {
                    int currDist = dist[edge.source] + edge.weight;
                    if(currDist < dist[edge.destination]) {
                        dist[edge.destination] = currDist;      // relaxation (also DP equation)
                    }
                }
            }
        }

        printDistances(graph, source, dist);

        // detect -ve weight cycle. We completed V-1 iterations. Do one more iteration. If there is relaxation now, there is -ve weight cycle
        for(int j = 0; j < edges.size(); j++) {
            WeightedDirectedGraphAdjList.Edge edge = edges.get(j);
            if(dist[edge.source] != Integer.MAX_VALUE) {
                int currDist = dist[edge.source] + edge.weight;
                if(currDist < dist[edge.destination]) {
                    System.out.println("Graph contains -ve weight cycle");
                    break;
                }
            }
        }
    }

    public static void printDistances(WeightedDirectedGraphAdjList graph, int source, int[] dist) {
        for(int i = 0; i < graph.numVertices; i++) {
            System.out.println(String.format("Shortest distance from source %d to %d: %d", source, i, dist[i]));
        }
    }
}


/*
Vertex   Distance from Source
0          0
1          -1
2          2
3          -2
4          1
*/
