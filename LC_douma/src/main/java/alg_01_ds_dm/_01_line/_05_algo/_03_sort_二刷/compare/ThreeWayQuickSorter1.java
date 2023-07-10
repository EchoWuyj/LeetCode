package alg_01_ds_dm._01_line._05_algo._03_sort_二刷.compare;

import alg_01_ds_dm._01_line._05_algo._03_sort_二刷.Sorter;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 19:51
 * @Version 1.0
 */

// KeyPoint 改造：三路快排
// 改造三路快排，支持自定义对象比较，泛型 E，支持任意类型
// 代码层面：将 int[] 都替换成 E[]，且 E 是实现 Comparable 接口
// 本质：泛型 E 是 将 int 等价看成 E 即可 => 数据类型更加通用
// KeyPoint 注意事项
// 泛型 E 是 extends 继承 Comparable 接口，而不是 implements 接口
// 和 Person implements Comparable<Person> 不太一样
public class ThreeWayQuickSorter1<E extends Comparable<E>> extends Sorter {

    // KeyPoint 方式一 通过 Person 类实现 Comparable 接口
    public void sort(E[] data) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1);
    }

    private void sort(E[] data, int low, int high) {
        if (low >= high) return;
        // int 替换 E => 因为涉及 data[high]，且 data 是关于 E 的数据类型，故使用 E
        E pivot = data[high];

        int less = low;
        int great = high;

        int i = low;
        while (i <= great) {
            // KeyPoint 代码比较逻辑需要修改
            // 1.因为 data[i] 不是基本数据类型，故不能使用 data[i] < pivot
            // 2.data[i] 调用 compareTo 方法，故 data[i] 就是 this
            //   data[i].compareTo(pivot) < 0 => data[i] - pivot < 0 => data[i] < pivot
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

    public static void main(String[] args) {

        // KeyPoint 方式一 通过 Person 实现 Comparable 接口
        // Person 类必须要实现 Comparable 接口，否则是报错
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        new ThreeWayQuickSorter1<Person>().sort(people);
        System.out.println(Arrays.toString(people));
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }
}
