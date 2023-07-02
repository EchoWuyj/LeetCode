package alg_01_ds_wyj._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-07-02 15:06
 * @Version 1.0
 */
public class _07_1095_FindInMountainArray2 {

    interface MountainArray {
        int get(int index);

        int length();
    }

    public static int findInMountainArray(int target, MountainArray nums) {
        if (nums == null || nums.length() == 0) return -1;

        int peakIndex = findPeek(nums);

        int res = findTargetFromFrontPart(target, nums, peakIndex);
        if (res != -1) return res;
        res = findTargetFromLatterPart(target, nums, peakIndex);
        return res;
    }

    private static int findTargetFromFrontPart(int target, MountainArray nums, int peakIndex) {
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

    private static int findTargetFromLatterPart(int target, MountainArray nums, int peakIndex) {
        int left = peakIndex + 1, right = nums.length() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (nums.get(left) == target) return left;
        return -1;
    }

    private static int findPeek(MountainArray nums) {
        int left = 0, right = nums.length() - 1;
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
}
