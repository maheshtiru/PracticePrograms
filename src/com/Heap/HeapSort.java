package com.Heap;
/*
*****Heap Sort is implemented by Max Heap Idea(array implementation). See BinaryHeapMaxPriorityQueue program.*****
****Differences from BinaryHeapMaxPriorityQueue:
*   - DO NOT need swim() helper function
*   - 0th index is also considered => for any key k...2k+1 is first child and 2k+2 is second
**** Working:
*   1)For unordered input array, we first Construct Max Order Heap
*       -implement bottom up approach i.e, we order heap from bottom, considering small sub heaps
*       -ignore all the leaves of the heap since they are always in order(no children), in the perspective of sink operation
*       -Parent keys start from parent of last leaf i.e, from N/2
*       -start from first parent from bottom and perform sink operation, to place in order
*   2)Sort down this Max order heap. The key idea is, Maximum key which has to be placed at last index is always at top of the heap
*       -Exchange top key(max) with last leaf(last index), get the now last index out of heap and keep it in array instead of nulling out
*       -Sink the new unordered top node key, to regain heap order, with new length 1 less than previous heap
* Complexity:
*   1)Time:
*       - O(NlogN) in all cases (NlogN in best, 2NlogN in average and worst)
*   2)Space:
*       -In place
*  Stable: Yes
*/

public class HeapSort<T extends Comparable<T>> {

    private int N=0;                //max index of array

    public void sort(T[] array){
        N=array.length-1;
        heapSort(array);
    }

    private boolean less(T[] array,int i, int j){
        return array[i].compareTo(array[j])<0;              //true if array[i]<array[j]
    }

    private void exchange(T[] array, int i, int j){
        T swap = array[i];
        array[i]=array[j];
        array[j]=swap;
    }

    // sink() helper function. Exchanges an out of order parent with its child down the heap, until heap order is retained
    private void sink(T[] array, int k)           {       //k is key index
        while (2*k+1 <= N) {
            int c= 2*k + 1;                               // first child is 2*k+1, since this is 0-based indexing
            if (c < N && less(array, c, c + 1)) c++;    // choose the greater child

            if(!less(array,k,c))return;                   //base case (if parent and key are in order)

            exchange(array, k, c);                        //exchange parent and child
            k=c;                                          //move down one level
        }
    }

    //constructHeap() constructs the heap structure in given array. Follows bottom-up approach. starts at the right-bottom parent of
    //the heap and places that sub-heap in order
    private void constructHeap(T[] array){
        for(int k = (N)/2 ; k>=0; k--) {              //start at first parent from bottom
            sink(array,k);
        }
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    //heapSort() sorts the heap ordered array. See "Working" section in header comments
    private void heapSort(T[] array) {
        constructHeap(array);                    //construct the heap
        //sort down the heap
        while (N >= 0) {
            exchange(array, 0, N--);          // exchange max element with last index and move last element out of heap(N--)
            sink(array, 0);                  //heapify the new key at top of the heap
        }
    }
    //end
}
