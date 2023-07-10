package alg_02_train_wyj._07_day_排序算法;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 20:20
 * @Version 1.0
 */
public class _06_315_count_of_smaller_numbers_after_self {

    private static int[] indexes;
    private static int[] tmpIndexes;
    private static int[] count;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null) return res;
        int n = nums.length;
        indexes = new int[n];
        for (int i = 0; i < n; i++) indexes[i] = i;
        tmpIndexes = new int[n];

        int[] tmp = new int[n];
        count = new int[n];
        sort(nums, 0, n - 1, tmp);

        for (int i : count) {
            res.add(i);
        }
        return res;
    }

    public void sort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sort(nums, left, mid, tmp);
        sort(nums, mid + 1, right, tmp);
        merge(nums, left, mid, right, tmp);
    }

    public void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
            tmpIndexes[i] = indexes[i];
        }

        int i = left;
        int j = mid + 1;
        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                nums[index] = tmp[j];
                indexes[index] = tmpIndexes[j];
                j++;
            } else if (j == right + 1) {
                nums[index] = tmp[i];
                indexes[index] = tmpIndexes[i];
                count[tmpIndexes[i]] += (j - mid - 1);
                i++;
            } else if (tmp[i] <= tmp[j]) {
                nums[index] = tmp[i];
                indexes[index] = tmpIndexes[i];
                count[tmpIndexes[i]] += (j - mid - 1);
                i++;
            } else {
                nums[index] = tmp[j];
                indexes[index] = tmpIndexes[j];
                j++;
            }
        }
    }
}
