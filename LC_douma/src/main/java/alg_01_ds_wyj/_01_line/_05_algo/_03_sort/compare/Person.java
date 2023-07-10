package alg_01_ds_wyj._01_line._05_algo._03_sort.compare;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 11:38
 * @Version 1.0
 */

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {

    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Person other) {
        return this.age - other.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}