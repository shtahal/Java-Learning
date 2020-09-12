package demo_Polymorphism;

/*
使用抽象接口的抽象方法
    1. 继承类必须重写/覆盖(Override)所有的抽象方法
    2. 抽象方法可省略写 public abstract
 */
public abstract interface USB {

    public abstract void open();

    public abstract void close();

}
