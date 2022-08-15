package com.CodingProblems;

import java.util.*;

//This problem was asked by Twitter.
//Implement an autocomplete system. That is, given a query string s and a set of all possible query strings,
// return all strings in the set that have s as a prefix.
//For example, given the query string "de" and the set of strings [dog, deer, deal], return [deer, deal].
public class TrieImplementation {
    public static void main(String[] args){
        List<String> list = Arrays.asList("dog", "deer", "deal","daces", "dacha", "daddy", "dabbed", "dabber", "dabble"
                ,"daredeviltry", "darmstadtium", "daughter", "demon", "lemon");
        String prefix = "dab";

        Trie autoComplete = new Trie();
        for(String s: list) autoComplete.insert(s);

        List<String> ans = autoComplete.wordsWithPrefix(prefix);

        System.out.println("words with prefix " +prefix +":");
        for(String s : ans) System.out.println(s);
        if(ans.isEmpty()) System.out.println("No words with given prefix");
    }
}

class Trie {
    private class Node{
        String prefix;                  //prefix till this node
        Map<Character, Node> children;
        boolean isWord;

        Node(String s){
            prefix = s;
            children = new HashMap<>();
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node("");        // start with empty node at root of the trie
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        //start from root
        Node n = root;
        for (int i = 0; i < word.length(); i++){
            Character c = word.charAt(i);

            //if char is new in the tree create new child and update node, else just follow the path
            if(!n.children.containsKey(c)){
                n.children.put( c, new Node(word.substring(0, i+1)) );
            }
            n = n.children.get(c);

            //if we approached last char of word, its a complete word
            if(i == word.length()-1) n.isWord = true;
        }
    }

    // return list of all the words with a given prefix
    public List<String> wordsWithPrefix(String prefix){
        List<String> result = new ArrayList<>();
        if(prefix.isEmpty()) return result;
        Node n = root;
        //first traverse till the prefix
        for(int i = 0; i<prefix.length(); i++){
            Character c = prefix.charAt(i);
            if(!n.children.containsKey(c)) return result;

            n = n.children.get(c);
        }

        //now recurse till the leaves and return all words
        findAllPossibleWords(result, n);
        return result;
    }

    private void findAllPossibleWords(List<String> result, Node n){
        if(n.isWord) result.add(n.prefix);

        for(Character c : n.children.keySet()){
            findAllPossibleWords(result, n.children.get(c));
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node n = root;

        //follow trie path till word ends and check for boolean isWord
        for(int i = 0; i < word.length(); i++){
            Character c = word.charAt(i);
            if(!n.children.containsKey(c)) return false;

            n = n.children.get(c);
            if(n.isWord && n.prefix.equals(word)) return true;
        }

        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node n = root;
        for(int i = 0; i<prefix.length(); i++){
            Character c = prefix.charAt(i);

            if(!n.children.containsKey(c)) return false;

            n = n.children.get(c);
            if(n.prefix.equals(prefix)) return true;
        }

        return false;
    }
}