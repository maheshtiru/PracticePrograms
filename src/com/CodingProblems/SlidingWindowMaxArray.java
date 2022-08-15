package com.CodingProblems;
//google
//Given an array of integers and a number k, where 1 <= k <= length of the array,
// compute the maximum values of each subarray of length k.
//For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8]. Do in O(N) time and O(k) extra space

import java.util.Deque;
import java.util.LinkedList;

//Solution idea: Use Double ended queue to store k elements of current sliding window
// Add each element from end of the queue.  Before adding, make sure that all the elements from previous window are removed(obviously from start of queue)
//Make sure the max element of each window stays at starting end of the queue.
//Complexity: O(N) time(to be spec: O(2N) since each element is added once and removed once). Space: O(k) for queue(not considering answer array)
public class SlidingWindowMaxArray {
    public static void main(String[] args){
        int[] nums = new int[]{10, 5, 2, 7, 8, 7};
        int k = 3;

        System.out.println("Solution:");
        for(int i : solution(nums, k)){
            System.out.print(i+" ");
        }
    }

    private static int[] solution(int[] nums, int k){
        if(nums.length == 0 || k == 0) return new int[0];
        if(k == 1) return nums;

        int n = nums.length, counter = 0;
        int[] ans = new int[n-k+1];
        Deque<Integer> dq = new LinkedList<>();             //can also use "ArrayDeque"

        //there should be only current sliding window elements in the deque and the max of the
        //current sliding window should be at the opposite end w.r.t the end where we add from

        //process first k elements and get the deque ready, (adding indicies to the queue instead of vals)
        for(int i = 0; i < k; i++){
            while (!dq.isEmpty() && nums[i] > nums[dq.getLast()]) dq.removeLast(); // max is always at first
            dq.add(i);                                                           //add index number
        }

        ans[counter++] = nums[dq.getFirst()];

        //process next n-k elements
        for(int i = k; i < n; i++){
            //remove element not in current window(i to i-k is the window)
            if(!dq.isEmpty() && dq.getFirst() <= i - k) dq.removeFirst();
            while(!dq.isEmpty() && nums[i] > nums[dq.getLast()]) dq.removeLast();  //put max at first end

            dq.add(i);
            ans[counter++] = nums[dq.getFirst()];
        }

        return ans;
    }
}
