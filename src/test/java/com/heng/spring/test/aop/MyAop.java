package com.heng.spring.test.aop;

import com.heng.springframework.stereotype.Component;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class MyAop {

    @Around("execution(* com.heng.spring.test.service.Say.*(..))")
    public Object pointcut(MethodInvocation invocation) throws Throwable {
        System.out.println("楷楷你好啊");
        Object proceed = invocation.proceed();
        System.out.println("楷楷再见。 ");
        return proceed;
    }
}
