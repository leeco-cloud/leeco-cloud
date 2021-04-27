package com.coco.cloud.patterns.behavior.visitor;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:58
 */
public class PeopleVisitor implements Visitor {

    @Override
    public void visitor(Tourist tourist) {
        tourist.doAction();
    }

}
