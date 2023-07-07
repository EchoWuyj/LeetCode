package alg_02_train_dm._08_day_二分查找_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 16:34
 * @Version 1.0
 */

public class _14_1539_kth_missing_positive_number {

    /*
        1539. 第 k 个缺失的正整数
        给你一个 严格升序排列 的 正整 数数组 arr 和一个整数 k 。
        请你找到这个数组里第 k 个缺失的正整数。

        示例 1：
        输入：arr = [2,3,4,7,11], k = 5
        输出：9
        解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。

        示例 2：
        输入：arr = [1,2,3,4], k = 2
        输出：6
        解释：缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。

        提示：
        1 <= arr.length <= 1000
        1 <= arr[i] <= 1000
        1 <= k <= 1000
        对于所有 1 <= i < j <= arr.length 的 i 和 j 满足 arr[i] < arr[j]

     */

    // KeyPoint 方法一 时间复杂度：O(n + k)
    // 最坏的情况：缺失的正整数都在最后元素往后，故还需要遍历 k 次
    // KeyPoint 直接模拟
    // 1.对于任何算法问题，一开始没有什么好的思路，则通过直接模拟方式来解决
    // 2.在模拟过程中，通过给定的测试用例数据，在该数据上进行模拟操作
    // 3.具体模拟操作，按照题目指定的要去来执行，将其翻译成代码语言
    //   => 先整体(代码框架)，后细节(边界)
    public int findKthPositive(int[] arr, int k) {

        // 从 1 开始，逐个比对，确定丢失正整数
        int currNum = 1;
        int missCnt = 0;
        int lastMissNum = -1;

        // i 用于遍历数组 arr 索引
        int i = 0;
        while (missCnt < k) {

            if (currNum == arr[i]) {
                // KeyPoint bug 修复：注意 i 的边界
                // i + 1 < arr.length
                // => i + 1 <= arr.length - 1
                // => i <= arr.length - 2，即 i 没有越界
                // 若 i + 1 >= arr.length，i = arr.length -1，则保持不变，还是 i
                i = (i + 1 < arr.length) ? i + 1 : i;
            } else {
                // 没有在数组 arr 中找到对应的 currNum，则丢失数加 1
                missCnt++;
                // 记录丢失正数值，最后 while 循环条件判断，达到 k 次，将其返回
                lastMissNum = currNum;
            }
            currNum++;
        }
        return lastMissNum;
    }

    public int findKthPositive1(int[] arr, int k) {
        int curNum = 1;
        int missCnt = 0;
        int LastNum = -1;

        int n = arr.length;
        int index = 0;
        while (missCnt < k) {
            // 凡是涉及数组索引操作，一定需要考虑是否越界
            // index++，最后可能越界，故需要 index < n 限制
            // 此时 if 分支不执行，只执行 else 分支
            if (index < n && arr[index] == curNum) {
                index++;
            } else {
                missCnt++;
                LastNum = curNum;
            }
            curNum++;
        }
        return LastNum;
    }

    // KeyPoint 方法二 二分查找
    public int findKthPositive2(int[] arr, int k) {

        // 数组 1 2 3 4 5
        // 索引 0 1 2 3 4
        // 常规情况：当没有缺失数字的情况下，数字和索引差 1 的关系
        // 即有 arr[index] - index - 1 = 0

        // 数组         2 3 4 7 11 => 因为存在缺失数字，故递增值可能突然变大
        // 索引         0 1 2 3 4  => 每次递增 1
        // 缺失元素个数  1 1 1 3 6  => arr[index] - index - 1 = 计算缺失元素个数

        // num[0] - 0 - 1 = 1 => 缺失 1 个元素
        // num[1] - 1 - 1 = 1 => 缺失 1 个元素
        // ...

        // => 元素 arr[index] 之前缺失的正整数的个数为：arr[index] - index - 1
        // => 每个元素缺失的正整数的个数，组成的数列是升序，后面缺失元素个数是包括前面
        // => 1 1 1 3 6

        // 找第 k 个缺失的正整数
        // => 找到 '第一个'前面缺失数字数'大于等于' k 的元素
        // => 找到 '第一个大于等于' target 元素 => 二分查找

        // arr[0] > k，1 ~ k 数字都缺失了，则直接返回 k
        if (arr[0] > k) return k;

        int left = 0, right = arr.length;

        // 解释：right 为什么是 arr.length 而非 arr.length - 1
        // 因为这里你需要考虑缺失的数字，可能是数组最后一个元素的下一个整数
        // 所以，这里进行二分的时候，范围必须是 [0....nums.length] 中查找

        // 比如：[1, 2, 3, 4, 5]  k = 1
        // 此时第 1 个缺失的数字是 6 ，是数组最后一个元素的下一个整数
        // => 即要查找的数字有可能超出数组的范围

        // 不断排除不是 >= k 的区间，通过思路二来求解，最后能确定 left 的位置，便于后续操作
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 找到 '第一个大于等于' target 元素
            // => if (target > nums[mid]) target 为中心，target 在前，[mid] 在后
            // => k > arr[mid] - mid - 1
            if (k > arr[mid] - mid - 1) {
                left = mid + 1;
            } else {
                // arr[mid] - mid - 1 >= k
                // '找第一' => 保证 right 从右往左向 mid 移动
                right = mid;
            }
        }
        // 为 left 第一个缺失数字数'大于等于' k 元素的下标索引
        // 根据找到的目标值 [left] 前面一个元素 [left-1]，来计算出第 k 个缺失的正整数
        int leftMissCnt = arr[left - 1] - (left - 1) - 1;

        // 剩余缺失数字个数 => k-leftMissCnt
        // 从 arr[left-1] 开始累加
        return arr[left - 1] + (k - leftMissCnt);
    }
}
