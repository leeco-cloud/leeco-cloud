package com.coco.cloud.netty.v2.protocol;


import java.io.Serializable;

/**
 * 自定义传输协议
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/21 19:40
 */
public class InvokerProtocol implements Serializable {

    private String serviceName;

    private String methodName;

    /**
     * 形参列表
     */
    private Class<?>[] params;

    /**
     * 实参列表
     */
    private Object[] values;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParams() {
        return params;
    }

    public void setParams(Class<?>[] params) {
        this.params = params;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
