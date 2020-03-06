package com.coco.cloud.design.patterns.factory.simpleFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:34
 */
class PaySimpleFactory {

    public PaySimpleFactory(){}

    static Pay create(Pay pay){
        return pay;
    }

}
