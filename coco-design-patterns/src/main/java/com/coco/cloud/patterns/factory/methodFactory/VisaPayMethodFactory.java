package com.coco.cloud.patterns.factory.methodFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:34
 */
public class VisaPayMethodFactory implements PayMethodFactory{

    @Override
    public VisaPay create() {
        return new VisaPay();
    }

}
