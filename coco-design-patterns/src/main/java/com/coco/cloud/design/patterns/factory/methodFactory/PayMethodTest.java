package com.coco.cloud.design.patterns.factory.methodFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:36
 */
public class PayMethodTest {

    public static void main(String[] args) {
        AliPay aliPay = new AilPayMethodFactory().create();
        aliPay.pay();
        WeChatPay weChatPay = new WeChatPayMethodFactory().create();
        weChatPay.pay();
        UniPay uniPay = new UniPayMethodFactory().create();
        uniPay.pay();
        VisaPay visaPay = new VisaPayMethodFactory().create();
        visaPay.pay();
    }

}
