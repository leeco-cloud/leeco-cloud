package com.coco.cloud.design.patterns.factory.simpleFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:36
 */
public class PaySimpleTest {

    public static void main(String[] args) {
        Pay aliPay = PaySimpleFactory.create(new AliPay());
        aliPay.pay();
        Pay weChatPay = PaySimpleFactory.create(new WeChatPay());
        weChatPay.pay();
        Pay uniPay = PaySimpleFactory.create(new UniPay());
        uniPay.pay();
        Pay visaPay = PaySimpleFactory.create(new VisaPay());
        visaPay.pay();
    }

}
