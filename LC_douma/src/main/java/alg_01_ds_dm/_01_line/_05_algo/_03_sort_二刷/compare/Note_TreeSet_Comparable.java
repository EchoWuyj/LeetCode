package alg_01_ds_dm._01_line._05_algo._03_sort_二刷.compare;

import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-07-11 10:41
 * @Version 1.0
 */
public class Note_TreeSet_Comparable {

    class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Person o) {
            return 0;
        }
    }

    public void test1() {
        TreeSet<Person> ts = new TreeSet<>();
        ts.add(new Person("张三", 23));
        ts.add(new Person("李四", 13));
        ts.add(new Person("王五", 43));
        ts.add(new Person("赵六", 33));
        System.out.println(ts);
    }

    // 小的存储在左边(负数)
    // 大的存储在右边(正数)
    // 相等就不存(0)

    // 返回 0，集合中只有一个元素
    // 返回 -1，集合会将存储元素倒序
    // 返回 1，集合会怎么存就怎么取

    // return 0
    // => 只存一个元素 new Person("张三", 23)
    // => 每次加入新的元素都认为和 new Person("张三", 23) 一样，故都不进行存储

    // return -1
    // => 依次和每个节点比较，返回 -1，比说明比该节点要小，存在其左侧
    // => 集合会将存储元素倒序

    //              new Person("张三", 23)
    //                  ↙
    //          new Person("李四", 13)
    //              ↙
    //      new Person("王五", 43)
    //           ↙
    // new Person("赵六", 33)

    // return 1
    // => 依次和每个节点比较，返回 1，比说明比该节点要大，存在其右侧
    // => 集合会怎么存就怎么取

    // new Person("张三", 23)
    //       ↘
    //   new Person("李四", 13)
    //          ↘
    //      new Person("王五", 43)
    //             ↘
    //         new Person("赵六", 33)

    public void test2() {
        TreeSet<Person> ts = new TreeSet<>();
        ts.add(new Person("张三", 23));
        ts.add(new Person("李四", 13));
        ts.add(new Person("周七", 13));  // age 相等，则不存
        ts.add(new Person("王五", 43));
        ts.add(new Person("赵六", 33));
        System.out.println(ts);
    }

    // 小的存储在左边(负数),大的存储在右边(正数),相等就不存(0)

    // 返回 0，集合中只有一个元素
    // 返回 -1，集合会将存储的元素倒序
    // 返回 1，集合会怎么存就怎么取

//         public int compareTo(Person other) {
//            return this.age - other.age;
//         }

    // 具体流程：
    // 第一个 ts.add(new Person("张三", 23))，直接为树根
    // 第二个 ts.add(new Person("李四", 13))，调用 compareTo 方法，谁进来谁调用 compareTo 方法
    //  =>  13 - 23 < 0，放在 23 的左边
    // 每次 ts 添加新的 Person 都是从根开始，依次进行比较
    //  => 如：new Person("赵六", 33)
    //  => 先和 23 比较，33 - 23 > 0，放在 23 的右边
    //     再和 43 比较，33 - 43 < 0，放在 43 的左边

    //            new Person("张三", 23)
    //                  ↙          ↘
    // new Person("李四", 13)   new Person("王五", 43)
    //                             ↙
    //                  new Person("赵六", 33)



    public static void main(String[] args) {

    }
}
