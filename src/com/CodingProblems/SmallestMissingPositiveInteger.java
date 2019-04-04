package com.CodingProblems;
//Question:
//Given an unsorted integer array, find the smallest missing positive integer. Solution should be linear time and constant space
//Input: [3,4,-1,1]
//Output: 2
//Input: [1,2,0]
//Output: 3
//Input: [7,8,9,11,12]
//Output: 1
public class SmallestMissingPositiveInteger {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, -1, 1};
        Solution sol = new Solution(nums);
    }
}

class Solution {
    //idea: if we fill the array starting with 1 and increasing by 1 till the length of the
    //array, the first one missing is the answer

    //loop through array- if we find -ve integer or +ve integer greater than length of
    //array, make it 0, place everything else in its position.
    public Solution(int[] nums){
        System.out.println("Solution:" +firstMissingPositive(nums));
    }

    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0) return 1;

        for(int i = 0; i<nums.length; i++){
            while(nums[i] > 0 && nums[i] <= nums.length){
                if(nums[i] == i+1) break; // in place
                putInPlace(i, nums);
            }

            if(nums[i] == i+1) continue;
            nums[i] = 0;
        }

        //check for first 0
        for(int i = 0; i<nums.length; i++){
            if(nums[i] == 0) return i+1;
        }

        return nums.length+1;
    }

    private void putInPlace(int currentIndex, int[] nums){
        int inPlaceIndex = nums[currentIndex] - 1;

        if(currentIndex == inPlaceIndex) return;

        int temp = nums[currentIndex];
        nums[currentIndex] = nums[inPlaceIndex];
        nums[inPlaceIndex] = temp;

        //if both are are equal after in-place operation
        if(nums[currentIndex] == nums[inPlaceIndex])
            nums[currentIndex] = 0;
    }
}