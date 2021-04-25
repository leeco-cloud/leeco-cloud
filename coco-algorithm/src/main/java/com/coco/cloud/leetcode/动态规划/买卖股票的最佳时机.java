package com.coco.cloud.leetcode.动态规划;

/**
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/25 21:54
 */
public class 买卖股票的最佳时机 {

    public int maxProfit(int[] prices) {
        int result = 0;
        int index = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0 ; i--) {
            if (prices[i] > index){
                index = prices[i];
            }else{
                if(result < index - prices[i]){
                    result = index - prices[i];
                }
            }
        }
        return result;
    }

}
