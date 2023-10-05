package alg_02_train_dm._11_day_优先队列_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 19:43
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays4_推荐 {

    // KeyPoint 方法四 二分查找 => 最优解，需要掌握
    // 数组有序 + 时间复杂度对数级别 log => 二分查找
    // 时间复杂度：O(log(m + n))
    // 空间复杂度：O(1)
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // KeyPoint 注意事项
        // 1.k 对应是第 k 小元素值，对应第几个，不是索引
        // 2.通过 +1 和 +2，将偶数和奇数的情况合并

        // m = 2，n = 3
        // 若 m+n 奇数
        // 0 1 2 3 4
        //     ↑
        // 中位数：第 3 小元素
        // leftK = (5+1) / 2 = 3
        // rightK = (5+2) / 2 = 3
        // => leftK 和 rightK 指向同一位置

        // m = 3，n = 3
        // 若 m+n 偶数
        //  0 1 2 3 4 5
        //      ↑ ↑
        // 中位数：第 3 小元素 和 第 4 小元素
        // leftK = (6+1) / 2 = 3
        // rightK = (6+2) / 2 = 4
        // => leftK 偏左 和 rightK 偏右 位置

        int leftK = (m + n + 1) / 2;
        int rightK = (m + n + 2) / 2;

        // leftNum => 数值
        int leftNum = getKth(nums1, nums2, leftK);
        // rightNum => 数值
        int rightNum = getKth(nums1, nums2, rightK);

        return (leftNum + rightNum) * 0.5;
    }

    // KeyPoint 两个有序数组，找第 k 小元素
    // KeyPoint 本质：不断使用二分，排除不可能存在的区间 => 对 k 二分
    private int getKth(int[] nums1, int[] nums2, int k) {

        // KeyPoint ==== 第一轮分析 ====

        // m + n = 14，k = (m + n) / 2 = 7 => 关键找第 7 小元素
        // 二分思想 => k / 2 = 3 => 分析 nums1 和 nums2 前 3 个元素

        // nums1 m： 1 3 4 9
        //               ↑
        //               i

        // nums2 n： 1 2 3 4 5 6 7 8 9 10
        //               ↑
        //               j

        // nums1[i] > nums2[j] => 第 7 小元素不在 [0,j] 元素中，故将其排除

        // KeyPoint === 第二轮分析 ====

        // k = 7 - 3 = 4  => 关键找第 4 小元素
        // 二分思想 => k / 2 = 2 => 分析 nums1 和 nums2 前 2 个元素

        // nums1 m： 1 3 4 9
        //             ↑
        //             i

        // nums2 n： 4 5 6 7 8 9 10
        //             ↑
        //             j

        // nums1[i] < nums2[j] => 第 4 小元素不在 [0,i] 元素中，故将其排除

        // KeyPoint === 第三轮分析 ====

        // k = 4 - 2 = 2  => 关键找第 2 小元素
        // 二分思想 => k / 2 = 1 => 分析 nums1 和 nums2 前 1 个元素

        // nums1 m： 4 9
        //           ↑
        //           i

        // nums2 n： 4 5 6 7 8 9 10
        //           ↑
        //           j

        // nums1[i] = nums2[j] => 排除上下都可以，这里排除下面 nums2 中 j 位置对应元素

        // KeyPoint === 第四轮分析 ====

        // k = 2 - 1 = 1  => 关键找第 1 小元素
        // 此时，不用二分，直接比较 nums1[i] 和 nums2[j]，看两个元素谁小

        // nums1 m： 4 9
        //           ↑
        //           i

        // nums2 n： 5 6 7 8 9 10
        //           ↑
        //           j

        // nums1[i] < nums2[j]，返回 nums1[i] 即可

        // KeyPoint 时间复杂度
        // 每次对 k 取一半，时间复杂度 log(k)，其中 k = (m + n) / 2，故时间复杂度 log(m + n)

        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0;
        // 使用 while 循环，不断进行二分，不断排除不可能存在的区间
        while (true) {

            // KeyPoint 边界条件判断
            // 每次新的一轮循环开始，i，j，k 都已经更新了
            // i == m，则 nums1 排除完，k 小的数据必然在 nums2 中，[j] 自身算一个元素，故索引需要减 1
            // KeyPoint 注意是 +k，而不是 -k，同时使用的 i 和 j，不是 m 和 n，动态变化
            if (i == m) return nums2[j + k - 1];
            if (j == n) return nums1[i + k - 1];

            // 退出条件 => 最后第 1 小元素，就是 nums1[i] 和 nums2[j] 中的最小值
            if (k == 1) return Math.min(nums1[i], nums2[j]);

            // 确定 newi 和 newj 位置，比较 nums1[newi] 和 nums2[newj] 大小
            // KeyPoint 计算 newi 和 newj 需要注意两点
            // 1.nums1 和 nums2 长度不定，其中数组可能没有第 k/2 个数字
            //   所以我们需要先检查一下，数组中到底存不存在第 k/2 个数字
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
                // KeyPoint System.out.println(1 - +1); // 输出结果 0，其中 + 表示正号，记得将 - 删除干净
                k = k - (newj - j + 1);
                j = newj + 1;
            }
        }
    }
}
