package com.heng.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 用于占位符使用的注释，spring会根据value提供的值去解析占位符，并将值注入到字段中去。
 */

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    String value();
}
