package com.coco.cloud.calculator;

import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

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
    private static Stack<String> mathStack = new Stack<>();

    int parse(String math) {
        // 去除空字符串 并且获取所有字符
        math = math.replaceAll(" ", "");
        // 得到逆波兰表达式
        List<String> expressionList = PolandUtil.transfer(math);
        // 依次入栈出栈计算
        return calcRevPolishNotation(expressionList);
    }

    /**
     * 通过逆波兰表达式计算结果
     */
    private static int calcRevPolishNotation(List<String> expressionList){
        expressionList.forEach(expression -> {
            // 1.如果是操作数 则直接入栈
            if (Pattern.matches("\\d",expression)){
                mathStack.push(expression);
                return;
            }
            // 2.如果是操作数 则取出栈顶的两个操作数 与运算符运算 将结果压入栈中
            String left = mathStack.pop();
            String right = mathStack.pop();
            int mathResult = calcValue(left,right,expression);
            mathStack.push(String.valueOf(mathResult));
        });
        return Integer.parseInt(mathStack.pop());
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
