package com.CodingProblems;

import java.util.Collections;
import java.util.PriorityQueue;

public class WindowSum {
    public static void main(String[] args) {

        //Y is array of length n, given an input, X, of length 2n-1.
        // The i-th answer is the sum of all the elements in the input in a window of length i+1 starting at i.
        int[] x = new int[]{6,5,8,1,7,4,7,-8,-2,3,5};
        int[] y = new int[6];
        int n = y.length;

        // quadratic algorithm
        for (int i=0; i<n; i++){
            for (int j=i; j<=2*i; j++){
                y[i] = y[i] + x[j];
            }
        }

        System.out.print( "\nQuadratic algorithm: ");
        for(int i: y){
            System.out.print(i+" ");
        }


        //linear algorithm
        //compute 2 arrays from input X..
        //Array1 - at any index i, the value is sum of all elements in x till i-th index
        //Array2 - at any index i, the value is sum of all elements in x from i-th index until last index
        //Output array y - at any index i, value we want is sum of all elements in array x starting from index i until 2*i,
        //so the value at index i, y[i]  = (total sum - Array1[i-1] - Array2[2*i+1])\
        y = new int[6];
        int[] arr1 = new int[x.length];
        int[] arr2 = new int[x.length];

        for(int i = 0; i < x.length; i++) {
            if(i == 0) arr1[i] = x[i];
            else arr1[i] = arr1[i-1] + x[i];
        }

        for(int i = x.length-1; i >= 0; i--) {
            if(i == x.length-1) arr2[i] = x[i];
            else arr2[i] = arr2[i+1] + x[i];
        }

        //populating 0th index and last index of y manually to avoid index out of bounds
        y[0] = x[0];
        y[n-1] = arr2[n-1];
        int total = arr2[0];

        for(int i = 1; i < n-1; i++){
            y[i] = total - arr1[i-1] - arr2[2*i+1];
        }

        //print elements
        System.out.print( "\nLinear algorithm:    ");
        for(int i: y){
            System.out.print(i+" ");
        }
    }
}
