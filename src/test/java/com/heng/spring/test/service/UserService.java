package com.heng.spring.test.service;

import com.heng.springframework.beans.factory.*;
import com.heng.springframework.beans.factory.annotation.Value;
import com.heng.springframework.stereotype.Component;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@Component
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware,Say {

    private String uId;

    private UserDao userDao;

    private String company;

    @Value("${name}")
    private String name;

    public String queryUserInfo() {
        return userDao.queryUserName(uId);
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void destory() throws Exception {
        System.out.println("执行销毁方法......");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("beanName为：" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("回去到beanFactory");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行初始化方法");
    }

    @Override
    public void say() {
        System.out.println("hello,world");
    }
}
