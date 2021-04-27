package com.coco.cloud.patterns.behavior.visitor;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:59
 */
public class VisitorTest {

    public static void main(String[] args) {
        Visitor visitor = new PeopleVisitor();
        Tourist tourist = new PeopleTourist();

        tourist.accept(visitor);
    }

}
