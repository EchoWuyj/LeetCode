package alg_02_train_wyj._01_day_数组技巧;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-17 23:22
 * @Version 1.0
 */
public class _00_Note {

    public static Map<Integer, Integer> countArray1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 5, 4, 5, 6, 2, 1};
        System.out.println(countArray1(nums));
        System.out.println(Arrays.toString(countArray2(nums)));
    }

    public static int[] countArray2(int[] arr) {
        int[] counts = new int[6];
        for (int num : arr) {
            counts[num - 1]++;
        }
        return counts;
    }
}
