package com.UnionFind;

/*This class represents a Quick-Find UF
    Complexity:
        Find = constant
        Union = Quadratic for N unions on N objects

-> Data Structure Understanding:
    1. Integer array of input length N
    2. p and q are connected "iff" id of pth index and qth index are equal i.e array[p]==array[q]
        Example:
        index  --> 0 1 2 3 4 5 6 7 8 9
        id(val)--> 0 1 1 8 8 0 0 1 8 8
        In the above, {0,5,6},{1,2,7},{3,4,8,9} are components. All elements in each component are connected.
*/
public class QuickFindUF {

    private int[] id;
    QuickFindUF(int N){
        id = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
        }
    }
    //"Find" sub routine
    private boolean connected(int p, int q){
        return id[p]==id[q];
    }
    //"Union" sub routine
    private void union(int p, int q){
        int pid=id[p];
        int qid=id[q];
        //change the value of all indexes connected to p, to id(value) of q
        for(int i=0;i<id.length;i++){
            if(id[i]==pid)                         // changing all parents of p, to point to q
                id[i]=qid;
        }
    }

}
