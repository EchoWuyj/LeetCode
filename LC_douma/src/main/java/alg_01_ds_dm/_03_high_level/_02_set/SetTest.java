package alg_01_ds_dm._03_high_level._02_set;

import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 23:42
 * @Version 1.0
 */
public class SetTest {
    private static double testSet(Set<String> set, List<String> words) {
        for (String word : words)
            // 将集合中所有的 word 都存在 set 集合中
            set.add(word);

        long start = System.nanoTime();

        // set 的 contains 接口使用非常多，所以这里主要测试 contains 方法
        for (int i = 0; i < 10000; i++) {
            set.contains("father");
        }
        long end = System.nanoTime();
        return (end - start) / 1000_000_000.0;
    }

    public static void main(String[] args) {

        // KeyPoint 文件路径使用 copy relative path => LC_douma/data
        List<String> words = FileReaderTest.readFile("LC_douma/data/test-data.txt");

        Set<String> arrSet = new ArraySet<>();
        double time1 = testSet(arrSet, words);
        System.out.println("ArraySet ：" + time1); // ArraySet ：0.012371

        Set<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, words);
        System.out.println("LinkedListSet ：" + time2); // LinkedListSet ：1.1038472

        // 原因：
        // 1 数组是一块连续的内存空间，在 cpu 读取数组中的一个元素的时候，会将这个元素旁边的
        //   多个元素一起加载进 cpu 的高速缓存，这样下次读取的话，就直接从高速缓存中读取，
        // 2 链表的数据是分散在内存中的，cpu 每次读取元素的时候都需要从主存中读取
        // 3 故数组的顺序遍历会比链表的顺序遍历要快
    }
}
