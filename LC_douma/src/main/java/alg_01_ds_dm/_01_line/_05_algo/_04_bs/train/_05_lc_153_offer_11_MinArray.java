package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 11:58
 * @Version 1.0
 */

//
public class _05_lc_153_offer_11_MinArray {

      /*
            153. 寻找旋转排序数组中的最小值

            已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
            例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
            若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
            若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]

            注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组
             [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

            给你一个元素值 互不相同 的数组 nums ，它原来是 一个升序排列 的数组，
            并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

            你必须设计一个时间复杂度为O(log n) 的算法解决此问题。

            示例 1：
            输入：nums = [3,4,5,1,2]
            输出：1
            解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。

            示例 2：
            输入：nums = [4,5,6,7,0,1,2]
            输出：0
            解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。

            提示：
            n == nums.length
            1 <= n <= 5000
            -5000 <= nums[i] <= 5000
            nums 中的所有整数 互不相同
            nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转

           KeyPoint 补充说明：旋转排序数组

            4 5 6 7 0 1 2
                    ↑
                  旋转点

            旋转数组特点
            1 前半部分有序，后半部分有序
            2 前半部分所有有序元素的值都大于后半部分所有有序元素的值
            => 部分有序旋转数组查找目标元素，也可以使用二分查找
     */

    // KeyPoint 方法一 暴力解法：遍历一遍数组找到最小值
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int findMin1(int[] nums) {
        int minVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minVal = Math.min(minVal, nums[i]);
        }
        return minVal;
    }

    // KeyPoint 方法二 暴力解法：遍历数组，但是找到了比前一位小的数字，就是最小值
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int findMin2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 数据走势上：类似山脉数组  ↗↘
            if (nums[i] < nums[i - 1]) {
                return nums[i];
            }
        }
        // 数组严格递增，且数组旋转一圈又变成已原样，则返回 nums[0]
        return nums[0];
    }

    //  KeyPoint 方法三 根据旋转数组特点，使用二分查找
    //                  => 存在 bug，该方法只是适用于数组中没有重复元素的情况
    // 时间复杂度是： O(logn)
    // 空间复杂度是：O(1)
    public int findMin3(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            // 关键是 if 条件，比较对象 nums[mid]，nums[left]，nums[right] 的选择问题
            // 旋转数组特点 => 最小值必然是旋转点，即找最小值 <=> 找旋转点
            // nums[mid] > nums[right] 非正常情况，min 必然在右侧，mid 以左，包括 mid 全部舍弃
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // 在数组中有重复元素时，nums[mid] <= nums[right]
                // 若只是直接 right = mid，则存在 bug，right 移动太多，导致略过了最小值
                right = mid;
            }
        }
        return nums[left];
    }

    // KeyPoint 方法三 改进，修复 bug，适用于数组中存在重复元素的情况
    public int findMin4(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // left < right，保证搜索区间中有 1 个元素存在
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                // nums[mid] = nums[right] 时，只是移动一格，而不是 right = mid
                right--;
            }
        }
        // 最终 left == right，返回最小值，不是 left 索引
        return nums[left];
    }

    // 错误代码
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // if 判断条件找的不对，测试用例不完全通过
            // 有些情况可能一开始成立，但移动 left 指针之后就不成立
            // 处理'旋转数组'和'正常数组'逻辑不统一
            //  4 5 6 7 0 1 2
            //  ↑     ↑     ↑
            // left  mid  right  => 成立
            //  0   1   2
            //  ↑   ↑   ↑
            // left mid right  => 不成立
            if (nums[left] < nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
