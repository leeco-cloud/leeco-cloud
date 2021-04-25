package com.coco.cloud.dataStruct;

import java.util.Stack;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/25 15:37
 */
public class 最小栈 {

    private static Stack<Integer> allNumberStack = new Stack<>();

    private static Stack<Integer> minNumberStack = new Stack<>();

    public static void push(Integer number){
        allNumberStack.push(number);
        if (!minNumberStack.isEmpty()){
            if (minNumberStack.peek() >= number){
                minNumberStack.push(number);
            }
        }else{
            minNumberStack.push(number);
        }
    }

    public static Integer pop(){
        Integer pop = allNumberStack.pop();
        if (minNumberStack.peek().equals(pop)){
            minNumberStack.pop();
        }
        return pop;
    }

    public static Integer findMinNumber(){
        if (minNumberStack.isEmpty()){
            return null;
        }
        return minNumberStack.peek();
    }

    public static void main(String[] args) {
        push(3);
        System.out.println(findMinNumber());
        pop();
        push(4);
        System.out.println(findMinNumber());
        push(2);
        System.out.println(findMinNumber());
        push(1);
        System.out.println(findMinNumber());
        pop();
        push(9);
        System.out.println(findMinNumber());
        push(6);
        System.out.println(findMinNumber());
    }

}
