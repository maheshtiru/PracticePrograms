package com.AdvancedDataStructures.SegmentTree;

public class SegTreeNode {
    int start;
    int end;
    int sum;
    SegTreeNode left;
    SegTreeNode right;
    SegTreeNode(int s, int e) {
        start = s;
        end = e;
    }
}
