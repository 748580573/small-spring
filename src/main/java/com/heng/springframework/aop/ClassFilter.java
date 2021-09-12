package com.heng.springframework.aop;

/**
 * aop代理匹配器，匹配符合规则的类，并根据规则对响应的方法进行代理
 */
public interface ClassFilter {

    /**
     * 用于判断clazz是否符合切点表达式的规则
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);

}
