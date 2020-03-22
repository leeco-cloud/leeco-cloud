package com.coco.cloud.patterns.build;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 21:30
 */
public class CakeBuild {

    public static CakeBuild cakeBuild = new CakeBuild();

    public static Cake cakeStatic;

    public static CakeBuild build(Cake cake) {
        cakeStatic = cake;
        return cakeBuild ;
    }

    public CakeBuild addEgg(){
        System.out.println("加一个鸡蛋.");
        return cakeBuild;
    }

    public CakeBuild addMeat(){
        System.out.println("加一个香肠.");
        return cakeBuild;
    }

}
