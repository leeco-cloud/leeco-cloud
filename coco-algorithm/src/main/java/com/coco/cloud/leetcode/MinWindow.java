package com.coco.cloud.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 *
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/23 17:42
 */
public class MinWindow {

    /**
     * 利用滑动窗口
     */
    public String minWindow(String s, String t) {
        // 需求包含的目标字符的数量
        Map<Character,Integer> needMap = new HashMap<>(t.length());
        // 滑动窗口包含的目标字符的数量
        Map<Character,Integer> windowMap = new HashMap<>(t.length());
        // 滑动窗口的左右指针
        int left = 0, right = 0;
        // 定义最小左右指针
        int leftMin = 0;
        int rightMin = Integer.MAX_VALUE;
        // 表示窗口中满足 need 条件的字符个数，如果 valid 和 need.size 的大小相同，则说明窗口已满足条件，已经完全覆盖了串 T
        int valid = 0;
        //初始化needs
        for(int i=0;i<t.length();i++){
            //needs.getOrDefault(t.charAt(i),0)+1 含义是：needs如果包含t.charAt(i)，
            //则取出值+1;不包含取0+1
            needMap.put(t.charAt(i),needMap.getOrDefault(t.charAt(i),0)+1);
        }
        // 扩展右指针
        while(right < s.length()){
            Character temp = s.charAt(right);
            right ++ ;
            // 目标map包含该字符
            if (needMap.containsKey(temp)){
                windowMap.put(temp,windowMap.getOrDefault(temp,0)+1);
            }
            if (windowMap.get(temp) != null && windowMap.get(temp).equals(needMap.get(temp))){
                // 该字符满足条件
                valid ++;
            }
            // 如果该字符满足了条件 则左指针右移
            while(valid == needMap.size()){
                if ((rightMin - leftMin) >= (right - left)){
                    rightMin = right;
                    leftMin = left;
                }
                Character tempLeft = s.charAt(left);
                left ++;
                if (needMap.containsKey(tempLeft)){
                    windowMap.put(tempLeft,windowMap.getOrDefault(tempLeft,0) - 1);
                }
                if (windowMap.get(tempLeft) != null && windowMap.get(tempLeft) < (needMap.get(tempLeft))){
                    valid --;
                }
            }
        }
        return rightMin==Integer.MAX_VALUE ? "":s.substring(leftMin,rightMin);
    }

    public static void main(String[] args) {
        String S = "aa", T = "aa";
        System.out.println(new MinWindow().minWindow(S,T));
    }

}
