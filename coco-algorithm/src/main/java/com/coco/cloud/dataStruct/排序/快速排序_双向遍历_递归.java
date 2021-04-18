package com.coco.cloud.dataStruct.排序;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/18 19:19
 */
public class 快速排序_双向遍历_递归 {

    private static List<Integer> result = new ArrayList<>();

    private static void sort(List<Integer> list){
        // 临界条件 子序列为空 或者只有一个元素时 回溯
        if (list.isEmpty() || list.size() <= 1){
            if (!list.isEmpty()){
                result.addAll(list);
            }
            return;
        }
        int basic = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        // 利用首尾指针遍历序列
        int startPoint = 0;
        int endPoint = list.size();
        while(startPoint < endPoint){
            for (int i = startPoint + 1; i < endPoint ; i++) {
                startPoint = i;
                Integer current = list.get(i);
                if (current < basic){
                    left.add(current);
                }else{
                    break;
                }
            }
            for (int i = endPoint - 1; i > startPoint - 1 ; i--) {
                endPoint = i;
                Integer current = list.get(i);
                if (current > basic){
                    right.add(current);
                }else{
                    break;
                }
            }
        }
        // 递归
        sort(left);
        result.add(basic);
        sort(right);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(10);
        list.add(3);
        list.add(6);
        list.add(7);
        list.add(11);
        list.add(8);
        list.add(12);
        list.add(9);
        list.add(14);
        list.add(13);
        list.add(15);
        sort(list);
        System.out.println(result.toString());
    }

}
