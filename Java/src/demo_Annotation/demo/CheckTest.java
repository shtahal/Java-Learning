package demo_Annotation.demo;

/*
    简单的测试框架
    当主方法执行后, 自动检测所有被加了Check注解的方法, 判断方法是否有异常, 记录到文件中
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CheckTest {
    public static void main(String[] args) throws IOException {
        int bugCount = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\demo_Annotation\\demo\\bug.txt"));

        // 1. 创建要测试类的对象
        Calculator cal = new Calculator();
        // 2. 获取字节码文件
        Class cls = cal.getClass();
        // 3. 获取所有方法
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            // 4. 判断方法是否有Check注解
            if (method.isAnnotationPresent(Check.class)) {
                // 5. 有注解则执行
                try {
                    // 6. 捕获异常
                    method.invoke(cal);
                } catch (Exception e) {
                    // 7. 记录到文件
                    bugCount++;
                    bw.write(method.getName() + "方法出现异常");
                    bw.newLine();
                    bw.write("异常名称: " + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常原因: " + e.getCause().getMessage());
                    bw.newLine();
                }
            }

        }
        bw.write("共" + bugCount + "次异常.");
        bw.flush();
        bw.close();
    }
}
