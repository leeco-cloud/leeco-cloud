package com.coco.cloud.leetcode.算法;


/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/30 18:06
 */
public class LargestRectangleArea {

    public int largestRectangleArea(int[] heights) {
        // 暴力解法
        int index = 0;
        int result = 0;
        while(index < heights.length){
            int win = index;
            int max = 0;
            int min = Integer.MAX_VALUE;
            while(win < heights.length){
                if (min >= heights[win]){
                    min = heights[win];
                }
                int temp = (win - index + 1) * min;
                if (temp >= max){
                    max = temp;
                }
                win ++;
            }
            if (max >= result){
                result = max;
            }
            index ++;
        }
        return result;
    }

}