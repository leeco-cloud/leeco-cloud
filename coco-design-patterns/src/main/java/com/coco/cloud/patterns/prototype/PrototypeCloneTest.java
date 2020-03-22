package com.coco.cloud.patterns.prototype;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/1 20:37
 */
public class PrototypeCloneTest {

    public static void main(String[] args) {
        PrototypeClone prototypeClone = new PrototypeClone();
        prototypeClone.setId(1);
        prototypeClone.setName("leeco");
        PrototypeClone prototypeClone2 = prototypeClone.deepClone(prototypeClone);
        System.out.println(prototypeClone2.toString());
    }

}
