package com.Search;
/*
->Input MUST be sorted array
->Divide and Conquer
*/

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.time.StopWatch;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr =  {2,2,4};
        int N=0;
        try{
            Scanner in = new Scanner(System.in);                  // scanner to read system.in
            N=in.nextInt();
        }catch(Exception e){
            System.out.println("broke down while reading input");
        }
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        System.out.println(N+" is found at index: "+bSearch(arr,N));       //binary search call
        stopwatch.stop();
        double time1 = stopwatch.getNanoTime();
        System.out.println("time bsearch: "+time1);

        stopwatch.reset();
        stopwatch.start();
        System.out.println(N+"linear/ is found at index: "+linearSearch(arr,N));       //linear search call
        stopwatch.stop();
        System.out.println("time linear: "+stopwatch.getNanoTime());

    }
    //Binary search starts
    private static int bSearch(int[] arr,int N){
        int lo=0, hi = arr.length-1;

        while(lo<=hi){
            int mid = lo + (hi-lo)/2;             // take middle index of array
            if(N>arr[mid]) lo=mid+1;              // to check in the left half
            else if(N<arr[mid]) hi=mid -1;        // to check in the right half
            else return mid;
        }
        return -1;
    }
    // normal linear search
    private static int linearSearch(int[] arr, int N){
        for(int i=0;i<arr.length-1;i++)
            if(arr[i]==N) return i;
        return -1;
    }
}
