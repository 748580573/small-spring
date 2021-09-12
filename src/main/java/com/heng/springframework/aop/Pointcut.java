package com.heng.springframework.aop;

/**
 * Pointcut表示一个切点
 */
public interface Pointcut {

    /**
     * 返回该切面对应的切点表达式过滤器
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 返回方法匹配器，用于对方法进行匹配用。
     * @return
     */
    MethodMatcher getMethodMatcher();
}
