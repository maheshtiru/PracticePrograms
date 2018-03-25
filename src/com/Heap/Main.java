package com.Heap;

public class Main {
    public static void main(String[] args) {
        binaryHeapMaxPriorityQueue();
    }

    private static void binaryHeapMaxPriorityQueue(){
        System.out.println("****Ordered Priority Queue****");

        int capacity=10;
        BinaryHeapMaxPriorityQueue<Integer> maxPQ = new BinaryHeapMaxPriorityQueue(capacity);

        int[] arr = {1,2,0,4,5,15,6,7,8};      //max is 15
        for(int i:arr)
            maxPQ .insert(i);

        maxPQ.display();
        System.out.println("\n"+"removed from max priority queue: "+maxPQ.deleteMax());
        System.out.println("\n"+"removed from max priority queue: "+maxPQ.deleteMax());
        maxPQ.display();
    }
}
