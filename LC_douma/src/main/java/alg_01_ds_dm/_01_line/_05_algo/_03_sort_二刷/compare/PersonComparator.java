package alg_01_ds_dm._01_line._05_algo._03_sort_二刷.compare;

import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 19:30
 * @Version 1.0
 */

// Person类比较器
// 记忆：比较器是个东西，单词以 or 结尾
public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {

        // 升序排列
//        if (o1.getAge() < o2.getAge()) {
//            return -1;
//        } else if (o1.getAge() > o2.getAge()) {
//            return 1;
//        } else {
//            return 0;
//        }

        // 升序排列
        // 记忆：o1 类似于 this
        return o1.getAge() - o2.getAge();

        // KeyPoint 简化形式 => 推荐使用
        // return Integer.compare(o1.getAge(), o2.getAge());
    }
}
