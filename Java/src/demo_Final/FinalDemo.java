package demo_Final;

/**
 * final关键字: 不可变的
 * 常见四种方法:
 * 1. 修饰一个类
 *      这个类不能被继承
 * 2. 修饰一个方法
 *      这个类的子类不能重写该方法
 * 3. 修饰一个局部变量
 *      a. 变量值一旦赋值不能改变
 *      b. 对于基本类型来说, 不可变的是变量中的数据
 *      c. 对于引用类型来说, 不可变的是变量中的地址值
 * 4. 修饰一个成员变量(LastTestClass)
 *      a. 成员变量会有默认值, 使用final后必须手动赋值
 *      b. 对于final的成员变量, 要么直接赋值; 要么使用构造方法赋值
 *      c. 若使用构造方法赋值, 必须保证类中所有的构造方法, 最终都对final的成员变量进行赋值
 *
 *
 */

public class FinalDemo {

    public static void main(String[] args) {

        TriTestClass test1 = new TriTestClass("talor");
        System.out.println(test1.getName());     // talor

        test1 = new TriTestClass("swif");
        System.out.println(test1.getName());     // swif

        System.out.println("=========================");

        final TriTestClass test2 = new TriTestClass("lilei");
        System.out.println(test2.getName());    // lilei

//        test2 = new TestClass("hanmeimei"); // 错误, final的引用类型变量, 地址值不可修改
        test2.setName("hanmeimei");           // 正确, 可以修改引用类型变量的内容
        System.out.println(test2.getName());  // hanmeimei


    }

}
