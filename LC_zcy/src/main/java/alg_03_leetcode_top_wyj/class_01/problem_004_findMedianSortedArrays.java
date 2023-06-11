package alg_03_leetcode_top_wyj.class_01;

import javax.sound.midi.MidiChannel;

/**
 * @Author Wuyj
 * @DateTime 2023-02-17 21:35
 * @Version 1.0
 */
public class problem_004_findMedianSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) != 1;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2D;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2D;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

    public static int findKthNum(int[] arr1, int[] arr2, int Kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;

        int l = longs.length;
        int s = shorts.length;

        if (Kth <= s) {
            return getUpMedian(longs, shorts, 0, Kth - 1, 0, Kth - 1);
        }

        if (Kth > l) {
            if (shorts[Kth - l - 1] >= longs[l - 1]) {
                return shorts[Kth - l - 1];
            }
            if (longs[Kth - s - 1] >= shorts[s - 1]) {
                return longs[Kth - s - 1];
            }
            return getUpMedian(shorts, longs, Kth - l, s - 1, Kth - s, l - 1);
        }

        if (longs[Kth - s - 1] >= shorts[s - 1]) {
            return longs[Kth - s - 1];
        }
        return getUpMedian(shorts, longs, 0, s - 1, Kth - s, Kth - 1);
    }

    public static int  getUpMedian(int[] arr1, int[] arr2, int s1, int e1, int s2, int e2) {
        int m1 = 0;
        int m2 = 0;

        while (s1 < e1) {
            m1 = (s1 + e1) / 2;
            m2 = (s2 + e2) / 2;

            if (arr1[m1] == arr2[m2]) {
                return arr1[m1];
            }

            // 奇数长度
            if (((e1 - s1 + 1) & 1) == 1) {
                // 大于
                if (arr1[m1] > arr2[m2]) {
                    if (arr2[m2] >= arr1[m1 - 1]) {
                        return arr2[m2];
                    }
                    e1 = m1 - 1;
                    s2 = m2 + 1;
                    // 小于
                } else {
                    if (arr1[m1] >= arr2[m2 - 1]) {
                        return arr1[m1];
                    }
                    e2 = m2 - 1;
                    s1 = m1 + 1;
                }
                // 偶数长度
            } else {
                // 大于
                if (arr1[m1] > arr2[m2]) {
                    e1 = m1;
                    s2 = m2 + 1;
                    // 小于
                } else {
                    e2 = m2;
                    s1 = m1 + 1;
                }
            }
        }
        return Math.min(arr1[s1], arr2[s2]);
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        System.out.println(findMedianSortedArrays(arr1, arr2));
    }
}
