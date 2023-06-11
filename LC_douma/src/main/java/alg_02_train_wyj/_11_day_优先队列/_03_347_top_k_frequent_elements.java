package alg_02_train_wyj._11_day_优先队列;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 14:37
 * @Version 1.0
 */
public class _03_347_top_k_frequent_elements {

    public int[] topKFrequent1(int[] nums, int k) {
        if (nums == null) return nums;
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq
                = new PriorityQueue<>(k + 1, (o1, o2) -> count.get(o1) - count.get(o2));

        for (int num : count.keySet()) {
            pq.add(num);
            if (pq.size() > k) pq.remove();
        }

        int[] res = new int[k];
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.remove();
        }
        return res;
    }

    private Map<Integer, Integer> count = new HashMap<>();
    private Random random = new Random(System.currentTimeMillis());

    public int[] topKFrequent2(int[] nums, int k) {
        if (nums == null) return nums;
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        int[] keyNums = new int[count.size()];
        int index = 0;
        for (int num : count.keySet()) keyNums[index++] = num;

        int left = 0, right = keyNums.length - 1;
        int target = keyNums.length - k;

        while (true) {
            int pivotIndex = partition(keyNums, left, right);
            if (pivotIndex == target) {
                break;
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return Arrays.copyOfRange(keyNums, target, keyNums.length);
    }

    public int partition(int[] nums, int left, int right) {
        if (right > left) {
            int pivotIndex = random.nextInt(right - left + 1) + left;
            swap(nums, pivotIndex, right);
        }
        int pivot = count.get(nums[right]);
        int less = left, great = left;
        for (; great < right; great++) {
            if (count.get(nums[great]) < pivot) {
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
}
