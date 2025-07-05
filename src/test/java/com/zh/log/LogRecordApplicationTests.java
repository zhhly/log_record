package com.zh.log;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
class LogRecordApplicationTests {

    @Test
    void contextLoads() {
        // int[][] intervals = {{2,6},{1,3},{8,10},{15,18}};
        // merge(intervals);
        // int [] nums = {1,2,3,4,5,6,7};
        int [] nums = {-1,-100,3,99};
        int k = 2;
        rotate(nums, k);
    }

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int[][] m = new int[intervals.length][2];
        // 二维数组排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i<intervals.length; i++){
            int i1 = intervals[i][0], i2 = intervals[i][1];
            if(merged.size() == 0 || merged.get(merged.size()-1)[1] < i1){
                merged.add(new int[]{i1,i2});
            }else{
                merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size() - 1)[1], i2);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int [] m = new int[len];
        if(nums.length>1){
            for (int i = 0, j=k-1; i<k; i++){
                m[j]=nums[len-1];
                j--;
                len--;
            }
            for (int i = 0,j=k; i < len; i++,j++) {
                m[j] = nums[i];
            }
            for(int i=0;i<nums.length;i++) nums[i]=m[i];
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if(headA == null || headB == null){
                return null;
            }
            ListNode lA = headA, lB = headB;
            while(lA != lB){
                lA = lA  == null ? headB : lA.next;
                lB = lB  == null ? headA : lB.next;
            }
            return lA;
        }
    }

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
            next = null;
        }
    }
}

