package com.heng.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 自动装配时，spring可根据该注解提供的value选择对应的bean进行注入
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
