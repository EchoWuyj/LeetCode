package alg_03_leetcode_top_wyj.class_04;

/**
 * @Author Wuyj
 * @DateTime 2023-02-18 11:11
 * @Version 1.0
 */
public class Problem_0033_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int m = 0;

        while (l <= r) {
            m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }

            if (nums[l] == nums[m] && nums[m] == nums[r]) {
                while (l != m && nums[l] == nums[m]) {
                    l++;
                }
                if (l == m) {
                    l = m + 1;
                    continue;
                }
            }

            // nums[m]!=target
            if (nums[l] != nums[m]) {
                if (nums[m] > nums[l]) {
                    // [l,m]有序
                    if (target >= nums[l] && target < nums[m]) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                } else {
                    // [m,r] 有序
                    if (target > nums[m] && target <= nums[r]) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
                // nums[l],nums[m],nums[r]不全相等
                // 且nums[l]=nums[m],则num[m]!=nums[r]
                // 对num[m]!=nums[r]进行细分
            } else {
                if (nums[m] > nums[r]) {
                    // [l,m]有序
                    if (target >= nums[l] && target < nums[m]) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                } else {
                    // [m,r]有序
                    if (target > nums[m] && target <= nums[r]) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
            }
        }
        return -1;
    }
}
