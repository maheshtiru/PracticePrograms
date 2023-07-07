package com.Graphs;

import java.util.*;

// In directed graph,while doing search, we trigger search algorithm until all nodes are processed
// idea is, while a node is not fully processed(i.e there are still neighbors left to process), if it is processed again..there is a cycle
public class DetectCycleDirectedGraph {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertices(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(5,6);
        graph.addEdge(6,7);
        graph.addEdge(6,4);


        DFSCycle dfsCycle = new DFSCycle(graph);
        System.out.println("DFS; cycle present: "+dfsCycle.dfsCycleHelper());

        BFScycle bfScycle = new BFScycle(graph);
        System.out.println("BFS; cycle present: "+bfScycle.hasCycleBfs());
    }

    // to keep track of whether node is fully processed, use a data structure...remove element from the DS only when all neighbors are processed
    static class DFSCycle {
        Set<Integer> nodeProcessTracker;
        Set<Integer> visited;
        Graph graph;

        DFSCycle(Graph graph) {
            this.graph = graph;
            nodeProcessTracker = new HashSet<>();
            visited = new HashSet<>();
        }

        public boolean dfsCycleHelper() {
            for(Integer n : graph.adjListMap.keySet()) {
                if(!visited.contains(n)) {
                    boolean res = dfs(n);
                    if(res)
                        return true;
                }
            }

            return false;
        }

        private boolean dfs(Integer n) {
            visited.add(n);
            nodeProcessTracker.add(n);

            for (Integer neighbor : graph.adjListMap.get(n)) {
                if (nodeProcessTracker.contains(neighbor))
                    return true; // Cycle detected
                if (!visited.contains(neighbor)) {
                    if (dfs(neighbor))
                        return true; // Cycle detected in recursive call (notice "return dfs(neighbour)" does not work coz it stops recursion after first neighbor returns false)
                }
            }

            nodeProcessTracker.remove(n);
            return false;
        }
    }

    // use kahn's algorithm for topological sort. After queue is empty, if there are nodes with indegree != 0, it indicates cycle
    // in other words, topological search result list will have less size than graph nodes - indicates cycle
    static class BFScycle {
        Graph graph;
        Map<Integer, Integer> indegreeMap;
        Set<Integer> processed;

        BFScycle(Graph graph) {
            this.graph = graph;
            indegreeMap = new HashMap<>();
            processed = new HashSet<>();
            getIndegrees();
        }

        public boolean hasCycleBfs() {
            Queue<Integer> q = new LinkedList<>();
            List<Integer> bfsAnswerList = new ArrayList<>();
            int zeroIndegreeVerticesCount = 0;

            // add all vertices with indegree == 0 to queue
            for(Map.Entry<Integer, Integer> entry : indegreeMap.entrySet()) {
                if(entry.getValue() == 0) {
                    q.add(entry.getKey());
                }
            }

            while(!q.isEmpty()) {
                Integer n = q.poll();

                if(processed.contains(n)) continue;

                processed.add(n);
                bfsAnswerList.add(n);
                zeroIndegreeVerticesCount++;

                List<Integer> neighbors = graph.adjListMap.get(n);
                for(Integer neighbor : neighbors) {
                    //update indegree
                    int indegree  = indegreeMap.get(neighbor);
                    indegreeMap.put(neighbor, --indegree);
                    if(indegree == 0)
                        q.add(neighbor);
                }
            }

            boolean cycle = zeroIndegreeVerticesCount != graph.size;
            if(!cycle){
                System.out.println("BFS, no cycle, topological order: "+bfsAnswerList.toString());
            }

            return cycle;
        }

        private void getIndegrees() {
            //put all vertices in map
            for(Integer n : graph.adjListMap.keySet()) {
                indegreeMap.put(n, 0);
            }

            //update indegrees
            for(Integer n : graph.adjListMap.keySet()) {
                List<Integer> neighbors = graph.adjListMap.get(n);
                for(Integer neighbor : neighbors) {
                    int indegree = indegreeMap.get(neighbor);
                    indegreeMap.put(neighbor, indegree+1);
                }
            }
        }
    }


    static class Graph{
        Map<Integer, List<Integer>> adjListMap;
        int size;

        Graph() {
            adjListMap = new HashMap<>();
            size = 0;
        }

        public void addVertices(List<Integer> val) {
            for(Integer v : val) {
                adjListMap.put(v, new ArrayList<>());
                size++;
            }
        }

        public void addEdge(int source, int dest) {
            adjListMap.get(source).add(dest);
        }

    }
}


