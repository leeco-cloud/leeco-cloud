package com.coco.cloud.patterns.factory.methodFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/24 18:52
 */
public interface PayMethodFactory {

    /**
     * 创建对象方法
     * @return pay Pay
     */
    Pay create();

}
