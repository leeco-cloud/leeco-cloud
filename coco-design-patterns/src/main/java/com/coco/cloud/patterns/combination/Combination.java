package com.coco.cloud.patterns.combination;

/**
 * 组合
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:01
 */
public class Combination extends RootNode{

    protected String url;

    Combination(String url) {
        super(url);
        this.url = url;
    }

    @Override
    protected void showUrl(){
        System.out.println(url);
    }

}
