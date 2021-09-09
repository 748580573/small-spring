package com.heng.spring.test;


import com.heng.spring.test.service.UserService;
import com.heng.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.heng.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.heng.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService",UserService.class);
        System.out.println(userService.getCompany());

    }
}
