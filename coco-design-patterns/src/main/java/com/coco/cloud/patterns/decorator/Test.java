package com.coco.cloud.patterns.decorator;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 21:04
 */
public class Test {

    public static void main(String[] args) {
        BaseMenu baseMenu = new BaseMenu();
        baseMenu.getAllMenu();
        System.out.println("\n");
        TeacherMenu teacherMenu = new TeacherMenu(baseMenu);
        teacherMenu.getAllMenu();
    }

}
