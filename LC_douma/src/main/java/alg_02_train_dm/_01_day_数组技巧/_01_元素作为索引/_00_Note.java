package alg_02_train_dm._01_day_数组技巧._01_元素作为索引;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:31
 * @Version 1.0
 */
public class _00_Note {
    /*
        通过数组实现字典(HashMap)
        count 3 1 0 1 3 1 -> 索引对应元素值 value
              0 1 2 3 4 5 -> 索引          key

        数组占用空间比 HashMap 要小，HashMap 是数组加链表实现，为了避免哈希冲突，数组中有很大部分是空闲的

     */

    // 计算输入数组中每个元素出现的次数
    // KeyPoint 方法一 HashMap
    private static Map<Integer, Integer> countArray1(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        return countMap;
    }

    // KeyPoint 方法二 数组
    // 前提: nums 中元素值范围不能太大，否则输出结果的数组很大，可能导致内存溢出
    private static int[] countArray2(int[] arr) {
        int[] countMap = new int[6];
        for (int num : arr) {
            // 元素值作为索引下标
            countMap[num - 1]++;
        }
        return countMap;
    }

    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 5, 4, 5, 6, 2, 1};

        Map<Integer, Integer> res1 = countArray1(nums);
        System.out.println(res1); //{1=3, 2=1, 4=1, 5=3, 6=1}

        int[] res2 = countArray2(nums);
        System.out.println(Arrays.toString(res2)); // [3, 1, 0, 1, 3, 1]
    }
}
