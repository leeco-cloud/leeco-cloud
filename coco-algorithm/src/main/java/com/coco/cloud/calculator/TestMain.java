package com.coco.cloud.calculator;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/22 16:39
 */
public class TestMain {

    public static void main(String[] args) {
        Context context = new Context();
        String math = " 1 * (2 + 4) - 6";
        int result = context.parse(math);
        System.out.println(result);
    }

}
