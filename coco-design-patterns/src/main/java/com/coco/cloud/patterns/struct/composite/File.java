package com.coco.cloud.patterns.struct.composite;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:12
 */
public interface File {

    void addFile(File file);

    String getName();

}
