package com.CodingProblems;
import com.LinkedList.LinkedListNode;
import com.LinkedList.LinkedList;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
//google
//Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.
//For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return the node with value 8.

//Solution idea: if both lists are same, no problem. If one list is shorter?.. lets say both have difference of k nodes.
// say the largest list A has n nodes, the shorter one B has n-k nodes. Start both pointers, by the time B
// list is done, A would be left with k more nodes. The idea is to exchage pointers after 1st traversal.
// Now B pointer comes to start of A. by the time A(K remaining) is done, B pointer travels K in Alist and
// Apointer starts at start of B. Now both pointers are left with same no.(n-k) of nodes.
public class LinkedListIntersection {
    public static void main(String[] args){
        List<Integer> list1 = Arrays.asList(4,1);
        List<Integer> list2 = Arrays.asList(5,0,1,5,6,3,4,2,1,9,6,4);
        List<Integer> comList = Arrays.asList(8,4,5);

        LinkedList<Integer> linkedList1 = new LinkedList();
        LinkedList<Integer> linkedList2 = new LinkedList();
        LinkedList<Integer> common = new LinkedList();

        for(Integer i : list1) linkedList1.addToBack(i);
        for(Integer i : list2) linkedList2.addToBack(i);
        for(Integer i : comList) common.addToBack(i);

        linkedList1.getTail().next = common.getHead();
        linkedList2.getTail().next = common.getHead();

        for(Integer i : linkedList1) System.out.print(i+" ");
        System.out.println();
        for(Integer i : linkedList2) System.out.print(i+" ");

        LinkedListNode answer = solution(linkedList1.getHead(), linkedList2.getHead());

        if(answer != null) System.out.println("Intersection at: " +answer.value);
        else System.out.println("Intersection at: " +"null");
    }

    private static LinkedListNode solution(LinkedListNode headA, LinkedListNode headB){
        if(headA == null || headB == null) return null;
        if(headA == headB) return headA;

        LinkedListNode a = headA, b = headB;
        int loops = 0;

        while(loops < 3){  //max loops val is 2, coz 2 nulls will be encountered at max, before arriving at ans
            if(a == b) return a;
            a = a.next;
            b = b.next;

            if(a == null || b == null) loops++;
            if(a == null) a = headB;
            if(b == null) b = headA;
        }

        return null;
    }
}
