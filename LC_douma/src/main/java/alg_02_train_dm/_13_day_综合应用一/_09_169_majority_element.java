package alg_02_train_dm._13_day_综合应用一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 19:12
 * @Version 1.0
 */
public class _09_169_majority_element {

    /*
        169. 多数元素(求众数)
        给定一个大小为 n 的数组，找到其中的多数元素。
        多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

        你可以假设数组是非空的，并且给定的数组总是存在多数元素。

        输入：[3,2,3]
        输出：3

        输入：[2,2,1,1,1,2,2]
        输出：2

        进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     */

    // KeyPoint 方法一 哈希查找
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // 每次计数加 1，否则 cnt 没有变化
            int cnt = map.getOrDefault(num, 0) + 1;
            if (cnt > nums.length / 2) return num;
            map.put(num, cnt);
        }
        return -1;
    }

    // KeyPoint 方法二 排序查找
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn) 或者 O(n)
    public int majorityElement2(int[] nums) {
        // 既然存在众数，其出现次数必然大于 ⌊n/2⌋ ，若将数组排序(全局排序)，则中间元素必然是众数
        Arrays.sort(nums);
        // 索引 nums.length/2，对应元素个数 nums.length/2 +1
        return nums[nums.length / 2];
    }

    // KeyPoint 方法三 堆查找
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public int majorityElement3(int[] nums) {
        // 局部排序，找 nums.length / 2 小的元素 => 大顶堆
        // 注意区别：第 k 小 和 第 k 大
        // 1.第 k 小 => 从数组头开始计数
        //   [1,2,3,4,5]，第 2 小，指的是 2
        // 2.第 k 大 => 从数组尾开始计数
        //   [1,2,3,4,5]，第 2 大，指的是 4

        // k 一定是数组中点偏右的位置
        int k = nums.length / 2 + 1;
        // 查找第 k 小元素
        // 大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> b - a);
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) pq.remove();
        }
        return pq.peek();
    }

    // KeyPoint 方法四 快速排序分区优化 => 查找第 k 小的元素 => 最优解
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    private Random random = new Random(System.currentTimeMillis());

    public int majorityElement4(int[] nums) {
        // 查找第 k 小元素
        int k = nums.length / 2 + 1;
        int left = 0, right = nums.length - 1;
        // KeyPoint 查找第 k 小元素 => 转成第 n-k 大元素 => 快速排序分区优化
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

    // 分区
    private int partition(int[] nums, int left, int right) {
        if (right > left) {
            int pivotIndex = left + random.nextInt(right - left + 1);
            swap(nums, pivotIndex, right);
        }
        int pivot = nums[right];
        int less = left, great = left;
        for (; great < right; great++) {
            if (nums[great] < pivot) {
                // KeyPoint 交换是 less，而不是 left，需要特别小心
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, right);
        return less;
    }

    // 交换
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // KeyPoint 方法五 分治
    // 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
    // 反证法：若 a 的个数小于 (l/2) + (r/2) = (l+r)/2 = n/2
    // 将数组一分为二，只要在其中一部分找到众数，即可返回 => 分治思想
    // 场景：若能将问题一分为二，一般都是能使用分治思想来做
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn)
    public int majorityElement5(int[] nums) {
        return majorityElement(nums, 0, nums.length - 1);
    }

    // 类似：归并排序
    // 求数组 low 和 high 区间的众数
    private int majorityElement(int[] nums, int low, int high) {
        // 只有一个元素，则自己就是众数
        if (low == high) return nums[low];

        int mid = low + (high - low) / 2;
        // 在 [low,mid] 区间上的众数
        int leftNum = majorityElement(nums, low, mid);
        // 在 [mid，high] 区间上的众数
        int rightNum = majorityElement(nums, mid + 1, high);

        // 左边众数 == 右边众数，则作为整个数组的众数，直接返回
        if (leftNum == rightNum) return leftNum;

        // 在 [low,high] 分别统计 leftNumCnt 和 rightNumCnt，谁多谁就是众数
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

    // KeyPoint 方法六 Boyer-Moore 投票算法
    // 两个数不相等，将其抵消掉，直到剩下的相同的数，该数就是众数
    // 该算法包含两个部分
    //  1.投票 => 不同数字，不同票数，将其抵消
    //  2.计数 => 最后剩下的数字重新计算出现次数是否大于 n/2(但是本题不需要)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int majorityElement(int[] nums) {

        // 摩尔投票算法 => 核心就是对拼消耗
        // 投票法是遇到相同的则票数 +1，遇到不同的则票数 -1，相当于抵消
        // 且"多数元素"的个数 > n/2，其余元素的个数总和 <= n/2
        // 因此"多数元素"的个数 - 其余元素的个数总和的结果肯定 >=1。
        // 这就相当于每个"多数元素"和其他元素两两相互抵消，抵消到最后肯定还剩余至少 1 个"多数元素"

        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            } else if (count == 0) {
                // count 为 0，则将 num 作为新的候选人
                candidate = num;
                count++;
            } else {
                count--;
            }
        }
        // 抵消到最后剩下的元素就是众数
        return candidate;
    }
}
