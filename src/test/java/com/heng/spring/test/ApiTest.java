package com.heng.spring.test;


import com.heng.spring.test.aop.MyServiceInterceptor;
import com.heng.spring.test.service.MyEvent;
import com.heng.spring.test.service.MyProxy;
import com.heng.spring.test.service.Say;
import com.heng.spring.test.service.UserService;
import com.heng.springframework.aop.AdvisedSupport;
import com.heng.springframework.aop.TargetSource;
import com.heng.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.heng.springframework.aop.fremework.Cglib2AopProxy;
import com.heng.springframework.aop.fremework.JdkDynamicAopProxy;
import com.heng.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.heng.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.heng.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

public class ApiTest {

    @Test
    public void test_BeanFactory(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService",UserService.class);
        MyProxy myProxy = applicationContext.getBean("myProxy",MyProxy.class);
        HashMap<String,Object> map = (HashMap<String, Object>) myProxy.getProxy();
        map.keySet().forEach(key -> System.out.println(key));
        System.out.println(userService.getCompany());
        MyEvent myEvent = new MyEvent(new Object(),1l,"hello,world");
        applicationContext.publishEvent(myEvent);
        System.out.println(userService.getName());

    }

    @Test
    public void test_component(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:scan-conponent.xml");
        UserService userService = applicationContext.getBean("userService",UserService.class);
        System.out.println(userService.getName());
        System.out.println(userService.getUserDao().queryUserName("10002"));
    }

    @Test
    public void aop_test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Say userService = applicationContext.getBean("userService",UserService.class);
        userService.say();
    }

    @Test
    public void dynamic_test(){
        UserService userService = new UserService();

        //组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new MyServiceInterceptor());

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.heng.spring..*.*(..))");
        advisedSupport.setMethodMatcher(pointcut);

        Say jdk_dynamic_object = (Say) new JdkDynamicAopProxy(advisedSupport).getProxy();
        jdk_dynamic_object.say();

        Say cglib_dynamic_objcet = (Say) new Cglib2AopProxy(advisedSupport).getProxy();
        cglib_dynamic_objcet.say();
    }
}
