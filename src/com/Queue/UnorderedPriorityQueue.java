package com.Queue;
/*
***Priority Queue
*   -Priority queue is used to delete either max key(max PQ) or min key(min PQ), as opposed to strict FIFO
***
* -In this program, we implement UNORDERED MAX PRIORITY QUEUE using array implementation
* -Capacity of the queue is to be provided
*               $$$$ For ORDERED PRIORITY QUEUE, see in heaps -> BINARY HEAP $$$
*/
public class UnorderedPriorityQueue<T extends Comparable<T>> {
    private T[] pq;
    private int N=0;         //counter
    public UnorderedPriorityQueue(int capacity){
        pq= (T[]) new Comparable[capacity];
    }

    private boolean less(T x, T y){
        return x.compareTo(y)<0;
    }

    private void exchange(int i, int j){
        T swap = pq[i];
        pq[i]=pq[j];
        pq[j]=swap;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void insert(T val){
        if(N>pq.length-1) throw new IndexOutOfBoundsException();
        pq[N++]= val;
    }

    public T deleteMax(){
         int max=0;
         for(int i=0;i<N;i++){
             if(less(pq[max],pq[i]))               //see if pq[i]>max
                max=i;
         }
        exchange(max,N-1);                        //place max element at last
         T del = pq[--N];
         pq[N+1]=null;                               //prevent loitering
         return del;
    }
}
