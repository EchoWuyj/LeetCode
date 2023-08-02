package alg_02_train_dm._13_day_综合应用一_二刷;

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

        Map<Integer, Integer> map;

        public TwoSum() {
            map = new HashMap<>();
        }

        // 添加一个元素 -> O(1)
        public void add(int num) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 查找是否存在两个数，这两个数的和等于 value
        // O(n)
        public boolean find(int value) {
            for (int num1 : map.keySet()) {
                int num2 = value - num1;
                // 只有两个数 => 分情况讨论：两个数相等和不等情况
                // 1.相等，且个数 > 2
                if (num2 == num1 && map.get(num2) > 1) return true;
                // 2.不相等，num2 存在num2
                if (num2 != num1 && map.containsKey(num2)) return true;
            }
            return false;
        }
    }

    // test2
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.add(1);
        twoSum.add(3);
        twoSum.add(5);
        System.out.println(twoSum.find(4)); // true
        System.out.println(twoSum.find(7)); // false
    }
}
