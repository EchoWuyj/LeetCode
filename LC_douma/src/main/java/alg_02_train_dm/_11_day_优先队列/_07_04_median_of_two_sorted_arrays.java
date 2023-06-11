package alg_02_train_dm._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-23 13:42
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays {

    /*
        4 号算法题：寻找两个正序数组的中位数

        给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2
        请你找出并返回这两个正序数组的 中位数

        输入：nums1 = [1,3], nums2 = [2]
        输出：2.00000
        解释：合并数组 = [1,2,3] ，中位数 2

        输入：nums1 = [1,2], nums2 = [3,4]
        输出：2.50000
        解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

        提示
        1. nums1.length == m
        2. nums2.length == n
        3. 0 <= m <= 1000、0 <= n <= 1000、1 <= m + n <= 2000
        4. -10^6 <= nums1[i], nums2[i] <= 10^6

        进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     */

    // 大顶堆 => 存储较小的一半元素
    private PriorityQueue<Integer> maxHeap;
    // 小顶堆 => 存储较大的一半元素
    private PriorityQueue<Integer> minHeap;

    // KeyPoint 方法一 大顶堆 + 小顶堆 => 题解能通过，但是击败了 7.91% 的用户，性能比较差
    // 将 nums1 和 nums2 中所有元素都放入到 maxHeap 和 minHeap 中，这样就可以直接获取中位数
    // 时间复杂度：O(mlogm + nlog(m + n))
    // 空间复杂度：O(m + n)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();

        // O(mlogm)
        for (int num : nums1) addNum(num);
        // O(nlog(m + n))
        for (int num : nums2) addNum(num);

        if (maxHeap.size() > minHeap.size()) {
            // 说明有奇数个元素，那么大顶堆堆顶元素就是中位数
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }
    }

    // 从数据流中添加一个整数到数据结构中
    // 时间复杂度：log(n)
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    // KeyPoint 总结
    // 1.对于已经有序数组，再使用堆就有点绕远了，使用堆主要是避免排序
    // 2.对于无序数组，使用大顶堆和小顶堆，寻找第 k 大值，中位数，其性能还是很好的

    // KeyPoint 方法二 合并排序 + 使用额外空间
    // 充分利用题目给的信息 => nums1 和 nums2 已经有序，联想归并排序
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] tmp = new int[m + n];

        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                tmp[k++] = nums1[i++];
            } else {
                tmp[k++] = nums2[j++];
            }
        }

        while (i < m) tmp[k++] = nums1[i++];
        while (j < n) tmp[k++] = nums2[j++];

        // 奇数
        if ((m + n) % 2 == 1) {
            return tmp[(m + n) / 2];
            // 偶数
        } else {
            return (tmp[(m + n) / 2] + tmp[(m + n - 1) / 2]) / 2.0;
        }
    }

    // KeyPoint 方法三 合并排序 + 不使用额外空间
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        int lower = -1, upper = -1;
        int start1 = 0, start2 = 0;
        // 中位数在排序后数组的中间位置，故只要迭代到中间位置就可以了，故不需要循环整个 len
        // 以 upper 指针正确位置为标准，执行 len/2+1 次循环即可
        for (int i = 0; i <= len / 2; i++) {
            // lower 紧跟着 upper，从而保证 lower 和 upper 是紧挨着的
            lower = upper;
            // 因为 upper 是中间靠右位置，所以先移动 upper
            if (start1 < m && (start2 >= n || nums1[start1] < nums2[start2])) {
                upper = nums1[start1++];
            } else {
                upper = nums2[start2++];
            }
        }
        return len % 2 == 0 ? (lower + upper) / 2.0 : upper;
    }

    // KeyPoint 方法四 二分查找 => 最优解
    // 数组有序 + 时间复杂度对数级别 => 二分查找
    // 时间复杂度：O(log(m + n))
    // 空间复杂度：O(1)
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 注意：k 对应是第 k 小元素值，对应个数，不是索引
        // 通过 +1 和 +2，将偶数和奇数的情况合并
        //  1.若 m+n 奇数，leftK 和 rightK 同一个位置
        //  2.若 m+n 偶数，leftK 偏左 和 rightK 偏右 位置
        int leftK = (m + n + 1) / 2;
        int rightK = (m + n + 2) / 2;

        // leftNum => 数值
        int leftNum = getKth(nums1, nums2, leftK);
        // rightNum => 数值
        int rightNum = getKth(nums1, nums2, rightK);
        return (leftNum + rightNum) * 0.5;
    }

    // 两个有序数组，找第 k 小元素
    // 本质：不断使用二分去排除不可能存在的区间 => 对 k 二分
    private int getKth(int[] nums1, int[] nums2, int k) {

        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0;
        // 使用 while 循环，不断进行二分，不断排除不可能存在的区间
        while (true) {

            // 边界条件判断：i ==m，则 nums1 排除完，k 小的数据必然在 nums2 中，因为索引关系，故需要减 1
            if (i == m) return nums2[j + k - 1];
            if (j == n) return nums1[i + k - 1];

            // 退出条件 => 最后第 1 小元素，就是 nums1[i] 和 nums2[j] 中的最小值
            if (k == 1) return Math.min(nums1[i], nums2[j]);

            // 计算 newi 和 newj 需要注意两点：
            // 1.nums1 和 nums2 长度不定，其中数组可能没有第 k/2 个数字
            //   所以我们需要先检查一下，数组中到底存不存在第K/2个数字
            //   num1 长度 m 可能很小，而 i + (k / 2) 有可能大于 m，此时会越界，加上 min 从而避免越界
            // 2.需要减 1，因为 newi 下标是从 0 开始的，表示索引，所以需要减 1
            int newi = Math.min(i + (k / 2), m) - 1;
            int newj = Math.min(j + (k / 2), n) - 1;

            // 排除小的部分
            if (nums1[newi] < nums2[newj]) {
                // k 减去被排除的长度，newi 和 i 两端都要包括，故需要加 1
                k = k - (newi - i + 1);
                // 排除 i 前面或者 j 前面的元素，i 或 j 往前走一步，故计算 i 和 j 的时候加 1
                i = newi + 1;
            } else {
                k = k - (newj - j + 1);
                j = newj + 1;
            }
        }
    }
}
