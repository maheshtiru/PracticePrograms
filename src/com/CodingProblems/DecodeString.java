package com.CodingProblems;
//Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
//
//For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
public class DecodeString {
    public static void main(String[] args){
        String s = "123412321053212";
        System.out.println("encoded msg is " +s +", solution(recursive): "+DecodeRecursiveSolution.numDecodings(s));
        System.out.println("encoded msg is " +s +", solution(iterative): "+DecodeIterativeSolution.numDecodings(s));
    }
}

class DecodeRecursiveSolution{
    // recursion c[i] = c[i-1] + c[i-2] (add c[i-2] only if 10 <= s(i-1,i) <= 26)
    public static int numDecodings(String s) {
        if(s.length() == 0) return 0;
        if(s.charAt(0) == 48) return 0;
        if(s.length() == 1) return 1;

        int[] memoize = new int[s.length()];

        return helper(s, memoize, s.length()-1, 0);
    }

    private static int helper(String s, int[] mem, int k, int count){
        if(mem[0] == -1) return 0;
        if(k == 0) return 1;
        if(k == 1) {
            int temp = Integer.parseInt(s.substring(0, 2));
            if(temp % 10 == 0 && temp > 26) return 0;                           // 30,40....
            if(temp % 10 == 0 || temp > 26) return helper(s,mem,k-1,count);  // 10,20 & >26
            return 2;
        }

        if(mem[k] != 0) return mem[k];

        int currAndPrev = Integer.parseInt(s.substring(k-1, k+1));
        if(currAndPrev % 10 == 0 && (currAndPrev > 26 || currAndPrev < 1)){    //'00' or '30','40'....
            count = 0;
            mem[0] = -1;
        }

        //c[i] = c[i-1] + c[i-2] with conditions
        if(s.charAt(k) != 48) count = helper(s, mem, k-1, count);           //check char for '0' if its a '0', just skip
        if(k > 1 && currAndPrev >= 10 && currAndPrev <= 26 )
            count += helper(s, mem, k-2, count);

        mem[k] = count;
        return count;
    }
}

class DecodeIterativeSolution{
    public static int numDecodings(String s) {
        if(s.length() == 0) return 0;
        if(s.charAt(0) == 48) return 0;
        if(s.length() == 1) return 1;

        int l = s.length(), temp = Integer.parseInt(s.substring(0, 2));
        int[] mem = new int[l];
        mem[0] = 1;
        mem[1] = 2;
        if(temp%10 == 0){
            if(temp > 26) mem[1] = 0;                       //30,40,50...
            else mem[1] = 1;                                //10 or 20
        } else if(temp > 26) {
            mem[1] = 1;
        }

        for(int i = 2; i < l; i++){
            int val = Integer.parseInt(s.substring(i-1,i+1));
            if(val%10 == 0){                //when '0' is encountered
                if (val > 26 || val < 1)    //00 or 30,40...
                    return 0;
                else {                      // '10' or '20'  example: '11203'
                    mem[i] = mem[i-2];
                    continue;
                }
            }

            // basic recursion for this problem: c[i] = c[i-1] + c[i-2]
            mem[i] = val > 26 || val < 10 ? mem[i-1] : mem[i-1] + mem[i-2];
        }

        return mem[l-1];
    }
}

