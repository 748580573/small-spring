package com.heng.spring.test;


import com.heng.spring.test.service.UserService;
import com.heng.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.heng.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) factory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
