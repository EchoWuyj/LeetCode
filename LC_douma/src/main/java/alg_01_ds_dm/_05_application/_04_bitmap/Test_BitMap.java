package alg_01_ds_dm._05_application._04_bitmap;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 15:20
 * @Version 1.0
 */
public class Test_BitMap {
    private static Random random = new Random();

    public static void main(String[] args) {
        test1();
        System.out.println("============");
        test2();
        System.out.println("============");
        test3();
        System.out.println("============");
    }

    public static int[] createData() {
        // 准备 1 千万个整数
        int[] data = new int[10_000_000]; // 8 位数表示千万，最高位是 1，剩余 7 个 0
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100_000_000); // 9 位数表示亿，最高位是 1，剩余 8 个 0
        }
        return data;
    }

    // KeyPoint 查找 -> 线性查找，二分查找，哈希查找

    public static void test1() {

        int[] data = createData();
        int target = data[8888];

        // KeyPoint 1. 使用哈希表
        // 1.1 数据预处理
        Set<Integer> set = new HashSet<>();  // 10_000_000 * 4 / 0.75 = 51 MB (理想情况)
        for (int i = 0; i < data.length; i++) {
            set.add(data[i]);
        }
        // 1.2 查询
        if (set.contains(target)) { // 时间复杂度 O(1)，会存在哈希冲突
            System.out.println("1 千万个整数中存在目标值：" + target);  // 1 千万个整数中存在目标值：78685631
        }
    }

    public static void test2() {
        int[] data = createData();
        int target = data[8888];

        // KeyPoint 2. 使用 boolean 类型的数组
        boolean[] arr = new boolean[100_000_000]; // 假设 boolean 数据类型大小为 1 字节，100_000_000 byte = 95 MB
        for (int i = 0; i < data.length; i++) {
            // 整数作为数据下标，data[i] 标记为 true，其余为 false
            arr[data[i]] = true;
        }

        if (arr[target]) { // 时间复杂度 O(1)
            System.out.println("1 千万个整数中存在目标值：" + target); // 1 千万个整数中存在目标值：78685631
        }
    }

    public static void test3() {

        int[] data = createData();
        int target = data[8888];

        // KeyPoint 3. 只有 20 MB 给你 --> 位图
        // 给你 1 千万个整数，整数的范围在 1 到 1 亿之间，
        // 如何快读的判断某个整数是否在这个 1 千万个整数中呢?
        BitMap1 bitMap = new BitMap1(100_000_000); // 100_000_000 bit = 12MB
        for (int i = 0; i < data.length; i++) {
            bitMap.set(data[i]);
        }

        if (bitMap.contains(target)) { // O(1)
            System.out.println("1 千万个整数中存在目标值：" + target); // 1 千万个整数中存在目标值：78685631
        }
    }
}
