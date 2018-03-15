package com.summer.beans.weave;

import com.summer.aop.aspect.Aspect;
import com.summer.aop.pointcut.DefaultPointcut;
import com.summer.aop.proxy.ProxyFactory;
import com.summer.beans.beanpost.BeanPostProcessor;
import com.summer.beans.exception.BeanException;
import com.summer.beans.factory.BeanFactory;
import com.summer.beans.factory.BeanFactoryAware;
import com.summer.beans.factory.DefaultListableFactory;

import java.util.List;

/**
 * weave the aop into ioc
 * @author zys
 * @date 2018/03/10
 */
public class AopWeaveBean implements BeanPostProcessor {

    private List<Aspect> aspects;

    public AopWeaveBean () {
    }

    public AopWeaveBean (List<Aspect> aspects) {

        this.aspects = aspects;
    }

    public void setAspects(List<Aspect> aspects) {
        this.aspects = aspects;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {

        Object returnObj = bean;

        for (Aspect aspect : aspects) {

            DefaultPointcut defaultAspect = (DefaultPointcut) aspect.getPointcut();
            String className = bean.getClass().getName();

            if (defaultAspect.matchClass(className)) {

                returnObj = ProxyFactory.createProxy(returnObj, aspect).getProxyObj();
            }
        }
        return returnObj;
    }

}
