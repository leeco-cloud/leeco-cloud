package com.coco.cloud.spring.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CoCoAutowired {
    String value() default "";
}
