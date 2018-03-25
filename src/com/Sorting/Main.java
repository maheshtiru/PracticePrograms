package com.Sorting;

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
        threeWaySort();
        //see com.Heap package for heap sort
    }

    //selection sort
    private static void selectionSort(){
        System.out.println("\n"+"\n"+"*****Selection Sort*****");

        String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray={65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};

        SelectionSort<Integer> selectionSortInt = new SelectionSort<>();
        SelectionSort<String> selectionSortString = new SelectionSort<>();

        printDataBeforeOperation(strArray);
        selectionSortString.sort(strArray);
        displayData(strArray);

        System.out.print("\n");

        printDataBeforeOperation(intArray);
        selectionSortInt.sort(intArray);
        displayData(intArray);
    }
    //insertion sort
    private static void insertionSort(){
        System.out.println("\n"+"\n"+"*****Insertion Sort*****");

        String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray={65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};

        InsertionSort<Integer> insertionSortInt = new InsertionSort<>();
        InsertionSort<String> insertionSortStr = new InsertionSort<>();

        printDataBeforeOperation(strArray);
        insertionSortStr.sort(strArray);
        displayData(strArray);

        System.out.print("\n");

        printDataBeforeOperation(intArray);
        insertionSortInt.sort(intArray);
        displayData(intArray);
    }
    //Shell sort
    private static void shellSort(){
        System.out.println("\n"+"\n"+"*****Shell Sort*****");

        //String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray={65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};
        printDataBeforeOperation(intArray);

        ShellSort<Integer> shellSortInt = new ShellSort<>();
        shellSortInt.sort(intArray);
        displayData(intArray);
    }
    //merge sort
    private static void mergeSort(){
        System.out.println("\n"+"\n"+"*****Merge Sort*****");

        //String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray={65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};
        printDataBeforeOperation(intArray);

        MergeSort<Integer> mergeSortInt = new MergeSort<>();
        mergeSortInt.sort(intArray);
        displayData(intArray);
    }
    //quick sort
    private static void quickSort(){
        System.out.println("\n"+"\n"+"*****Quick Sort*****");

        String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray= {9,10,4,1,3,7,6,8,3};//{65,34,98,56,24,96,74,1,51,84,62,75,21,88,56,1,4,315,31,421,512,512,12,57,2,64};

        QuickSort<String> quickSortInt = new QuickSort<>();

        printDataBeforeOperation(strArray);
        quickSortInt.sort(strArray);
        displayData(strArray);
    }

    //quick select..select kth smallest element in unordered list
    private static void quickSelect(){
        System.out.println("\n"+"\n"+"*****Quick Select*****");

        String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray= {3,5,1,4,2,8,7,9,6};//all numbers from 1 to 9
        printDataBeforeOperation(intArray);

        int k=3;
        QuickSelect<Integer> quickSelect = new QuickSelect<>();

        System.out.println(k+"th/rd smallest element(starting from 0th): "+quickSelect.select(intArray,k));
        //displayData(strArray);
    }

    //three way sort
    private static void threeWaySort(){
        System.out.println("\n"+"*****3-way sort*****");

        //String[] strArray= {"bbc","zxy","dsd","fef","uyu","ewe","hek","zit","hwk"};
        Integer[] intArray= {9,4,5,6,9,4,4,9,9,4,7,4,6,5,5,5,6,6,7,7};
        printDataBeforeOperation(intArray);

        DutchNationalFlag<Integer> dutchNationalFlag = new DutchNationalFlag<>();
        dutchNationalFlag.sort(intArray);

        displayData(intArray);
    }

    private static void printDataBeforeOperation(Comparable[] arr){
        System.out.print("before: ");
        displayData(arr);
        System.out.print("\n");
    }

    //display method
    private static  void displayData(Comparable[] arr){
        //System.out.println("\n");
        for(Comparable c: arr)
            System.out.print(c+" ");
    }
}
