package alg_01_ds_dm._01_line._05_algo._03_sort.compare;

import alg_01_ds_dm._01_line._05_algo._03_sort.Sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 19:51
 * @Version 1.0
 */

// KeyPoint 改造三路快排，支持自定义对象比较，泛型 E，支持任意类型
// 代码层面：将 int[] 都替换成 E[]， 且 E 是实现 Comparable 接口
// KeyPoint 注意事项
// 1.E 是 extends 继承 Comparable 接口，而不是 implements
// 2.和 Person implements Comparable<Person> 不太一样
public class ThreeWayQuickSorter<E extends Comparable<E>> extends Sorter {

    // KeyPoint 方式一 通过 Person 类实现 Comparable 接口
    public void sort(E[] data) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1);
    }

    private void sort(E[] data, int low, int high) {
        if (low >= high) return;
        // 分区
        E pivot = data[high];

        int less = low;
        int great = high;

        int i = low;
        while (i <= great) {
            // data[i] 调用 compareTo 方法，故 data[i] 就是 this
            // data[i].compareTo(pivot) < 0 => data[i] - pivot < 0 => data[i] < pivot
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

    private static void test1() {

        // KeyPoint 方式一：通过 Person 实现 Comparable 接口
        // Person 类必须要实现 Comparable 接口，否则是报错
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        new ThreeWayQuickSorter<Person>().sort(people);
        System.out.println(Arrays.toString(people));
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }

    // KeyPoint 方式二  传入 Comparator 比较器
    // Comparator 泛型和 data 保持一致，不能 Comparator<Person>，比较的就是 data 的数据类型
    public void sort(E[] data, Comparator<E> comparator) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1, comparator);
    }

    private void sort(E[] data, int low, int high, Comparator<E> comparator) {
        if (low >= high) return;
        // 分区
        E pivot = data[high];

        int less = low;
        int great = high;

        int i = low;
        while (i <= great) {
            // 通过 comparator 比较器的 compare 方法实现比较，而不是调用 data[i].compareTo(pivot)
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

    private static void test2() {

        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        // KeyPoint 方式二：通过 Comparator 比较器，实现比较

        // 定义比较器
        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        // 排序方式
        // 1.升序 (o1, o2) -> o1.getAge() - o2.getAge()
        // 2.降序 (o1, o2) -> o2.getAge() - o1.getAge()

        // 传入比较器
        new ThreeWayQuickSorter<Person>().sort(people, comparator);
        System.out.println(Arrays.toString(people));
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }

    public static void main(String[] args) {
        test1();
        System.out.println("=======");
        test2();
    }
}
