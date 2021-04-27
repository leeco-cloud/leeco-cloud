package com.coco.cloud.patterns.behavior.command;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:00
 */
public class CommandTest {

    public static void main(String[] args) {
        CommandActive commandActive = new CommandActive();
        commandActive.invoke("play");
        commandActive.invoke("parse");
    }

}
