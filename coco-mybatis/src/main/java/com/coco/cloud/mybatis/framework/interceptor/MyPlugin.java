package com.coco.cloud.mybatis.framework.interceptor;

import com.coco.cloud.mybatis.framework.annotation.Intercepts;
import com.coco.cloud.mybatis.framework.plugin.Interceptor;
import com.coco.cloud.mybatis.framework.plugin.Invocation;
import com.coco.cloud.mybatis.framework.plugin.Plugin;

import java.util.Arrays;

/**
 * 自定义插件
 */
@Intercepts("query")
public class MyPlugin implements Interceptor{

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String statement = (String) invocation.getArgs()[0];
        Object[] parameter = (Object[]) invocation.getArgs()[1];
        Class pojo = (Class) invocation.getArgs()[2];
        System.out.println("插件输出：SQL：["+statement+"]");
        System.out.println("插件输出：Parameters："+Arrays.toString(parameter));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

}
