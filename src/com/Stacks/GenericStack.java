package com.Stacks;

import java.util.NoSuchElementException;


/*
This class serves as an API for a stack, independent of data type.
This class
-builds linked list implementation of stack
-Adds and removes items from the head/first
-uses generics
-provides Iterator methods for the stack
    - implements iterable interface
    -implements the iterator() method of iterable
    -created a iterator instance which has implementations to hasNext() and next() methods
    Usage:
        -invoke iterator() method on stack instace (Eg. Iterator<String> it = stack.iterator();)
        -Use hasNext and next methods of iterator instance (Eg.it.hasNext();)
 */
public class GenericStack<Item> implements Iterable<Item> {

    private Node head = null;
    //Node of the linked list
    private class Node{
        Item item;
        Node next;
    }

    //check if stack is empty (if head is null stack is empty)
    private boolean isEmpty(){
        return head==null;
    }

    //push operation - push from head
    public void push(Item s){

        //create a new node and insert incoming data
        Node node=new Node();
        node.item=s;
        node.next=head;              //make node the current head pointer
        //make head point the new node
        head=node;
    }

    //pop from head
    public Item pop(){
        Item item;
        if(isEmpty()) return null;   // list empty
        if(head.next==null){         // list has only one element
            item=head.item;
            head=null;
            return item;
        }
        item=head.item;
        head = head.next;
        return item;
    }

    //Iterator implementation (Iterable interface)
    public Iterator<Item> iterator(){
        return new Iterator<Item>();                              //Iterator interface implementation instance(has all Iterator methods)
    }
    //inner class for iterator
    private class Iterator<Item> implements java.util.Iterator<Item>{

        private Node current=head;

        public boolean hasNext(){                               // returns false if there are no more elements
            return current!=null;
        }
        //return the next item(current), if called without checking hasNext() and no item exists, throw an exception
        public Item next(){
            if(current==null) {
                throw new NoSuchElementException();
            }

            Item item = (Item) current.item;
            current=current.next;
            return item;
        }

        public void remove() {   // not needed, empty implementation

        }
    }

}
