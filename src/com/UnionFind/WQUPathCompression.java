package com.UnionFind;
/*
*Weighted quick union with PATH COMPRESSION
-> This data structure is optimized weighted quich union
-> WE COMPRESS THE TREE HEIGHT BY POINTING EVERY EXAMINED NODE TO THE MAIN ROOT
-> onlu one line change from WQU

Complexity: Iterative logarithmic
*/

public class WQUPathCompression {
    private int[] id,sz;

    WQUPathCompression(int N){
        id = new int[N];
        sz= new int[N];                   // size array
        for(int i=0; i<N; i++){
            id[i]=i;
            sz[i]=1;                      // initial size of all roots is 1
        }
    }

    //chase parents until you reach root i.e, until parent and object are equal
    private int root(int i){
        //half the path lenght(which is already logarithmic length) by pointing each examined node to its grand parent
        while(i != id[i]){
            id[i]=id[id[i]];   //ONLY EXTRA LINE from Weighted Quick union
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
