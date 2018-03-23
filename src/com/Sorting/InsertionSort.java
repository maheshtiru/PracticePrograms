package com.Sorting;
/*
********Insertion Sort************
*At any index i, while in traversal:
* -all the elements in indexes before i should be in ASCENDING order
* !-break from loop and continue with next if no exception to ascending order is found in any compare
* Complexity:
* point "!" suggests when the array is already sorted, there are only N-1 compares
*   Time Complexity:
*   -Best case: linear complexity
*   -Average case : quadratic
*   -Worst case(array is in descending order): 1,2,3...N-1 compares => N^2/2 compares and N^2/2 exchanges
 */
public class InsertionSort<T> {

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
            for(int j=i;j>0;j--){                       // ensure everything before 'i' is in ascending order
                if(less(array[j],array[j-1])<0)         //use less method to find out which is minimum(compare every element with prev element)
                    exchange(array,j,j-1);
                else
                    break;                              //break inner loop if even one compare is ascending(ensures remaining all are ascending before)
            }
        }
        return array;
    }
}
