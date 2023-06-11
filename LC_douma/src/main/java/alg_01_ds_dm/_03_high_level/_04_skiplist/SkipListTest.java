package alg_01_ds_dm._03_high_level._04_skiplist;

/**
 * @Author Wuyj
 * @DateTime 2023-03-20 19:30
 * @Version 1.0
 */
public class SkipListTest {
    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>();
        skipList.add(3);
        skipList.add(4);
        skipList.add(6);
        skipList.add(9);
        skipList.add(12);
        skipList.add(16);
        skipList.add(18);
        skipList.add(20);
        skipList.display(); // 3,4,6,9,12,16,18,20
        System.out.println(skipList.contains(9)); // true
        System.out.println(skipList.contains(17)); // false
        skipList.remove(16);
        skipList.remove(7);
        skipList.display(); // 3,4,6,9,12,18,20
    }
}
