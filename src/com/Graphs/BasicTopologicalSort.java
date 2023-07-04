package com.Graphs;

import java.util.*;

public class BasicTopologicalSort {
    private List<GraphNodes.Node> noDependencyNodes;
    private Stack<GraphNodes.Node> stack;
    private Set<GraphNodes.Node> visited;

    public BasicTopologicalSort(List<GraphNodes.Node> noDependencyNodes) {
        this.noDependencyNodes = noDependencyNodes;
        visited = new HashSet<>();
        stack = new Stack<>();
    }

    public List<GraphNodes.Node> getTopologicalOrder() {
        for(GraphNodes.Node node :  noDependencyNodes) {
            dfsHelper(node);
        }

        List<GraphNodes.Node> list = new ArrayList<>();
        while(!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }


    private void dfsHelper(GraphNodes.Node node) {
        if(node == null || visited.contains(node)) return;

        visited.add(node);

        int counter = 0;

        if(counter == node.adjList.size())  // no more neighbors to explore
            stack.push(node);
        else {
            while(true) {
                GraphNodes.Node n = node.adjList.get(counter);
                dfsHelper(n);
                counter++;

                if(counter == node.adjList.size()) {
                    stack.push(node);
                    return;
                }
            }
        }
    }

}
