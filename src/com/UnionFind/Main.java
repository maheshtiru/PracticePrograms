package com.UnionFind;

import java.util.Scanner;

/*This package is for Union-Find data structure
->Union-find "is connected to" relation is equivalence relation(i.e reflexive, symmetric and transitive)
->connected() method indicates if input elements are connected or not i.e, "find" action
->Union() method connects two input elements
*/
public class Main {

    public static void main(String[] args) {

        QuickFindUF quickFindUF = new QuickFindUF(9);
        QuickUnion qu = new QuickUnion(9);
        WeightedQuickunion wqu = new WeightedQuickunion(9);
        WQUPathCompression wqupc = new WQUPathCompression(9);
    }
}
