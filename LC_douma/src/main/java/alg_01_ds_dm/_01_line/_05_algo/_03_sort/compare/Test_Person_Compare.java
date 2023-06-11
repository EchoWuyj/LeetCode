package alg_01_ds_dm._01_line._05_algo._03_sort.compare;

import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 19:34
 * @Version 1.0
 */
public class Test_Person_Compare {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {

        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        // KeyPoint 方式二 通过 Comparator 类(Java 内置类)，实现 Person 类比较，Person 得实现 Comparable 接口
        // 定义比较器，Lambda 表达式，直接写 Lambda 表达式，不用创建对象
        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        // 调用 compare 方法
        int res = comparator.compare(p1, p2);
        System.out.println(res); // 10
    }

    private static void test2() {

        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);

        // KeyPoint 方式一 Person 类型，实现 Comparator 比较接口
        PersonComparator comparator = new PersonComparator();
        int res = comparator.compare(p1, p2);
        System.out.println(res); // 10
    }
}
