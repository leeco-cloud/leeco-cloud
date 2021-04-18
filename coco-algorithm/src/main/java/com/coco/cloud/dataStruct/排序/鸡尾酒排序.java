package com.coco.cloud.dataStruct.排序;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/18 18:26
 */
public class 鸡尾酒排序 {

    private static List<Integer> sort(List<Integer> list){
        if (list == null || list.isEmpty()){
            return list;
        }
        if (list.size() == 1){
            return list;
        }
        int left = 0;
        int right = list.size() - 1;
        while(left < right){

            for (int i = left; i < right ; i++) {
                Integer current = list.get(i);
                if (current > list.get(i + 1)){
                    Integer temp = list.get(i + 1);
                    list.set(i,temp);
                    list.set(i + 1,current);
                }
            }
            left++;

            for (int i = right - 1; i > left - 1 ; i--) {
                Integer current = list.get(i);
                if (current < list.get(i - 1)){
                    Integer temp = list.get(i - 1);
                    list.set(i,temp);
                    list.set(i - 1,current);
                }
            }
            right--;
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(11);
        list.add(2);
        list.add(5);
        list.add(1);
        List<Integer> sort = sort(list);
        System.out.println(sort.toString());
    }

}
