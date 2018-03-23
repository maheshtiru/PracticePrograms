package com.Sorting;
/*
**************SELECTION SORT**************
* all the description is based on an arbitrary index i, where, all the elements in indexes less than i are sorted and all the elements in indexes more
* than i are not sorted yet
Working:
-traverse through all the indexes more that i, find the minimum(min index)
-exchange elements at indexes i and min
Complexity:
-space is in place
Time:
-Quadratic worst and best cases, coz the algorithm compares all elements more than i, for every i
    i.e,
    when i=1, it does N-1 compares, when i=2, does N-2, then N-3 so on...
    (N-1)+(N-2)+....+1
*/
public class SelectionSort<T> {

    //routine to compare 2 elements
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
        for(int i=0;i<N;i++){
            int min=i;                                              //min stores the minimum element's index
            for(int j=i+1;j<N;j++){
                if(less(array[j],array[min])<0)                     //use less method to find out which is minimum(compare every element with prev min)
                    min=j;                                          //update new minimum element's index
            }
            exchange(array,i,min);                                  //exchange element's at i and min indexes
        }
        return array;
    }


}
