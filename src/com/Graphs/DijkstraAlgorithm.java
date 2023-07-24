package com.Graphs;

import java.util.*;
import com.Graphs.WeightedUndirectedGraphAdjList.Edge;

/* Finds the shortest distances from a source vertex to all other vertices
   Graph built:

    0 ----------1\
    |     6    /| \
    |        /  |  \
    |1     /    |   \
    |    /2     |2   \5
    |  /        |     \
    |/          |      \
    3-----------4-------2
          1         5
*/

// Dijkstra Algorithm calculates the shortest distance of all vertices from a SINGLE source vertex.
// Limitation: Does not work with negative edge weights because of its greedy approach(polling from min heap is greedy)
public class DijkstraAlgorithm {
    static int N = 5;
    public static void main(String[] args) {
        //build the graph
        WeightedUndirectedGraphAdjList graph = new WeightedUndirectedGraphAdjList(N);
        graph.addEdge(0,1,6);
        graph.addEdge(0,3,1);
        graph.addEdge(1,2,5);
        graph.addEdge(1,3,2);
        graph.addEdge(1,4,2);
        graph.addEdge(2,4,5);
        graph.addEdge(3,4,1);

        shotestPath(graph,0);
    }

    // 1. We need to track distances from source to every vertex. Create an array "dist" and store distances at each index
    // 2. dist[source] = 0. remaining all are infinity
    // 3. We take a min heap, add source and repeat below steps until heap is empty
    //  -   Update shortest distances of all neighbors. Add all neighbors to heap
    //  -   Pick neighbor with min dist from heap. Repeat. At the end we will have "dist" array filled with min distances from source.
    //  -   Keep track of shortest path by noting down all polled vertices.
    // TIME COMPLEXITY is O(E+V)*O(LogV) which is O((E+V)*LogV) = O(ELogV)
    public static void shotestPath(WeightedUndirectedGraphAdjList graph, int source) {
        int[] dist = new int[5];

        for(int i = 0; i < dist.length; i++) {
            if(i == source) dist[i] = 0;
            else dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(v -> dist[v])); // compare w.r.t distances
        Set<Integer> visited = new HashSet<>();
        StringBuilder path = new StringBuilder();
        heap.add(source);

        while(!heap.isEmpty()) {
            Integer v = heap.poll();
            if(visited.contains(v)) continue;

            visited.add(v);
            path.append(String.valueOf(v));
            path.append("-");

            List<Edge> adjEdgeList = graph.getEdges(v);
            for(Edge edge : adjEdgeList) {
                int neighbor = edge.destination;
                int distFromSource = dist[v] + edge.weight;

                // update dist
                dist[neighbor] = Math.min(dist[neighbor], distFromSource);
                heap.add(neighbor);
            }
        }

        System.out.println("shortest distances to each vertex from source, "+source+" : "+Arrays.toString(dist));
        System.out.println("shortest path: "+path.toString());
    }
}



