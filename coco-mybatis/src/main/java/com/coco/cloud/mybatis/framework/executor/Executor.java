package com.coco.cloud.mybatis.framework.executor;

public interface Executor {
    <T> T query(String statement, Object[] parameter, Class pojo);
}
