package com.coco.cloud.patterns.struct.composite;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:19
 */
public class Test {

    public static void main(String[] args) {
        File folder = new Folder();

        File text = new TextFile();
        folder.addFile(text);

        folder.getName();
    }

}
