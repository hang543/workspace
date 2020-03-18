package Dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/3/8
 * @Content:
 **/
public class DynamicProxyTest {
    interface  IHello{
        void sayHello();
    }
    static class Hello implements IHello{
        @Override
        public void sayHello() {
            System.out.println("hello word");
        }
    }
    static class DynamicProxy implements InvocationHandler{
        Object originalObj;
        Object bind(Object originalObj){
            this.originalObj=originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),originalObj.getClass().getInterfaces(),this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Welcome");
            return method.invoke(originalObj,args);
        }
    }

    public static void main(String[] args) {
        IHello iHello= (IHello) new DynamicProxy().bind(new Hello());
        iHello.sayHello();
    }
}
