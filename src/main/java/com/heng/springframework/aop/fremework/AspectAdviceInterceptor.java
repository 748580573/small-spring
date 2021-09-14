package com.heng.springframework.aop.fremework;

import com.heng.springframework.aop.AopObject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Around;

import java.lang.reflect.Method;

public class AspectAdviceInterceptor implements MethodInterceptor {

    private AopObject aopObject;

    public AspectAdviceInterceptor(AopObject aopObject){
        this.aopObject = aopObject;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method aroundMethod = getAroundMethod();
        return aroundMethod.invoke(aopObject.getAspectObject(),invocation);
    }

    private Method getAroundMethod(){
        Class<?> clazz = aopObject.getAspectObject().getClass();
        for (Method method : clazz.getMethods()){
            boolean accessible = method.isAccessible();
            method.setAccessible(true);
            if (method.isAnnotationPresent(Around.class)){
                return method;
            }
        }
        return null;
    }
}
