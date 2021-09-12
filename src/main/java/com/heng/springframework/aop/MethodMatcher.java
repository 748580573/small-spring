package com.heng.springframework.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

    /**
     * 检查方法是否符合切点表达式
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);
}
