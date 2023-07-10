package alg_01_ds_dm._01_line._05_algo._04_bs_二刷.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 15:30
 * @Version 1.0
 */
public class _07_1095_FindInMountainArray1 {

    /*
        1095. 山脉数组中查找目标值
        （这是一个 交互式问题）

        给你一个 山脉数组mountainArr，请你返回能够使得
        mountainArr.get(index) 等于 target 最小的下标 index 值。

        如果不存在这样的下标 index，就请返回-1。

        何为山脉数组？如果数组A 是一个山脉数组的话，那它满足如下条件：
        1.首先，A.length >= 3
        2.其次，在0 < i< A.length - 1条件下，存在 i 使得：
            2.1 A[0] < A[1] < ... A[i-1] < A[i]
            2.2 A[i] > A[i+1] > ... > A[A.length - 1]
        
        你将不能直接访问该山脉数组，必须通过MountainArray接口来获取数据：
        1.MountainArray.get(k)- 会返回数组中索引为k的元素（下标从 0 开始）
        2.MountainArray.length()- 会返回该数组的长度

        注意：
        对MountainArray.get发起超过 100 次调用的提交将被视为错误答案。
        此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
        
        为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：
        https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。

        示例 1：
        输入：array = [1,2,3,4,5,3,1], target = 3
        输出：2
        解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。

        示例 2：
        输入：array = [0,1,2,4,2,1], target = 3
        输出：-1
        解释：3 在数组中没有出现，返回 -1。


        提示：
        3 <= mountain_arr.length() <= 10000
        0 <= target <= 10^9
        0 <= mountain_arr.get(index) <= 10^9

     */

    interface MountainArray {
        // 接口默认 public，可以供外部调用，不需要显示声明 public 可以省略
        // 抽象方法没有方法体 {}，只有函数签名
        int get(int index);

        int length();
    }

    // 时间复杂度 O(logn)
    public int findInMountainArray(int target, MountainArray nums) {

        // 思路：山脉数组，在峰顶处进行划分，前一半有序，后一半有序，故可以使用二分查找，查找 target
        // 1 3 4 5 6 8 9 4 3  1
        // ↑   有序     ↑ 有序 ↑

        // 注意:山脉数组长度 > 3，判空条件可以省略

        // 1. 找到峰顶元素索引 => 转化：力扣 852 算法题
        int peakIndex = searchPeakIndex(nums);

        // 2. 在前半部分应用二分查找算法查找目标值 => 找到直接返回，求的是最小的下标 index 值
        int index = BSFrontPart(nums, peakIndex, target);
        if (index != -1) {
            return index;
        }

        // 3. 在后半部分应用二分查找算法查找目标值
        return BSLatterPart(nums, peakIndex, target);
    }

    // 1. 找到峰顶元素索引
    private int searchPeakIndex(MountainArray nums) {
        int left = 0;
        int right = nums.length() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < nums.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 2. 在前半部分，升序序列，二分查找
    // KeyPoint 之前 BS 代码模板，都是针对升序序列，若是降序序列，需要对 BS 模板进行修改
    private static int BSFrontPart(MountainArray nums, int peakIndex, int target) {
        int left = 0;
        int right = peakIndex;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums.get(mid)) {
                return mid;
            } else if (target < nums.get(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // 3. KeyPoint 在后半部分，降序序列，不再是升序序列
    //             => 变形二分查找  不能单纯地复制升序序列代码
    private static int BSLatterPart(MountainArray nums, int peakIndex, int target) {
        int left = peakIndex;
        int right = nums.length() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums.get(mid)) {
                return mid;
            } else if (target < nums.get(mid)) {
                // KeyPoint 针对降序序列 => 只需要将 BS 中 else if 和 else 代码对调即可
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
