package alg_01_ds_wyj._01_line._05_algo._03_sort.compare;

import alg_01_ds_wyj._01_line._05_algo._03_sort.Sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-07-09 14:32
 * @Version 1.0
 */
public class ThreeWayQuickSorter2<E> extends Sorter {
    public void sort(E[] data, Comparator<E> comparator) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        sort(data, 0, n - 1, comparator);
    }

    public void sort(E[] data, int low, int high, Comparator<E> comparator) {
        if (low >= high) return;
        int i = low;
        int less = low;
        int great = high;
        E pivot = data[high];

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

    public static void test() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        new ThreeWayQuickSorter2().sort(people, comparator);
        System.out.println(Arrays.toString(people));
    }

    public static void main(String[] args) {
        test();
    }
}
