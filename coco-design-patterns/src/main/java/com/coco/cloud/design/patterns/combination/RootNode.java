package com.coco.cloud.design.patterns.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * 根节点
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:01
 */
public class RootNode {

    protected String url;

    private List<Combination> combinationList = new ArrayList<>();

    private List<Node> nodeList = new ArrayList<>();

    RootNode(String url){
        this.url = url;
    }

    protected void showUrl(){
        System.out.println(url);
    }

    public void addNode(Node node){
        nodeList.add(node);
        node.url = url + node.url;
    }

    public void addCombination(Combination combination){
        combinationList.add(combination);
        combination.url = url + combination.url;
    }

}
