package com.Heap;

import java.util.NoSuchElementException;

/*
                ****This program USES Max Binary Heap and implements Ordered Max priority queue****
***BINARY HEAP is ARRAY REPRESENTATION of heap ordered complete binary tree
*   -complete Binary tree means each node has exactly 2 children and height of tree is lower bound of logN where N is array size
*   -In this program, we implement MAX HEAP i.e, every parent key is greater than or equal to children
*   -Max heap=> Maximum element is at 1st node
***Array Representation:
*   -We start keys from index 1(Not 0)
*   -Maximum key is always at index 1
*   -If parent is at index 'k', then its children are at 2k, 2k+1. Similarly, for a child at index 'k', parent is at k/2
****Main helper routines:
*   -swim()-> If any child is out of order(greater than parent), swim it up until order is restored
*   -sink()-> If any parent is out of order(less than one or both of the children), sink it down until order is restored
*/
public class BinaryHeapMaxPriorityQueue<T extends  Comparable<T>> {

    private int N=0;        // counter
    private T[] pq;

    public BinaryHeapMaxPriorityQueue(int capacity){
        pq = (T[]) new Comparable[capacity];
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exchange(int i, int j){
        T swap = pq[i];
        pq[i]=pq[j];
        pq[j]=swap;
    }
    //swim routine, to swim an out of order key up the tree, to put it back in order
    private void swim(int k){
        while(k>1) {
            int j = k / 2;                //index of parent
            if (less(j, k)) {               //parent key < input key
                exchange(j, k);
                k = k / 2;
            }else
                break;                    //base case
        }
    }
    //sink routine, to sink an out of order key down the tree, back into order
    private void sink(int k){
        while(2*k<=N) {
            int j = 2 * k;               //first child of input key, second will be j+1
            if (j<N && less(j, j + 1)) //choose only the bigger child for exchange(if smaller child chosen and made parent, it will be less than its child)
                j++;
            if(!less(k,j))break;        //base case
            exchange(k, j);
            k=j;
        }
    }

    public boolean isEmpty(){
        return N==0;
    }
    //insert routine starts filling from first index
    public void insert(T val){
        if(N+1>pq.length-1) throw new IndexOutOfBoundsException();
        pq[++N] = val;
        swim(N);            //place in order
    }
    //deleteMax routine pushes max key out of queue, i.e key at index 1
    public T deleteMax(){
        if(N==1){
            T del = pq[1];
            pq[1]=null;
            return del;
        }

        if(N<1){
            throw new NoSuchElementException();
        }

        T del=pq[1];
        exchange(1,N--);
        pq[N+1]=null;           // avoid loitering
        sink(1);
        return del;
    }

    public void display(){
        System.out.println("---display---");
        for(T t: pq)
            System.out.print(t+"\t");
    }
}
