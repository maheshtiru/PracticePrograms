package com.Queue;



public class Main {
    public static void main(String[] args) {
        String[] str = {"a","-","b","c","d","e","-","-","z"};
        linkedListQueue(str);
        unorderedPriorityQueue();
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

        System.out.println("Items in the stack: ");
        llQueue.displayQueue();
    }

    private static void unorderedPriorityQueue(){
        System.out.println("****unordered priority queue****");
        int capacity=10;
        UnorderedPriorityQueue<Integer> pq = new UnorderedPriorityQueue(capacity);

        pq.insert(3);
        pq.insert(4);
        pq.insert(1);

        System.out.println("remove from U/O max priority queue: " +pq.deleteMax() );
    }
}
