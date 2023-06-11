package alg_01_ds_wyj._03_high_level._02_set;

import jdk.internal.dynalink.beans.StaticClass;

import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 13:15
 * @Version 1.0
 */
public class SetTest {

    private static double testSet(Set<String> set, List<String> words) {
        for (String word : words) {
            set.add(word);
        }
        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            set.contains("father");
        }
        long end = System.nanoTime();
        return (end - start) / 1000_000_000.0;
    }

    public static void main(String[] args) {
        List<String> words = FileReaderTest.readFile("LC_douma/data/test-data.txt");
        Set<String> arrSet = new ArraySet<>();
        double time1 = testSet(arrSet, words);
        System.out.println("ArraySet ：" + time1); // ArraySet ：0.012371

        Set<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, words);
        System.out.println("LinkedListSet ：" + time2); // LinkedListSet ：1.1038472
    }
}
