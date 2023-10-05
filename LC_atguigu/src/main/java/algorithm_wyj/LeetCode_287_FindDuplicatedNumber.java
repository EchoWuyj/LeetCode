package algorithm_wyj;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-09-24 13:37
 * @Version 1.0
 */
public class LeetCode_287_FindDuplicatedNumber {

    // 排序 => 修改数组
    public int findDuplicate01(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) return nums[i];
        }
        return -1;
    }

    // 二分查找
    public int findDuplicate02(int[] nums) {
        int left = 1;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }

            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
            if (left == right) {
                return left;
            }
        }
        return -1;
    }
}
