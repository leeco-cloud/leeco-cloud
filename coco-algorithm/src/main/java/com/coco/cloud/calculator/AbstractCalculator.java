package com.coco.cloud.calculator;

/**
 * 抽象计算器
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/22 16:35
 */
public interface AbstractCalculator {

    /**
     * 计算
     * @param expression 表达式
     * @return result 暂时只考虑int整形
     */
    int parse(Object expression);

}
