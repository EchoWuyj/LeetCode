package alg_01_ds_wyj._01_line._02_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 15:53
 * @Version 1.0
 */

class Home {
    private String address;

    public Home(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Home{" +
                "address='" + address + '\'' +
                '}';
    }
}

class Person {
    private String name;
    private int age;

    private Home home;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", home=" + home +
                '}';
    }
}

public class ReferenceTest {
    public static void main(String[] args) {
        Person person = new Person("jeffy", 12);
        person.setHome(new Home("杭州"));
        System.out.println(person);
    }
}
