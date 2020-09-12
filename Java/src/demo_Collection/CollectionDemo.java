package demo_Collection;

/*
    集合 java.util.Collection \ java.util.Iterator \ java.util.Map

    1. 集合和数组的区别
        a. 数组长度是固定的, 集合长度是可变的
        b. 数组存储同一类型的基本数据类型值; 集合存储的是对象, 对象类型可以不一致

    2. 集合框架-Collection接口
        a. Collection接口
                |                                   1) 定义所有集合中共性的方法
                |                                   2) 底层是接口或抽象类, 无法直接创建对象, 需要使用创建子类对象使用
                |-  List接口
                        |                           1) 有序
                        |                           2) 允许重复元素
                        |                           3) 有索引, 可使用for循环遍历
                        |-  ArrayList集合
                        |                           底层数组实现, 查询快, 增删慢
                        |-  LinkedList集合
                        |                           底层链表实现, 查询慢, 增删快
                        |-  Vector集合
                |-  Set接口
                        |                           1) 不允许重复元素
                        |                           2) 无索引, 不能使用普通for循环遍历
                        |-  HashSet集合
                        |                           底层哈希表实现, 存取无序; hashCode()和equals()方法保证数据元素的唯一性
                                |-LinkedHashSet集合
                                |                   底层 哈希表+链表 实现, 存取有序
                        |-  TreeSet集合
                        |                           底层 红黑树 实现
        b. 常用方法
            boolean add(E e):    向集合中添加元素
            boolean remove(E e)
            void clear()
            boolean contains(E e)
            boolean isEmpty()
            int size()
            Object[] toArray():   将集合转为数组

    3. 集合框架-Iterator接口
        a. Iterator迭代器
            1) 迭代遍历Collection集合中的元素, 遍历前要先判断集合中是否有元素
            2) 适合无索引集合的遍历
            3) Iterator是一个接口, 无法直接使用;
               使用Collection接口中提供的 Iterator<E> iterator()方法, 返回Iterator接口的实现类对象
        b. 常用方法
            public E next():            返回迭代的下一个元素
            public boolean hasNext():   判断集合中是否有元素, 若有元素, 返回true
        c. 增强for循环
            1) 底层仍然是Iterator迭代器, 遍历目标只能说集合或数组
            2) for (数据类型 变量名 : 集合/数组) {...}

    4. 集合框架-Map接口
        a. 特点
            1) Map集合是一个双列集合, 一个元素包含两个值(key, value)
            2) Map集合中key和value的数据类型可相同, 可不同
            3) Map集合中key不允许重复, value可重复, 并且key和value可为null值
            4) Map集合中key和value是一一对应的
        b. Map接口
              |-  HashMap
                    |                   1) 底层 哈希表 实现, 存取无序, 多线程, 线程不安全
                    |                   2) jdk1.8之前: 数组 + 单向链表
                    |                   3) jdk1.8之后: 数组 + 单向链表 / 红黑树(长度超多8)
                    |-  LinkedHashMap
                    |                   1) 底层 哈希表+链表 实现, 存取有序
              |-  TreeMap
              |                         底层 红黑树 实现
              |-  HashTable
              |                         1) 底层是线程安全的 哈希表, 单线程, 速度慢, key和value值不可为null
              |                         2) 其子类 Properties集合是一个唯一和IO流有结合的集合
         c. 常用方法
            public V put(K key, V value):           添加键值对(key, value)
                若向map集合放入的key不是重复的,返回null; 反之, 使用key对应的新value值替换原来的value值, 并返回旧的value值
            public V get(Object key):               根据key返回value
            public V remove(Object key)
                通过key删除键值对, 若key存在返回删除对应的value值, 反之则返回null
            boolean containsKey(Object key)
            public Set<K> keySet():                 将Map集合中所有的Key存储到Set集合中, 然后用Set集合的方法遍历所有的key
            public Set<Map.Entry<K, V>> entrySet()  将Map集合中所有的key-value键值对存储到Set集合中, 然后遍历所有的key-value对

    5. 泛型
        a. 是一种未知的数据类型, 不知道使用什么数据类型时使用
        b. 1) 创建集合对象时不使用泛型
                ex. ArrayList list = new ArrayList()
                集合默认是Object类型, 可以存储任意类型的数据; 不安全, 会引发异常
           2) 创建集合对象时使用泛型
                ex. ArrayList<String> list = new ArrayList<>()
                集合只能存储指定泛型类型的数据; 但是避免了类型转换的麻烦
        c. 定义一个泛型类 GenericClass
           类似ArrayList, 可以直接使用指定的数据类型
        d. 定义一个泛型接口 GenericInterface
           1) 定义接口的实现类, 并指定接口的泛型类型
                 比如Scanner类实现了Iterator接口, 并指定接口泛型为String类型. 所以重写的next()方法的类型默认就为String
                 ex. public final class Scanner implements Iterator<String>
           2) 接口使用什么泛型, 实现类就使用什么泛型. 相当于定义一个含有泛型的类, 创建对象时再确定泛型的类型
                 比如ArrayList类实现了List接口, 并直接使用List接口的泛型. 所以使用时再手动给定泛型的类型
                 ex. public class ArrayList<E> implements List<E>
        e. 通配符
           1) 不知道使用什么类型接收数据时, 可以使用 ? 表示未知通配符
           2) 不同创建对象使用, 只能作为方法的参数使用. 此时只能接收数据, 不能往集合中存储数据
        d. 高级通配符 - 受限泛型
           1) 泛型的上限
                只能接收该类型及其子类型: 类型名称 <? extends 类> 对象名称
           2) 泛型的下限
                只能接收该类型及其父类型: 类型名称 <? super 类> 对象名称

 */

