package com.coco.cloud.patterns.factory.methodFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:32
 */
public class VisaPay extends VisaPayMethodFactory implements Pay {

    @Override
    public void pay() {
        System.out.println("visa支付...");
    }

}
