package com.Sorting;
/*
****Merge Sort*****
* Time Complexity: divide: logarithmic, merge linear...total: linearithmic
* Space Complexity: linear
* Merge sort is stable
*/
public class MergeSort<T> {

    //compare method
    private int less(Comparable<T> x,Comparable<T> y){
        return (x).compareTo((T)y);
    }
    //routine to exchange places of two elements
    private void exchange(Comparable<T>[] array, int i, int j){
        Comparable<T> temp = array[i];
        array[i]=array[j];
        array[j]=temp;
    }
    //check if the array is sorted between given indexes
    private boolean isSorted(Comparable<T>[] arr,int lo, int hi){
        for(int i=1;i<(hi-lo);i++)
            if( less(arr[i],arr[i-1])<0 )
                return false;
        return true;
    }

    //public method accessible to outside classes
    public Comparable<T>[] sort(Comparable<T>[] a ){
        int lo=0,hi=a.length-1;
        Comparable<T>[] aux = new Comparable[a.length];             //auxiliary array
        mergeSort(a,aux,lo,hi);
        return a;
    }

    //merge method to merge two SORTED arrays
    private void merge(Comparable<T>[] array,Comparable<T>[] aux, int lo, int mid, int hi ){
        //assert key word throws exception if boolean is false
        assert isSorted(array,lo,mid);
        assert isSorted(array,mid+1,hi);
        // copy elements to auxiliary array

        for(int i=lo;i<=hi;i++){                                     //copy
            aux[i]=array[i];
        }

        int i=lo, j= mid+1;                                         // two sorted arrays (before mid and after mid)

        for(int k=lo;k<=hi; k++){                                     // traverse through entire array

            if(j>hi)                                                //base case if right part is done
                array[k]=aux[i++];
            else if(i>mid )                                          //base case if left part is done
                array[k]=aux[j++];
            else if(less(aux[i],aux[j])<0)                          //actual comparisions from left and right parts of the array
                array[k]=aux[i++];
            else
                array[k]=aux[j++];
        }
    }

    private void mergeSort(Comparable<T>[] array, Comparable<T>[] aux, int lo, int hi){

        if(lo>=hi)                                      //base case to stop mergeSort() recursion
            return;

        int mid = lo + (hi-lo)/2;

        mergeSort( array,aux,lo,mid );                      // recurse on left half of the array
        mergeSort( array,aux,mid+1,hi);                  // recurse on right half of the array

        merge(array,aux,lo,mid,hi);                         // merge when base case of above two recursions is done
    }
}
