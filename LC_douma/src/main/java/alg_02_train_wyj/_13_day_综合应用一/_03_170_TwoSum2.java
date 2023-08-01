package alg_02_train_wyj._13_day_综合应用一;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 15:02
 * @Version 1.0
 */
public class _03_170_TwoSum2 {
    static class TwoSum {
        Map<Integer, Integer> map;

        public TwoSum() {
            map = new HashMap<>();
        }

        public void add(int num) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        public boolean find(int value) {
            for (int num1 : map.keySet()) {
                int num2 = value - num1;
                if (num1 == num2 && map.get(num1) > 1) return true;
                if (num1 != num2 && map.containsKey(num2)) return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.add(1);
        twoSum.add(3);
        twoSum.add(5);
        System.out.println(twoSum.find(4)); // true
        System.out.println(twoSum.find(7)); // false
    }
}
