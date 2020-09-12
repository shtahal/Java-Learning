package demo_Collections;

/*
    java.util.Collections   针对集合进行操作的工具类
        1. sort方法
        sort(List<T> list)
            使用前提: 被排序集合中存储的数据元素, 必须实现Comparable接口, 重写compareTo()中定义的排序规则
        sort(List<T>, Comparator<? super T>)
            使用前提: 自己实现
        
        2. Comparable 和 Comparator 区别
            Comparable: 自己(this)和别人(传入参数)比较; 自己需要实现Comparable接口, 重写compareTo()中定义的排序规则
            Comparator: 直接实现接口 Comparator 的 compare() 方法

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CollectionsDemo {

    public static void main(String[] args) {

        demo1();    // sort(List<T> list), 继承 Comparable 接口, 重写 compareTo() 方法中的排序规则
        demo2();    // sort(List<T>, Comparator<? super T>), 在方法内直接实现 Comparator接口的compare()方法

    }

    private static void demo2() {
        // 基本类型数据排序
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(6);
        list1.add(1);
        list1.add(3);
        System.out.println(list1);
        Collections.sort(list1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; // 降序
            }
        });
        System.out.println(list1);
        System.out.println("===================================");

        // 对象类型数据排序
        ArrayList<ComparatorDemo> list2 = new ArrayList<>();
        list2.add(new ComparatorDemo("lisa", 25));
        list2.add(new ComparatorDemo("sam", 18));
        list2.add(new ComparatorDemo("jerry", 20));
        list2.add(new ComparatorDemo("lisa", 18));
        System.out.println(list2);
        Collections.sort(list2, new Comparator<ComparatorDemo>() {
            @Override
            public int compare(ComparatorDemo o1, ComparatorDemo o2) {
                int res = o1.getAge() - o2.getAge();    // 按年龄大小排序
                if (res == 0) {                         // 若年龄相同, 则按姓名顺序排序
                    res = o1.getName().compareTo(o2.getName());
                }
                return res;
            }
        });
        System.out.println(list2);
    }

    private static void demo1() {
        ComparableDemo tc1 = new ComparableDemo(25, "lisa");
        ComparableDemo tc2 = new ComparableDemo(17, "sam");
        ComparableDemo tc3 = new ComparableDemo(22, "linda");

        ArrayList<ComparableDemo> list = new ArrayList<>();
        list.add(tc1);
        list.add(tc2);
        list.add(tc3);
        System.out.println(list);

        Collections.sort(list); // 重写了Comparable接口的compareTo方法, 定义新的排序规则

        System.out.println(list);
    }

}
