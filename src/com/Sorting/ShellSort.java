package com.Sorting;
/*
**********Shell sort**************
*   This is insertion sort, but with more than 1 step decrement in internal loop(loop checking ascending order)
*   Best step decrement is multiples of 3x+1
*   First step is max in the series f(h)=3h+1, second step is g=f(h)/3....
*   IMP! -> when h-sorted array is g-sorted, h-sort order is preserved
* Complexity:
*   In-place and O(N^3/2) in worst case
 */
public class ShellSort<T> {

    //routine to compare two elements
    private int less(Comparable<T> x,Comparable<T> y){
        return (x).compareTo((T)y);
    }
    //routine to exchange places of two elements
    private void exchange(Comparable<T>[] array, int i, int j){
        Comparable<T> temp = array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    public Comparable<T>[] sort(Comparable<T>[] array){

        int N=array.length;
        int h=1;
        //get maximum step decrement in f(h)=3*h+1 series
        while(h<N/3)
            h=3*h+1;

        while (h >= 1){                                           //h-sort till h=1
            for (int i = h; i < N; i++) {                         // start from h index
                for (int j = i; j >= h ; j -= h) {                //ensure all h-step index elements are in ascending order
                    if (less(array[j], array[j - h]) < 0)
                        exchange(array, j, j - h);             //exchange elements in h step decrement indexes
                    else
                        break;                                   //break inner loop if even one compare is ascending(ensures remaining all are ascending before)
                }
            }

            h = h / 3;
        }
        return array;
    }
}
