package alg_01_ds_wyj._01_line._05_algo._03_sort;

import alg_01_ds_dm._01_line._05_algo._03_sort_二刷.compare.Person;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-07-09 12:15
 * @Version 1.0
 */
public class _14_JavaSorter {
    public static void test1() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        Arrays.sort(people);
        System.out.println(Arrays.toString(people));
    }

    public static void test2() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        Arrays.sort(people, comparator);
        System.out.println(Arrays.toString(people));
    }

    public static void main(String[] args) {
        //test1();
        test2();
    }
}