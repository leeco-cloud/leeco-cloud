package com.coco.cloud.patterns.create.build;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 20:37
 */
public class CarBuild {

    private Car car;

    CarBuild(Car car) {
        this.car = car;
    }

    public Car buildName(String name){
        car.setName(name);
        return car;
    }

    public Car buildLogo(String logo){
        car.setLogo(logo);
        return car;
    }

    public Car buildType(String type){
        car.setType(type);
        return car;
    }

}
