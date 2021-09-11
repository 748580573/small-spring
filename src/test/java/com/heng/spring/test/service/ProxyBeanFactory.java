package com.heng.spring.test.service;

import com.heng.springframework.beans.factory.FactoryBean;

import java.util.HashMap;

public class ProxyBeanFactory implements FactoryBean<MyProxy> {
    @Override
    public MyProxy getObject() throws Exception {
        return new MyProxy() {
            @Override
            public Object getProxy() {
                return new HashMap<String,Object>(){
                    {
                        this.put("代理的key","代理的value");
                    }
                };
            }
        };
    }

    @Override
    public Class<?> getObjectType() {
        return MyProxy.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
