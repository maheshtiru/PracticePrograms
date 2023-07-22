package com.Graphs;
// Gives shortest paths between all pairs of vertices. Does not work in case of negative weight cycles.
/*
ALGORITHM:
- Initialize the solution matrix same as the input graph matrix as a first step.
- Then update the solution matrix by considering all vertices as an intermediate vertex.
- The idea is to one by one pick all vertices and updates all shortest paths which include the picked vertex as an intermediate vertex in the shortest path.
- When we pick vertex number k as an intermediate vertex, we already have considered vertices {0, 1, 2, .. k-1} as intermediate vertices.
- For every pair (i, j) of the source and destination vertices respectively, there are two possible cases. (Dynamic Programming)
    - k is not an intermediate vertex in shortest path from i to j. We keep the value of dist[i][j] as it is.
    - k is an intermediate vertex in shortest path from i to j. We update the value of dist[i][j] as dist[i][k] + dist[k][j] if dist[i][j] > dist[i][k] + dist[k][j]

TIME COMPLEXITY: O(V^3)
*/
public class FloydWarshall {
    public static void main(String... args) {
        int MAX = Integer.MAX_VALUE;
        int[][] graph = { { 0, 5, MAX, 10 },
                { MAX, 0, 3, MAX },
                { MAX, MAX, 0, 1 },
                { MAX, MAX, MAX, 0 } };

        int[][] dist = allPairShortestPaths(graph);

        //print shortest paths
        for(int i = 0; i < graph.length; i++) {
            System.out.println("\nSource: "+i);
            for(int j = 0; j < graph.length; j++) {
                String distance = dist[i][j] == MAX ? "INF" : String.valueOf(dist[i][j]);
                System.out.println("Shortest distance to destination "+j+": "+distance);
            }
        }
    }

    public static int[][] allPairShortestPaths(int[][] graph) {
        int V = graph.length;           //vertices
        int[][] sol = new int[V][V];
        int MAX = Integer.MAX_VALUE;
        //copy graph to sol
        for(int i = 0; i < V; i++) {
            for(int j = 0; j < V; j++) {
                sol[i][j] = graph[i][j];
            }
        }

        // pick each vertex as intermediate one by one
        for(int k = 0; k < V; k++) {
            //pick all vertices as source one by one
            for(int s = 0; s < V; s++) {
                //pick all vertices as destination for a source
                for(int d = 0; d < V; d++) {
                    if((sol[s][k] != MAX && sol[k][d] != MAX) && (sol[s][d] > sol[s][k] + sol[k][d])) {
                        sol[s][d] = sol[s][k] + sol[k][d];
                    }
                }
            }
        }

        return sol;
    }
}

/*
 weighted graph
          10
    (0)------->(3)
     |         /|\
   5 |          |
     |          | 1
    \|/         |
    (1)------->(2)
           3
*/
