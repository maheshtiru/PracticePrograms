package com.UnionFind;
/*
-> Data Structure Understanding:
        1. Integer array of input length N
        2. Tree structure implementation
            a)id[i] is parent of ith index
            b) so root of i is id[id[....id[i]]]
        3. p and q are CONNECTED "iff" index p and index q have SAME ROOTS
        4. UNION method sets the id(val) of P's ROOT TO Q's ROOT i.e sets root of Q as parent of P (can be vice-versa..all we need is attaching 2 sets)

        Example:
        index  --> 0 1 2 3 4 5 6 7 8 9
        id(val)--> 0 1 9 4 9 6 6 7 8 9
        In the above, root of 3 is 9. Parent of 3 is id[3]==4.

-> Complexity:
    Initialization : Linear
    Find: Linear+ (find root) (Less efficient "find" than QuickFind, but overall better because union operation is reduced to linear)
    Union: Linear (not quadratic like QuickFind. Hence the name Quick Union)
*/
public class QuickUnionUF {

    private int[] id;

    QuickUnionUF(int N){
        id = new int[N];
        for(int i=0; i<N; i++){
            id[i]=i;
        }
    }

    //chase parents until you reach root i.e, until parent and object are equal
    private int root(int i){

        while(i != id[i]){
            i=id[i];
        }
        return i;
    }

    //see if roots are equal
    private boolean connected(int p, int q){
        return root(p)==root(q);
    }

    //assign root of q as parent to root of p
    private void union(int p, int q){
        int pRoot = root(p);
        int qRoot =  root(q);

        id[pRoot] = qRoot;
    }
}
