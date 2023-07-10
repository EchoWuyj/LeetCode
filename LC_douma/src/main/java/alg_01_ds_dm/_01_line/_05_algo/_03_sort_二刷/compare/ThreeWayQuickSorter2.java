package alg_01_ds_dm._01_line._05_algo._03_sort_二刷.compare;

import alg_01_ds_dm._01_line._05_algo._03_sort_二刷.Sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-07-09 14:29
 * @Version 1.0
 */
public class ThreeWayQuickSorter2<E> extends Sorter {
    // KeyPoint 方式二  传入 Comparator 比较器
    // Comparator 泛型和 data 保持一致，不能 Comparator<Person>，比较的就是 E[] data 的数据类型
    public void sort(E[] data, Comparator<E> comparator) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        sort(data, 0, n - 1, comparator);
    }

    private void sort(E[] data, int low, int high, Comparator<E> comparator) {
        if (low >= high) return;
        E pivot = data[high];
        int less = low;
        int great = high;
        int i = low;
        while (i <= great) {
            // 通过 comparator 比较器的 compare 方法实现比较，而不是调用 data[i].compareTo(pivot)
            // compare(data[i], pivot) < 0 => data[i] < pivot
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
        // 递归
        sort(data, low, less - 1, comparator);
        sort(data, great + 1, high, comparator);
    }

    public static void main(String[] args) {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        // KeyPoint 方式二：通过 Comparator 比较器，实现比较

        // 定义比较器
        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());

        // KeyPoint 排序方式
        // 1.升序 (o1, o2) -> o1.getAge() - o2.getAge()
        // 2.降序 (o1, o2) -> o2.getAge() - o1.getAge()

        // 传入比较器
        new ThreeWayQuickSorter2<Person>().sort(people, comparator);
        System.out.println(Arrays.toString(people));
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }
}
