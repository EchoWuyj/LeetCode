package alg_02_train_wyj._13_day_综合应用一;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 15:55
 * @Version 1.0
 */
public class _08_350_IntersectionOfTwoArraysII {
    public int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        for (int num : nums2) {
            if (countMap.containsKey(num) && countMap.get(num) > 0) {
                res[index++] = num;
                countMap.put(num, countMap.get(num) - 1);
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        int i = 0;

        while (i < nums1.length) {
            int firstTargetIndex = bs(nums2, nums1[i]);
            if (firstTargetIndex == -1) {
                i++;
                continue;
            }

            int count = 0;
            while (firstTargetIndex < nums2.length && nums2[firstTargetIndex] == nums1[i]) {
                count++;
                firstTargetIndex++;
            }

            int j = i;
            while (j < nums1.length && nums1[j] == nums1[i]) {
                j++;
                if (count > 0) {
                    res[index++] = nums1[i];
                    count--;
                }
            }
            i = j;
        }
        return Arrays.copyOfRange(res, 0, index);
    }

    public int bs(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }

    public int[] intersect3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res[index++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }
}
