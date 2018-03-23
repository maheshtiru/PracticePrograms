package com.LinkedList;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        int[] intArr = {0,1,2,3,4,5};
        for(int i: intArr)
            list.addToBack(i);

        list.addAtIndex(3,3);
        System.out.println("added at index 3");

        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());

        list.removeAtIndex(6);
        System.out.println("removed at 6");

        Iterator it2 = list.iterator();
        while(it2.hasNext())
            System.out.println(it2.next());
    }
}
