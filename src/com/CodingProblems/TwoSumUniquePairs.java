package com.CodingProblems;

import java.util.*;

public class TwoSumUniquePairs {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(2,2,4,5,8,2,3,4,9,-1,-3,3,6,-4,12,-1,-5,6,13,14,11,1,4,4,4,4,4,4,4,4,3,7,7,3,6);
        System.out.println(countPairs(list.size(), list, 8));
        System.out.println(uniquePairs(list, 8));
        System.out.println(getUniquePairs(list, 8));
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    static int countPairs(int numCount, List<Integer> ratingValues, int target)
    {
        Set<String> result = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        //build map to get frequency of the ratingValues
        for (int i = 0; i < ratingValues.size(); i ++) {
            if (!map.containsKey(ratingValues.get(i)))
                map.put(ratingValues.get(i), 0);
            map.put(ratingValues.get(i), map.get(ratingValues.get(i)) + 1);
        }

        //check if a particular rating value has a compliment value summing up to target, if
        //found add the pair to set in min-max format to eliminate duplicate pairs
        for (int i: map.keySet()) {
            if (target - i == i && map.get(i) >= 2)  //same number adds up to target
                result.add(String.valueOf(i) + "-" + String.valueOf(i));
            else if (target - i != i && map.containsKey(target - i)) {
                result.add(String.valueOf(Math.min(i, target - i)) + "-" +
                        String.valueOf(Math.max(i, target - i)));
            }
        }
        return result.size();
    }

    public static int uniquePairs (List<Integer> nums, int target){
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> seen = new HashSet<Integer>();
        System.out.println(nums);
        int count = 0;
        for(int num : nums){
            if(set.contains(target-num) && !seen.contains(num)){
                count++;
                seen.add(target-num);
                seen.add(num);
            }
            if(!set.contains(num)){
                set.add(num);
            }

        }

        return count;
    }

    public static int getUniquePairs(List<Integer> nums, int target){
        Collections.sort(nums);
        System.out.println(nums);
        int i = 0;
        int j = nums.size()-1;
        int ans = 0;
        while (i < j){
            int sum = nums.get(i)+ nums.get(j);
            if (sum < target){
                i++;
            } else if (sum > target){
                j--;
            } else {
                ans++;
                i++;
                j--;
                while(i<j && i > 0 && nums.get(i) == nums.get(i-1)) i++;
                while(j-1>j && j < nums.size()-1 && nums.get(j)==nums.get(j+1)) j--;
            }
        }
        return ans;
    }

}



