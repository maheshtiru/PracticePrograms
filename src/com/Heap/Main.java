package com.Heap;

public class Main {
    public static void main(String[] args) {
        binaryHeapMaxPriorityQueue();
        heapSort();
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
    //heap sort
    private static void heapSort(){
        System.out.println("\n"+"\n"+"****Heap Sort****");

        //Integer[] arr = {1,2,0,4,5,15,8,7,6,3};
        String[] arr = {"z","a","x","c","v","b"};

        System.out.print("before: ");
        display(arr);

        HeapSort<String> heapSort = new HeapSort<>();
        heapSort.sort(arr);

        System.out.print("\n"+"after: ");
        display(arr);
    }

    private static void display(Comparable[] arr){
        System.out.print("\n");
        for(Comparable i: arr)
            System.out.print(i+"\t");
    }
}
