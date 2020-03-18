package juc;
@FunctionalInterface
interface  Foo{
//    public void  sayHello();
    public  int add(int i,int j);
    default int mul(int x,int y){
        return x*y;
    }
    public static  int div(int x,int y){
        return  x/y;
    }
}
/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/21
 * @Content:
 * 1.函数式编程
 * lamata 口诀
 * 1.1拷贝中括号，写死右箭头，落地大括号
 * 1.2@FunctionalInterface
 * 1.3deafult
 * 1.4static
 **/
public class demo2 {
    public static void main(String[] args) {
//        Foo foo=new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("****hello word");
//            }
//        };
//        foo.sayHello();
//        Foo foo=()->{ System.out.println("****hello  lambda express"); };
//        foo.sayHello();
        Foo foo= (int i,int j) -> {
            System.out.println("hello  come");
            return i+j;
        };
        System.out.println(foo.add(3,5));
        System.out.println(foo.mul(8,9));
        System.out.println(Foo.div(9,10));
    }
}
