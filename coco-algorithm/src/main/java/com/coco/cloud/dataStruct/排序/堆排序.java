package com.coco.cloud.dataStruct.排序;

import com.coco.cloud.dataStruct.大小堆;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/18 19:48
 */
public class 堆排序 {

    private static void sort(List<Integer> list){
        List<Integer> stackList = 大小堆.convertStackList(list);
        while(!stackList.isEmpty()){
            System.out.print(list.get(0) + ",");
            大小堆.delete(stackList);
        }
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
    }

}
