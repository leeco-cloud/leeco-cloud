package com.coco.cloud.leetcode.动态规划;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/25 21:24
 */
public class 爬楼梯 {

    public static int climbStairs(int n) {
        // 滑动数组
        int p, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    public static void main(String[] args) {
        int i = climbStairs(5);
        System.out.println(i);
    }

}
