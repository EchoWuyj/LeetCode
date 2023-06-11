package alg_02_train_dm._11_day_优先队列;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 11:16
 * @Version 1.0
 */
public class _03_347_top_k_frequent_elements {

     /*
        347 号算法题：前 K 个高频元素
        给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。
        你可以按 任意顺序 返回答案。

        输入: nums = [1,1,1,2,2,3], k = 2
        输出: [2, 1]

        输入: nums = [1], k = 1
        输出: [1]

        1 <= nums.length <= 10^5
        k 的取值范围是 [1, 数组中不相同的元素的个数]
        题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的

        进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
        KeyPoint => 优于 O(nlogn)，说明不能直接排序，不能全局排序，但是可以考虑局部排序，或者某个元素排序
     */

    // KeyPoint 方法一 小顶堆
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(n) => 数组中没有重复元素，空间大小 n
    // => 如果我们只关心与输入规模，即为数组 nums 大小，空间复杂度是 O(n)
    public int[] topKFrequent1(int[] nums, int k) {
        // 统计词频
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // 空间复杂度 O(k) < O(n)，
        PriorityQueue<Integer> pq
                // 按照出现次数，升序排列
                // Comparator => 直接使用 Lambda 表达式 (a,b) -> 比较逻辑
                = new PriorityQueue<>(k + 1, (a, b) -> count.get(a) - count.get(b));

        // 小堆顶，堆顶出现次数为最小值
        for (int num : count.keySet()) { // O(n)
            // 小顶堆，堆顶以下(包括堆顶)，则为前 k 个高频
            pq.add(num); // O(logk)
            if (pq.size() > k) pq.remove();
        }

        int[] res = new int[k];
        int index = 0;
        // 小顶堆中存储的就是出现了前 k 个高频的元素，将其复制到 res 中即可
        while (!pq.isEmpty()) {
            res[index++] = pq.remove();
        }
        return res;
    }

    // KeyPoint 直接在定义变量的同时，也就创建了对象
    private Random random = new Random(System.currentTimeMillis());
    private Map<Integer, Integer> count = new HashMap<>();

    // KeyPoint 方法二 快速排序分区优化
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int[] topKFrequent(int[] nums, int k) {
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

        // 获取 keyNums 后面 k 个最大元素，但是这个 k 个最大元素不一定是有序的
        // Arrays.copyOfRange，范围拷贝，需要有个 range，返回值为 int[]
        return Arrays.copyOfRange(keyNums, keyNums.length - k, keyNums.length);
    }

    // KeyPoint less 和 left 尽量不要同时出现的，避免自动提示给出错误提示，使用 low 和 high 替换
    private int partition(int[] nums, int low, int high) {
        if (high > low) {
            int pivotIndex = low + 1 + random.nextInt(high - low);
            swap(nums, pivotIndex, high);
        }
        // 比较元素出现次数，而不是比较元素本身
        int pivot = count.get(nums[high]);
        int less = low, great = low;
        for (; great < high; great++) {
            // 升序排列
            if (count.get(nums[great]) < pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, high);
        return less;
    }

    // KeyPoint 数组越界 => 逐层往上分析，找原因，而不是漫无目的找 bug
    //  1.swap没有问题，则可能是 partition 的 low 和 high 问题
    //  2.partition 没有问题，则 topKFrequent 中 left 和 right 问题
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
