package demo_Final;

public class LastTestClass {

//    private final String name = "sam";  // 直接赋值, 则不能再使用构造方法和setter方法
    private final String name;

    public LastTestClass() {
        this.name = "sam";                // 构造函数赋值, 则不能再使用setter方法
    }

    public LastTestClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
}
