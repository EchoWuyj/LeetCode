package alg_02_train_wyj._13_day_综合应用一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 19:18
 * @Version 1.0
 */
public class _09_169_majority_element {

    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > n / 2) return num;
        }
        return -1;
    }

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n / 2];
    }

    public int majorityElement3(int[] nums) {
        int n = nums.length;
        int size = n / 2 + 1;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(size, (a, b) -> b - a);
        for (int num : nums) {
            if (maxHeap.size() < size) {
                maxHeap.add(num);
            } else {
                if (num < maxHeap.peek()) {
                    maxHeap.remove();
                    maxHeap.add(num);
                }
            }
        }
        return maxHeap.peek();
    }

    private Random random = new Random(System.currentTimeMillis());

    public int majorityElement4(int[] nums) {

    }

    public int partition(int[] nums, int left, int right) {

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int majorityElement5(int[] nums) {
        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            } else if (count == 0) {
                candidate = num;
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}
