package com.coco.cloud.design.patterns.factory.simpleFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:32
 */
public class UniPay extends PaySimpleFactory implements Pay{

    @Override
    public void pay() {
        System.out.println("银联支付...");
    }

}
