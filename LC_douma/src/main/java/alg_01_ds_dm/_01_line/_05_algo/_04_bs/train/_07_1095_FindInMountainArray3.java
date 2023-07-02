package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-07-02 14:44
 * @Version 1.0
 */
public class _07_1095_FindInMountainArray3 {

    interface MountainArray {
        int get(int index);

        int length();
    }

    // 时间复杂度 O(logn)
    public int findInMountainArray(int target, MountainArray nums) {

        int peakIndex = searchPeakIndex(nums);
        int res = BSFrontPart(nums, peakIndex, target);
        if (res != -1) {
            return res;
        }
        res = BSLatterPart(nums, peakIndex, target);
        return res;
    }

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

    private int BSFrontPart(MountainArray nums, int peakIndex, int target) {
        int left = 0, right = peakIndex;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums.get(left) == target) return left;
        return -1;
    }

    private int BSLatterPart(MountainArray nums, int peakIndex, int target) {
        int left = peakIndex + 1, right = nums.length() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 针对降序数组，将 > 修改成 <，其余保持和升序魔板代码一样
            // 区别于：思路一：在循环体内 => 将 BS 中 else if 和 else 代码对调即可
            // left →↘
            //        ↘
            //    mid → ↘
            //            ↘ ← target
            //              ↘ ← right
            if (target < nums.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums.get(left) == target) return left;
        return -1;
    }
}
