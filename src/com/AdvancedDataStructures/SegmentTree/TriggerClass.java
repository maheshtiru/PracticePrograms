package com.AdvancedDataStructures.SegmentTree;

import java.util.Arrays;

public class TriggerClass {
    public static void main(String... args) {
        int[] arr =  {1,2,3,4,5,6,7};
        int[] indices = new int[arr.length];
        for(int i = 0; i < arr.length; i++) indices[i] = i;
        System.out.println("Array-> "+ Arrays.toString(arr));
        System.out.println("Index-> "+ Arrays.toString(indices));

        SegmentTreeWithPointUpdates segmentTreeWithPointUpdates = new SegmentTreeWithPointUpdates(arr);
        int[][] queries = {{1,3}, {2,6},{3,7}};
        for(int[] q : queries) {
            System.out.println("Range sum for range "+q[0]+", "+q[1]+" -> "+segmentTreeWithPointUpdates.query(q[0],q[1]));
        }
        // point update
        int updateIndex = 3, updateVal = 10;
        System.out.println("\nPOINT UPDATE. Updating index-> "+updateIndex+"; value-> "+updateVal);
        segmentTreeWithPointUpdates.pointUpdate(updateIndex, updateVal);
        for(int[] q : queries) {
            System.out.println("Range sum for range "+q[0]+", "+q[1]+" -> "+segmentTreeWithPointUpdates.query(q[0],q[1]));
        }

    }
}
