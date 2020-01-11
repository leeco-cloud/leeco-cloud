package com.coco.cloud.spring.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CoCoRequestParam {
    String value() default "";
}
