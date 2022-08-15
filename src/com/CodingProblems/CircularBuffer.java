package com.CodingProblems;
//This problem was asked by Twitter.
//You run an e-commerce website and want to record the last N order ids in a log. Implement a data structure to accomplish this, with the following API:
//record(order_id): adds the order_id to the log
//get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N
//SOLUTION: Insertion and deletion can be done in CONSTANT TIME using circular buffer and takes only LINEAR SPACE

import java.util.Arrays;
import java.util.List;

public class CircularBuffer {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
        int N = 5, i = 3;            //i <= N
        CircularBufferSolution buffer = new CircularBufferSolution(N);

        for(int k : list) buffer.record(k);

        System.out.println("N = 5, last "+i+"th element: " +buffer.get_last(i));
    }
}

//visualize with size 5 i.e index 0 to 4
//At any point of time there are maximum of N elements in the data structure
class CircularBufferSolution {
    int N;
    int[] buffer;
    int counter = 0;

    CircularBufferSolution(int capacity){
        N = capacity;
        buffer = new int[N];
    }

    //to load array
    public void record(int order_id){
        buffer[counter] = order_id;
        counter = (counter + 1) % N;
    }

    public int get_last(int i){
        int last_ith = (counter - i + N) % N;
        return buffer[last_ith];
    }
}
