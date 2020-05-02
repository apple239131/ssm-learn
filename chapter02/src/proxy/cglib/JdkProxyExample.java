package proxy.cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyExample implements InvocationHandler {
    //真实对象
    private Object target=null;

    /**
     *建立代理对象和真实对象的代理关系，并返回代理对象
     *
     *
     *
     *方法中使用类的属性target保存真实的对象，然后通过newProxyInstance（）方法建立并生成代理对象
     * 其参数分别为：
     * 1.类加载器
     * 2.生成的动态代理下挂到哪些接口下，本例为HelloWord
     * 3.定义实现方法逻辑的代理类，this表示当前对象，它必须实现InvocationHandler接口的invoke方法，它就是代理逻辑方法的现实方法
     * @param target 真实对象
     * @return 代理对象
     */
    public Object bind(Object target) {
        this.target=target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    /**
     * 实现代理方法逻辑
     *
     * @param proxy 代理对象 bind方法生成的对象
     * @param method 当前调度方法
     * @param args 调度方法参数
     * @return
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法");
        System.out.println("在调度真实对象之前的服务");
        Object obj=method.invoke(target,args); //相当于调用sayHelloWorld方法
        return obj;
    }
}
