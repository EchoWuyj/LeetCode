package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 11:58
 * @Version 1.0
 */

// 153. 寻找旋转排序数组中的最小值
public class _05_lc_153_offer_11_MinArray {

      /*
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
    public int findMin_1(int[] nums) {
        int minVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minVal = Math.min(minVal, nums[i]);
        }
        return minVal;
    }

    // KeyPoint 方法二 暴力解法：遍历数组，但是找到了比前一位小的数字，就是最小值
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int findMin_2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return nums[i];
            }
        }
        return nums[0];
    }

    //  KeyPoint 方法三 根据旋转数组特点，使用二分查找
    //                  => 存在 bug，该方法只是适用于数组中没有重复元素的情况
    // 时间复杂度是： O(logn)
    // 空间复杂度是：O(1)
    public int findMin_3(int[] nums) {
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
