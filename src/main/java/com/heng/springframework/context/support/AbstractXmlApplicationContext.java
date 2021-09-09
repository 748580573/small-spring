package com.heng.springframework.context.support;

import com.heng.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.heng.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinition(ConfigurableListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
