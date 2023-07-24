package alg_02_train_dm._11_day_优先队列;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-07-21 23:45
 * @Version 1.0
 */
public class _03_347_top_k_frequent_elements2 {

    // KeyPoint 方法二 快速排序分区优化
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int[] topKFrequent(int[] nums, int k) {
        // 统计词频
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int[] keyNums = new int[count.size()];
        int index = 0;
        for (int num : count.keySet()) keyNums[index++] = num;

        int len = keyNums.length;
        int left = 0, right = len - 1;
        int target = len - k;
        while (true) {
            int pivotIndex = partition(keyNums, left, right, count);
            if (pivotIndex == target) {
                // 通过 break 结束循环，而不是通过 return 来结束
                break;
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

        // KeyPoint 特别注意
        // 1.获取 keyNums 后面 k 个最大元素，但是这个 k 个最大元素不一定是有序的
        // 2.Arrays.copyOfRange
        //   2.1 数组范围拷贝，故需要有个 range
        //   2.2 包括 from，但是不包括 to
        //   2.3 返回值为数组 int[]
        return Arrays.copyOfRange(keyNums, len - k, len);
    }

    // KeyPoint 变量命名，避免混淆
    // less 和 left 尽量不要同时出现的，避免自动提示给出错误提示，使用 low 和 high 替换
    private int partition(int[] nums, int low, int high, Map<Integer, Integer> count) {
        if (high > low) {
            // 直接创建了对象 new Random()，并且调用 nextInt 方法
            int pivotIndex = new Random().nextInt(high - low + 1) + low;
            swap(nums, pivotIndex, high);
        }
        // KeyPoint 分区比较规则变化
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

    // KeyPoint 解决 bug 经验
    // 1.数组越界，先检查 swap 部分代码，swap 没有问题逐层往上找，找调用 swap 的方法
    //   可能是 partition 的 low 和 high 问题
    // 2.若partition 没有问题，则 topKFrequent 中 left 和 right 问题
    // KeyPoint 总结
    // 找 bug 一定有章法，而不是漫无目的找 bug
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
