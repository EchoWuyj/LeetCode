package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 10:06
 * @Version 1.0
 */
public class _04_88_merge_sorted_array {

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m + n];
        int i = 0, j = 0;
        for (int index = 0; index < (m + n); index++) {
            if (i == m) {
                tmp[index] = nums2[j++];
            } else if (j == n) {
                tmp[index] = nums1[i++];
            } else if (nums1[i] <= nums2[j]) {
                tmp[index] = nums1[i++];
            } else {
                tmp[index] = nums2[j++];
            }
            System.out.println(tmp[index]);
        }

        for (int index = 0; index < (m + n); index++) {
            nums1[index] = tmp[index];
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                i--;
                k--;
            } else if (j >= 0) {
                nums1[k] = nums2[j];
                j--;
                k--;
            }
        }
    }
}
