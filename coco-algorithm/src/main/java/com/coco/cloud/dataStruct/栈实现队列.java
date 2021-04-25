package com.coco.cloud.dataStruct;

import java.util.Stack;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/25 15:46
 */
public class 栈实现队列 {

    private static Stack<Integer> tempStack = new Stack<>();

    private static Stack<Integer> stack = new Stack<>();

    private static void push(Integer number){
        tempStack.push(number);
    }

    private static Integer pop(){
        if (stack.isEmpty()){
            while(!tempStack.isEmpty()){
                stack.push(tempStack.pop());
            }
        }
        return stack.isEmpty()? null : stack.pop();
    }

    public static void main(String[] args) {
        push(3);
        push(4);
        push(2);
        push(1);
        push(9);
        push(6);

        while(!stack.isEmpty() || !tempStack.isEmpty()){
            System.out.println(pop());
        }
    }

}
