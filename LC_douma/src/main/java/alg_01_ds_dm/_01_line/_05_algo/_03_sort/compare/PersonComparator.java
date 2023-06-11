package alg_01_ds_dm._01_line._05_algo._03_sort.compare;

import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 19:30
 * @Version 1.0
 */

public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {

//        if (o1.getAge() < o2.getAge()) {
//            return -1;
//        } else if (o1.getAge() > o2.getAge()) {
//            return 1;
//        } else {
//            return 0;
//        }

        return o1.getAge() - o2.getAge();

        // KeyPoint 简化形式 => 推荐使用
        // return Integer.compare(o1.getAge(), o2.getAge());
    }
}
