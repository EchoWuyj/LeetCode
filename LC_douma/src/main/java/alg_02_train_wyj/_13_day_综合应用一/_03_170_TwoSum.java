package alg_02_train_wyj._13_day_综合应用一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-24 20:25
 * @Version 1.0
 */
public class _03_170_TwoSum {
    static class TwoSum1 {
        private List<Integer> nums;
        private boolean isSorted;

        public TwoSum1() {
            nums = new ArrayList<>();
            isSorted = false;
        }

        public void add(int num) {
            nums.add(num);
            isSorted = false;
        }

        public boolean find(int value) {
            if (!isSorted) {
                Collections.sort(nums);
                isSorted = true;
            }

            int left = 0;
            int right = nums.size() - 1;
            while (left < right) {
                int sum = nums.get(left) + nums.get(right);
                if (sum == value) {
                    return true;
                } else if (sum < value) {
                    left++;
                } else {
                    right--;
                }
            }
            return false;
        }
    }

    public static void test1() {
        TwoSum1 twoSum1 = new TwoSum1();
        twoSum1.add(1);
        twoSum1.add(3);
        twoSum1.add(5);
        System.out.println(twoSum1.find(4)); // true
        System.out.println(twoSum1.find(7)); // false
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test2() {
        TwoSum2 twoSum2 = new TwoSum2();
        twoSum2.add(1);
        twoSum2.add(3);
        twoSum2.add(5);
        System.out.println(twoSum2.find(4)); // true
        System.out.println(twoSum2.find(7)); // false
    }

    static class TwoSum2 {
        private Map<Integer, Integer> nums;

        public TwoSum2() {
            nums = new HashMap<>();
        }

        public void add(int num) {
            nums.put(num, nums.getOrDefault(num, 0) + 1);
        }

        public boolean find(int value) {
            for (Integer num : nums.keySet()) {
                int target = value - num;
                if (target == num && nums.get(target) > 1) return true;
                if (target != num && nums.containsKey(target)) return true;
            }
            return false;
        }
    }
}
