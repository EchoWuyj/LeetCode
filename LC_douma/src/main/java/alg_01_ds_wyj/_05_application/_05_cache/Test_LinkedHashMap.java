package alg_01_ds_wyj._05_application._05_cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 23:48
 * @Version 1.0
 */
public class Test_LinkedHashMap {
    public static void main(String[] args) {
        test1();
        System.out.println("===============");
    }

    public static void test1() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(5, 5);
        map.put(2, 2);
        map.put(9, 9);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void test2() {
        LinkedHashMap<Integer, Integer> map
                = new LinkedHashMap<>(3, 0.75f, true);
        map.put(5, 5);
        map.put(2, 2);
        map.put(9, 9);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }


    }
}
