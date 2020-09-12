package demo_Thread;

/*
    多线程 java.lang.Thread

    常用方法
        public String getName():                后去当前线程名称
        public void start():                    新线程从此函数开始执行, JVM调用该线程的run()方法
        public void run():                      定义新线程要执行的代码
        public static void sleep(long millis):  当前正执行的线程以指定的毫秒数暂时停止运行
        public static Thread currentThread():   返回当前正执行线程的对象引用

    实现多线程方法一:   继承Thread类
        1. 创建Thread类的子类, 在子类总重写Thread类的run()方法, 设置线程的任务
        2. 创建Thread类的子类对象, 调用Thread类中的start()方法, 开启新的线程执行run()方法
            a) 主线程(main)和新开的线程并发运行
            b) 不能多次启动同一个线程, 特别是线程结束后就不能再重新启动

    实现多线程方法二:  实现Runnable接口 (推荐使用)
        1. 创建Runnable接口的实现类, 并重写Runnable接口的run()方法, 设置线程的任务
        2. 创建Runnable接口的实现类对象
        3. 创建Thread类对象, 在构造方法中传递Runnable接口的实现类对象, 调用Thread类中的start()方法, 开启新的线程执行run()方法

    实现Runnable接口比继承Thread类的优势:
        1. 避免java单继承的局限性
                java中一个类只能继承一个类, 但可以实现多个接口
        2. 设置线程任务和开启新线程两部分代码实现解耦
                若要实现不同任务的线程, 只需要在Thread类对象的构造方法中,传递不同的Runnable接口的实现类对象即可
        3. 线程池只能放入实现Runnable或Callable的类线程, 不能直接放入继承Thread的类

    匿名内部类实现线程的创建
        格式:
            new 父类/接口() {
                重写父类/接口的方法
            }
        1. 更方便实现不同线程执行不同的任务
        2. 直接匿名类实现Runnable接口, 并重写run()方法, 简化代码

    线程安全
        1. 当多线程访问同一资源, 且对资源有写的操作, 就容易出现线程安全的问题
        2. 同步机制(synchronized关键字)
            a) 同步代码块
                在共享代码外需要定义一个锁对象, 同步保证只有一个线程获取该对象, 执行共享代码; 但会频繁获取锁, 释放锁, 效率降低
                synchronized(锁对象) {
                    可能出现线程安全问题, 资源共享的代码
                }
            b) 同步方法
                将可能出现线程安全问题的代码抽取出来, 单独放入到一个方法中, 同步保证只有一个线程执行, 锁住的对象是this, 也就是线程的实现对象
                public synchronized void method() {
                    可能出现线程安全问题, 资源共享的代码
                }
            c) 锁机制  java.util.concurrent.locks.Lock接口
                1) 常用方法
                    public void lock():     加同步锁
                    public void unlock():   释放同步锁
                2) 使用
                    创建一个Lock接口的实现类ReentrantLock对象
                    在可能出现安全问题的代码前调用Lock接口的lock()方法获取锁
                    在可能出现安全问题的代码后调用Lock接口的unlock()方法释放锁

    线程状态
        java.util.Thread内部类State定义了线程的六种状态
        --------------------------------------------------------------
        线程状态                |        状态发生条件
        NEW(新建)              |   线程被创建, 没有调用start()方法启动
        RUNNABLE(可运行)        |   线程在JVM中运行
        BLOCKED(锁阻塞)         |   当线程试图获取对象锁, 但对象锁被其他线程持有, 则进入Blocked状态; 获取锁对象后, 变成Runnable状态
        WAITING(无限等待)       |  锁对象调用Object.wait()后, 无法自动唤醒, 需要锁对象调用Object.notify()唤醒
        TIMED_WAITING(计时等待) |  给Object.wait()传入时间参数或者调用sleep()方法, 线程一直保持时间到期或者被唤醒
        TERMINATED(终止)        |  线程被终止
        ---------------------------------------------------------------

    线程池 java.util.concurrent.ExecutorService接口
        1. 一个容纳多个线程的容器， 可用ArrayList, LinkedList等集合; 其中的线程可反复使用, 避免频繁创建线程而消耗大量资源
        2. JDK1.5后可以使用java.util.concurrent.Executors线程工厂类生成线程池


 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo {

    public static void main(String[] args) {
        /*
            1. 实现多线程方法一
                a) for循环中设置重点要大一些, 不然电脑配置好的会直接运行完main方法
                b) 结果时main线程和新线程交叉输出
            2. 实现多线程方法二
            3. 获取线程的名称
                a. 使用Thread类中的静态方法getName()直接获取线程的名称
                b. 先使用Thread类中的静态方法currentThread获取当前正执行的线程, 再使用线程的getName()获取线程的名称
            4. 匿名内部类实现线程的创建
            5. 线程的RUNNABLE和WAITING
                a. 客户线程买包子, 调用wait()方法后, 等待老板线程包包子, 进入WAITING状态
                b. 老板线程花费5秒包包子后, 调用notify()方法, 唤醒客户线程吃包子
                c. 注意事项
                    两个线程的等待和唤醒过程只能由一个在运行
                    同步的锁对象必须保证唯一, 锁对象可以是任何类型的
                    只有锁对象才能调用Object类的wait()和notify()方法, 两种方法必须放在同步方法或者同步代码块
            6. 线程池
                a. 使用线程池的工厂类Executors提供的静态方法newFixedThreadPool()生产一个指定线程数量的线程池
                b. 创建Runnable接口的实现类, 重写run()方法, 设置线程任务
                c. 调用ExecutorService接口中的submit()方法, 传递线程任务(实现线程类), 执行run()方法开启线程
                d. 调用ExecutorService接口中的shutdown()方法销毁线程池(不建议执行)

         */
        
