package com.coco.cloud.patterns.behavior.state;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:13
 */
public class StateContext {

    private OrderState orderState;

    public void setOrderState(OrderState orderState){
        this.orderState = orderState;
    }

    public void executor(){
        if (orderState == null){
            System.out.println("订单已结束");
            return;
        }
        orderState = orderState.doBusiness();
    }

}
