package alg_01_ds_dm._03_high_level._03_map;

import com.sun.media.sound.SoftTuning;

import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 16:41
 * @Version 1.0
 */
public class MapTest {

    static List<String> words = FileReaderTest.readFile("LC_douma/data/test-data.txt");

    private static void testMap() {
        Map<String, Integer> map = new LinkedListMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                Integer count = map.get(word);
                map.set(word, count + 1);
            } else {
                map.add(word, 1);
            }
        }
        System.out.println(map.size());
    }

    private static double testMapTime(Map<String, Integer> map) {
        long startTime = System.nanoTime();
        for (String word : words) {
            if (map.containsKey(word)) {
                Integer count = map.get(word);
                map.set(word, count + 1);
            } else {
                map.add(word, 1);
            }
        }
        return (System.nanoTime() - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        testMap(); // 13619
        // KeyPoint 比较难以发现 bug，最好通过 debug 的方式来解决，不要想通过肉眼看出来，效率不高

        System.out.println("========================");

        Map<String, Integer> llMap = new LinkedListMap<>();
        double time1 = testMapTime(llMap);
        System.out.println("链表实现的 Map 花的时间：" + time1);

        Map<String, Integer> bstMap = new BSTMap<>();
        double time2 = testMapTime(bstMap);
        System.out.println("BST 实现的 Map 花的时间：" + time2);

        Map<String, Integer> hashMap = new HashMap<>();
        double time3 = testMapTime(hashMap);
        System.out.println("Hash 实现的 Map 花的时间：" + time3);

        // 注意:在数据量非常大时，不建议使用 HashMap
        // 1 HashMap 占用内存空间是成块，底层是使用数组存储
        // 2 数据量大，HashMap 存在大量哈希冲突，反而使得 HashMap 性能下降
        // 3 推荐 BSTMap，虽然时间复杂度高点，但是性能很稳定
    }
}
