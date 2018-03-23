package com.Queue;



public class Main {
    public static void main(String[] args) {
        String[] str = {"a","-","b","c","d","e","-","-","z"};
        linkedListQueue(str);
    }

    private static void linkedListQueue(String[] str){
        LinkedListQueue llQueue = new LinkedListQueue();

        for(String s : str){
            if(s.equals("-"))
                System.out.println("Dequeued:--"+llQueue.dequeue());
            else{
                llQueue.enqueue(s);
                System.out.println("inserted in queue: " +s);
            }
        }
    }
}
