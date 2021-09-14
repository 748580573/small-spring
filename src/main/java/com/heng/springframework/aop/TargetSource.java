package com.heng.springframework.aop;

import com.heng.springframework.util.ClassUtils;

public class TargetSource {

    private final Object target;

    public TargetSource(Object target){
        this.target = target;
    }

    public Class<?>[] getTargetClass(){
        if (ClassUtils.isCglibProxyClass(target.getClass())){
            return this.target.getClass().getSuperclass().getInterfaces();
        }
        return this.target.getClass().getInterfaces();
    }

    /**
     * 返回一个目标实例
     * @return
     */
    public Object getTarget(){
        return this.target;
    }
}
