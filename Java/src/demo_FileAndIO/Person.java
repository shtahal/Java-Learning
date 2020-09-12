package demo_FileAndIO;

import java.io.Serializable;

public class Person implements Serializable {
//    private static final long serialVersionID = 2L; // 自定义序列号
    private String name;
//    private int age;
//    private static int age;
    private transient int age;
//    public transient int age; // InvalidClassException

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
