package alg_02_train_dm._07_day_排序算法;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 10:47
 * @Version 1.0
 */

// 了解
public class _06_315_count_of_smaller_numbers_after_self_2 {
    private int[] indexes;
    private int[] tmpIndexes;

    private int[] ans;

    public List<Integer> countSmaller(int[] nums) {
        this.indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) indexes[i] = i;
        this.tmpIndexes = new int[nums.length];

        this.ans = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);

        List<Integer> list = new ArrayList<Integer>();
        for (int num : ans) {
            list.add(num);
        }
        return list;
    }

    private void mergeSort(int[] nums, int lo, int hi, int[] tmp) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;

        mergeSort(nums, lo, mid, tmp);
        mergeSort(nums, mid + 1, hi, tmp);

        merge(nums, lo, mid, hi, tmp);
    }

    // 使用另一种 merge 的方法 => 使用不多
    private void merge(int[] data, int left, int mid, int right, int[] tmp) {
        int tmpPos = left;
        int i = left;
        int j = mid + 1;
        // 将左边和右边的元素按照顺序拷贝到临时的数组中
        while (i <= mid && j <= right) {
            if (data[i] <= data[j]) {
                tmp[tmpPos] = data[i];
                tmpIndexes[tmpPos] = indexes[i];
                // 计算比当前元素小的后面元素的个数
                ans[indexes[i]] += (j - mid - 1);
                tmpPos++;
                i++;
            } else { // data[i] > data[j]
                tmp[tmpPos] = data[j];
                tmpIndexes[tmpPos] = indexes[j];
                tmpPos++;
                j++;
            }
        }
        // 如果左边还有元素，则直接将左边的元素拷贝到临时数组
        while (i <= mid) {
            tmp[tmpPos] = data[i];
            tmpIndexes[tmpPos] = indexes[i];
            // 计算比当前元素小的后面元素的个数
            ans[indexes[i]] += (j - mid - 1);
            tmpPos++;
            i++;
        }
        // 如果右边还有元素，则直接将右边的元素拷贝到临时数组
        while (j <= right) {
            tmp[tmpPos] = data[j];
            tmpIndexes[tmpPos] = indexes[j];
            tmpPos++;
            j++;
        }
        // 拷贝
        for (tmpPos = left; tmpPos <= right; tmpPos++) {
            data[left] = tmp[tmpPos];
            indexes[left] = tmpIndexes[tmpPos];
            left++;
        }
    }
}