//        demo1();    // 1.
//        demo2();    // 2.
//        demo3();    // 3.
//        demo4();    // 4.
//        demo5();    // 5.
        demo6();    // 6.

    }

    private static void demo6() {
        // 使用线程池的工厂类Executors提供的静态方法newFixedThreadPool()生产一个容量为2的线程池
        ExecutorService es = Executors.newFixedThreadPool(2);
        // 创建Runnable接口的实现类, 重写run()方法, 设置线程任务
        ThreadPoolImpl tpl = new ThreadPoolImpl();
        // 调用ExecutorService接口中的submit()方法
        es.submit(tpl);
        // 线程池会一直开启, 使用完线程后自动将线程归还给线程池, 线程可重复使用
        es.submit(new ThreadPoolImpl());
        es.submit(new ThreadPoolImpl());
        es.submit(new ThreadPoolImpl());
        es.submit(new ThreadPoolImpl());
        // 调用ExecutorService接口中的shutdown()方法销毁线程池
        es.shutdown();
    }

    private static void demo5() {

        // 创建锁对象
        Object obj = new Object();

        // 客户线程
        new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("客户: 老板, 来十个包子.");
                    // 调用wait()方法, 进入WAITING状态
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 老板线程唤醒当前客户线程后, 会继续执行wait()后的代码
                    System.out.println("客户: 老板, 我走了.");
                }
            }
        }.start();

        // 老板线程
        new Thread() {
            @Override
            public void run() {
                // 老板花费了5秒包包子
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 唤醒WAITING状态的客户线程
                synchronized (obj) {
                    System.out.println("老板: 包子好了, 拿去吃.");
                    obj.notify();
                }
            }
        }.start();
    }

    private static void demo4() {
        // 方法1
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + 1);
                }
            }
        }.start();

        // 方法2
        Runnable r = new Runnable() {   // 用了多态, 左边本应该是Runnable接口的实现类
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    System.out.println(Thread.currentThread().getName() + " " +  2);
                }
            }
        };
        Thread t = new Thread(r);
        t.start();

        // 方法2的简化
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    System.out.println(Thread.currentThread().getName() + " " +  3);
                }
            }
        }).start();
    }

    private static void demo3() {
        CommonMethods cm1 = new CommonMethods();
        cm1.start();                    // Thread-1
        new CommonMethods().start();    // Thread-2
        new CommonMethods().start();    // Thread-0

        String name = Thread.currentThread().getName();
        System.out.println(name);       // main
    }

    private static void demo2() {
        Thread t = new Thread(new CreateThreadMethodTwo());
        t.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }

    private static void demo1() {
        // 调用新线程
        CreatThreadMethodOne t1 = new CreatThreadMethodOne();
        t1.start();
        // 主线程
        for (int i = 0; i < 100; i++) {
            System.out.println("main " + i);
        }
    }

}
