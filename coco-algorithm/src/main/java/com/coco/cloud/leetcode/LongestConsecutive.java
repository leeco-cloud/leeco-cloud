package com.coco.cloud.leetcode;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/6/6 14:39
 */
public class LongestConsecutive {

    /**
     * 思路：
     * 先排序 再遍历
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 0){
            return 0;
        }
        // 排序
        Arrays.sort(nums);
        // 记录最大长度
        int maxLength = 1;
        // 记录每个连续的临时长度
        int tempLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]){
                nums[i] = nums[i-1];
                continue;
            }
            if (nums[i] == nums[i-1] + 1){
                tempLength ++;
            }else{
                maxLength = Math.max(maxLength,tempLength);
                tempLength = 1;
            }
        }
        // 做最后的边界检查
        maxLength = Math.max(maxLength,tempLength);
        return maxLength;
    }

    public static void main(String[] args) {
        int i = new LongestConsecutive().longestConsecutive(new int[]{1,2,0,1});
        System.out.println(i);
    }

}
