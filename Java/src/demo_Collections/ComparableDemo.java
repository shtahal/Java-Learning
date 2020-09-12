package demo_Collections;

public class ComparableDemo implements Comparable<ComparableDemo> {
    private int age;
    private String name;

    public ComparableDemo() {
    }

    public ComparableDemo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(ComparableDemo o) {
        return this.age - o.age;
    }
}
