package proxy.jdk;

import proxy.cglib.HelloWord;
import proxy.cglib.HelloWordImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 1.在bind()方法中用jdk动态代理绑定了一个对象，然后返回代理对象
 * 2.没设置拦截器，直接反射真实对象的方法，结束，否则到3
 * 3.通过反射生成拦截器，并准备使用
 * 4.调用拦截器的before方法，返回为true时反射原来的方法，否则运行拦截器的around方法
 * 5.调用拦截器的after方法
 * 6.返回结果
 * @author Administrator
 */
public class InterceptorJdkProxy implements InvocationHandler {
    //真实对象
    private Object target;
    //拦截器全限定名
    private String interceptorClass=null;

    public InterceptorJdkProxy(Object target, String interceptorClass) {
        this.target = target;
        this.interceptorClass = interceptorClass;
    }

    /**
     * 绑定委托对象并返回一个独占代理
     *
     * @param target 真实对象
     * @param interceptorClass
     * @return 代理对象【占位】
     */
    public static Object bind(Object target,String interceptorClass){
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),new InterceptorJdkProxy(target,interceptorClass));
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 方法，被调用对象
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (interceptorClass == null) {
            //没有设置拦截器则直接反射原有方法
            return method.invoke(target,args);
        }
        Object result=null;
        //通过反射生产拦截器
        Interceptor interceptor =(Interceptor)Class.forName(interceptorClass).newInstance();
        //调用前置方法
        if (interceptor.before(proxy, target, method,args)) {
            //反射原有对象方法
            result=method.invoke(target,args);
        }else{
            interceptor.around(proxy,target,method,args);
        }
        //调用后置方法
        interceptor.after(proxy,target,method,args);
        return result;
    }

    public static void main(String[] args) {
        HelloWord proxy=(HelloWord) InterceptorJdkProxy.bind(new HelloWordImpl(),
                "proxy.jdk.MyInterceptor");
        proxy.sayHello();
        //反射方法前逻辑
        //取代被代理对象的方法
        //反射后方法逻辑

        //责任链测试
        HelloWord proxy1=(HelloWord) InterceptorJdkProxy.bind(new HelloWordImpl(),
                "proxy.jdk.Interceptor1");
        HelloWord proxy2=(HelloWord) InterceptorJdkProxy.bind(proxy1,
                "proxy.jdk.Interceptor2");
        HelloWord proxy3=(HelloWord) InterceptorJdkProxy.bind(proxy2,
                "proxy.jdk.Interceptor3");
        proxy3.sayHello();
        //【拦截器3】的before方法
        //【拦截器2】的before方法
        //【拦截器1】的before方法
        //hello word
        //【拦截器1】的after方法
        //【拦截器2】的after方法
        //【拦截器3】的after方法
    }
}
