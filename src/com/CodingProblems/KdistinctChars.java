package com.CodingProblems;
//This problem was asked by Amazon.
//Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.
//For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".

//idea: sliding window. two pointers for start(i) and end(j) of substring. when j is moved forward, if distinct characters
//between i and j exceed k, move i forward. Run time O(n)
public class KdistinctChars {
    public static void main(String[] args){
        String input = "eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh";
        int k = 16;
        String sol = solution(input, k);
        System.out.println("longest substring of "+input+" with "+k+" distinct chars is: " +sol +" ,length: " +sol.length());

    }

    private static String solution(String input, int limit){
        if(limit == 0 || input.length() == 0) return "";

        int[] alphabet = new int[129];  //accomodates all 128 ascii chars, 1-based indices
        int i = 0, distChars = 0, maxLength = 0, length = 0, ansStart = 0, ansEnd = 0;

        for(int j = 0; j < input.length(); j++){
            int index = input.charAt(j);

            if(alphabet[index] == 0) {  //indicates new character which is not in out substring
                distChars++;
            }

            alphabet[index] = alphabet[index] + 1;

            while(distChars > limit){    //if distChars in substring exceeds limit, increment i till distChars is equal to limit
                index = input.charAt(i++);
                alphabet[index] = alphabet[index] - 1;

                if(alphabet[index] == 0) distChars--;
            }

            length = j - i + 1;

            if(length >= maxLength){
                ansStart = i;
                ansEnd = j;
                maxLength = length;
            }
        }

        return input.substring(ansStart, ansEnd+1);
    }
}
