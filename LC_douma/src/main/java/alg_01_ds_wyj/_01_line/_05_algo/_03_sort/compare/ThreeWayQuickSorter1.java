package alg_01_ds_wyj._01_line._05_algo._03_sort.compare;

import alg_01_ds_wyj._01_line._05_algo._03_sort.Sorter;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 13:32
 * @Version 1.0
 */

public class ThreeWayQuickSorter1<E extends Comparable<E>> extends Sorter {

    public void sort(E[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        sort(data, 0, n - 1);
    }

    public void sort(E[] data, int low, int high) {
        if (low >= high) return;

        int less = low;
        int great = high;
        int i = low;
        E pivot = data[high];

        while (i <= great) {
            if (data[i].compareTo(pivot) < 0) {
                swap(data, i, less);
                less++;
                i++;
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

    public static void test1() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        new ThreeWayQuickSorter1<Person>().sort(people);
        System.out.println(Arrays.toString(people));
    }

    public static void main(String[] args) {
        test1();
    }
}
