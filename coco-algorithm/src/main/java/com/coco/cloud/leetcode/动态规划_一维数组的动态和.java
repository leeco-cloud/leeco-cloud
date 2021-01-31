package com.coco.cloud.leetcode;

import java.util.Arrays;

/**
 * 一维数组的动态和
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/1/31 15:23
 */
public class 动态规划_一维数组的动态和 {
    public static int[] runningSum(int[] nums) {
        int temp = 0;
        for(int i = 0; i < nums.length; i ++){
            if (i != 0){
                nums[i] = nums[i] + nums[i - 1];
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,1,2,10,1};
        System.out.println(Arrays.toString(runningSum(nums)));
    }

}
