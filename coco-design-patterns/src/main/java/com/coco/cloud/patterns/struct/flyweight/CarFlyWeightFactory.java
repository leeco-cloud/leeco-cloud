package com.coco.cloud.patterns.struct.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:27
 */
public class CarFlyWeightFactory implements Car{

    List<Car> cars = new ArrayList<>();

    public CarFlyWeightFactory() {
        cars.add(new CarFlyWeight("宝马525"));
        cars.add(new CarFlyWeight("宝马530"));
    }

    @Override
    public void sendCar(String state) {
        cars.get((int) (Math.random() * 10) % 2).sendCar(state);
    }
}
