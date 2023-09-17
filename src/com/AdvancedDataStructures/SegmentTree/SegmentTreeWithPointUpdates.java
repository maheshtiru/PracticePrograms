package com.AdvancedDataStructures.SegmentTree;
/*
- A segment tree is perfect data structure to answer queries on an array/list in LOGARITHMIC time (brute force takes Linear time)
- The segment tree we are implementing is for answering queries for range sum
- In segment tree each node will hold the answer(sum here) for a range. Answer can be sum, min, max etc of a range. We can have 2 types of implementation:
    * Array style:  See the pictures for example and to know why we take tree with 4*n size when input array is of size n
    * Pointer Style: In this we have nodes, and node will have pointers to children...like a real binary tree. This can handle dynamic input sizes instead of fixed input size
        We will implement POINTER-STYLE.
- The below implementation can also do point updates. Meaning we can change one value of the array per update query and the sum queries would have the result with updated value.
- For given input range, we have 3 scenarios:
    * when the input range is out of node's range -> return 0 as sum
    * when node's range is subset of input range -> return node sum
    * else when input range is overlapping with node's range -> recurse on both children
- Note that actual values of the input are the leaves of the tree(see pictures folder). Hence, every 2 values in input have a parent.
    And hence this is complete binary tree, always guaranteed height = logn

Time complexities:
- O(n) for pre-processing; i.e. to construct the tree
- O(logn) for each sum query or point update query
 */
public class SegmentTreeWithPointUpdates {
    SegTreeNode root;
    int[] nums;
    int size;
    SegmentTreeWithPointUpdates(int[] num) {
        nums = num;
        size = nums.length;
        root = buildSegTree(0, size - 1);
//        System.out.println("\nTree built, nodes are:");
//        printTree(root);
    }

    public void pointUpdate(int index, int value) {
        updateTree(root, index, value);
    }

    // returns the sum of all values in nums from start to end indices range
    public int query(int start, int end) {
        return querySum(root, start, end);
    }

    private SegTreeNode buildSegTree(int start, int end) {
        if(start == end) { //left, so load from input array
            SegTreeNode node = new SegTreeNode(start, end);
            node.sum = nums[start];
            return node;
        }

        int mid = start + (end - start)/2;
        SegTreeNode left = buildSegTree(start, mid);
        SegTreeNode right = buildSegTree(mid+1, end);

        SegTreeNode node = new SegTreeNode(start, end);
        node.sum = left.sum + right.sum;
        node.left = left;
        node.right = right;

        return node;
    }

    // Updates the point update in logn time, just traverse height of tree
    private void updateTree(SegTreeNode node, int index, int val) {
        if(node.start == node.end) {    // reached the leaf i.e. node corresponding index in nums array
            node.sum = val;
            return;
        }

        int mid = node.start + (node.end - node.start)/2;
        if(index <= mid){
            updateTree(node.left, index, val);
        } else {
            updateTree(node.right, index, val);
        }

        node.sum = node.left.sum + node.right.sum;
    }

    private int querySum(SegTreeNode node, int start, int end) {
        if(start > node.end || end < node.start) {                      // out of node's range
            return 0;
        } else if(start <= node.start && end >= node.end) {             // node's range is subset of input range
            return node.sum;
        } else {                                                        // recurse both children
            int mid = node.start + (node.end - node.start)/2;
            int leftSum = querySum(node.left, start, Math.min(end, mid));       // notice corner case min(end, mid)..eg. range (3,3)
            int rightSum = querySum(node.right, Math.max(start, mid + 1), end); // notice corner case max(start, mid+1)
            return leftSum + rightSum;
        }
    }

    private void printTree(SegTreeNode node) {
        if(node.start == node.end) {
            System.out.println("range: "+node.start+","+node.end+" sum: "+node.sum);
            return;
        }

        System.out.println("range: "+node.start+","+node.end+" sum: "+node.sum);
        printTree(node.left);
        printTree(node.right);
    }
}
