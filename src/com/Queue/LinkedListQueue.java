package com.Queue;

/*
The queue data structure is first in first out type
This class:
-Implements Linked list queue
-data type used is String
-enqueue() is adding an element to queue, we add from last i.e, tail
-dequeue() is removing an element from the queue, we remove from first i.e, head
 */
public class LinkedListQueue {

    private Node head=null, tail=null;
    //Node element
    private class Node {
        String item;
        Node next;
    }
    //check if queue is empty
    public boolean isEmpty(){
        return head==null;
    }

    public void enqueue(String s){
        // create new node for new element
        Node node = new Node();

        node.item = s;
        node.next=null;       // since we add at last, next will be null

        if(isEmpty()){
            head=tail=node;
            return;
        }
        tail.next=node;       // attach new element to end of the list
        tail=node;            //point tail to the new node


    }

    public String dequeue(){
        if(isEmpty()) return null;

        String str= head.item;
        head = head.next;
        //now that head is updated, check if it is null i.e if queue is empty
        if(isEmpty()) tail=null;
        return str;
    }

    public void displayQueue(){
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        Node current=head;
        while(current!=null){
            System.out.println(current.item);
            current=current.next;
        }
    }

}


