package com.coco.cloud.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/28 20:43
 */
public class DecodeString {

    private Stack<String> oneStack = new Stack<>();

    /**
     * 有点类似与波兰表达式的问题 考虑优先级
     */
    public String decodeString(String s) {
        if (s == null || "".equals(s)){ return "";}
        int index = 0;
        while(index < s.length()){
            // 遇到"]"则停止 否则装填栈中
            if(s.charAt(index) != ']'){
                // 如果是字母 或者"["符号 则直接进栈
                if (Character.isLetter(s.charAt(index)) || s.charAt(index) == '['){
                    oneStack.add(String.valueOf(s.charAt(index)));
                    index ++;
                }else{
                    // 如果是数字 则拼接完成的数字之后再进栈
                    StringBuilder nums = new StringBuilder();
                    while(Character.isDigit(s.charAt(index))){
                        nums.append(s.charAt(index));
                        index ++;
                    }
                    oneStack.add(nums.toString());
                }
            }else{
                LinkedList<String> sub = new LinkedList<>();
                String temp = oneStack.pop();
                // 取出中括号里面的字符
                while(!"[".equals(temp)){
                    sub.addFirst(String.valueOf(temp));
                    temp = oneStack.pop();
                }
                // 遇到了左括号 获取数字
                String num = oneStack.pop();
                StringBuffer ret = new StringBuffer();
                for (String tempStr : sub) {
                    ret.append(tempStr);
                }
                StringBuilder data = new StringBuilder();
                for (int i = 0; i < Integer.parseInt(num); i++) {
                    data.append(ret);
                }
                oneStack.push(data.toString());
                index ++;
            }
        }
        LinkedList<String> result = new LinkedList<>();
        while(!oneStack.isEmpty()){
            result.addFirst(oneStack.pop());
        }
        StringBuilder haha = new StringBuilder();
        for (String s1 : result) {
            haha.append(s1);
        }
        return haha.toString();
    }

    public static void main(String[] args) {
//         * s = "3[a]2[bc]", 返回 "aaabcbc".
//                * s = "3[a2[c]]", 返回 "accaccacc".
//                * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
        String s= "100[ab]";
        System.out.println(new DecodeString().decodeString(s));
    }

}
