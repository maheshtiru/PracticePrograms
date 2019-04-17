package com.CodingProblems;

//Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.
//
//For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5
public class MaxSumNonAdjacent{
    public static void main(String[] args) {
        int[] arr = new int[]{4,3,6,8,2,4,6,9,6,4,3,5,7,9,6,4,3,5,6,8,9,6,5,7,4,6,4,67,8,4,76,89,3,3,2,3,5,4,6,87,4,3,4};
        System.out.println("solution: "+maxSum(arr));
    }

    // recursion: c[i] = max{c[i-1], arr[i] + c[i-2]}
    private static  int maxSum(int[] arr){
        int n = arr.length;

        if(n==0) return 0;

        int[] ans = new int[n];
        ans[0] = arr[0];
        ans[1] = Math.max(arr[0], arr[1]);

        for(int i = 2; i < n; i++){
            ans[i] = Math.max(ans[i-1], arr[i] + ans[i-2]);
        }

        return ans[n-1];
    }
}


