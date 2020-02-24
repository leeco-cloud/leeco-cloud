package com.coco.cloud.design.patterns.factory.abstractFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:32
 */
public class VisaPay extends VisaPayAbstractFactory implements Pay {

    @Override
    public void pay() {
        System.out.println("visa支付...");
    }

}
