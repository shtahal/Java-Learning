package demo_Annotation;

/*
    注解
        注释: 给程序员看
        注解: 给程序看
        作用:
            1. 编写文档: 通过代码里标识的注解生成文档[使用javadoc生成doc文档, 需以ANSI格式保存]
            2. 代码分析: 通过代码里标识的注解对代码进行分析[使用反射]
            3. 编译检查: 通过代码里标识的注解让编译器实现基本的编译检查[比如Override]
        JDK中预定义的一些注解
            1. @Override:           检测被该注解标注的方法是否继承自父类(接口)
            2. @Deprecated:         该注解标注的内容表示已过时
            3. @SuppressWarnings:   压制警告, 一般传递参数"all"
        自定义注解
            元注解
                public @interface 注解名称{
                    属性列表
                }
        在程序中使用(解析)注解
            1. 获取注解定义的位置的对象 (Class, Method, Field)
            2. 获取指定的注解 (getAnnotation(class))
            3. 使用注解中的抽象方法获取配置的属性值

 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Properties(className = "demo_Annotation.Demo1", methodName = "show")
public class AnnotationDemo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        // 1. 解析注释
        // 1.1 获取该类的字节码文件
        Class<AnnotationDemo> annotationDemoClass = AnnotationDemo.class;
        // 2. 获取上边的注释对象
        // 其实就是在内存中生成了一个该注释接口的子类实现对象
        Properties annotation = annotationDemoClass.getAnnotation(Properties.class);
        // 3. 调用注解对象中定义的抽象方法, 获取返回值
        String className = annotation.className();
        String methodName = annotation.methodName();

        System.out.println(className);
        System.out.println(methodName);

        // 反射部分
        Class cls = Class.forName(className);
        Object o = cls.newInstance();
        Method method = cls.getMethod(methodName);
        method.invoke(o);
    }

}
