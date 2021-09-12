package com.heng.springframework.aop;

public class TargetSource {

    private final Object target;

    public TargetSource(Object target){
        this.target = target;
    }

    public Class<?>[] getTargetClass(){
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
