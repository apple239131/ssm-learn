package proxy.jdk;
/**
 * jdk动态代理
 */

import java.lang.reflect.Method;

public interface Interceptor {
    /**
     *
     * @param proxy 代理对象
     * @param target 真是对象
     * @param method 方法
     * @param args 运行方法参数
     * @return 在真实对象前调用，返回true则反射真实对象的方法，false则调用around方法
     */
    public boolean before(Object proxy, Object target, Method method,Object[] args);

    public void around(Object proxy,Object target,Method method,Object[] args);

    /**
     * 反射真实对象方法或around方法执行后调用after方法
     * @param proxy
     * @param target
     * @param method
     * @param args
     */
    public void after(Object proxy,Object target,Method method,Object[] args);

}
