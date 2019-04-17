package com.CodingProblems;
import java.util.Arrays;
import java.util.List;

//A unival tree (which stands for "universal value") is a tree where all nodes have the same value.
//Given the root to a binary tree, count the number of unival subtrees.
//For example, the following tree has 7 unival subtrees(except 4 and 5 at root):
//
//              5
//            /   \
//           4     5
//         /  \     \
//        5   5      5
//       / \          \
//      5  5           5

//SOLUTION : for a tree to be unival, all the nodes should have same value
// idea: if left subtree and right sub tree are unival AND root, left child, right child have same value, Current tree is unival

//implementation of BinaryTreeNode:
//class BinaryTreeNode<T> {
//    T val;
//    BinaryTreeNode left;
//    BinaryTreeNode right;
//    BinaryTreeNode(T x) { val = x; }
//}
public class UnivalSubtrees {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5,4,5,5,5,null,5,5,5,null,null,5);

        AAbuildBinaryTree<Integer> buildTree = new AAbuildBinaryTree<>(list);
        BinaryTreeNode root = buildTree.buildTreeInLevelOrder();

        buildTree.levelOrderTraversal(root);
        System.out.println("No. of unival trees: " +UnivalSubtreesSolution.numUnivalSubTrees(root));
    }
}

class ReturnValue{
    int count;
    boolean isUnival;
    ReturnValue(int c, boolean b){
        count = c;
        isUnival = b;
    }
}

class UnivalSubtreesSolution{
    public static int numUnivalSubTrees(BinaryTreeNode root){
        ReturnValue ret = helper(root, new ReturnValue(0, false));
        return ret.count;
    }

    private static ReturnValue helper(BinaryTreeNode root, ReturnValue ret){
        if(root == null) {
            return new ReturnValue(ret.count, true);
        }

        ReturnValue leftReturn = helper(root.left, ret);
        ReturnValue rightReturn = helper(root.right, ret);

        //if left sub tree and right sub tree are unival AND root, leftchild, right child are equal - current tree is unival
        if(leftReturn.isUnival && rightReturn.isUnival && valuesEqual(root, root.left, root.right)){
            return new ReturnValue(++ret.count, true);   //increment unival count
        } else {
            return new ReturnValue(ret.count, false);
        }
    }

    private static boolean valuesEqual(BinaryTreeNode root, BinaryTreeNode left, BinaryTreeNode right){
        Integer ro = (Integer)root.val, le = null, ri = null;
        if(left != null) le = (Integer)left.val;
        if(right != null) ri = (Integer)right.val;

        if(left == null && right == null) return true;
        if(left == null) return ro.compareTo(ri) == 0;
        if(right == null) return ro.compareTo(le) == 0;

        return ro.compareTo(le) == 0 && ro.compareTo(ri) == 0;
    }
}
