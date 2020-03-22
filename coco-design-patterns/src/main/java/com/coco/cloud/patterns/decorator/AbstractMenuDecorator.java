package com.coco.cloud.patterns.decorator;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 20:54
 */
public class AbstractMenuDecorator extends AbstractMenu{

    private AbstractMenu abstractMenu;

    AbstractMenuDecorator(AbstractMenu abstractMenu){
        this.abstractMenu = abstractMenu;
    }

    @Override
    void getAllMenu() {
        abstractMenu.getAllMenu();
    }

}
