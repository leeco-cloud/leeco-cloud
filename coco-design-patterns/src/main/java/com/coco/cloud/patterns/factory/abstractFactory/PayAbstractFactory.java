package com.coco.cloud.patterns.factory.abstractFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:52
 */
public interface PayAbstractFactory {

    /**
     * 创建对象方法
     * @return pay Pay
     */
    Pay create(Pay pay);

}
