package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 13:04
 * @Version 1.0
 */
public class _08_493_reverse_pairs {

    /*
        493.翻转对
        给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
        你需要返回给定数组中的重要翻转对的数量。

        示例 1:
        输入: [1,3,2,3,1]
        输出: 2

        示例 2
        输入: [2,4,3,5,1]
        输出: 3
        2 -> 没有
        4 -> 1
        3 -> 1
        5 -> 1
        1 -> 没有

        注意:
        给定数组的长度不会超过 50000。
        输入数组中的所有数字都在 32 位整数的表示范围内。

     */

    // 本质：和区间和的个数一样，只是修改了规则
    public int reversePairs(int[] nums) {
        int[] tmp = new int[nums.length];
        return mergeSort(nums, 0, nums.length - 1, tmp);
    }

    private int mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;

        int leftSumCount = mergeSort(nums, left, mid, tmp);
        int rightSumCount = mergeSort(nums, mid + 1, right, tmp);
        int count = 0;
        // 计算当前翻转对的个数
        int i = left;
        int j = mid + 1;
        while (i <= mid) {
            // i 和 j 都是不回退的
            // 输入数组中的所有数字都在 32 位整数的表示范围内
            // => 2 * nums[j] 可能越界，需要将其转成 long 类型
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) j++;
            // 不满足 while 循环，j 在满足 while 循环的后一个位置，故直接相减即可
            count += (j - mid - 1);
            // 执行 while 循环，直到 [i] > 2*[j] 不满足，说明 i 不够大，此时再去移动 i
            i++;
        }

        merge(nums, left, mid, right, tmp);

        return leftSumCount + rightSumCount + count;
    }

    private void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        for (int index = left; index <= right; index++) {
            if (i == mid + 1) nums[index] = tmp[j++];
            else if (j == right + 1) nums[index] = tmp[i++];
            else {
                if (tmp[i] <= tmp[j]) nums[index] = tmp[i++];
                else nums[index] = tmp[j++];
            }
        }
    }
}
