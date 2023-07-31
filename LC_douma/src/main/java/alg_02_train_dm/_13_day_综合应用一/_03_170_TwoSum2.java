package alg_02_train_dm._13_day_综合应用一;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 14:47
 * @Version 1.0
 */
public class _03_170_TwoSum2 {
    // KeyPoint 方法二 哈希查找
    static class TwoSum {
        // key -> 数组元素
        // value -> 数组元素出现次数
        private Map<Integer, Integer> nums;

        public TwoSum() {
            nums = new HashMap<>();
        }

        // 添加一个元素 -> O(1)
        public void add(int num) {
            nums.put(num, nums.getOrDefault(num, 0) + 1);
        }

        // 查找是否存在两个数，这两个数的和等于 value
        // O(n)
        public boolean find(int value) {
            for (Integer num : nums.keySet()) {
                int target = value - num;
                // 只有两个数 => 分情况讨论：两个数相等和不等情况
                if (target == num && nums.get(target) > 1) return true;
                if (target != num && nums.containsKey(target)) return true;
            }
            return false;
        }
    }

    // test2
    public static void test2() {
        TwoSum twoSum = new TwoSum();
        twoSum.add(1);
        twoSum.add(3);
        twoSum.add(5);
        System.out.println(twoSum.find(4)); // true
        System.out.println(twoSum.find(7)); // false
    }
}
