package com.coco.cloud.calculator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/22 17:10
 */
public class PolandUtil {

    /**
     * 运算符
     */
    private static Stack<String> calculatorSignStack = new Stack<>();

    /**
     * 后缀表达式（逆波兰表达式）
     */
    private static List<String> polandExpressList = new ArrayList<>();

    /**
     * 将中缀表达式转换为后缀表达式（逆波兰表达式）
     */
    public static List<String> transfer(String express){
        // 1 * (2 + 4) - 6
        char[] elements = express.toCharArray();
        for (char elementChar : elements) {
            // 取出元素
            String element = String.valueOf(elementChar);
            // 1.如果是操作数 则直接加入到队列
            if (Pattern.matches("\\d", element)) {
                polandExpressList.add(element);
                continue;
            }
            // 2.如果是左括号则直接入栈
            if ("(".equals(element)) {
                calculatorSignStack.push(element);
                continue;
            }
            // 3.如果是右括号 则从栈中取出元素放至队列 知道第一个左括号结束 并且舍弃左右括号
            if (")".equals(element)) {
                String pop = calculatorSignStack.pop();
                while (!"(".equals(pop)) {
                    polandExpressList.add(pop);
                    pop = calculatorSignStack.pop();
                }
                continue;
            }
            /* 4.如果是运算符 则判断栈顶的运算符优先级
                  I   : 栈顶为空 则直接入栈
                  II  : 优先级 >= 栈顶运算符 则直接入栈
                  III : 优先级 <  站定运算符 出栈入队列 直至满足I/II
             */
            if (calculatorSignStack.empty() || "(".equals(calculatorSignStack.peek())) {
                calculatorSignStack.push(element);
            } else {
                String pop = calculatorSignStack.peek();
                if (comparable(element) >= comparable(pop)) {
                    calculatorSignStack.push(element);
                } else {
                    pop = calculatorSignStack.pop();
                    while (comparable(element) < comparable(pop)) {
                        polandExpressList.add(pop);
                        if (calculatorSignStack.empty()){
                            break;
                        }
                        pop = calculatorSignStack.pop();
                    }
                    calculatorSignStack.push(element);
                }
            }
        }
        // 将剩余栈中的元素依赖入队列
        while(!calculatorSignStack.empty()){
            polandExpressList.add(calculatorSignStack.pop());
        }
        return polandExpressList;
    }

    /**
     * 获取运算符的优先级
     */
    private static int comparable(String calculatorSign){
        switch (calculatorSign){
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default: return 0;
        }
    }

}
