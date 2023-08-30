package com.Sorting;
/*
***********QUICK SORT************
* most widely used and quicker than merge sort
* Working:
*   Note: In this particular algorithm, first element of the array is used as pivot!
*   -shuffle the array for better performance before starting (knuth shuffle used here)
*   -Take a pivot element(random for better performance-Large sets - median of 3 random elements, very large sets- median of 3 medians)
*   -in each recursion make sure all the elements less than pivot are to its left and all elements greater are to right
*   Time Complexity:
*       -Average case NlogN, WORST CASE IS QUADRATIC (for sorted array - no exchanges => all elements serve as pivots, pivot is compared with all
*        other elements...N pivots total...). Worst case can always be eliminated by shuffle
*   Space complexity: In place
*   Quick sort is not stable
 */
public class QuickSort<T> {

    public Comparable<T>[] sort(Comparable<T>[] array){
        int lo=0, hi = array.length -1;
        KnuthShuffle<T> knuth = new KnuthShuffle<>();               //shuffle class instance
        //shuffle and quick sort the array
        knuth.shuffle(array);
        quickSort(array,lo,hi);
        return array;
    }

    private boolean less(Comparable<T> x, Comparable<T> y){
        return x.compareTo((T) y) < 0;
    }

    private void exchange(Comparable<T>[] array, int i, int j){
        Comparable<T> swap = array[i];
        array[i]=array[j];
        array[j]=swap;
    }

    //this is the divide part of the algorithmS
    private void quickSort(Comparable<T>[] array, int lo, int hi){

        if(lo>=hi)                                      //base case to stop a recursion routine
            return;

        int pi= partition(array, lo, hi);               // get pivot element from partition routine
        quickSort(array,lo,pi-1);                    //recurse on left part of the pivot(exclusive)
        quickSort(array,pi+1,hi);                    //recurse on right half of the pivot(exclusive)
    }

    //this is the conquer part of the algorithm

    //double counter
    private int partition(Comparable<T>[] array, int lo, int hi){
        //we use 'lo' i.e first element as the pivot
        // we use two counters, can also be done by single counter(just put less elements to left)..traverse entire array, put all less elements to left
        int i=lo, j=hi+1;                                        //using two pointers one from left and one from right
        while(true){                                               // stop when pointers meet(base case)
            while(less(array[++i], array[lo]) )                   //increment left counter until the wrong element is found(>pivot)
                if (i == hi)break;
            while(less(array[lo],array[--j]) )                    //decrement right counter until wrong element is found(<pivot)
                if(j==lo)break;

            if(i>=j)break;                                       //check for base case just before swap
            exchange(array,i,j);
        }
        exchange(array,lo,j);                                    //grab pivot from first index, put in place(where pointers met)
        return j;
    }

    //single counter
//    private int partition(Comparable<T>[] array, int lo, int hi){
//
//        int pi=lo;
//        int i=lo;                       // counter for partition
//        for(int k=lo+1;k<=hi;k++){
//            if(less(array[k],array[pi])){
//                exchange(array,k,++i);  //increment partition counter
//            }
//        }
//
//        exchange(array,lo,i);           //exchange pivot with border of smallest elements, so that pivot seperates small and big elements
//        return i;
//    }
}
