package com.coco.cloud.patterns.behavior.state;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:12
 */
public interface OrderState {

    OrderState doBusiness();

}
