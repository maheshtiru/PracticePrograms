package com.Search;
/*
NOTES:
1. After all the iterations when low == high, if target is present in the array then it should be present at low == high == mid , if not then we can surely say that target is not present in the array.
2. Note this, for the very first time when low > high in that case ceil(target) = arr[low] and floor(target) = arr[high].
*/

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchVariations {

    public static void main(String[] args) {
        int[] arr =  {1,2,2,2,4,4,7,8,8,9,10,10,10,10,12,15,16,16};
        int[] indices = new int[arr.length];
        for(int i = 0; i < arr.length; i++) indices[i] = i;
        System.out.println("Array-> "+ Arrays.toString(arr));
        System.out.println("Index-> "+ Arrays.toString(indices));
        System.out.println("Enter a number in the range(or out of range) of above Array");

        int target=0;
        try{
            Scanner in = new Scanner(System.in);                  // scanner to read system.in
            target=in.nextInt();
        }catch(Exception e){
            System.out.println("broke down while reading input");
        }

        System.out.println("\nBasic binary search index: "+bSearch(arr,target));
        System.out.println("\nround down MID binary search index: "+roundDownMid(arr,target));
        System.out.println("\nround up MID binary search index: "+roundUpMid(arr,target));
        System.out.println("\nno comparision round down MID binary search index: "+noComparisionRoundDownMid(arr,target));
        System.out.println("\nno comparision round up MID binary search index: "+noComparisionRoundUpMid(arr,target));
        System.out.println("\ntarget's lower bound index: "+lowerBoundofTarget(arr,target));
        System.out.println("\ntarget's upper bound index: "+upperBoundofTarget(arr,target));
        System.out.println("\nIndex of Greatest element less than target: "+strictLowerBoundofTarget(arr,target));
        System.out.println("\nIndex of Least element greater than target: "+strictUpperBoundofTarget(arr,target));
        int[] range = rangeOfTarget(arr,target);
        System.out.println("\ntarget's range indices: "+range[0]+","+range[1]+" ;\tNote: same as [noComparisionRoundDownMid, noComparisionRoundUpMid]");
    }

    //Binary search
    private static int bSearch(int[] arr,int target){
        int lo=0, hi = arr.length-1;

        while(lo <= hi){
            int mid = (lo + hi)/2;  // overflows for big numbers

            if(target > arr[mid])
                lo = mid+1;
            else if(target < arr[mid])
                hi = mid - 1;
            else
                return mid;
        }
        return -1;                  //target not found
    }

    // in case of even sized search space, MID will be the LEFT INDEX of the middle 2
    private static int roundDownMid(int[] arr, int target) {
        int lo = 0, hi = arr.length-1;

        while(lo <= hi) {
            int mid = lo + (hi - lo)/2; // rounds DOWN mid to left

            if(target < arr[mid])
                hi = mid - 1;
            else if(target > arr[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;                     // target not found
    }

    // in case of even sized search space, MID will be the RIGHT INDEX of the middle 2
    private static int roundUpMid(int[] arr, int target) {
        int lo = 0, hi = arr.length-1;

        while(lo <= hi) {
            int mid = lo + (hi - lo + 1)/2; // rounds UP mid to right

            if(target < arr[mid])
                hi = mid - 1;
            else if(target > arr[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;                          // target not found
    }

    // round down MID and don't do == compare until end of search, instead just keep compressing search space around target
    private static int noComparisionRoundDownMid(int[] arr, int target) {
        int lo = 0, hi = arr.length-1;

        while(lo < hi) {                    // strictly less than because we are not comparing mid ==
            int mid = lo + (hi - lo)/2;     // rounds down mid to left

            if(target <= arr[mid])
                hi = mid;                   // not mid-1, because we are not comparing mid ==
            else
                lo = mid + 1;
        }
        return arr[lo] == target ? lo : -1;  // lo == hi
    }

    // round up MID and don't do == compare anything until end of search, instead just keep compressing search space around target
    private static int noComparisionRoundUpMid(int[] arr, int target) {
        int lo = 0, hi = arr.length-1;

        while(lo < hi) {                    // strictly less than because we are not comparing mid ==
            int mid = lo + (hi - lo + 1)/2; // rounds UP mid to right

            if(target >= arr[mid])          //notice this check is different from noComparisionRoundDownMid, else can trigger infinite loop
                lo = mid;
            else
                hi = mid - 1;
        }
        return arr[lo] == target ? lo : -1;  // lo == hi
    }

    // gets the lower bound of target(if not present) from elements present in array. If target is present, return matching index
    private static int lowerBoundofTarget(int[] arr, int target) {
        int lo = 0, hi = arr.length-1;

        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;

            if(target < arr[mid])
                hi = mid - 1;
            else if(target > arr[mid])
                lo = mid + 1;
            else
                return mid;
        }

        //by now lo > hi holds true. so floor(target) = hi
        return hi;
    }

    // strictLowerBoundofTarget = greatest element in the array less than the target
    // algorithm: Go on shrinking search space until nothing is left to search and update ans in every iteration
    private static int strictLowerBoundofTarget(int[] arr, int target) {
        int lo = 0, hi = arr.length-1;

        int ans = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;

            if(target < arr[mid]) {
                hi = mid - 1;
            } else if(target > arr[mid]){ // [lo, mid] are < target. Pick greatest of them
                ans = mid;
                lo = mid + 1;
            } else                        // target == arr[mid], go on shrinking search space, strictLowerBoundofTarget is on left side
                hi = mid - 1;
        }

        return ans;
        //by now lo > hi holds true. so floor(target) = hi. We can also return "hi"
    }

    // gets the upper bound of target(if not present) from elements present in array. If target is present, return matching index
    private static int upperBoundofTarget(int[] arr, int target) {
        int lo = 0, hi = arr.length-1;

        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;

            if(target < arr[mid])
                hi = mid - 1;
            else if(target > arr[mid])
                lo = mid + 1;
            else
                return mid;
        }

        //by now lo > hi holds true. so ceil(target) = lo
        return lo >= arr.length ? -1 : lo;
    }

    // strictUpperBoundofTarget = least element in the array greater than the target
    // algorithm: Go on shrinking search space until nothing is left to search and update ans in every iteration
    private static int strictUpperBoundofTarget(int[] arr, int target) {
        int lo = 0, hi = arr.length-1;

        int ans = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;

            if(target < arr[mid]) {         // [mid, hi] are > target. pick the least among this range
                ans = mid;
                hi = mid - 1;
            } else if(target > arr[mid]) {
                lo = mid + 1;
            } else                          // target == arr[mid], go on shrinking search space, strictUpperBoundofTarget is on right side
                lo = mid + 1;
        }

        return ans;
        //by now lo > hi holds true. so ceil(target) = lo. We can also return "lo"
        //return lo >= arr.length ? -1 : lo;
    }

    // returns range [i,j] of target in the array (especially useful when duplicates are present)
    // Note: this range is same as [noComparisionRoundDownMid, noComparisionRoundUpMid]
    // This is basically <LowerBound, UpperBound>
    private static int[] rangeOfTarget(int[] arr, int target) {
        int lo = 0, hi = arr.length-1;

        int start = -1, end = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;

            if(target <= arr[mid]) {
                if(target == arr[mid])
                    start = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        lo = 0; hi = arr.length-1;

        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;

            if(target >= arr[mid]) {
                if(target == arr[mid])
                    end = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return new int[]{start, end};
    }
}
