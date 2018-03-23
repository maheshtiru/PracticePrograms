package com.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

    private Node<T> head=null;
    private Node<T> tail=null;

    //Linked list Node implementation
    private class Node<T>{
        Comparable<T> value;
        Node<T> next = null;
    }
    //check if two values are equal
    private boolean checkEquals(Comparable<T> x, Comparable<T> y){
        return x.compareTo((T) y) == 0;
    }
    //check if list is empty
    private boolean isEmpty(){
        return head==null;
    }
    //add element from front
    public void addToFront(Comparable<T> val){
        //create a node and link it in the front i.e, make it head
        Node<T> node = new Node<T>();
        node.value=val;

        if(isEmpty()){                          //list empty,head and tail points to the only element
            head=tail=node;
            node.next=null;
            return;
        }

        node.next=head;
        head=node;
    }
    //add element to back of list i.e, make it tail
    public void addToBack(Comparable<T> val){

        //create a new node and make it tail
        Node<T> node = new Node<T>();
        node.value=val;

        if(isEmpty()){                  //edge case for empty list
            head=tail=node;
            node.next=null;
            return;
        }

        node.next=null;
        tail.next=node;

        tail=node;
    }
    //adding element at given index
    public void addAtIndex(int index, Comparable<T> val){

        if(index<0)                     //if index is negative
            throw new IndexOutOfBoundsException();

        if( isEmpty() && index>0)       //list is empty and provided index is not 0
            throw new IndexOutOfBoundsException();

        if(index==0){
            addToFront(val);
            return;
        }

        Node<T> current = head;
        for(int i=0;i<index-1;i++) {    //traverse to the element before the index and attach the new node
            if(current.next!=null)      //using current.next because we are traversing only till index-1
                current = current.next;
            else
                throw new IndexOutOfBoundsException();
        }//end of for

        Node<T> node = new Node<T>();   //node for new element
        node.value=val;

        if(current.next==null) {        //implies element before index(current) is tail, so adding at last
            addToBack(val);
            return;
        }
        //place the node between current(element before index) and current.next
        node.next=current.next;
        current.next=node;
    }

    //contains
    public boolean contains(Comparable<T> val){
        if(isEmpty())return false;          // edge case

        Node current=head;
        while(current!=null){
            if(checkEquals(current.value,val))
                return true;
            current=current.next;
        }
        return false;
    }

    public Comparable<T> getByIndex(int index){

        if(isEmpty() || index<0) throw new IndexOutOfBoundsException();

        Comparable<T> val = null;
        Node current=head;

        for(int i=0; i<=index; i++){                                     //traverse till the index
            if(current==null) throw new IndexOutOfBoundsException();     //index too big

            val = current.value;
            current=current.next;
        }

        return val;
    }

    public void removeFromFront(){
        if(isEmpty()) return;
        if(head==tail){             // check "references" of head and tail
            head=tail=null;
            return;
        }
        head=head.next;
    }

    public void removeFromBack(){
        if(isEmpty()) return;
        if(head == tail ) {
            removeFromFront();
            return;
        }

        Node current=head;
        while(current.next.next!=null)          //traverse till last before element
            current=current.next;
        current.next=null;
        tail=current;
    }

    public void removeAtIndex(int index){
        if(isEmpty() || index<0) throw new IndexOutOfBoundsException();
        if(index==0){
            removeFromFront();
            return;
        }

        Node current=head;
        for(int i=0; i<index-1 ; i++ ){         // traverse till the element preceding target element
            current=current.next;
            if(current.next==null) throw new IndexOutOfBoundsException();   //index too big i.e, previous index element is tail
        }

        current.next=current.next.next;         //skip the target element from link
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>();
    }

    private class Iterator<T> implements java.util.Iterator<T>{
        Node current=head;
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public T next() {
            if (current == null) throw new NoSuchElementException();

            T value = (T) current.value;
            current=current.next;
            return value;
        }
    }

}
