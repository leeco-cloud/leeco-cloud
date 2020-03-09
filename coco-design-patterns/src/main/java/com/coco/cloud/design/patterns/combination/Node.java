package com.coco.cloud.design.patterns.combination;

/**
 * 叶子节点
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:01
 */
public class Node extends RootNode{

    protected String url;

    Node(String url) {
        super(url);
        this.url = url;
    }

    @Override
    protected void showUrl(){
        System.out.println(url);
    }

}
