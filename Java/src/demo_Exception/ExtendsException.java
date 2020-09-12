package demo_Exception;

// 5.

public class ExtendsException {
    public void demo1() throws NullPointerException, ClassCastException{}
    public void demo2() throws IndexOutOfBoundsException{}
    public void demo3() throws IndexOutOfBoundsException{}
    public void demo4(){}
}

class Zi extends ExtendsException {
    // 子类重写父类方法时, 可以抛出和父类相同的异常
    public void demo1() throws NullPointerException, ClassCastException{}
    // 子类重写父类方法时, 可以抛出父类异常的子类异常
    public void demo2() throws IndexOutOfBoundsException{}
    // 子类重写父类方法时, 可以不抛出异常
    public void demo3(){}
    // 父类没有抛出异常, 子类不能抛出异常, 但可以使用try...catch解决异常
    public void demo4() {
        try {
            throw new Exception("不可以抛出异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
