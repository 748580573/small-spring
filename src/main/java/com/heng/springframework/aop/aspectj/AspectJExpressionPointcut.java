package com.heng.springframework.aop.aspectj;

import com.heng.springframework.aop.ClassFilter;
import com.heng.springframework.aop.MethodMatcher;
import com.heng.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    /**
     * 存储切点表达式的原语，execution，@target，@annotation等等。
     * @Around("@annotation(com.heng.log.LogProperty)")，懂的都懂
     */
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    //表示一个 AspectJ 切入点表达式，并提供方便的方法来确定切入点是否匹配根据 java.lang.reflect 接口指定的连接点。
    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression){
        //返回一个切入点解析器，它可以解析从 AspectJ 支持的切入点原语的用户定义子集构建的切入点表达式。 以下限制适用
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES,this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    // 匹配目标类是否符合切点表达式
    @Override
    public boolean matches(Class<?> clazz) {
        //确定此切入点是否可以匹配给定类中的连接点
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    /**
     * 匹配目标类的某个方法是否符合切点表达式
     * @param method
     * @param targetClass 改参数仅用作理解用。没有实际被  使用
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
