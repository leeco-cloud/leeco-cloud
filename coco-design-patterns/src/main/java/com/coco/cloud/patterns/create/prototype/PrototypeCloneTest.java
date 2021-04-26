package com.coco.cloud.patterns.create.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/1 20:37
 */
public class PrototypeCloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        PrototypeClone prototypeClone = new PrototypeClone();
        prototypeClone.setId(1);
        prototypeClone.setName("leeco");
        ArrayList<String> list = new ArrayList<>();
        list.add("hahaha");
        prototypeClone.setList(list);
        PrototypeClone prototypeClone2 = prototypeClone.clone();
        System.out.println(prototypeClone2.getList().toString());

        list.add("hahaha2");
        System.out.println(prototypeClone2.getList().toString());
    }

}
