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
            int cnt = map.getOrDefault(num, 0) + 1;
            if (cnt > n / 2) return num;
            map.put(num, cnt);
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

    public int majorityElement4(int[] nums) {
        int n = nums.length;
        int k = n / 2 + 1;
        int left = 0, right = n - 1;
        int target = n - k;
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
            int pivotIndex = new Random().nextInt(right - left + 1) + left;
            swap(nums, pivotIndex, right);
        }
        int pivot = nums[right];
        int less = left, great = less;
        for (; great < right; great++) {
            if (nums[great] > pivot) {
                swap(nums, less, great);
                left++;
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
        return merge(nums, 0, nums.length - 1);
    }

    public int merge(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int mid = left + (right - left) / 2;

        int leftNum = merge(nums, left, mid);
        int rightNum = merge(nums, mid + 1, right);
        if (leftNum == rightNum) return leftNum;

        int leftNumCnt = count(nums, leftNum, left, right);
        int rightNumCnt = count(nums, rightNum, left, right);
        return leftNumCnt > rightNumCnt ? leftNum : rightNum;
    }

    public int count(int[] nums, int num, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (num == nums[i]) count++;
        }
        return count;
    }

    public int majorityElement6(int[] nums) {
        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            } else {
                if (count == 0) {
                    candidate = num;
                    count++;
                } else {
                    count--;
                }
            }
        }
        return candidate;
    }
}
