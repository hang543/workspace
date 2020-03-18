package invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/3/7
 * @Content:
 **/
public class Test {
    class GrandFather{
        void thinking(){
            System.out.println("我是爷爷");
        }
    }
    class Father extends GrandFather{
       @Override
       void thinking(){
           System.out.println("我是爸爸");
       }
    }
    class Son extends Father{
        @Override
        void thinking(){
            try {
                MethodType my= MethodType.methodType(void.class);
                Field lookupImpl= MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle methodHandle=((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class,"thinking",my,GrandFather.class);
                methodHandle.invoke(this);

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        (new Test().new Son()).thinking();
    }
}
