package com.coco.cloud.design.patterns.factory.methodFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:32
 */
public class WeChatPay extends WeChatPayMethodFactory implements Pay {

    @Override
    public void pay() {
        System.out.println("微信支付...");
    }

}
