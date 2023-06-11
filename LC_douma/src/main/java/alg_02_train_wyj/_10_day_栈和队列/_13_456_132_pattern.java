package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.TreeMap;

/**
 * @Author Wuyj
 * @DateTime 2023-04-28 12:10
 * @Version 1.0
 */
public class _13_456_132_pattern {
    public boolean find132pattern1(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean find132pattern2(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int numi = nums[0];
        for (int j = 1; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (numi < nums[k] && nums[k] < nums[j]) {
                    return true;
                }
            }
            numi = Math.min(numi, nums[j]);
        }
        return false;
    }

    public boolean find132pattern3(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int numi = nums[0];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int k = 2; k < n; k++) {
            treeMap.put(nums[k], treeMap.getOrDefault(nums[k], 0) + 1);
        }

        for (int j = 1; j < n - 1; j++) {
            if (nums[j] > numi) {
                Integer numk = treeMap.ceilingKey(numi + 1);
                if (numk != null && numk < nums[j]) return true;
            }
            numi = Math.min(numi, nums[j]);
            treeMap.put(nums[j + 1], treeMap.get(nums[j + 1]) - 1);
            if (treeMap.get(nums[j + 1]) == 0) treeMap.remove(nums[j + 1]);
        }
        return false;
    }

    public boolean find132pattern4(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int[] minPrefix = new int[n];
        minPrefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            minPrefix[i] = Math.min(minPrefix[i - 1], nums[i]);
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        for (int j = n - 2; j >= 1; j--) {
            if (nums[j] > minPrefix[j]) {
                while (!stack.isEmpty() && stack.peek() <= minPrefix[j]) {
                    stack.pop();
                }

                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }

                stack.push(nums[j]);
            }
        }
        return false;
    }

    public boolean find132pattern5(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        int mask = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            if (mask > nums[i]) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                mask = stack.peek();
                stack.pop();
            }
            if (nums[i] > mask) stack.push(nums[i]);
        }
        return false;
    }
}
