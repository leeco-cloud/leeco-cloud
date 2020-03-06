package com.coco.cloud.design.patterns.decorator;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 20:56
 */
public class TeacherMenu extends AbstractMenuDecorator{

    TeacherMenu(AbstractMenu abstractMenu) {
        super(abstractMenu);
    }

    @Override
    void getAllMenu() {
        super.getAllMenu();
        System.out.println("老师新增指责:作业,题库,通知...");
    }

}
