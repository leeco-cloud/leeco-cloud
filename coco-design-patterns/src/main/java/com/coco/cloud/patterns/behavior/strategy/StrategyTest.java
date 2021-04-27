package com.coco.cloud.patterns.behavior.strategy;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 21:48
 */
public class StrategyTest {

    public static void main(String[] args) {
        StrategyContext strategyContext = new StrategyContext(new FullPaymentCar());
        strategyContext.buyCar();

        strategyContext = new StrategyContext(new LoanCar());
        strategyContext.buyCar();
    }

}
