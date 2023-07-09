package com.UnionFind;
import java.util.*;

/*
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.


TestCase:
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

*/

class UnionFindExampleProblem {

    UnionFindExampleProblem() {
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("Mary","mary@mail.com"),
                Arrays.asList("John","johnnybravo@mail.com")
        );


        List<List<String>> ans = accountsMerge(accounts);
        System.out.println("\nANSWER - merged accounts:");
        for(List<String> list : ans)
            System.out.println(list.toString());
    }


    // add all emails to union-find/disjoint sets data structure
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ans = new ArrayList<>();
        Map<Integer, Node> disjointSetMap = new HashMap<>();  // index-Node pair to later identify DJS array indices with email Nodes
        int counter = -1;
        for(int i = 0; i < accounts.size(); i++) {
            List<String> list = accounts.get(i);
            String name = list.get(0);

            for(int j = 1; j < list.size(); j++) {
                String email = list.get(j);
                disjointSetMap.put(++counter, new Node(name, email, i));
            }
        }

        // 1. make sets according to index in orginal accounts list. i.e. All emails in any index i in "accounts" are one set
        // 2. Note down duplicate email's indices
        Map<String, List<Integer>> emailIndices = new HashMap<>(); // email<->List of indices in DJS
        DisjointSet djs = new DisjointSet(disjointSetMap.size());
        int prevIndexInOriginalList = 0;
        for(int i = 0; i < disjointSetMap.size(); i++) {
            Node node = disjointSetMap.get(i);
            int indexInOriginalList = node.indexInOriginalList;
            String email = node.email;

            if(!emailIndices.containsKey(email)){
                emailIndices.put(email, new ArrayList<>());
            }
            emailIndices.get(email).add(i);

            if(i > 0 && indexInOriginalList == prevIndexInOriginalList) { // same set
                djs.union(i-1, i);
            }

            prevIndexInOriginalList = indexInOriginalList;
        }

        //make union sets for duplicates emails. This is the constraint on problem statement
        for(List<Integer> indicesList : emailIndices.values()) {
            if(indicesList.size() > 1) { //duplicates
                for(int i = 1; i < indicesList.size(); i++) {
                    djs.union(indicesList.get(i), indicesList.get(i-1));
                }
            }
        }

        // now get merged sets from djs, add them to map(root-list of elements in that set)
        Map<Integer, TreeSet<Node>> map =  new HashMap<>();   // root-list (root indicates same set)
        for(int i = 0; i < djs.id.length; i++) {
            Node node = disjointSetMap.get(i);
            int root = djs.getRoot(i);

            if(!map.containsKey(root)) {
                TreeSet<Node> sortedSet = new TreeSet<>();
                map.put(root, sortedSet);
            }

            map.get(root).add(node);
        }


        //load above map to answer
        for(TreeSet<Node> sortedSet : map.values()) {
            List<String> list = new ArrayList<>();
            String name = sortedSet.first().name;
            list.add(name);

            Iterator it = sortedSet.iterator();
            while(it.hasNext()) {
                Node node = (Node) it.next();
                list.add(node.email);
            }
            ans.add(list);
        }

        return ans;
    }

    // union-find data structure with path compression
    class DisjointSet{
        int[] id;
        int[] rank;
        DisjointSet(int capacity){
            id = new int[capacity];
            for(int i = 0; i < capacity; i++)
                id[i] = i;

            rank = new int[capacity];
            for(int i = 0; i < capacity; i++)
                rank[i] = 1; // initially every element is root of itself, so size is 1
        }

        private int getRoot(int i) {
            while(i != id[i]){
                id[i] = id[id[i]]; //path compression
                i = id[i];
            }
            return i;
        }

        private void union(int i, int j){
            int iRootIndex = getRoot(i);
            int jRootIndex = getRoot(j);

            // make higher rank root parent of lesser rank root
            if(rank[iRootIndex] < rank[jRootIndex]) {
                id[iRootIndex] = jRootIndex;
                rank[jRootIndex] += rank[iRootIndex];
            } else {
                id[jRootIndex] = iRootIndex;
                rank[iRootIndex] += rank[jRootIndex];
            }
        }
    }

    class Node implements Comparable<Node> {
        String name;
        String email;
        int indexInOriginalList;
        Node(String n, String e, int i) {
            name = n;
            email = e;
            indexInOriginalList = i;
        }

        @Override
        public int compareTo(Node node) {
            return this.email.compareTo(node.email);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", indexInOriginalList=" + indexInOriginalList +
                    '}';
        }
    }
}