import java.util.*;

public class CollectionDemo {

    public static void main(String[] args) {

        /*
            1. Iterator迭代器
            2. 增强for循环
            3. 自定义一个泛型类并实现
            4. 自定义一个泛型接口并实现
            5. 通配符
            6. HashSet存储自定义类型数据
            7. 受限泛型
            8. Map常用方法
            9. Map存储自定义类型作为key
            10. 计算一个字符串中每个字符出现的次数
         */
//        Collection<String> col = demo1();    // 1.
//        demo2(col);                          // 2.
//        demo3();                             // 3.
//        demo4();                             // 4.
//        demo5();                             // 5.
//        demo6();                             // 6.
//        demo7();                             // 7.
//        demo8();                             // 8.
//        demo9();                             // 9.
        demo10();                             // 10.
    }

    private static void demo10() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
            } else {
                map.put(chars[i], 1);
            }
        }
        System.out.println(map);
    }

    private static void demo9() {
        /*
            String类型作为Map集合的key
            String已经重写了hashCode()方法和equals()方法, 保证了存入Map集合时key的唯一性
            使用keySet()方法遍历, 运行结果:
                DivMapClass{name='Hanmeimei', age=19}
                DivMapClass{name='Linda', age=26}
                DivMapClass{name='Tomas', age=22}
         */
        Map<String, DivMapClass> map1 = new HashMap<>();
        map1.put("China", new DivMapClass("Liming", 18));
        map1.put("America", new DivMapClass("Linda", 26));
        map1.put("Germany", new DivMapClass("Tomas", 22));
        map1.put("China", new DivMapClass("Hanmeimei", 19));

        Set<String> set1 = map1.keySet();
        for (String s : set1) {
            DivMapClass divMapClass = map1.get(s);
            System.out.println(divMapClass);
        }

        /*
            自定义DivMapClass类作为Map集合的key
            DivMapClass类需要自己重写hashCode()方法和equals()方法, 保证类作为key的唯一性
            重写前, 使用entrySet()的Iterator迭代器遍历, 运行结果:
                DivMapClass{name='Tomas', age=22}-Germany
                DivMapClass{name='Linda', age=26}-America
                DivMapClass{name='Liming', age=18}-China
                DivMapClass{name='Liming', age=18}-Japan
            重写后, 使用entrySet()的增强for循环遍历, 运行结果:
                DivMapClass{name='Tomas', age=22}-Germany
                DivMapClass{name='Liming', age=18}-Japan
                DivMapClass{name='Linda', age=26}-America
         */
        Map<DivMapClass, String> map2 = new HashMap<>();
        map2.put(new DivMapClass("Liming", 18), "China");
        map2.put(new DivMapClass("Linda", 26), "America");
        map2.put(new DivMapClass("Tomas", 22), "Germany");
        map2.put(new DivMapClass("Liming", 18), "Japan");

//        Iterator<Map.Entry<DivMapClass, String>> it = map2.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<DivMapClass, String> next = it.next();
//            DivMapClass key = next.getKey();
//            String value = next.getValue();
//            System.out.println(key + "-" + value);
//        }

        for (Map.Entry<DivMapClass, String> entry : map2.entrySet()) {
            DivMapClass key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "-" + value);
        }
    }

    private static void demo8() {
        Map<Integer, String> map = new HashMap<>();
        // public V put(K key, V value)
        String mp1 = map.put(1, "lisa");
        System.out.println(mp1);    // null
        String mp2 = map.put(1, "sam");
        System.out.println(mp2);    // lisa

        map.put(2, "jerry");
        map.put(3, "sam");  // key不能重复, 但是value可以重复

        // public Set<K> keySet()
        Set<Integer> set1 = map.keySet();    // [1,2,3]
        for (Integer i : set1) {             // 增强for循环遍历
            System.out.println(map.get(i));
        }

        // public Set<Map.Entry<K, V>> entrySet()
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();    // Iterator迭代器遍历
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "-" + value);
        }

    }

    private static void demo7() {
        Collection<Integer> list1 = new ArrayList<>();
        Collection<String> list2 = new ArrayList<>();
        Collection<Number> list3 = new ArrayList<>();   // Number是Integer的父类
        Collection<Object> list4 = new ArrayList<>();   // 所有类的父类

        getElement1(list1);
//        getElement1(list2); // Error
        getElement1(list3);
//        getElement1(list4); //Error

//        getElement2(list1); //Error
//        getElement2(list2); //Error
        getElement2(list3);
        getElement2(list4);
    }
    // 泛型的上限: 此时的泛型 ? 必须是Number类型或者Number的子类
    public static void getElement1(Collection<? extends Number> col){}
    // 泛型的上限: 此时的泛型 ? 必须是Number类型或者Number的父类
    public static void getElement2(Collection<? super Number> col){}


    private static void demo6() {
        HashSet<DivClass> set = new HashSet<>();
        DivClass dc1 = new DivClass(16, "lisa");
        DivClass dc2 = new DivClass(16, "lisa");
        DivClass dc3 = new DivClass(18, "sam");
        set.add(dc1);
        set.add(dc2);
        set.add(dc3);
        System.out.println(set);    // [DivClass{age=16, name='lisa'}, DivClass{age=18, name='sam'}]
    }

    private static void demo5() {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("alex");
        list2.add("fox");

        printList(list1);                         // 作为方法的参数使用
        printList(list2);

//        ArrayList<?> list = new ArrayList<?>(); // 不能定义使用
    }

    private static void printList(ArrayList<?> list) {
        Iterator ie = list.iterator();
        while (ie.hasNext()) {
            System.out.println(ie.next());
        }
    }

    private static void demo4() {
        // 实现方法1
        GenericInterfaceImpl1 gi1 = new GenericInterfaceImpl1();
        gi1.method("字符串类型");
        System.out.println("=============================");

        // 实现方法2
        GenericInterfaceImpl2<Integer> gi2 = new GenericInterfaceImpl2<>();
        gi2.method(5);
        GenericInterfaceImpl2<Double> gi3 = new GenericInterfaceImpl2<>();
        gi3.method(3.14);
    }

    private static void demo3() {
        GenericClass<Integer> egi = new GenericClass<>();    // 创建ExampleGeneric对象, 泛型使用Integer类型
        egi.setVariable(366);
        Integer i = egi.getVariable();
        System.out.println(i);                               // 366

        GenericClass<String> egs = new GenericClass<>();     // 创建ExampleGeneric对象, 泛型使用String类型
        egs.setVariable("hello");
        String str = egs.getVariable();
        System.out.println(str);                             // hello
    }

    private static void demo2(Collection<String> col) {
        for (String str : col) {
            System.out.println(str);
        }
        System.out.println("=====================");

        int[] num = {1,2,3,4,5};
        for (int i : num) {
            System.out.println(i);
        }
    }

    private static Collection<String> demo1() {
        Collection<String> col = new ArrayList<>();
        col.add("lisa");
        col.add("sam");
        col.add("tom");

        Iterator<String> iterator = col.iterator(); // 多态 向下转型
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        return col;
    }
}
