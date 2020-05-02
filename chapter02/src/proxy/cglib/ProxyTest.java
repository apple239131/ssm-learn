package proxy.cglib;

public class ProxyTest {
    public static void main(String[] args) {
        testJdkProxy();
    }

    public static void testJdkProxy() {
        JdkProxyExample jdk=new JdkProxyExample();
        //绑定关系，因为挂在接口HelloWorld,所以声明代理对象HelloWorld proxy
        HelloWord proxy=(HelloWord)jdk.bind(new HelloWordImpl());
        // 注意，此时HelloWorld对象已经是一个代理对象，它会进入代理的逻辑方法invoke里
        proxy.sayHello();
    }
}
