package com.CodingProblems;

import java.util.*;


//Serialization is the process of converting a data structure or object into a sequence of bits so that
//it can be stored in a file or memory buffer, or transmitted across a network connection link to be
//reconstructed later in the same or another computer environment.
//
//QUESTION: Design an algorithm to serialize and deserialize a binary tree.
//There is no restriction on how your serialization/deserialization algorithm should work.
//You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
public class SerializeBinaryTree {
    public static void main(String[] args) {
        List<String> list  = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(null);
        list.add(null);
        list.add("4");
        list.add("5");
        list.add("6");

        SerializeTree serializeTree = new SerializeTree();
        //build tree from above list
        TreeNode root = serializeTree.buildTreeInLevelOrder(list);
        System.out.println("Input tree in level order: ");
        levelOrderTraversal(root);

        //serialize to sstring
        System.out.println("Serialized string: ");
        System.out.println(serializeTree.serialize(root));

        //deserialize
        System.out.println("deserialized tree in level order: ");
        levelOrderTraversal(serializeTree.deserialize(serializeTree.serialize(root)));
    }

    private static void levelOrderTraversal(TreeNode root) {
        if(root == null) System.out.println("tree empty");

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node==null){
                System.out.print("null,");
            } else {
                System.out.print(node.val+",");
            }
            if(node!=null) q.add(node.left);
            if(node!=null) q.add(node.right);
        }

        System.out.println("");
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class SerializeTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        String comma = ",";
        Queue<TreeNode> q = new LinkedList<>();
        boolean first = true;

        if (root == null) {
            return null;
        }

        q.add(root);

        //build a comma seperated string by traversing tree in level order
        while (q.size() != 0) {
            TreeNode node = q.poll();

            if (first) {
                sb.append(Integer.toString(node.val));
                first = false;
            } else {
                sb.append(comma);
                if (node != null) {
                    sb.append(Integer.toString(node.val));
                } else {
                    sb.append("N");    //N in this string means null value
                }
            }

            if (node != null) q.add(node.left);
            if (node != null) q.add(node.right);
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) return null;
        if(data.length() == 1) return new TreeNode(Integer.parseInt(data));

        List<String> list = Arrays.asList(data.split(","));
        return buildTreeInLevelOrder(list);
    }

    //given a list, build a binary tree by level order
    public TreeNode buildTreeInLevelOrder(List<String> list) {
        String[] arr = new String[list.size() + 1];   // copy list to this 1-index based array to easily calculate children for a node(2k,2k+1)
        Queue<TreeNode> q = new LinkedList<>();
        int k = 1;

        for(int i = 0; i < list.size(); i++) {
            arr[i+1] = list.get(i);   ///start copying from 1
        }

        TreeNode root = new TreeNode(Integer.parseInt(arr[1]));
        q.add(root);

        for(int i = 1; i < arr.length; i++) {
            TreeNode node = null;
            if(q.size() != 0) {
                node = q.poll();
            }

            if(node!=null){
                TreeNode left = null;
                TreeNode right = null;

                int a = 2*k;
                int b = 2*k + 1;

                if(a < arr.length && arr[a] != null && !arr[a].equals("N"))     //N in the string means null value
                    left = new TreeNode(Integer.parseInt(arr[a]));

                if(b < arr.length && arr[b] != null && !arr[b].equals("N"))
                    right = new TreeNode(Integer.parseInt(arr[b]));

                node.left = left;
                node.right = right;
                if(i<arr.length-1){
                    q.add(left);
                    q.add(right);
                }

                k++;
            }
        }
        return root;
    }
}