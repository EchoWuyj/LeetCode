package alg_02_train_dm._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 16:34
 * @Version 1.0
 */

// 1539. 第 k 个缺失的正整数
public class _14_1539_kth_missing_positive_number {

    /*
        给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。
        请你找到这个数组里第 k 个缺失的正整数。

        提示：
            1 <= arr.length <= 1000
            1 <= arr[i] <= 1000
            1 <= k <= 1000
            对于所有 1 <= i < j <= arr.length 的 i 和 j 满足 arr[i] < arr[j]

        KeyPoint 对于任何算法问题，没有什么好的思路，通过直接模拟来解决
     */

    // KeyPoint 方法一 时间复杂度：O(n + k)
    // 最坏的情况：缺失的正整数都在最后元素往后，故还需要遍历 k 次
    public int findKthPositive1(int[] arr, int k) {
        // 从 1 开始，逐个比对，确定丢失正整数
        int currNum = 1;
        int missCnt = 0;
        int lastMissNum = -1;

        // i 用于遍历数组 arr 索引
        int i = 0;
        while (missCnt < k) {
            if (currNum == arr[i]) {
                // bug 修复：注意 i 的边界
                // i + 1 < arr.length => i + 1 <=  arr.length -1，即 i 没有越界
                // 若 i + 1 >= arr.length，i = arr.length -1，则保持不变，还是 i
                i = (i + 1 < arr.length) ? i + 1 : i;
            } else {
                // 没有在数组 arr 中找到对应的 currNum，则丢失数加 1
                missCnt++;
                // 记录丢失正数值，最后达到 k 次，将其返回
                lastMissNum = currNum;
            }
            currNum++;
        }
        return lastMissNum;
    }

    // KeyPoint 方法二 二分查找
    public int findKthPositive(int[] arr, int k) {

        // 数组 1 2 3 4 5
        // 索引 0 1 2 3 4
        // 当没有缺失数字的情况下，数字和索引差 1 的关系，即有 arr[i] - i - 1 = 0

        //     数组      2 3 4 7 11
        //     索引      0 1 2 3 4
        // 缺失元素个数  1 1 1 3 6
        // => 元素 arr[i] 之前缺失的正整数的个数为：a[i] - i - 1
        // => 每个元素缺失的正整数的个数，组成的数列是升序

        // 找第 k 个缺失的正整数
        // => 找到'第一个'前面缺失数字数'大于等于' k 的元素
        // => 二分查找

        // 解释：right 为什么是 arr.length 而非 arr.length - 1
        // 因为这里你需要考虑缺失的数字可能是数组最后一个元素的下一个整数
        // 所以，这里进行二分的时候，范围必须是 [0....nums.length] 中查找

        // 比如：[1, 2, 3, 4, 5]  k = 1
        // 那么这个时候第 1 个缺失的数字是 6 ，是数组最后一个元素的下一个整数
        // 就是说你要查找的数字有可能超出数组的范围

        // 0 ~ k 数字都缺失了，则直接返回 k
        if (arr[0] > k) return k;

        int left = 0, right = arr.length;
        // 不断排除不是 >= k 的区间，通过思路二来求解，最后能确定 left 的位置，便于后续操作
        while (left < right) {
            int mid = left + (right - left) / 2;
            // arr[mid] - mid - 1 >= k 反面 => arr[mid] - mid - 1 < k
            // 类似于 if (target > nums[mid])，最好方向一致，即 target 在前，[mid] 在后
            if (arr[mid] - mid - 1 < k) {
                left = mid + 1;
            } else {
                // arr[mid] - mid - 1 >= k
                // '找第一' => 保证 right 从右往左向 mid 移动
                right = mid;
            }
        }
        // 为 left 第一个前面缺失数字数'大于等于' k 元素的下标索引
        // 根据找到的目标值 [left] 前面一个元素 [left-1]，来计算出第 k 个缺失的正整数
        int leftMissCnt = arr[left - 1] - (left - 1) - 1;
        return arr[left - 1] + (k - leftMissCnt);
    }
}
