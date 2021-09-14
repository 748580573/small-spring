package com.heng.springframework.aop;


public interface AopObject extends PointcutAdvisor{

    Object getAspectObject();
}
