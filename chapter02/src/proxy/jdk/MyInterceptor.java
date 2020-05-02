package proxy.jdk;

import java.lang.reflect.Method;

public class MyInterceptor implements Interceptor {
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("反射方法前逻辑");
        //不反射方法
        return false;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("取代被代理对象的方法");

    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("反射后方法逻辑");

    }
}
