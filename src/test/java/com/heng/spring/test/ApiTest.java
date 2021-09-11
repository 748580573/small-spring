package com.heng.spring.test;


import com.heng.spring.test.service.MyProxy;
import com.heng.spring.test.service.UserService;
import com.heng.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.heng.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.heng.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

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

    }
}
