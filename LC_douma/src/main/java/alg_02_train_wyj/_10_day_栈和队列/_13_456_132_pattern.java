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
        int numsi = nums[0];
        for (int j = 1; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (numsi < nums[k] && nums[k] < nums[j]) {
                    return true;
                }
                numsi = Math.min(numsi, nums[j]);
            }
        }
        return false;
    }

    public boolean find132pattern3(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int numsi = nums[0];
        TreeMap<Integer, Integer> numskMap = new TreeMap<>();
        for (int k = 2; k < n; k++) {
            numskMap.put(nums[k], numskMap.getOrDefault(nums[k], 0) + 1);
        }

        for (int j = 1; j < n - 1; j++) {
            if (nums[j] > numsi) {
                Integer numsk = numskMap.ceilingKey(numsi + 1);
                if (numsk != null && nums[j] > numsk) {
                    return true;
                }
            }
            numsi = Math.min(numsi, nums[j]);
            numskMap.put(nums[j + 1], numskMap.get(nums[j + 1]) - 1);
            if (numskMap.get(nums[j + 1]) == 0) numskMap.remove(nums[j + 1]);
        }
        return false;
    }

    public boolean find132pattern4(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int[] prefixMin = new int[n];
        prefixMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMin[i] = Math.min(prefixMin[i - 1], nums[i]);
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        for (int j = n - 2; j >= 1; j--) {
            if (nums[j] > prefixMin[j]) {
                while (!stack.isEmpty() && prefixMin[j] >= stack.peek()) {
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
        int maxK = Integer.MIN_VALUE;

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < maxK) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                maxK = stack.peek();
                stack.pop();
            }
            if (nums[i] > maxK) stack.push(nums[i]);
        }
        return false;
    }
}
