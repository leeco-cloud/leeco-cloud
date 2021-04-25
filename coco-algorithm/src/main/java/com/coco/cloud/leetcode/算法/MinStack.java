package com.coco.cloud.leetcode.算法;

import java.util.*;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/12 19:20
 *
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
public class MinStack {

    private Stack<Integer> stack;

    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()){
            minStack.push(x);
        }else{
            Integer peek = minStack.peek();
            if (peek >= x){
                minStack.push(x);
            }else{
                minStack.push(peek);
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()){
            throw new RuntimeException();
        }else{
            stack.pop();
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()){
            throw new RuntimeException();
        }else{
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);

        System.out.println(stack.getMin());
        stack.pop();

        System.out.println(stack.top());

        System.out.println(stack.getMin());

    }

}
