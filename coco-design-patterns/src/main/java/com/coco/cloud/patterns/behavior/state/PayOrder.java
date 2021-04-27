package com.coco.cloud.patterns.behavior.state;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:13
 */
public class PayOrder implements OrderState {
    @Override
    public OrderState doBusiness() {
        System.out.println("支付订单");
        return null;
    }
}
