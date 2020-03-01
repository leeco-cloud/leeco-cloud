package com.coco.cloud.design.patterns.prototype;


import com.alibaba.fastjson.JSON;

/**
 * 原型模式  深克隆
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/1 20:32
 */
public class PrototypeClone extends PrototypeCloneTest {

    private Integer id;

    private String name;

    public PrototypeClone deepClone(PrototypeClone prototypeClone){
        String jsonString = JSON.toJSONString(prototypeClone);
        return JSON.parseObject(jsonString,PrototypeClone.class);
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

    @Override
    public String toString() {
        return "PrototypeClone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
