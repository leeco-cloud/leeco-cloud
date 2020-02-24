package com.coco.cloud.design.patterns.factory.abstractFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:36
 */
public class PayMethodTest {

    public static void main(String[] args) {
        PayAbstractFactory factoryChina = new ChinaPayAbstractFactory();
        Pay aliPay = factoryChina.create(new AliPay());
        aliPay.pay();
        Pay weChatPay = factoryChina.create(new WeChatPay());
        weChatPay.pay();
        Pay uniPay = factoryChina.create(new UniPay());
        uniPay.pay();
        PayAbstractFactory factoryVisa = new VisaPayAbstractFactory();
        Pay visaPay = factoryVisa.create(new VisaPay());
        visaPay.pay();
    }

}
