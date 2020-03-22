package com.coco.cloud.patterns.factory.abstractFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:32
 */
public class UniPay extends ChinaPayAbstractFactory implements Pay {

    @Override
    public void pay() {
        System.out.println("银联支付...");
    }

}
