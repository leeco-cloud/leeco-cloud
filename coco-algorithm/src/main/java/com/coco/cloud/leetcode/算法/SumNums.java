package com.coco.cloud.leetcode.算法;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/6/2 18:50
 */
public class SumNums {
    public int sumNums(int n) {
        boolean check = n > 0 && (n += sumNums(n-1)) > 0;
        return n;
    }

}
