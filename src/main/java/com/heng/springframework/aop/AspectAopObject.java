package com.heng.springframework.aop;

import com.heng.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.heng.springframework.aop.fremework.AspectAdviceInterceptor;
import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Around;

import java.lang.reflect.Method;

public class AspectAopObject implements AopObject{

    private final Object aspectObject;

    private PointcutAdvisor pointcutAdvisor;

    public AspectAopObject(Object aspectObject){
        this.aspectObject = aspectObject;
        initPointcutAdvisor();
    }

    @Override
    public Advice getAdvice() {
        return pointcutAdvisor.getAdvice();
    }

    @Override
    public Object getAspectObject() {
        return aspectObject;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcutAdvisor.getPointcut();
    }

    private void initPointcutAdvisor(){
        Class<?> clazz = aspectObject.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            boolean accessible = method.isAccessible();
            method.setAccessible(true);
            if (method.isAnnotationPresent(Around.class)){
                Around around = method.getAnnotation(Around.class);
                String express = around.value();
                AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
                pointcutAdvisor.setAdvice(new AspectAdviceInterceptor(this));
                pointcutAdvisor.setExpression(express);
                this.pointcutAdvisor = pointcutAdvisor;
                method.setAccessible(accessible);
                break;
            }
        }
    }
}
