package com.UnionFind;

/*
In the previous data structure quick union, the tree height may get very large and finding root may get complex.

-> In weighted Quick Union, we put check on tree height by ATTACHING A SHORT TREE TO A TALL TREE
-> This data structure is same as quiuck union except that an EXTRA ARRAY IS MAINTAINED TO STORE HEIGHT OF THE EACH ROOT
    i.e, size of root i is stored in SZ[i]
-> Complexity:
    Find: Logarithmic (since heigth of the tree it atmost logN)
    Union: Logarithmic
*/

public class WeightedQuickunion {
    private int[] id,sz;

    WeightedQuickunion(int N){
        id = new int[N];
        sz= new int[N];                   // size array
        for(int i=0; i<N; i++){
            id[i]=i;
            sz[i]=1;                      // initial size of all roots is 1
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

        //attach shorter root to longer root
        if(sz[qRoot]>sz[pRoot]){
            //root of q is bigger => make root of q parent to root of p
            id[pRoot]=qRoot;
            sz[qRoot]+= sz[pRoot];            //size of qRoot gets bigger
        }else{
            id[qRoot]=pRoot;
            sz[pRoot]+=sz[qRoot];
        }

    }

}
