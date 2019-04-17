package com.CodingProblems;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BinaryTreeNode<T> {
    T val;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode(T x) { val = x; }
}

//build and traverse a binary tree in LEVEL ORDER
public class AAbuildBinaryTree<T> {

    private List<T> list;

    public AAbuildBinaryTree(List<T> list){
        this.list = list;
    }

    //given a list, build a binary tree by level order
    public BinaryTreeNode buildTreeInLevelOrder() {
        int n = list.size();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        int k = 0;                                       //counter to calculate parent-child

        BinaryTreeNode root = new BinaryTreeNode<T>(list.get(0));
        q.add(root);

        for(int i = 1; i < n; i++) {
            BinaryTreeNode node = null;
            if(q.size() != 0) {
                node = q.poll();
            }

            if(node!=null){
                BinaryTreeNode left = null;
                BinaryTreeNode right = null;

                int a = 2*k + 1;                    //0-based
                int b = 2*(k + 1);

                if(a < n && list.get(a) != null)
                    left = new BinaryTreeNode(list.get(a));

                if(b < n && list.get(b) != null)
                    right = new BinaryTreeNode(list.get(b));

                node.left = left;
                node.right = right;

                q.add(left);
                q.add(right);

                k++;
            }
        }
        return root;
    }

    //traverse in level order
    public void levelOrderTraversal(BinaryTreeNode root) {
        if(root == null) System.out.println("tree empty");

        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            BinaryTreeNode node = q.poll();
            if(node==null){
                System.out.print("null,");
            } else {
                System.out.print(node.val+",");
            }

            if(node!=null) {
                q.add(node.left);
                q.add(node.right);
            }
        }

        System.out.println("");
    }
}
