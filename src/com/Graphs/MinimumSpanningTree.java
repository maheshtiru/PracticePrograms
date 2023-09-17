package com.Graphs;

import java.util.*;

import com.Graphs.WeightedUndirectedGraphAdjList.Edge;
import com.UnionFind.WQUPathCompression;

/*
SPANNING TREE: These are especially for Undirected weighted graphs
- A spanning tree is a subset of minimum number of edges, required to connect all vertices. So in a graph with V vertices, spanning tree has V-1 edges
- Spanning tree does not contain cycles
- Spanning tree should not be disconnected

MINIMUM SPANNING TREE:
- A spanning tree with minimum edge cost sum.
- We have 2 GREEDY algos to find MST (prims and kruskals)

Note: A CUT is a group of edges that connected 2 sets of vertices.


BASIC IDEA:
    1. we need minimum of V-1 edges in MST for the graph to be connected.
    2. We prefer picking edges with least edge weights. If any edge is forming cycle, we omit it.
*/
public class MinimumSpanningTree {
    public static void main(String[] args) {
        // Graph used : com/Graphs/Pictures/MSTgraph.png
        WeightedUndirectedGraphAdjList graph = new WeightedUndirectedGraphAdjList(7);
        graph.addEdge(0,1,2);
        graph.addEdge(0,3,3);
        graph.addEdge(0,6,4);
        graph.addEdge(1,2,3);
        graph.addEdge(1,4,2);
        graph.addEdge(3,4,5);
        graph.addEdge(4,5,7);
        graph.addEdge(4,6,6);

        PrimsMST prims = new PrimsMST(graph);
        prims.primsMST();

        KruskalsMST kruskals = new KruskalsMST(graph);
        kruskals.kruskalsMST();
    }

    /*
    Tree vertices - edges connecting already selected edges/vertices in MST
    Fringe Vertices - vertices which are not part of MST yet
    1. Pick a random vertex. Because there is no MST yet, the edge cost is 0. Add the vertex and its edge cost(0, 0. can maintain an object, say node.) to heap
    2. Poll the node. Polled node has min edge cost and associated vertex. From this vertex, we pick all the edges to fringe vertices.
        Add them to heap (in "node" format of vertex, edgeWeight)
    3. Repeat the step 2 until we pick V-1 edges. This is the min number of edges required to connect this undirected graph
    TIME COMPLEXITY: O((VlogV) + (E logV)) = O(E logV)
    */
    static class PrimsMST {
        int V;
        WeightedUndirectedGraphAdjList graph;

        PrimsMST(WeightedUndirectedGraphAdjList g) {
            graph = g;
            V = graph.numVertices;
        }

        public void primsMST() {
            // Array to store constructed MST. For vertex i, parent[i] will store the index of its parent vertex in the MST. This is just for tracking
            int[] parent = new int[V];

            // By the end of algorithm, minCosts[i] will represent the weight of the minimum edge that connects vertex i to the MST. This is just for tracking
            int[] minCosts = new int[V];

            //To represent set of vertices included in MST
            boolean[] mstSet = new boolean[V];

            PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparing(n -> n.edgeCost));
            //select a vertex
            minCosts[0] = 0;
            minHeap.add(new Node(-1,0,0));
            int edgesAdded = 0;

            //we need only V-1 edges
            while(edgesAdded < V) {
                Node node = minHeap.poll();
                int vertex = node.vertex;
                if(mstSet[vertex])
                    continue;

                mstSet[vertex] = true;
                minCosts[vertex] = node.edgeCost;
                parent[vertex] = node.prev;
                edgesAdded++;

                List<Edge> connectingEdges = graph.getEdges(vertex);
                for(Edge edge : connectingEdges) {
                    int neighbor = edge.destination;
                    int edgeCost = edge.weight;

                    if(!mstSet[neighbor]) {                 // prevents cycles, because we only consider fringe vertices
                        minHeap.add(new Node(vertex, neighbor, edgeCost));
                    }
                }
            }

            printMST(parent, minCosts);
        }

        public void printMST(int[] parent, int[] minCosts) {
            System.out.println("----Prim's algorithm----");
            int cost = 0;
            System.out.println("Path\tWeight");
            for(int i = 1; i < parent.length; i++) {
                cost += minCosts[i];
                int src = parent[i];
                int dest = i;
                System.out.println(src + " - " + dest + "\t" + minCosts[i]);    // print out tracked parents and edge costs
            }
            System.out.println("total cost: "+cost);
        }

        class Node {
            int prev;
            int vertex;
            int edgeCost;
            Node(int p, int v, int e) {
                prev = p;
                vertex = v;
                edgeCost = e;
            }
        }
    }


    /*
    1. Sort all edges in the graph in ascending order of edge weights
    2. Pick each edge one by one. If any edge is forming cycle, skip it. Continue until we pick V-1 edges.
    3. To find if a cycle is formed, use union find. If source and destination of edge are connected, that edge forms cycle
    TIME COMPLEXITY: O(E * logE) or O(E * logV)
    */
    static class KruskalsMST {
        WeightedUndirectedGraphAdjList graph;
        int V;
        List<Edge> allEdges;
        KruskalsMST(WeightedUndirectedGraphAdjList graph) {
            this.graph = graph;
            V = graph.numVertices;
            allEdges = new ArrayList<>();

            //load all the edges
            for(List<Edge> edges : graph.adjEdgeList) {
                for(Edge edge : edges) {
                    allEdges.add(edge);
                }
            }

            //sort edges
            Collections.sort(allEdges, Comparator.comparing(e -> e.weight));
        }

        public void kruskalsMST() {
            int edgesAdded = 0;
            WQUPathCompression unionFind = new WQUPathCompression(V);
            List<Edge> path =  new ArrayList<>();
            List<Integer> minCosts = new ArrayList<>();

            for(int i = 0; edgesAdded < V && i < allEdges.size(); i++) {
                Edge edge = allEdges.get(i);
                int source = edge.source;
                int dest = edge.destination;

                if(unionFind.connected(source, dest))       //cycle, skip edge
                    continue;

                unionFind.union(source, dest);
                edgesAdded++;

                path.add(edge);
                minCosts.add(edge.weight);
            }

            printMST(path, minCosts);
        }

        public void printMST(List<Edge> path, List<Integer> minCosts) {
            System.out.println("\n----Kruskal's algorithm----");
            int cost = 0;
            System.out.println("Path\tWeight");
            for(int i = 0; i < path.size(); i++) {
                cost += minCosts.get(i);
                Edge edge = path.get(i);
                int src = edge.source;
                int dest = edge.destination;
                System.out.println(src + " - " + dest + "\t" + minCosts.get(i));    // print out tracked parents and edge costs
            }
            System.out.println("total cost: "+cost);
        }
    }
}
