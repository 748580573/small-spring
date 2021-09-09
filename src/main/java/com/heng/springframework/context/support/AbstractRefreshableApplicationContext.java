package com.heng.springframework.context.support;

import com.heng.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.heng.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 该类负责初始化beanFactory以及刷新beanFactory
 *
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private ConfigurableListableBeanFactory beanFactory;

    /**
     * 刷新beanFactory，创建beanFactory并且加载beaenDefinition
     */
    @Override
    protected void refreshBeanFactory() {
        ConfigurableListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinition(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * loadBeanDefinition
     * @param beanFactory
     */
    protected abstract void loadBeanDefinition(ConfigurableListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }


    protected ConfigurableListableBeanFactory getBeanFactory(){
        return this.beanFactory;
    }
}
