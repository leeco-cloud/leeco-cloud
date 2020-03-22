package com.coco.cloud.patterns.factory.simpleFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:32
 */
public class AliPay extends PaySimpleFactory implements Pay{

    @Override
    public void pay() {
        System.out.println("支付宝支付...");
    }

}
