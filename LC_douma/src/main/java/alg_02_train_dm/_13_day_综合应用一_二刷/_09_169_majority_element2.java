package alg_02_train_dm._13_day_综合应用一_二刷;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-08-01 12:52
 * @Version 1.0
 */
public class _09_169_majority_element2 {

    // KeyPoint 方法四 快速排序分区优化 => 查找第 k 小的元素 => 最优解
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int majorityElement4(int[] nums) {
        // 查找第 k 小元素
        int n = nums.length;
        int k = n / 2 + 1;
        int left = 0, right = n - 1;
        // KeyPoint 查找第 k 小元素
        int target = n - k;
        // 二分查找
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

    // 分区
    private int partition(int[] nums, int left, int right) {
        if (right > left) {
            int pivotIndex = new Random().nextInt(right - left + 1) + left;
            swap(nums, pivotIndex, right);
        }
        int pivot = nums[right];
        int less = left, great = left;
        for (; great < right; great++) {
            // 快排降序，使用 > 符号
            if (nums[great] > pivot) {
                // KeyPoint 易错点
                // 1.交换是 less，而不是 left，需要特别小心，不要给自动提示误导了
                // 2.less 在交换后，也是需要往前移动的
                swap(nums, less, great);
                less++;
            }
        }
        // right 对应元素，最终在 less 位置
        swap(nums, less, right);
        return less;
    }

    // 交换
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // KeyPoint 方法五 分治思想
    // 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分数组，那么 a 必定是其中一部分数组的众数。
    // 如：[2,2,1,1,1,2,2] => 划分 [2,2,1,|1,1,2,2]
    //                             ------ --------
    //                               l       r
    // 反证法：若 a 既不是左边众数，又不是右边众数
    // => a 的个数 < (l/2) 且 < (r/2)
    // => a 的个数 < (l+r)/2 = n/2，和 a 为 nums 的众数相矛盾
    // 分治思想
    // => 将数组一分为二，只要在其中一部分找到众数，即可返回
    // 场景：若能将问题一分为二，一般都是能使用分治思想来做
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn)
    public int majorityElement5(int[] nums) {
        return majorityElement(nums, 0, nums.length - 1);
    }

    // 类似：归并排序
    // 求数组 low 和 high 区间的众数
    // 返回值即为 [low,high] 区间的众数
    private int majorityElement(int[] nums, int low, int high) {
        // 数组中只有一个元素，则自身就是众数，直接返回
        if (low == high) return nums[low];

        int mid = low + (high - low) / 2;
        // 在 [low,mid] 区间上的众数
        int leftNum = majorityElement(nums, low, mid);
        // 在 [mid，high] 区间上的众数
        int rightNum = majorityElement(nums, mid + 1, high);

        // 合并过程
        // 1.左边众数 == 右边众数，则作为整个数组的众数，直接返回
        if (leftNum == rightNum) return leftNum;
        // 2.在 [low,high] 分别统计 leftNumCnt 和 rightNumCnt，谁多谁就是众数
        int leftNumCnt = countInRange(nums, leftNum, low, high);
        int rightNumCnt = countInRange(nums, rightNum, low, high);
        return leftNumCnt > rightNumCnt ? leftNum : rightNum;
    }

    // O(n)
    private int countInRange(int[] nums, int num, int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    // KeyPoint 方法六 Boyer-Moore 投票算法 => 扩充自己知识面
    // 核心思想：两个数不相等，将其抵消掉，直到剩下的相同的数，该数就是众数
    // 该算法包含两个部分
    // 1.投票 => 不同数字算作不同票数，将其抵消
    // 2.计数 => 最后剩下的数字可能是众数，需要重新计算出现次数是否大于 n/2
    // 注意：本题保证一定存在众数，不需要重新计算
    //   比如：7 6  5  7 2 1  7
    //        |--| |--| |--| 剩下元素 7 不是众数
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int majorityElement(int[] nums) {

        // 摩尔投票算法 => 核心就是对拼消耗
        // 投票法
        // 1.遇到相同的则票数 +1，遇到不同的则票数 -1，相当于抵消
        // 2.且"多数元素"的个数 > n/2，其余元素的个数总和 <= n/2
        // 3.因此"多数元素"的个数 - 其余元素的个数总和的结果肯定 >=1。
        // 这就相当于每个"多数元素"和其他元素两两相互抵消，抵消到最后肯定还剩余至少 1 个"多数元素"

        // 候选人
        int candidate = -1;
        // 票数
        int count = 0;
        for (int num : nums) {
            // num 和候选人相同，count++
            if (num == candidate) {
                count++;
            } else {
                // num 和候选人不同，判断 count
                if (count == 0) {
                    // 若 count 为 0，则将 num 作为新的候选人
                    // 将 num 值赋值给 candidate
                    candidate = num;
                    count++;
                } else {
                    count--;
                }
            }
        }
        // 抵消到最后剩下的元素就是众数
        return candidate;
    }
}

