package com.coco.cloud.patterns.struct.composite;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:18
 */
public class TextFile implements File {


    @Override
    public void addFile(File file) {
    }

    @Override
    public String getName() {
        return "文件";
    }
}
