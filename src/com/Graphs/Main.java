package com.Graphs;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UndirectedGraphMatrix undirectedGraphMatrix = buildUndirectedMatrixGraph();
        System.out.println("------- undirectedGraphMatrix --------");
        undirectedGraphMatrix.printGraph();

        UndirectedGraphAdjList undirectedGraphAdjList = buildUndirectedAdjListGraph();
        System.out.println("------- undirectedGraphAdjList --------");
        undirectedGraphAdjList.printGraph();

        GraphNodes graphNodes = buildUndirectedNodesGraph();
        System.out.println("------- graphNodes --------");
        graphNodes.printGraph();


        System.out.println("------- dfs topological sort --------");
        topologicalSort();
    }

    public static UndirectedGraphMatrix buildUndirectedMatrixGraph() {
        UndirectedGraphMatrix graph = new UndirectedGraphMatrix(3);

        // Add vertices
        graph.addVertex(0, "New York");
        graph.addVertex(1, "London");
        graph.addVertex(2, "Tokyo");

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        return graph;

//                    New York   London   Tokyo
//        New York        0         1        0
//        London          1         0        1
//        Tokyo           0         1        0

    }

    public static GraphNodes buildUndirectedNodesGraph() {
        GraphNodes graph = new GraphNodes();

        GraphNodes.Node nodeA = graph.addVertex("A");
        GraphNodes.Node nodeB = graph.addVertex("B");
        GraphNodes.Node nodeC = graph.addVertex("C");
        GraphNodes.Node nodeD = graph.addVertex("D");
        GraphNodes.Node nodeE = graph.addVertex("E");

        graph.addEdge(nodeA, nodeB);
        graph.addEdge(nodeA, nodeC);
        graph.addEdge(nodeB, nodeD);
        graph.addEdge(nodeC, nodeE);

        return graph;
    }

    public static UndirectedGraphAdjList buildUndirectedAdjListGraph() {
        UndirectedGraphAdjList graph = new UndirectedGraphAdjList();

        // Add vertices
        graph.addVertex("New York");
        graph.addVertex("London");
        graph.addVertex("Tokyo");

        // Add edges
        graph.addEdge("New York", "London");
        graph.addEdge("London", "Tokyo");

        return graph;
    }

    public static void topologicalSort() {
        GraphNodes graph =  new GraphNodes();
        buildDirectedGraph(graph);
        TopologicalSort bts = new TopologicalSort(graph);
        List<GraphNodes.Node> topologicalOrderList =  bts.getTopologicalOrder();

        System.out.println("\ntopological order: \n");
        for(GraphNodes.Node n : topologicalOrderList) {
            System.out.print(n.value+" -> ");
        }
    }

    //returns a node list which has no dependencies
    private static void buildDirectedGraph(GraphNodes graph) {
        GraphNodes.Node nodeCN = graph.addVertex("Computer Networks");
        GraphNodes.Node nodeDS = graph.addVertex("Data Structures");
        GraphNodes.Node nodeAlgo = graph.addVertex("Algorithms");
        GraphNodes.Node nodeDB = graph.addVertex("Database Systems");
        GraphNodes.Node nodeOS = graph.addVertex("Operating Systems");
        GraphNodes.Node nodeSE = graph.addVertex("Software Engineering");
        GraphNodes.Node nodeAI = graph.addVertex("Artificial Intelligence");

        // pre-req's for algo class
        graph.addDirectedEdges(nodeDS, nodeAlgo);  // DS(source) -> Algo(dest)

        // pre-req's for OS class
        graph.addDirectedEdges(nodeDS, nodeOS);
        graph.addDirectedEdges(nodeAlgo, nodeOS);

        // pre-req's for CN class
        graph.addDirectedEdges(nodeDS, nodeCN);

        // pre-req's for SE class
        graph.addDirectedEdges(nodeAlgo, nodeSE);
        graph.addDirectedEdges(nodeDB, nodeSE);

        // pre-req's for AI class
        graph.addDirectedEdges(nodeDB, nodeAI);
        graph.addDirectedEdges(nodeAlgo, nodeAI);

        System.out.println("-- Node: "+"comma seperated list for which this node is a dependency --");
        graph.printGraph();

        //    List of different classes available (classList):
        //        Data Structures
        //        Algorithms
        //        Database Systems
        //        Operating Systems
        //        Computer Networks
        //        Software Engineering
        //        Artificial Intelligence
        //
        //    Pre-requisites
        //        Data Structures: None
        //        Algorithms: Data Structures
        //        Database Systems: None
        //        Operating Systems: Data Structures, Algorithms
        //        Computer Networks: Data Structures
        //        Software Engineering: Algorithms, Database Systems
        //        Artificial Intelligence: Algorithms, Database Systems
    }
}
