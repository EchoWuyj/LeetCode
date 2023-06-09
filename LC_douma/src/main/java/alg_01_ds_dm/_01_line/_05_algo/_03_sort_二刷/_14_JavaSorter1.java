package alg_01_ds_dm._01_line._05_algo._03_sort_二刷;

import alg_01_ds_dm._01_line._05_algo._03_sort_二刷.compare.Person;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 18:21
 * @Version 1.0
 */
public class _14_JavaSorter1 {

    // KeyPoint Java 内置排序 => 通用的排序 => 基本数据类型
    private static void test1() {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        // 对基本类型排序
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));
    }

    // KeyPoint Java 内置排序 => 通用的排序 => 引用数据类型
    private static void test2() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);

        Person[] people = new Person[]{p1, p2, p3, p4};

        int res = p1.compareTo(p2);
        System.out.println("res = " + res); // // 40 - 30 = 10

        // 1.直接调用 sort 方法 => 要求对象实现 Comparable 接口
        // Arrays 对数组排序
        Arrays.sort(people); // 升序
        System.out.println(Arrays.toString(people));
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }

    // KeyPoint Java 内置排序 => 通用的排序 => 引用数据类型
    private static void test3() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        // 2.通过 comparator 实现排序
        // KeyPoint 定义比较器 => Lambda 实现
        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        // 传入比较器
        Arrays.sort(people, comparator);
        System.out.println(Arrays.toString(people));
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }

    // KeyPoint 引用数据类型，排序总结：
    // 小规模数据，选择插入排序
    // 大规模数据，选择归并排序
    //  1.老版本使用：递归实现归并
    //  2.新版本使用：迭代实现归并

    // KeyPoint 注意事项
    // 对于基本数据类型而言，排序算法的稳定性不重要
    // 而引用数据类型，要求排序算法具有稳定性，故不能使用快排，因为快排是不稳定的排序算法

    // KeyPoint Java 内置排序 => 通用的排序 => 引用数据类型
    private static void test4() {
        Person p1 = new Person("douma", 40);
        Person p2 = new Person("laotang", 30);
        Person p3 = new Person("douma1", 32);
        Person p4 = new Person("laotang2", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        // KeyPoint 传入比较器 => 匿名内部类
        Arrays.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(Arrays.toString(people));
        // [Person{name='laotang', age=30}, Person{name='douma1', age=32},
        // Person{name='laotang2', age=33}, Person{name='douma', age=40}]
    }

    public static void main(String[] args) {
        System.out.println("====== test1 =======");
        test1();
        System.out.println("====== test2 =======");
        test2();
        System.out.println("====== test3 =======");
        test3();
    }
}
