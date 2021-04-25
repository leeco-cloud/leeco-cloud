package com.coco.cloud.leetcode.算法;

import java.util.HashMap;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/15 21:59
 */
public class SolutionMap {

    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap < Integer, Integer > mp = new HashMap< >();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)){
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[5];
        int k = 6;
        nums[0] = 1;
        nums[1] = 3;
        nums[2] = 2;
        nums[3] = 1;
        nums[4] = 5;
        System.out.println(new SolutionMap().subarraySum(nums,k));
    }

}
