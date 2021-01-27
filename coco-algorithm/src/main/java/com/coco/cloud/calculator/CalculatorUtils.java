package com.coco.cloud.calculator;

import java.util.*;

import static jdk.nashorn.internal.runtime.JSType.isNumber;

/**
 * 逆波兰表达式工具类
 * @author liuqiang@ourdocker.cn
 */
public class CalculatorUtils {

    public static Map<String,Integer> level = new HashMap<>();

    static{
        level.put("(",1);
        level.put("+",1);
        level.put("-",1);
        level.put("*",2);
        level.put("/",2);
    }

    /**
     * 中缀表达式转换逆波兰表达式
     * 根据空格分隔成列表
     * @return List<String>
     */
    public static String[] midExpressConvertUnPolandExpress(String midExpressStr){
        String[] midExpress = midExpressStr.split("\\s+");
        return midExpressConvertUnPolandExpress(midExpress);
    }

    /**
     * 中缀表达式转换逆波兰表达式
     * @return List<String>
     */
    public static String[] midExpressConvertUnPolandExpress(String[] midExpress){
        System.out.println("中缀表达式：" + Arrays.toString(midExpress));
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for(String item : midExpress){
            // 如果是变量 则直接入队列
            if (isNumber(item)){
                result.add(item);
                continue;
            }
            // 如果左括号则直接入栈
            if ("(".equals(item)){
                stack.push(item);
                continue;
            }
            // 如果是右括号 则从栈中取出操作数进队列 直到遇到左括号 并舍弃掉这一对括号
            if (")".equals(item)){
                String stackItem = stack.pop();
                while(stack.isEmpty() || !"(".equals(stackItem)){
                    result.add(stackItem);
                    stackItem = stack.pop();
                }
                continue;
            }
            // 其他操作符 例如+-*/
            if (stack.isEmpty()){
                // 栈顶为空 则直接入栈
                stack.push(item);
                continue;
            }
            // 栈顶不为空 则比较优先级
            if (compare(item,stack.peek())){
                // 如果当前操作符大于等于栈顶操作数 则直接入栈
                stack.push(item);
                continue;
            }
            // 如果当前操作符小于从栈顶操作数 则取出栈顶元素 直到栈顶操作符优先级小于当前操作数
            if (compare(stack.peek(),item)){
                // 如果当前操作符大于等于栈顶操作数 则直接入栈
                String stackItem = stack.peek();
                while(!stack.isEmpty() && compare(stackItem,item)){
                    result.add(stack.pop());
                }
                stack.push(item);
            }
        }
        if (!stack.isEmpty()){
            while(!stack.isEmpty()){
                result.add(stack.pop());
            }
        }
        System.out.println("逆波兰表达式：" + result);
        return result.toArray(new String[0]);
    }

    private static boolean compare(String peek, String item) {
        return level.get(peek) >= level.get(item);
    }

    /**
     * 根据逆波兰表达式计算最终结果
     * @return result
     */
    public static String calculate(String midExpressStr){
        // 根据中缀表达式转换成波兰表达式
        String[] unPolandExpress = midExpressConvertUnPolandExpress(midExpressStr);
        // 辅助栈
        Stack<String> stack = new Stack<>();
        for (String item : unPolandExpress) {
            // 遇到变量 则直接入栈
            if (level.get(item) == null){
                stack.push(item);
                continue;
            }
            // 遇到操作符 则取出栈顶两个变量 进行运算
            String right = stack.pop();
            String left = stack.pop();
            String result = convert(left,right,item);
            stack.push(result);
        }
        System.out.println("最终结果: " + stack.peek());
        return stack.pop();
    }

    /**
     * 两数做运算
     * @param left number1
     * @param right number2
     * @param item 运算符
     * @return result
     */
    private static String convert(String left, String right, String item) {
        double result;
        switch (item){
            case "+": result = Double.parseDouble(left) + Double.parseDouble(right); break;
            case "-": result = Double.parseDouble(left) - Double.parseDouble(right); break;
            case "*": result = Double.parseDouble(left) * Double.parseDouble(right); break;
            case "/": result = Double.parseDouble(left) / Double.parseDouble(right); break;
            default: return null;
        }
        return Double.toString(result);
    }

}
