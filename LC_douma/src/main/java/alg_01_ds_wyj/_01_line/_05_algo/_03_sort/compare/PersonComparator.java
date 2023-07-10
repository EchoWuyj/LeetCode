package alg_01_ds_wyj._01_line._05_algo._03_sort.compare;

import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 13:23
 * @Version 1.0
 */
public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAge() - o2.getAge();
    }
}
