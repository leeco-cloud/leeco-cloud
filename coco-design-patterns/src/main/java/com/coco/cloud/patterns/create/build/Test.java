package com.coco.cloud.patterns.create.build;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 20:42
 */
public class Test {

    public static void main(String[] args) {
        Car car = new Car();
        CarBuild carBuild = new CarBuild(car);
        carBuild.buildLogo("宝马");
        carBuild.buildName("520Li");
        carBuild.buildType("轿车");
        System.out.println(car.toString());
    }

}
