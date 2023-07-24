package com.CodingProblems;


import java.util.*;

public class demo {
    static String elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {
        String[] strs = new String[]{"a","b","c","d"};


        Map<String, Node> map = new HashMap<>();
        map.putIfAbsent("st", new Node("st"));
        map.putIfAbsent("st", new Node("sa"));
        System.out.println(map.get("st").val);
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingDouble(n -> n.aDouble));
    }

    public static String base10ToBase62(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, elements.charAt(n % 62));
            n /= 62;
        }
        while (sb.length() != 7) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    static class Node {
        Node parent;
        String val;
        int size;
        double aDouble;
        Node(String str) {
            val = str;
            parent = this;
        }
    }
}
