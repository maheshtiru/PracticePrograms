package com.Sorting;

import java.util.Random;
/*
* This algorithm randomly shuffles the given array
 */
public class KnuthShuffle<T> {
    private Random random = new Random();
    public Comparable<T>[] shuffle(Comparable<T>[] array){
        int N=array.length, r=0;
        for(int i=0;i<N;i++){
            r= random.nextInt(i+1);                        // get a random number between 0(inclusive) and i(inclusive)
            exchange(array,i,r);                                 // exchange the current index element with random index element
        }
        return array;
    }

    private void exchange(Comparable<T>[] a, int i, int r){
        Comparable<T> swap = a[i];
        a[i]=a[r];
        a[r]=swap;
    }
}
