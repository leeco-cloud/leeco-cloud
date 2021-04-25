package com.coco.cloud.leetcode.动态规划;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/25 20:56
 */
public class 最大子序列和 {

    public int maxSubArray(int[] nums) {
        int index = 1;
        int sum = nums[0];
        int max = sum;
        while(index < nums.length){
            sum = Math.max(sum + nums[index], nums[index]);
            if (sum > max){
                max = sum;
            }
            index++;
        }
        return max;
    }

}
