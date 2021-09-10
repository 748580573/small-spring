package com.heng.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.beans.factory.DisposableBean;
import com.heng.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destoryMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition){
        this.bean = bean;
        this.beanName = beanName;
        this.destoryMethodName = beanDefinition.getDestoryMethodName();
    }

    @Override
    public void destory() throws Exception {
        // 1. 实现接口 DisposableBean
        if (bean instanceof DisposableBean){
            ((DisposableBean)bean).destory();
        }

        // 2. 配置信息 destroy-method {判断是为了避免二次执行销毁}
        if (StrUtil.isNotEmpty(destoryMethodName) && (!(bean instanceof DisposableBean && !"destory".equals(destoryMethodName)))){
            Method method = bean.getClass().getMethod(destoryMethodName);
            if (method == null){
                throw new BeansException("Could not find an destory method named '" + destoryMethodName + "' on bean with name '" + beanName + "'");
            }
            method.invoke(bean);
        }
    }
}
