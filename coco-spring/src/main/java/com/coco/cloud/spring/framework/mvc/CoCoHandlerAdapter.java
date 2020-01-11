package com.coco.cloud.spring.framework.mvc;

import com.coco.cloud.spring.framework.annotation.CoCoRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqiang@ourdocker.cn
 * HandlerAdapter
 * 适配器模式
 * 将HandlerMapping参入参数进行类型转换为method接受的参数类型
 */
public class CoCoHandlerAdapter {

    public boolean supports(Object handler){
        return (handler instanceof CoCoHandlerMapping);
    }

    public CoCoModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        CoCoHandlerMapping handlerMapping = (CoCoHandlerMapping)handler;
        //把方法的形参列表和request的参数列表所在顺序进行一一对应
        Map<String,Integer> paramIndexMapping = new HashMap<String, Integer>();
        //提取方法中加了注解的参数
        //把方法上的注解拿到，得到的是一个二维数组
        //因为一个参数可以有多个注解，而一个方法又有多个参数
        Annotation[][] parameterAnnotations = handlerMapping.getMethod().getParameterAnnotations();
        for (int index = 0; index < parameterAnnotations.length ; index ++) {
            for(Annotation annotation : parameterAnnotations[index]){
                if(annotation instanceof CoCoRequestParam){
                    String paramName = ((CoCoRequestParam) annotation).value();
                    if(!"".equals(paramName.trim())){
                        paramIndexMapping.put(paramName, index);
                    }
                }
            }
        }
        //提取方法中的request和response参数
        Class<?> [] paramsTypes = handlerMapping.getMethod().getParameterTypes();
        for (int index = 0; index < paramsTypes.length ; index ++) {
            Class<?> type = paramsTypes[index];
            if(type == HttpServletRequest.class ||
                    type == HttpServletResponse.class){
                paramIndexMapping.put(type.getName(),index);
            }
        }
        //获得方法的形参列表
        Map<String,String[]> params = request.getParameterMap();
        //实参列表
        Object [] paramValues = new Object[paramsTypes.length];
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("[\\[\\]]","")
                    .replaceAll("\\s",",");
            if(!paramIndexMapping.containsKey(param.getKey())){
                continue;
            }
            int index = paramIndexMapping.get(param.getKey());
            paramValues[index] = caseStringValue(value,paramsTypes[index]);
        }
        if(paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = request;
        }
        if(paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = response;
        }
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(),paramValues);
        if(result == null || result instanceof Void){
            return null;
        }
        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == CoCoModelAndView.class;
        if(isModelAndView){
            return (CoCoModelAndView) result;
        }
        return null;
    }

    private Object caseStringValue(String value, Class<?> paramsType) {
        if(String.class == paramsType){
            return value;
        }
        if(Integer.class == paramsType){
            return Integer.valueOf(value);
        }
        else if(Double.class == paramsType){
            return Double.valueOf(value);
        }else {
            return value;
        }
        //如果还有double或者其他类型，继续加if
        //这时候，我们应该想到策略模式了
        //在这里暂时不实现，后期完善
    }

}