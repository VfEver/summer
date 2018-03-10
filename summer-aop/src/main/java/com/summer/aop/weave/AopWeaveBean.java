package com.summer.aop.weave;

import com.summer.aop.advice.DefaultAdvice;
import com.summer.aop.aspect.Aspect;
import com.summer.aop.aspect.DefaultAspect;
import com.summer.aop.pointcut.DefaultPoincut;
import com.summer.aop.proxy.Proxy;
import com.summer.aop.proxy.ProxyFactory;
import com.summer.beans.beanpost.BeanPostProcessor;
import com.summer.beans.exception.BeanException;
import com.summer.beans.factory.BeanFactory;
import com.summer.beans.factory.BeanFactoryAware;

import java.util.List;

/**
 * weave the aop into ioc
 * @author zys
 * @date 2018/03/10
 */
public class AopWeaveBean implements BeanFactoryAware, BeanPostProcessor {

    private BeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {

        Object returnObj = bean;

        List<Aspect> aspects = getAspects();
        for (Aspect aspect : aspects) {

            DefaultPoincut defaultAspect = (DefaultPoincut) aspect.getPointcut();
            String className = bean.getClass().getName();

            if (defaultAspect.matchClass(className)) {

                DefaultAdvice defaultAdvice = (DefaultAdvice) aspect.getAdvice();
                returnObj = ProxyFactory.createProxy(returnObj, aspect).getProxyObj();

            }
        }
        return returnObj;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {

        this.beanFactory = beanFactory;
    }

    private List<Aspect> getAspects () {


        return null;
    }
}
