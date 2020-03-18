package listner;

import org.omg.CORBA.ObjectHolder;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/3/2
 * @Content:
 **/
public class JHSDB_TestCase {
    static class Test{
        static ObjectHolder objectHolder=new ObjectHolder();
        ObjectHolder instanceObj=new ObjectHolder();
        void foo(){
            ObjectHolder localObj= new ObjectHolder();
            System.out.println("done");
        }
        private static class ObjectHolder{}

        public static void main(String[] args) {
            Test test=new Test();
            test.foo();
        }
    }
}
