package alg_03_leetcode_top_zcy.class_04_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-18 12:42
 * @Version 1.0
 */

// 在排序数组中查找元素的第一个和最后一个位置
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {

    /*
        给你一个按照 非递减顺序排列的整数数组 nums,和一个目标值target
        请你找出给定目标值在数组中的开始位置和结束位置

        [1,1,2,3,3,3,4,4]  target=3
        返回3开始位置和结束位置 [3,5],
        若不存在返回[-1,-1]

        思路:使用两个二分BSNearLeft,BSNearRight,主函数分别调用,从而得到起始和结束的位置
        注意:这里不能直接使用BSNearLeft和BSNearRight,因为原始的代码照顾你>=target,如果target不在数组中,
            则返回>=target最近的值,而本题应该严格找到,找不到则应该返回-1

        int[] test = {5, 7, 7, 8, 8, 10};
        int i = BSNearLeft(test, 6); 返回值 1

     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = BSNearLeft(nums, target);
        int end = BSNearRight(nums, target);
        return new int[]{start, end};
    }

    public int BSNearLeft(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 只有严格相等时,才进行赋值操作
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                index = mid;
                r = mid - 1;
            }
        }
        return index;
    }

    public int BSNearRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                index = mid;
                l = mid + 1;
            }
        }
        return index;
    }
}
