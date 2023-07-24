package alg_02_train_wyj._11_day_优先队列;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 15:19
 * @Version 1.0
 */
public class _03_347_top_k_frequent_elements2 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        int size = count.size();
        int[] keyArr = new int[size];
        int index = 0;
        for (int key : count.keySet()) {
            keyArr[index++] = key;
        }
        int len = keyArr.length;
        int left = 0, right = len - 1;
        int target = len - k;
        while (true) {
            int pivotIndex = partition(keyArr, left, right, count);
            if (pivotIndex == target) {
                break;
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return Arrays.copyOfRange(keyArr, len - k, len);
    }

    public int partition(int[] nums, int low, int high, Map<Integer, Integer> count) {
        if (high > low) {
            int pivotIndex = new Random().nextInt(high - low + 1) + low;
            swap(nums, pivotIndex, high);
        }

        int pivot = count.get(nums[high]);
        int less = 0, great = 0;
        for (; great < high; great++) {
            if (count.get(nums[great]) < pivot) {
                swap(nums, great, less);
                less++;
            }
        }
        swap(nums, high, less);
        return less;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
