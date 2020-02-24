package com.coco.cloud.design.patterns.factory.methodFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:34
 */
public class AilPayMethodFactory implements PayMethodFactory{

    @Override
    public AliPay create() {
        return new AliPay();
    }

}
