package com.CodingProblems;


import java.util.*;

public class demo {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.permute(new int[]{1,2,3}).toString());

    }

    static class Solution {
        List<List<Integer>> ans;
        List<Integer> list;
        Set<Integer> permutationSet;
        public List<List<Integer>> permute(int[] nums) {
            ans = new ArrayList<>();
            list = new ArrayList<>();
            permutationSet = new HashSet<Integer>();

            backTrack(nums);
            return ans;
        }

        private void backTrack(int[] nums) {
            System.out.println("set: "+permutationSet.toString());

            for(int i = 0; i < nums.length; i++) {
                if(permutationSet.contains(nums[i]))
                    continue;

                permutationSet.add(nums[i]);
                list.add(nums[i]);
                if(list.size() == nums.length) ans.add(new ArrayList<>(list));

                System.out.println("list: "+list.toString());
                backTrack(nums);

                int removeIndex = list.size() - 1;
                int remove = list.remove(removeIndex);
                permutationSet.remove(remove);
            }
        }
    }
}
