package com.Graphs;

import java.util.*;

// Note: nodes starting with Indegree 0 have 0 incoming connections/edges i.e, no dependencies
// Works only on Directed, Acyclic graphs
// Time Complexity: O(V+E)
public class TopologicalSort {
    GraphNodes graph;

    private List<GraphNodes.Node> noDependencyNodesList;
    private Map<GraphNodes.Node, Integer> inDegreeMap;
    private Stack<GraphNodes.Node> dfsAnswerStack;
    private List<GraphNodes.Node> bfsAnswerList;
    private Set<GraphNodes.Node> visited;

    public TopologicalSort(GraphNodes graph) {
        this.graph = graph;

        visited = new HashSet<>();
        dfsAnswerStack = new Stack<>();
        inDegreeMap = new HashMap<>();
        this.noDependencyNodesList = new ArrayList<>();
        bfsAnswerList = new ArrayList<>();
        loadPreReqs();
    }

    public List<GraphNodes.Node> getTopologicalOrder() {
//      return performDfs();
        return bfsHelper();
    }

    private List<GraphNodes.Node> performDfs() {
        for(GraphNodes.Node node : noDependencyNodesList) {
            dfsHelper(node);
        }

        List<GraphNodes.Node> list = new ArrayList<>();
        while(!dfsAnswerStack.isEmpty()) {
            list.add(dfsAnswerStack.pop());
        }
        return list;
    }

    // loads inDegreeMap and noDependencyNodesList
    private void loadPreReqs() {
        List<GraphNodes.Node> nodes = graph.getNodesList();

        // load map with nodes
        for(GraphNodes.Node node : nodes)
            inDegreeMap.put(node, 0);

        // overwrite indegrees
        for(GraphNodes.Node node : nodes) {
            for(GraphNodes.Node neighbor : node.adjList) {
                int val = inDegreeMap.get(neighbor);
                inDegreeMap.put(neighbor, val+1);
            }
        }

        // load list with indegree 0 nodes
        for(Map.Entry<GraphNodes.Node, Integer> entry : inDegreeMap.entrySet()) {
            GraphNodes.Node key = entry.getKey();
            Integer val = entry.getValue();

            if(val == 0) {
                noDependencyNodesList.add(key);
            }
        }
    }

    // start trigger this routine on all indegree 0 nodes. Once a node's all neighbours are processed add node to topological sort "STACK"
    // pop stack one by one to answer list
    private void dfsHelper(GraphNodes.Node node) {
        if(node == null || visited.contains(node)) return;

        visited.add(node);

        int counter = 0;

        if(counter == node.adjList.size())  // no more neighbors to explore
            dfsAnswerStack.push(node);
        else {
            while(true) {
                GraphNodes.Node n = node.adjList.get(counter);
                dfsHelper(n);
                counter++;

                if(counter == node.adjList.size()) {
                    dfsAnswerStack.push(node);
                    return;
                }
            }
        }
    }

    // kahn's algorithm : put all indegree 0 nodes in topological bfsQueue. indegree = no. of incoming connections
    // process each node. update indegree of neighbors and add any new nodes with indegree == 0 to the queue and repeat.
    private List<GraphNodes.Node> bfsHelper() {
        Queue<GraphNodes.Node> q = new LinkedList<>();
        for(GraphNodes.Node gn :  noDependencyNodesList) //add all indegree 0 nodes
            q.add(gn);

        while(!q.isEmpty()) {
            GraphNodes.Node node = q.poll();

            if(visited.contains(node)) continue;

            visited.add(node);
            bfsAnswerList.add(node);

            for(GraphNodes.Node n : node.adjList) {
                int indegree = inDegreeMap.get(n);
                inDegreeMap.put(n, --indegree);
               if(indegree == 0) // add neighbors with indegree 0 to queue
                   q.add(n);
            }
        }

        return bfsAnswerList;
    }

}
