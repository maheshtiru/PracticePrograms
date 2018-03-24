package com.Sorting;

import java.util.ArrayList;

/*
Main class for providing inputs to sort algorithms
 */
public class Main {
    public static void main(String[] args) {
        selectionSort();
        insertionSort();
        shellSort();
        mergeSort();
        quickSort();
        quickSelect();
    }

    //selection sort
    private static void selectionSort(){
        System.out.println("*****Selection Sort*****");

        String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray={65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};

        SelectionSort<Integer> selectionSortInt = new SelectionSort<>();
        SelectionSort<String> selectionSortString = new SelectionSort<>();

        selectionSortString.sort(strArray);
        displayData(strArray);

        selectionSortInt.sort(intArray);
        displayData(intArray);
    }
    //insertion sort
    private static void insertionSort(){
        System.out.println("*****Insertion Sort*****");

        String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray={65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};

        InsertionSort<Integer> insertionSortInt = new InsertionSort<>();
        InsertionSort<String> insertionSortStr = new InsertionSort<>();

        insertionSortStr.sort(strArray);
        displayData(strArray);

        insertionSortInt.sort(intArray);
        displayData(intArray);
    }
    //Shell sort
    private static void shellSort(){
        System.out.println("*****Shell Sort*****");

        //String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray={65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};

        ShellSort<Integer> shellSortInt = new ShellSort<>();

        shellSortInt.sort(intArray);
        displayData(intArray);
    }
    //merge sort
    private static void mergeSort(){
        System.out.println("*****Merge Sort*****");

        //String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray={65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};

        MergeSort<Integer> mergeSortInt = new MergeSort<>();

        mergeSortInt.sort(intArray);
        displayData(intArray);
    }
    //quick sort
    private static void quickSort(){
        System.out.println("*****Quick Sort*****");

        String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray= {9,10,4,1,3,7,6,8,3};//{65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};

        QuickSort<String> quickSortInt = new QuickSort<>();

        quickSortInt.sort(strArray);
        displayData(strArray);
    }

    //quick select..select kth smallest element in unordered list
    private static void quickSelect(){
        System.out.println("*****Quick Select*****");

        String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray= {3,5,1,4,2,8,7,9,6};//all numbers from 1 to 9

        int k=-1;
        QuickSelect<Integer> quickSelect = new QuickSelect<>();

        System.out.println(k+"th/rd smallest element(starting from 0th): "+quickSelect.select(intArray,k));
        //displayData(strArray);
    }
    //display method
    private static  void displayData(Comparable[] arr){
        System.out.println("----display----");
        for(Comparable c: arr)
            System.out.println(c);
    }
}
