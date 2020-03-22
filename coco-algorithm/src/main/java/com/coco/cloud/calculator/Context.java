package com.coco.cloud.calculator;

import java.util.Stack;

/**
 * 环境
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/22 16:39
 */
class Context {

    /**
     * 操作数栈
     */
    private Stack<AbstractCalculator> mathStack = new Stack<>();

    /**
     * 表达式栈
     */
    private Stack<AbstractCalculator> expressionStack = new Stack<>();

    int parse(String math) {
        // 去除空字符串 并且获取所有字符
        math = math.replaceAll(" ", "");
        // 得到逆波兰表达式
        String transfer = PolandUtil.transfer(math);
        // 依次入栈出栈计算
        return calcRevPolishNotation(transfer);
    }

    /**
     * 通过逆波兰表达式计算结果
     */
    private static int calcRevPolishNotation(String express){
        Stack<String> stack = new Stack<>();
        for (int i = 0; i <express.length() ;i++) {
            // 普通数值的处理
            if ("\\d".matches(express.charAt(i) + "")){
                stack.push(express.charAt(i) + "");
                // + - * / 运算符的处理
            }else if ((express.charAt(i) + "").matches("[+\\-*/]")){
                String k1 = stack.pop();
                String k2 = stack.pop();
                // 计算结果
                int res = calcValue(k1, k2, (express.charAt(i) + ""));
                stack.push(res+"");
            }

        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 根据运算符计算结果
     */
    private static int calcValue(String k1, String k2, String c) {
        switch(c){
            case "+":
                return Integer.parseInt(k1)+Integer.parseInt(k2);
            case "-":
                return Integer.parseInt(k2)-Integer.parseInt(k1);
            case "*":
                return Integer.parseInt(k1)*Integer.parseInt(k2);
            case "/":
                return Integer.parseInt(k2)/Integer.parseInt(k1);
            default:
                throw new RuntimeException("没有该类型的运算符！");
        }
    }


}
