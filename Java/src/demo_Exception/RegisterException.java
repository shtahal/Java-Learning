package demo_Exception;

/*
    自定义异常类
        1. 一般以Exception结尾, 说明该类时一个异常类
        2. 必须继承Exception或RuntimeException
            若继承Exception, 那么自定义的是一个编译期异常类; 方法内部抛出异常的话, 必须处理, 要么throws, 要么try...catch
            若继承untimeException, 那么自定义的是一个运行期异常类; 无需处理, 直接交给JVM处理

 */

public class RegisterException extends Exception {
    // 空参数构造方法
    public RegisterException() {
        super();
    }
    /*
        带异常信息的构造方法
        根据其他异常类的源码, 都会有一个带异常信息的构造方法;
        方法内部调用父类异常信息的构造方法, 让父类处理这个异常
     */
    public RegisterException(String message) {
        super(message);
    }
}
