package demo_Lambda;

/*
    Lambda表达式

    前提
        若使用接口, 接口中有且只有一个抽象方法

    两种方式
        1. 无参数无返回值
        2. 有参数有返回值

    省略规则
        1. 小括号内参数的类型可省略
        2. 小括号只有一个参数时, 小括号可省略
        3. 大括号只有一个语句时, 无论是否有返回值, 可省略大括号, return关键字和最后的分号

 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LambdaDemo {

    public static void main(String[] args) {

        /*
            1. 无参数无返回值的Lambda表达式
            2. 有参数有返回值的Lambda表达式
            3. 有参数有返回值的Lambda表达式
         */

//        demo1();    // 1.
//        demo2();    // 2.
//        demo3();    // 3.
        demo4();    // 4.

    }

    private static void demo4() {

    }

    private static void demo3() {
        // 方法一: 常规写法
        /*
        invokeCal(10, 20, new Calculator() {
            @Override
            public int cal(int a, int b) {
                return a + b;
            }
        });
         */

        // 方法二: Lambda表达式
        /*
        invokeCal(10, 20, (int a, int b) -> {
            return a + b;
        });
         */

        // 方法三: Lambda表达式的简化
        invokeCal(10, 20, (a, b) -> a + b);

    }
    private static void invokeCal(int a, int b, Calculator c) {
        int sum = c.cal(a, b);
        System.out.println(sum);
    }

    // 按照年龄将类对象数组按照升序进行排序
    private static void demo2() {
        Person[] arr = {
                new Person("Lisa", 18),
                new Person("Linda", 32),
                new Person("Jerry", 25)
        };

        // 方法一: 常规写法
        /*
        Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
         */

        // 方法二: Lambda表达式
        /*
        Arrays.sort(arr, (Person o1, Person o2) -> {
            return o1.getAge() - o2.getAge();
        });
         */

        // 方法三: Lambda表达式的简化
        Arrays.sort(arr, (o1, o2) -> o1.getAge() - o2.getAge());

        for (Person p: arr) {
            System.out.println(p);
        }

    }

    private static void demo1() {
        // 方法一: 常规写法
        /*
        invokePrintSome(new Print() {
            @Override
            public void PrintSome() {
                System.out.println("实现自定义Print接口的PrintSome方法.");
            }
        });
         */

        // 方法二: Lambda表达式
        /*
        invokePrintSome(()->{
            System.out.println("实现自定义Print接口的PrintSome方法.");
        });
         */

        // 方法三: Lambda表达式的简化
        invokePrintSome(() -> System.out.println("实现自定义Print接口的PrintSome方法."));
    }
    private static void invokePrintSome(Print p) {
        p.PrintSome();
    }



}
