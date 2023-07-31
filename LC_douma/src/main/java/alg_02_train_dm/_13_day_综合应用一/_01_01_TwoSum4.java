package alg_02_train_dm._13_day_综合应用一;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 13:05
 * @Version 1.0
 */
public class _01_01_TwoSum4 {

    // KeyPoint 方法四 哈希查找 + 两次遍历 => 空间换时间
    // 空间换时间 => 绝大多数情况，时间和空间是不可兼得的
    // 时间复杂度：O(2n)
    // 空间复杂度：O(n)
    public static int[] twoSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        // 数据预处理 => 建立映射表
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) { // O(n)
            // nums[i] 相同，但是 i 是不同的
            map.put(nums[i], i);
            // System.out.println(map);
        }
        for (int i = 0; i < n; i++) { // O(n)
            int num1 = nums[i];
            // 哈希查找 -> O(1)
            if (map.containsKey(target - num1)) {
                int index = map.get(target - num1);
                // 因为有两次独立 for 循环，此时获取 index 可能是 i，题目要求：同一个元素不能使用两次
                // i != index => num1 和 target - num1 不是同一个元素
                // KeyPoint 注意：i 和 index 使用的 for 循环中对应的索引，不是 map 中对应的索引，不存在覆盖情况
                if (i != index) return new int[]{i, index};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 3};
        System.out.println(Arrays.toString(twoSum4(nums, 6)));
    }

    // KeyPoint 方法五 哈希查找 + 一次遍历 => 空间换时间 => 最优解
    // 空间换时间，绝大多数情况，时间和空间是不可兼得的
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int[] twoSum(int[] nums, int target) {
        // KeyPoint 判空逻辑递进
        // 判空先后逻辑有讲究，先是 null，再是 nums.length，避免空指针异常
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) { // O(n)
            int num1 = nums[i];
            int num2 = target - num1;
            // 哈希查找
            if (map.containsKey(num2)) {
                int index = map.get(num2);
                // 同一个 for 循环，此时 map 还没有 put(num1, i)，故不需要 i != index
                return new int[]{i, index};
            }
            // 不在也要将其放进 map 中，后面别的元素可能要使用到
            map.put(num1, i);
        }
        return null;
    }
}
