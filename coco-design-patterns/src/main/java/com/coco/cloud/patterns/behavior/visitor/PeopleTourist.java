package com.coco.cloud.patterns.behavior.visitor;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:57
 */
public class PeopleTourist implements Tourist {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }

    @Override
    public void doAction() {
        System.out.println("我的是一个游客");
    }


}
