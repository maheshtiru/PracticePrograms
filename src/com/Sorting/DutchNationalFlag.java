package com.Sorting;

import java.util.Comparator;

/*
*****3-WAY partition sorting*****
* Goal: place same keys together as groups...
* Quick Sort takes quadratic time for more duplicate keys, coz almost all elements act as pivots
* Working:
*   -select any random 'element to compare'(C).
*   -Three counters-> "lt" which has all elements less than C, to its left..."gt" which has all elements greater than C, to its right...
*   .."i" which is the traverse counter.
*   -when arr[i]<C, exchange elements at lt,i and increment both counters
*   -when arr[i]==C, no exchanges, increment i
*   -when arr[i]>C, exchange elements at i and gt, decrement gt(do not increment i)
*   -terminate when "i" meets "gt"
* Explanation for increments:
*   At any instance in traversal:
*       -All elements left to "i" are either equal to (or) less than C. "lt" clearly acts as boundary between two groups. No discrepancies.
*       so, when i and lt exchange, we can increment i, coz we know that lt is a clear boundary
*       but, when i and gt exchange, we are not sure whether the new element at i is >C or <C, thus we need to check again. No "i" increment.
* Recursion:
*   -C is randomly chosen. So it may be least of the three or greatest of the three. So we need to recurse.
*   -It is clear that at end of one recursion, elements between lt and gt are grouped together, all equal to C
*   -So we just need to recurse only on elements before lt and after gt.
*   $$$--This way of recursion allows grouping even if there are MORE THAN 3 GROUPS---$$$
*/
public class  DutchNationalFlag<T> {

    public void sort(Comparable<T>[] array){
        int lo=0,hi=array.length-1;
        threeWaySort(array,lo,hi);
    }

    private int compareElements(Comparable<T> x, Comparable<T> y){
        return x.compareTo((T) y);
    }

    private void exchange(Comparable<T>[] array, int i, int j){
        Comparable<T> swap = array[i];
        array[i]=array[j];
        array[j]=swap;
    }

    private void threeWaySort(Comparable<T>[] array,int lo, int hi){
        if(hi<=lo)return;                         //base case

        int lt=lo, gt=hi, i=lo;

        Comparable<T> min, mid, max;
        min = mid = max = array[lo];
        for(int j = 0; j <= hi; j++) {
            if(compareElements(array[j], min) <= 0) min = array[j];
            else if(compareElements(array[j], max) >= 0) max = array[j];
            else mid = array[j];
        }

        Comparable<T> C = mid;               //If you choose random compare element, uncomment recursive calls at end of this method. It turns into sorting

        while(i<=gt) {
            if(compareElements(array[i],C)<0)
                exchange(array,i++,lt++);                   //increment both counters, i and lt
            else if(compareElements(array[i],C)>0)
                exchange(array,i,gt--);                     //decrement only the gt counter
            else
                i++;
        }

        // This(is Sorting) way of recursion allows grouping even if there are more than 3 colors and a random compare element
        // It works by putting compare element's group in correct place in every recursive call, similar to quick sort
//        threeWaySort(array,lo,lt-1);                    //recurse on left side of 'C(element chosen for comparision)' group
//        threeWaySort(array,gt+1,hi);                    //recurse on right side of 'C' group
    }
}
