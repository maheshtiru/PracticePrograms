package com.CodingProblems;

//A builder is looking to build a row of N houses that can be of K different colors.
//Given an NxK matrix where the nth row and kth column represents the cost to build the nth house with kth color,
//return the minimum cost which achieves this goal.

//DP SOLUTION:
//recursion: ans[i][j] = ans[i][j] +  min{costs[i][j']}  (where j' = 0 to k except j)
//Observe that in k-1 cases, ans[i][j] = ans[i][j] + min of costs in i-1 row, except for 1 case i.e
//when j = color used for min value in i-1. In this 1 case we can use second min from i-1 row.
//Complexity: time: O(nk), space: O(1)
public class PaintHousesDP {
    public static void main(String args[]){
        int[][] costs = new int[][]{{14,2,3},{11,1,5},{14,3,4},{20,10,1}};  //answer: 2+5+3+1 = 11
        System.out.println("Min cost: " +solution(costs));
    }

    private static int solution(int[][] costs){
        int n = costs.length, k;

        if(costs.length == 0) return 0;
        if(costs[0].length == 0) return 0;

        k = costs[0].length;
        int prevMin = 0, prevMin2 = 0, prevColor = 0;

        for(int i = 0; i < n; i++){

            int min = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, color = 0;
            for(int j = 0; j < k; j++){
                if(i > 0){
                    //dp recursion equation
                    costs[i][j] += (j == prevColor) ? prevMin2 : prevMin;
                }

                //simultaneously calculate min and min2 for current row
                if(costs[i][j] <= min) {
                    min2 = min;
                    min = costs[i][j];
                    color = j;
                } else if (costs[i][j] < min2){
                    min2 = costs[i][j];
                }
            }

            prevMin = min;
            prevMin2 = min2;
            prevColor = color;
        }

        return prevMin;
    }
}
