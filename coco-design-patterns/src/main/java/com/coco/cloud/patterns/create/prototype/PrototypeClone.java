package com.coco.cloud.patterns.create.prototype;


import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式  深克隆
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/1 20:32
 */
public class PrototypeClone implements Cloneable{

    private Integer id;

    private String name;

    private ArrayList<String> list;

    @Override
    public PrototypeClone clone() throws CloneNotSupportedException {
        PrototypeClone prototypeClone = (PrototypeClone) super.clone();
        prototypeClone.setList((ArrayList<String>) getList().clone());
        return prototypeClone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PrototypeClone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
