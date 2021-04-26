package com.coco.cloud.patterns.struct.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:15
 */
public class Folder implements File {

    List<File> files = new ArrayList<>();

    @Override
    public void addFile(File file) {
        files.add(file);
    }

    @Override
    public String getName() {
        System.out.println("我是文件夹");
        for (File file : files) {
            System.out.println("我是文件夹下面的文件: " + file.getName());
        }
        return "文件夹";
    }
}
