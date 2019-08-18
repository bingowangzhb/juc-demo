package com.bgw.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * desc：
 *
 * @author wangzhb 2019/8/12 18:25
 */
public class CglibProxy implements MethodInterceptor {

    private static CglibProxy instance = new CglibProxy();

    // 单例
    private CglibProxy() {

    }

    public static CglibProxy getInstance() {
        return instance;
    }

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(obj, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("---------------before----------------");
    }

    private void after() {
        System.out.println("---------------after----------------");
    }
}
