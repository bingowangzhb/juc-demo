package com.bgw.proxy;

/**
 * desc：
 *
 * @author wangzhb 2019/8/12 18:12
 */
public class JdkProxyTestCase {
    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy(new Hello());
        IHello proxyService = jdkProxy.getProxy();
        proxyService.sayHello("bwg");
    }
}
