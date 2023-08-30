package com.Stacks;
/*
->Add and remove items from the head/first
-> Datatype used is String
*/


public class LinkedListStack {

    private Node head = null;
    //Node of the linked list
    private class Node{
        String item;
        Node next;
    }

    //check if stack is empty (if head is null stack is empty)
    private boolean isEmpty(){
        return head==null;
    }

    //push operation - push from head
    public void push(String s){

        //create a new node and insert incoming data
        Node node=new Node();
        node.item=s;
        node.next=head;              //make node the current head pointer
        //make head point the new node
        head=node;
    }

    //pop from head
    public String pop(){
        String str;
        if(isEmpty()) return null;   // list empty
        if(head.next==null){         // list has only one element
            str=head.item;
            head=null;
            return str;
        }
        str=head.item;
        head = head.next;
        return str;
    }

    public void displayStack(){
        if(isEmpty()) System.out.println("Stack Empty");
        Node current = head;

        while(current!=null){
            System.out.println(current.item);
            current=current.next;
        }
    }
}
