package com.coco.cloud.patterns.decorator;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 20:53
 */
public class BaseMenu extends AbstractMenu {

    @Override
    void getAllMenu() {
        System.out.println("当前权限:问答,文章,精品课,冒泡,商城,");
    }

}
