package alg_01_ds_wyj._01_line._05_algo._03_sort.compare;

import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 13:11
 * @Version 1.0
 */
public class Test_Person_Compare {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);

        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        int res = comparator.compare(p1, p2);
        System.out.println(res);
    }

    public static void test2() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);

        PersonComparator comparator = new PersonComparator();
        int res = comparator.compare(p1, p2);
        System.out.println(res);
    }
}
