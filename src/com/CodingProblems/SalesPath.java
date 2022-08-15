package com.CodingProblems;
import java.util.*;
import java.lang.*;

//min path sum(cost) from leaf to node in a tree(not binary)
public class SalesPath {
    static class SalesPathNode {
        int cost;
        List<SalesPathNode> children;
        SalesPathNode parent;

        SalesPathNode(int cost) {
            this.cost = cost;
            children = new ArrayList<SalesPathNode>();
            parent = null;
        }
    }

    static int getCheapestCost(SalesPathNode root) {
        // your code goes here
        if(root == null) return 0;

        return helper(root, 0, 0);
    }

    static int helper(SalesPathNode root, int min, int sum){
        if(root == null) return min;

        sum += root.cost;

        for(SalesPathNode c : root.children){
            return helper(c, min, sum);
        }

        //completed one path
        if(root.children.size() == 0) min = min == 0 ? sum : Math.min(sum, min);
        sum -= root.cost;
        return min;
    }

    public static void main(String[] args) {
        //====level 0
        SalesPathNode rootNode = new SalesPathNode(0);

        //                     0
        //               /    \      \
        //             5      3       6
        //            /     /   \    /  \
        //           4     2     0  1    5
        //                /      /
        //               1     10
        //               \
        //                1

        ////====level 1
        SalesPathNode leve1_c1 = new SalesPathNode(5);
        rootNode.children.add(leve1_c1);

        SalesPathNode leve1_c2 = new SalesPathNode(3);
        rootNode.children.add(leve1_c1);

        SalesPathNode leve1_c3 = new SalesPathNode(6);
        rootNode.children.add(leve1_c1);

        //====level 2
        SalesPathNode leve2_c1_c1 = new SalesPathNode(4);
        leve1_c1.children.add(leve1_c1);

        SalesPathNode leve2_c2_c1 = new SalesPathNode(2);
        leve1_c2.children.add(leve1_c1);

        SalesPathNode leve2_c2_c2 = new SalesPathNode(0);
        leve1_c2.children.add(leve1_c1);


        SalesPathNode leve2_c3_c1 = new SalesPathNode(1);
        leve1_c3.children.add(leve1_c1);

        SalesPathNode leve2_c3_c2 = new SalesPathNode(5);
        leve1_c3.children.add(leve1_c1);

        //====level 3
        SalesPathNode leve3_c2_c1_c1  = new SalesPathNode(1);
        leve2_c2_c1.children.add(leve1_c1);

        SalesPathNode leve3_c2_c2_c1  = new SalesPathNode(10);
        leve2_c2_c2.children.add(leve1_c1);

        //====level 4
        SalesPathNode leve4_c2_c1_c1_c1 =  new SalesPathNode(1);
        leve3_c2_c1_c1.children.add(leve1_c1);

        int minCost = getCheapestCost(rootNode);
        System.out.println(minCost);
    }
}
