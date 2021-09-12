package com.heng.springframework.aop.fremework;

import com.heng.springframework.aop.AdvisedSupport;

public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        AopProxy aopProxy = createAopProxy();
        return aopProxy.getProxy();
    }

    private AopProxy createAopProxy(){

        if (advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
