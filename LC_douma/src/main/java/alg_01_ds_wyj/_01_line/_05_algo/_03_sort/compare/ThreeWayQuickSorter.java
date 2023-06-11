package alg_01_ds_wyj._01_line._05_algo._03_sort.compare;

import alg_01_ds_wyj._01_line._05_algo._03_sort.Sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 13:32
 * @Version 1.0
 */

public class ThreeWayQuickSorter<E extends Comparable<E>> extends Sorter {
    public void sort(E[] data) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1);
    }

    public void sort(E[] data, int low, int high) {
        if (low >= high) return;
        E pivot = data[high];
        int less = low;
        int great = high;

        int i = low;
        while (i <= great) {
            if (data[i].compareTo(pivot) < 0) {
                swap(data, i, less);
                i++;
                less++;
            } else if (data[i].compareTo(pivot) > 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }
        sort(data, low, less - 1);
        sort(data, great + 1, high);
    }

    private static void test1() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};
        new ThreeWayQuickSorter().sort(people);
        System.out.println(Arrays.toString(people));
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33},Person{name='douma', age=40}]
    }

    public void sort(E[] data, Comparator<E> comparator) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1, comparator);
    }

    public void sort(E[] data, int low, int high, Comparator<E> comparator) {
        if (low >= high) return;
        E pivot = data[high];
        int less = low;
        int great = high;
        int i = low;

        while (i <= great) {
            if (comparator.compare(data[i], pivot) < 0) {
                swap(data, i, less);
                less++;
                i++;
            } else if (comparator.compare(data[i], pivot) > 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }
        sort(data, low, less - 1, comparator);
        sort(data, great + 1, high, comparator);
    }

    public static void main(String[] args) {
        test1();
        System.out.println("=======");
        test2();
    }

    private static void test2() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        new ThreeWayQuickSorter().sort(people, comparator);
        System.out.println(Arrays.toString(people));

        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }
}
