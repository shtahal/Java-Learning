package demo_ObjectAndObjects;

import java.util.Objects;

/**
 * Object类是所有类的父类
 * 1. toString()方法
 *      重写前: 输出 包名类名@地址值
 *      重写后: 输出 对象的属性值
 * 2. equals()方法
 *      重写前: 比较对象的地址值
 *      重写后: 比较对象中的属性值
 */

public class Demo extends Object {

    public static void main(String[] args) {

        String str1 = "abc";
        String str2 = "abc";
        System.out.println(str1/*.toString()*/); // String重写了Object的toString方法
        System.out.println(str1.equals(str2));   // String重写了Object的equals方法
        System.out.println("========================");

        TestClass demo1 = new TestClass("Lilei", 23);
        System.out.println(demo1/*.toString()*/);   // 重写前: demo_ObjectAndObjects.TestClass@1b6d3586
        System.out.println(demo1/*.toString()*/);   // 重写后: TestClass{name='Lilei', age=23}
        System.out.println("========================");

        TestClass demo2 = new TestClass("Lilei", 23);
        System.out.println(demo1.equals(demo2));    // 重写前: false
        System.out.println(demo1.equals(demo2));    // 重写后: ture

        System.out.println("========================");
        String tmp = "aaa";
        String tmp1 = null;
        System.out.println(tmp1.equals(tmp));   // false, NullPointerException

        boolean equals = Objects.equals(tmp, tmp1);
        System.out.println(equals);             // true

    }

}
