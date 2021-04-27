package com.coco.cloud.patterns.behavior.command;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 21:57
 */
public class CommandActive {

    Active active = new Active();

    public void invoke(String cmd){
        if ("play".equals(cmd)){
            active.play();
        }
        if ("parse".equals(cmd)){
            active.parse();
        }
    }

}
