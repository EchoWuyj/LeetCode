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
        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0) + 1;
            if (cnt > nums.length / 2) return num;
            map.put(num, cnt);
        }
        return -1;
    }

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElement3(int[] nums) {
        int k = nums.length / 2 + 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> b - a);
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.remove();
            }
        }
        return pq.peek();
    }

    private Random random = new Random(System.currentTimeMillis());

    public int majorityElement4(int[] nums) {
        int k = nums.length / 2 + 1;
        int left = 0, right = nums.length - 1;
        int target = nums.length - k;
        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    public int partition(int[] nums, int left, int right) {
        if (right > left) {
            int pivotIndex = random.nextInt(right - left + 1) + left;
            swap(nums, right, pivotIndex);
        }
        int pivot = nums[right];
        int less = left, great = left;
        for (; great < right; great++) {
            if (nums[great] < pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, right);
        return less;
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
