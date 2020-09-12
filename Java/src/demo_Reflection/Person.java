package demo_Reflection;

public class Person {

    private int a;
    protected int b;
    int c;
    public int d;
    public double e;

    @Override
    public String toString() {
        return "Person{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                '}';
    }

    public void sleep() {
        System.out.println("Person类中的方法.");
    }
}
