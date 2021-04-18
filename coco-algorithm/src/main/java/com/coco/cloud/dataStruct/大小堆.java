package com.coco.cloud.dataStruct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/17 22:02
 */
public class 大小堆 {


    /** 1. 构建二叉堆 */
    private static List<Integer> convertStackList(List<Integer> list){
        int index = list.size() - 1;
        while(index > 1){
            Integer current = list.get(index);
            if (current == null){
                index--;
                continue;
            }
            if (index % 2 == 0){
                // 右节点
                Integer parent = list.get((index - 2) / 2);
                if (current < parent){
                    list.set((index - 2) / 2,current);
                    list.set(index,parent);
                }
            }else{
                // 左节点
                Integer parent = list.get((index - 1) / 2);
                if (current < parent){
                    list.set((index - 1) / 2,current);
                    list.set(index,parent);
                }
            }
            index--;
        }
        return list;
    }


    /** 2. 插入(直接插入堆底 然后逐步上浮) */
    private static List<Integer> addNode(List<Integer> stackList, Integer value) {
        stackList.add(value);
        int index = stackList.size() - 1;
        while(index > 1){
            Integer current = stackList.get(index);
            if (index % 2 == 0){
                // 右节点
                Integer parent = stackList.get((index - 2) / 2);
                if (current < parent){
                    stackList.set((index - 2) / 2,current);
                    stackList.set(index,parent);
                }
                index = (index - 2) / 2;
            }else{
                // 左节点
                Integer parent = stackList.get((index - 1) / 2);
                if (current < parent){
                    stackList.set((index - 1) / 2,current);
                    stackList.set(index,parent);
                }
                index = (index - 1) / 2;
            }
        }
        return stackList;
    }


    /** 3. 删除(删除堆顶元素 拿堆底元素补充 然后逐步下沉) */
    private static List<Integer> delete(List<Integer> stackList) {
        Integer lastNode = stackList.get(stackList.size() - 1);
        stackList.set(0,lastNode);
        stackList.remove(stackList.size() - 1);
        int index = 0;
        while(index < ((stackList.size() - 2) / 2)){
            Integer current = stackList.get(index);
            Integer min;
            Integer leftChild = stackList.get(index * 2 + 1);
            Integer rightChild = stackList.get(index * 2 + 2);
            if (rightChild > leftChild){
                min = index * 2 + 1;
            }else{
                min = index * 2 + 2;
            }
            if (current > stackList.get(min)){
                stackList.set(index,stackList.get(min));
                stackList.set(min,current);
                index = min;
            }else{
                break;
            }
        }
        return stackList;
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
        // 1
        List<Integer> stackList = convertStackList(list);
        System.out.println(stackList.toString());
        // 2
        stackList = addNode(stackList, 5);
        System.out.println(stackList.toString());
        // 3
        stackList = delete(stackList);
        System.out.println(stackList.toString());
    }

}
