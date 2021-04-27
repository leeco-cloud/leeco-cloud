package com.coco.cloud.patterns.behavior.state;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:16
 */
public class StateTest {

    public static void main(String[] args) {
        StateContext stateContext = new StateContext();

        stateContext.setOrderState(new CreateOrder());
        stateContext.executor();
        stateContext.executor();

        stateContext.executor();
        stateContext.executor();
    }

}
