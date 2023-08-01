package alg_02_train_wyj._13_day_综合应用一;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 21:17
 * @Version 1.0
 */
public class _07_349_IntersectionOfTwoArrays2 {
    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Arrays.sort(nums1);
        HashSet<Integer> set = new HashSet<>();
        for (int num2 : nums2) {
            if (binarySearch(nums1, num2)) {
                set.add(num2);
            }
        }
        int size = set.size();
        int[] res = new int[size];
        int index = 0;
        for (int num : set) {
            res[index++] = num;
        }
        return res;
    }

    public boolean binarySearch(int[] num, int target) {
        int left = 0, right = num.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (num[mid] == target) {
                return true;
            } else if (num[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
