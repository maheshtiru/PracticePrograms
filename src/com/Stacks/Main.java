package com.Stacks;

import java.util.Iterator;

// "-" means pop operation (0 for integers)
public class Main {
    public static void main(String[] args) {
        String[] str = {"a","-","b","c","d","e","-","-","z"};
        linkedListStack(str);                   // operations on linked list Stack
        arrayStack(str);                        // operations on array Stack
        genericStack();                         // implementation of stack independent of data type, also provides iterator
    }

    private static void  linkedListStack(String[] str){


        //linked list stack implementation
        LinkedListStack llStack = new LinkedListStack();
        System.out.println("---Linked list stack----");
        for(String s : str){
            if(s=="-")System.out.println("poped --: " +llStack.pop());        // call pop method
            else{
                llStack.push(s);
                System.out.println("pushed: "+s);
            }
        }
        System.out.println("Items in the stack: ");
        llStack.displayStack();
    }

    private static void arrayStack(String[] str){

        //resizing array stack implementation
        ArrayStack arrayStack = new ArrayStack();
        System.out.println("---Resizing array stack----");
        for(String s : str){
            if(s=="-") System.out.println("poped--: "+arrayStack.pop());
            else {
                arrayStack.push(s);
                System.out.println("pushed: "+s);
            }
        }
        System.out.println("Items in the stack: ");
        arrayStack.displayStack();
    }

    private static  void genericStack(){
        String[] str = {"a","-","b","c","d","e","-","-","z"};
        // let us assume 0 pops the number from stack
        int[] numbers = {1,0,2,34,5,6,0,0,7};

        //---------String stack-------------
        GenericStack<String> stringStack = new GenericStack<String>();
        System.out.println("----Generic Stack, using strings----");
        for(String s: str){
            if(s.equals("-"))
                System.out.println("poped:-- "+String.valueOf(stringStack.pop()));  // poping the element
            else{
                stringStack.push(s);                                                                  // pushing the element
                System.out.println("pushed: "+s);
            }
        }
        //Iterator
        Iterator<String> stringIterator = stringStack.iterator();
        System.out.println("Items in stack:");
        while(stringIterator.hasNext()){
            System.out.println(stringIterator.next());
        }

        //---------Integer stack---------------
        GenericStack<Integer> intStack = new GenericStack<>();
        System.out.println("----Generic Stack, using integers----");
        for(int i: numbers){
            if(i==0)
                System.out.println("poped:-- "+String.valueOf(intStack.pop()));  // poping the element
            else{
                intStack.push(i);                                                                   // pushing the element
                System.out.println("pushed: "+i);
            }
        }
        //Iterator
        Iterator<Integer> intIterator = intStack.iterator();
        System.out.println("Items in stack:");
        while(intIterator.hasNext()){
            System.out.println(intIterator.next());
        }
    }
}
