package alg_01_ds_wyj._05_application._04_bitmap;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 17:04
 * @Version 1.0
 */
public class Test_BitMap {
    private static Random random = new Random();

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static int[] createData() {
        int[] data = new int[10_000_000];
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100_000_000);
        }
        return data;
    }

    public static void test1() {
        int[] data = createData();
        int target = data[8888];

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < data.length; i++) {
            set.add(data[i]);
        }

        if (set.contains(target)) {
            System.out.println("1 千万个整数中存在目标值：" + target);
        }
    }

    public static void test2() {

        int[] data = createData();
        int target = data[8888];

        boolean[] arr = new boolean[100_000_000];
        for (int i = 0; i < data.length; i++) {
            arr[data[i]] = true;
        }

        if (arr[target]) {
            System.out.println("1 千万个整数中存在目标值：" + target);
        }
    }

    public static void test3() {

        int[] data = createData();
        int target = data[8888];

        BitMap1 bitMap = new BitMap1(100_000_000);
        for (int i = 0; i < data.length; i++) {
            bitMap.set(data[i]);
        }

        if (bitMap.contains(target)) {
            System.out.println("1 千万个整数中存在目标值：" + target);
        }
    }
}
