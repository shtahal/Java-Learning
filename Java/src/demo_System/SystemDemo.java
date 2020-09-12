package demo_System;

/*
    java.lang.System类提供大量静态方法, 可以获取与系统相关的信息或操作
    1. 常见方法
        public static long currentTimeMillis(): 返回已毫秒为单位的当前时间
        public static void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length): 将数组指定数据复制到另一数组
            src     源数组
            srcPos  源数组的起始位置
            dest    目标数组
            destPos 目标数组的起始位置
            length  要复制元素的数量
 */

import java.util.Arrays;

public class SystemDemo {

    public static void main(String[] args) {

        // currentTimeMillis() 测试运行时间
        demo1();

        // arraycopy
        demo2();

    }

    private static void demo2() {
        int[] src = {1,2,3,4,5};
        int[] dest = {6,7,8,9,10};
        System.out.println(Arrays.toString(dest));  // [6, 7, 8, 9, 10]
        System.arraycopy(src, 0, dest, 0, 3);
        System.out.println(Arrays.toString(dest));  // [1, 2, 3, 9, 10]
    }

    private static void demo1() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(time);
    }

}
