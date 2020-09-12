package demo_Exception;

/*
    异常
    1. java.lang.Throwable
            |-  java.lang.Error
            |                           运行期异常
            |-  java.lang.Exception
            |                           编译期异常

    2. 异常处理的五个关键字: try catch finally throw throws
        a. throw关键字         处理异常
            1) throw关键字必须写在方法的内部
            2) throw关键字后边new的对象必须是Exception或Exception的子类对象
            3) throw关键字中抛出指定的异常对象, 就必须进行处理
                    若创建的是RuntimeException或者RuntimeException的子类对象, 可以不处理, 直接交给JVM处理(打印异常对象, 中断程序)
                    若创建的是编译异常, 就必须处理, throws 或者 try...catch
        b. throws关键字        声明异常
            1) throws关键字将异常对象声明抛出给方法的调用者处理, 自己不处理, 最终交给JVM处理
            2) throws关键字必须写在方法声明处
            3) throws关键字声明的异常必须是Exception或Exception的子类对象
            4) 方法内部若抛出多个异常, throws关键字必须也声明多个异常; 若抛出多个异常有父子类关系, 那么直接声明父类即可
            5) 若调用了声明异常的方法, 就必须处理声明的异常
                    继续使用throws声明抛出, 交给方法的调用者处理, 最终交给JVM
                    try...catch自己处理异常
        c. try...catch关键字   捕获并处理异常
            格式
                try {
                    可能产生异常的代码
                } catch (异常变量 变量名) {
                    声明异常并处理
                    将异常信息记录到日志
                }
                ...
                catch (异常变量 变量名) {
                }
            1) try中可能抛出多个异常对象, 那么可以使用多个catch处理
            2) 若try中产生异常, 就会执行catch中的处理异常代码, 执行完后继续执行try...catch后的代码
               若try中没有产生异常, 就不会执行catch中的处理异常代码, 执行完try后继续执行try...catch后的代码
        d. finally关键字
            格式:
                try {
                    可能产生异常的代码
                } catch (异常变量 变量名) {
                    声明异常并处理
                    将异常信息记录到日志
                }
                ...
                catch (异常变量 变量名) {
                }
            1) try中代码发生异常导致程序跳转, 后面语句执行不到, 而finally代码块的代码一定被执行
            2) finally关键字无法单独使用, 必须和try...catch一起使用
            3) finally关键字一般用于资源释放(资源回收), 无论程序是否出现异常, 资源最后都会释放
            4) 若finally代码块中有return, 则一定返回finally代码块中的值, 避免使用

    3. java.lang.Throwable 查看异常的方法
            public String getMessage():         获取异常的描述信息
            public String toString():           获取异常的类型和异常的描述信息
            public String printStackTrace():    打印异常的跟踪栈信息并输出到控制台

    4. 多异常的捕获处理
        a. 多个异常分别处理
        b. 多个异常一次捕获, 分别处理
        c. 多个异常一次捕获, 一次处理
        d. 运行时抛出的异常可以不处理, 直接交给JVM处理

    5. 子父类异常 (见ExtendsException)
        a. 子类重写父类方法时, 可以抛出和父类相同的异常
        b. 子类重写父类方法时, 可以抛出父类异常的子类异常
        c. 子类重写父类方法时, 可以不抛出异常
        d. 父类没有抛出异常, 子类不能抛出异常, 但可以使用try...catch解决异常

    6. 自定义异常 (见RegisterException)
        a. JDK自带的异常类数量有限, 在不满足业务的异常情况下, 需要根据具体的业务异常情况自定义异常类
        b. 自定义编译期异常: 必须继承 java.util.Exception
           自定义运行期异常: 必须继承 java.util.RuntimeException


 */

import java.io.IOException;

public class ExceptionDemo {

    /*
        1. throws关键字声明异常, 并抛给调用方法main()方法
            运行结果:
                Exception in thread "main" java.io.IOException: 传入文件后缀名不是.txt
                    at demo_Exception.ExceptionDemo.ReadFile(ExceptionDemo.java:67)
                    at demo_Exception.ExceptionDemo.main(ExceptionDemo.java:63)
        2. throws关键字声明异常, 使用try...catch处理异常, 输出Throwable 查看异常的三种方法
            运行结果:
                java.io.IOException: 传入文件后缀名不是.txt
                写入日志...
                其他代码.
        3. finally关键字
            运行结果:
                java.io.IOException: 传入文件后缀名不是.txt
                写入日志...
                资源回收
                其他代码.
        4. 多异常处理
             a. 多个异常分别处理
             b. 多个异常一次捕获, 分别处理
                    catch 中定义的异常变量若有子父类关系, 子类的异常变量必须定义在上面
             c. 多个异常一次捕获, 一次处理
        5. 子父类异常
     */

    // 1.
//    public static void main(String[] args) throws IOException {
//        ReadFile("C:\\a.tx");
//    }

    // 2.
//    public static void main(String[] args) {
//        try {
//            ReadFile("C:\\a.tx");
//        } catch (IOException e) {
////            System.out.println(e/*.toString()*/);   //  java.io.IOException: 传入文件后缀名不是.txt
////            System.out.println(e.getMessage());     //    传入文件后缀名不是.txt
//            System.out.println(e.getStackTrace());  //  [Ljava.lang.StackTraceElement;@1b6d3586
//
//            System.out.println("写入日志...");
//        }
//        System.out.println("其他代码.");
//    }

    // 3.
//    public static void main(String[] args) {
//        try {
//            // 可能产生异常的代码
//            ReadFile("C:\\a.tx");
//            System.out.println("执行try代码块中剩余的代码.");  // 出现异常后运行不到的代码
//        } catch (IOException e) {
//            // 处理异常的代码
//            System.out.println(e);
//            System.out.println("写入日志...");
//        } finally {
//            // 无论是否有异常, 都会执行的代码
//            System.out.println("资源回收");
//        }
//        System.out.println("其他代码.");
//    }

    private static void ReadFile(String filename) throws IOException {
        if (!filename.endsWith(".txt")) {
            throw new IOException("传入文件后缀名不是.txt");
        }
    }

    // 4.
    public static void main(String[] args) {

        // a. 多个异常分别处理
        /*
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[3]);             // java.lang.ArrayIndexOutOfBoundsException: 3
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            ArrayList<Integer> list = new ArrayList<>();
            System.out.println(list.get(3));        // java.lang.IndexOutOfBoundsException: Index: 3, Size: 0
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
         */

        // b. 多个异常一次捕获, 分别处理
        /*
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[3]);
            ArrayList<Integer> list = new ArrayList<>();
            System.out.println(list.get(3));        // java.lang.IndexOutOfBoundsException: Index: 3, Size: 0
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
         */

        // c. 多个异常一次捕获, 一次处理
        /*
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[3]);
            ArrayList<Integer> list = new ArrayList<>();
            System.out.println(list.get(3));        // java.lang.IndexOutOfBoundsException: Index: 3, Size: 0
        } catch (Exception e) {
            System.out.println(e);
        }
         */

    }


}
