package alg_01_ds_dm._01_line._05_algo._03_sort_二刷;

import alg_01_ds_dm._01_line._05_algo._03_sort_二刷.compare.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-07-09 14:52
 * @Version 1.0
 */
public class _14_JavaSorter2 {

    // KeyPoint 对动态数组进行排序
    //  1.直接比较
    public static void test1() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        ArrayList<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        // Collections 对集合排序，同样要求对象实现 Comparable 接口
        // 补充说明：底层 Arrays.sort
        Collections.sort(list);
        System.out.println(list);
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }

    // KeyPoint 对动态数组进行排序
    //  2.传入比较器
    public static void test2() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        ArrayList<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        // 传入比较器
        Collections.sort(list, comparator); // 底层：Arrays.sort
        System.out.println(list);
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }

    public static void main(String[] args) {
        System.out.println("====== test1 =======");
        test1();
        System.out.println("====== test2 =======");
        test2();
    }
}
