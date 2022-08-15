package com.CodingProblems;
//snapchat
//leetcode 253
import java.util.Arrays;
import java.util.PriorityQueue;

//Given an array of meeting time intervals consisting of start and end times
// [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
//Input: [[0, 30],[5, 10],[15, 20]]
//Output: 2
public class ConferenceRooms {
    public static void main(String[] args){
        int[][] intervals = new int[][]{{0,30},{5,10},{15,20},{8,13},{14,21},{16,25}};
        System.out.println("Min no of rooms req: " +solution(intervals));
    }

    //idea: sort according to start times. Now at ith interval how can we know whether we can allot a used
    //room to this ith interval? we should check whether any of the previous i-1 intervals are already completed.
    //if yes, we can allot that room to current meeting.But how can we determine that? Suppose if the earliest
    //finishing meeting is not yet finished, it means that no meeting is finished yet. So we need the finish
    //time of earliest finishing meeting from previous meetings. Easy -> use min heap for finish times!
    //the size of min heap is the answer (or) put a counter, increment if peek of heap is after start time
    //of current meeting
    private static int solution(int[][] intervals){
        int n = intervals.length;
        if(n == 0) return 0;

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        //give comparator object to sort according to start times i.e, 1st column of each row(row is int[] array)
        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);
        heap.add(intervals[0][1]);

        for(int i = 1; i<n; i++){
            if(heap.peek() <= intervals[i][0]) heap.poll();    //check start time
            heap.add(intervals[i][1]);                         // add finish time
        }

        return heap.size();
    }
}
