package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) {

    ReflectTest test=new ReflectTest();
    ReflectServiceImpl reflectService=test.reflect();
    reflectService.sayHello("aaaaaaaa");
    ReflectServiceImpl2 serviceImpl2=test.getInstance();
    serviceImpl2.sayHello();
    test.reflecTest();

    }

    //无参
    public ReflectServiceImpl reflect()  {
        ReflectServiceImpl object=null;
        try {
            object= (ReflectServiceImpl) Class.forName("reflect.ReflectServiceImpl").newInstance();
        } catch (ClassNotFoundException| InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return object;
    }

    //含参
    //通过反射生成带有参数的构造方法
    public ReflectServiceImpl2 getInstance() {
        ReflectServiceImpl2 retObject=null;
        //相当于object=new eflectServiceImpl2("apple")
        try{
            retObject=(ReflectServiceImpl2)Class.forName("reflect.ReflectServiceImpl2").getConstructor(String.class)
                    .newInstance("apple");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return retObject;
    }

    //反射方法
    public Object reflectMethod() {
        ReflectServiceImpl target=new ReflectServiceImpl();
        Object object=null;
        try{
            Method method=ReflectServiceImpl.class.getMethod("sayHello", String.class);
            object=method.invoke(target,"apple",333);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }


    //反射生成对象和反射调度方法
    public Object reflecTest() {
        ReflectServiceImpl reflectService=null;
        try {
            reflectService = (ReflectServiceImpl) Class.forName("reflect.ReflectServiceImpl").newInstance();
            Method method = reflectService.getClass().getMethod("sayHello", String.class);
            method.invoke(reflectService, "apple");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reflectService;
    }
}
