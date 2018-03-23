package com.Stacks;
/*
This class implements re-sizing array stack.
-Add and remove items from the head/first
- The array doubles once the capacity limit is reached.
-The array is halved when the capacity is 25% of the length
-data type used is String
 */
public class ArrayStack {
    String[] stackArray=new String[1]; //initialize the stack array with length 1
    int N=0;                           //counter

    //resize array with capacity 2x
    private void resizeArray(int capacity){
        //take temporary array to copy elements
        String[] copy = new String[capacity];
        for(int i=0;i<N;i++){
            copy[i]=stackArray[i];
        }
        //point the temp array to main array
        stackArray=copy;
    }
    //push element at the end, resize array 2x if capacity is full
    public void push(String s){
        //resize array
        if(N==stackArray.length)resizeArray(2*N);
        stackArray[N++]=s;
    }
    //pop element from end, resize the array to x/2 at 25% capacity
    public String pop(){
        //base case for empty stack
        if(N==0)return null;

        String str = stackArray[--N];
        stackArray[N]=null;

        //resize the array
        if(N==stackArray.length/4 && N>0) resizeArray(stackArray.length/2);

        return str;
    }

    public void displayStack(){
        for(int i=--N;i>=0;i--){
            System.out.println(stackArray[i]);
        }
    }
}
