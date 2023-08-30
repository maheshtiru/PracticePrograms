package com.CodingProblems;

import java.util.Arrays;
import java.util.Random;

/*
The number of products in each pile is represented by the array numProducts •
Select any subarray from the array numProducts and pick up products from that subarray
such that the number of products you pick from the ith pile is strictly less than the
number of products you pick from the (i+1)th pile for all indices i of the subarray.
If no possible sub-array, return -1
Eg: numProducts = [7, 4, 5, 2, 6, 5]
1. Choose subarray from indices [0, 2]; max is 3+4+5 = 12
2. Choose subarray from indices [2,5]; max is 1+2+4+5 = 12
So return 12.
 */
/* ALGORITHM: DP, one pass, start from n-1
    - At every index i, we have 2 choices:
        - continue from (i+1)th index or start freshly at i. We determine this using runningSum
        - While traversing in above fashion, if at an index i, our choice is 1; We update max and start freshly .
        - Reccurence relation: Let running(continuing) sum be "runningSum"
                if(runningSum < nums[i])    // start fresh
                    runningSum = 0;
                    dp[i] = nums[i]
                else
                    dp[i] = Min{dp[i+1] - 1, nums[i]};

                //update runningSum and max
 */
public class AmazonFreshPiles {
    public static void main(String[] args) {
        int nums[] = testCase();
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = nums[nums.length -1];
        int max = -1, runningSum = dp[nums.length -1];

        // wrong answer (run with [9, 5, 9, 4, 5, 1, 4, 4, 2, 3])
        for(int i = nums.length - 2; i >= 0; i--) {
            dp[i] = Math.min(dp[i+1] - 1, nums[i]);
            runningSum += dp[i];

            if(runningSum < nums[i] || dp[i] == 1) {
                max = Math.max(runningSum, max);
                System.out.println(nums[i]+", "+runningSum);

                dp[i] = nums[i];
                runningSum = dp[i];
            }
        }

        //update max
        max = Math.max(runningSum, max);
        System.out.println(Arrays.toString(dp));
        System.out.println("dp max: "+max);

        System.out.println("\nbrute force:=======");
        System.out.println(findMaxProducts(nums));
    }

    // this is what you wrote in OA
    public static int findMaxProducts(int[] products) {
        int size = products.length;
        int ans = -1;
        for (int i = size - 1; i >= 0; i--) {
            int add = products[i];               // starting with this product
            int tempSum = add;
            int j = i - 1;                     // now traverse how much i can get by starting from i
            while (add > 0 && j >= 0) {
                add = Math.min(add - 1, products[j]);
                tempSum += add;
                j--;
            }

            ans = Math.max(ans, tempSum);
        }

        return ans;
    }

    static int[] testCase() {
        int[] products = new int[10];
        Random random = new Random();

        for (int i = 0; i < products.length; i++) {
            products[i] = 1 + random.nextInt(9); // Generates random value between 1 to 9
        }

        System.out.println("testcase: "+Arrays.toString(products)+"\n");
        return products;
    }
}


