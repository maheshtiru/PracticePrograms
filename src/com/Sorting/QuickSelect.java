package com.Sorting;

/*
 ******QUICK SELECT******
 * Goal: To find the kth smallest element and order the array around that element(all less elements on left and big elements on right)
 *       Here, we only return the kth smallest element, don't care about returning array!
 *       k-> ***Min is 0, max is N-1***
 * Working:
 *   -Similar to Quick sort, but instead of recurring on both sides of pivot, we recurse on the side with 'k'th index
 *   - When pivot = kth index, we return the element at k
 * Time Complexity:
 *   - Average case is LINEAR. Worst is quadratic, can be almost avoided by pivot picking and shuffling
 */
public class QuickSelect<T> {

    public Comparable<T> select(Comparable<T>[] array, int k) {
        int lo = 0, hi = array.length - 1;

        if(k>hi || k<0) throw new IndexOutOfBoundsException();

        KnuthShuffle<T> knuth = new KnuthShuffle<>();               //shuffle class instance
        //shuffle and quick select the array
        knuth.shuffle(array);
        return quickSelect(array, lo, hi, k-1);                 //pass k-1 for kth smallest because array is not 1 based
    }

    private boolean less(Comparable<T> x, Comparable<T> y) {
        return x.compareTo((T) y) < 0;
    }

    private void exchange(Comparable<T>[] array, int i, int j) {
        Comparable<T> swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

    //this is the divide part of the algorithm
    private Comparable<T> quickSelect(Comparable<T>[] array, int lo, int hi, int k) {
        if (lo <= hi) {                                      //base case to stop a recursion routine
            int pi = partition(array, lo, hi);             // get pivot element from partition routine
            if (k > pi)                                      //recurse on the right side of the array, if k is right to pivot index
                quickSelect(array, pi + 1, hi, k);
            else if (k < pi)                                 //recurse on the left side of the array, if k is left to pivot index
                quickSelect(array, lo, pi - 1, k);
            else if (k == pi)
                return array[k];
        }
        return array[k];
    }

    //same as quick sort partition, place pi in correct place
    private int partition(Comparable<T>[] array, int lo, int hi) {

        int pi = lo;
        int i = lo;                       // counter for partition
        for (int k = lo + 1; k <= hi; k++) {
            if (less(array[k], array[pi])) {
                exchange(array, k, ++i);  //increment partition counter
            }
        }

        exchange(array, lo, i);           //exchange pivot with border of smallest elements, so that pivot seperates small and big elements
        return i;
    }
}
