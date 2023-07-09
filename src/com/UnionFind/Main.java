package com.UnionFind;

/*This package is for Union-Find data structure
->Union-find "is connected to" relation is equivalence relation(i.e reflexive, symmetric and transitive)
->connected() method indicates if input elements are connected or not i.e, "find" action
->Union() method connects two input elements

IN SIMPLE TERMS:
This data structure is for DISJOINT SETS. Two main operations:
1. FIND(or connected): Says if 2 sets are merged/connected
2. UNION: Connects/merges 2 disjoint sets
We start the data structure at a state where every element is its own set(all disjoint). Later Union may merge multiple disjoint sets.
*/
public class Main {

    public static void main(String[] args) {

        QuickFindUF quickFindUF = new QuickFindUF(9);
        QuickUnionUF qu = new QuickUnionUF(9);
        WeightedQuickunion wqu = new WeightedQuickunion(9);
        WQUPathCompression wqupc = new WQUPathCompression(9);

        //example problem
        UnionFindExampleProblem exampleProblem = new UnionFindExampleProblem();
    }
}
