package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-09 23:29
 * @Version 1.0
 */
public class _07_327_count_of_range_sum2 {

    // KeyPoint 问题转换
    // num           9 -3 4  5  -2  -1  -4
    // prefixSum   0 9  6 10 15 13  12  8
    // 查找 [i，j] 对，使得：prefixSum[j] - prefixSum[i] 在 [lower，upper] 范围内
    // 找到所有符合条件的 [i，j]对，其中 i < j，
    // 注意：i < j，否则 prefixSum[j] - prefixSum[i] = 0，则不是一个有效区间，不满足题目要求

    // KeyPoint 归并排序 => 通过分治思想来降低时间复杂度

    // 0 9  6 10 15 13  12  8

    // 一一比较
    // 0 9 | 6 10 | 15 13 | 12  8 => 比较完后，升序排列

    // 二二比较
    // 0 9  6 10 | 13 15  8 12

    // ...

    // KeyPoint 补充说明
    // 在合并之后，合并数组已是有序状态，且数组排序之后，不会影响最终的结果
    //  => 因为一组元素和二组元素之间的相对顺序没有发生改变，只是自己组顺序变化了，不影响结果

    // 归并排序前
    // 15，13 | 12，8 => 数字之间两两比较，一共比较 4 对
    //               => 15，12 | 15，8 | 13，12 | 13，8

    // 归并排序后
    // 13，15 | 8，12 => 同样比较 4 对
    //  i       j
    // i 和 j 指针，依次比较，逐一从小往大排序，也是比较 4 对
    // 8，12，13，15

    // 综上：所以数组排序之后，不会影响最终的结果

    // KeyPoint 方法三 归并排序
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        long[] tmp = new long[n + 1];
        // 对 前缀和数组 归并排序
        return mergeSort(prefixSum, 0, n, tmp, lower, upper);
    }

    private int mergeSort(long[] prefixSum, int left, int right,
                          long[] tmp, int lower, int upper) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;

        // 左侧区间和
        int leftCount = mergeSort(prefixSum, left, mid, tmp, lower, upper);
        // 右侧区间和
        int rightCount = mergeSort(prefixSum, mid + 1, right, tmp, lower, upper);
        int count = 0;

        // KeyPoint 核心思路
        // 在合并之前，计算有效的区间和个数
        // 此时，左侧数组 [left,mid]右侧数组 [mid+1,right] 都已经排好序了

        //                                    u
        // 0 6 9 10 11 23 24 | 8 12 13 15 17 19 20
        // i                         l

        // lower = 13
        // upper = 17

        // 有效区间和 count += u-l

        // 遍历左侧数组的指针
        int i = left;

        // l 和 u 遍历右侧数组的指针 => l，u 是对 lower 和 upper 指针简写
        // 1.l 指针 => p[l]-p[i] >= lower => 第一个 >= lower
        // 2.u 指针 => p[u]-p[i] > upper => 第一个 > upper => 严格大于
        // => 有效区间和：u-l
        int l = mid + 1, u = mid + 1;

        // i <= mid，一直遍历左侧数组，执行 while 循环
        // i > mid，数组越界，跳出 while 循环
        while (i <= mid) {
            // KeyPoint 注意事项
            // 1.l 和 u 不回退，因为 i++，则 p[i] 增大，l 只能往右才能找到 p[l]-p[i] >= lower
            // 2.同时 右移 l 和 u，且需要保证 l 和 u 不能越界

            // while 循环条件 => 执行 while 循环体的条件，不是跳出 while 循环的条件
            //               => 逻辑关系不能搞反了，特别注意
            // 通过 while 循环条件，不断将 l 和 u 指针 移动到合适位置，再去计算 count
            while (l <= right && prefixSum[l] - prefixSum[i] < lower) l++;
            while (u <= right && prefixSum[u] - prefixSum[i] <= upper) u++;
            count += (u - l);
            i++;
        }

        // KeyPoint 在 return 之前，需要合并区间和，不要将 merge 遗漏了
        merge(prefixSum, left, mid, right, tmp);
        return leftCount + rightCount + count;

        // KeyPoint 总结
        //  => 本题在合并之前处理，区别于：前面在合并中处理题目
        //  => 在合并前操作，还是在合并中操作
        //     关键：取决于一些信息，是在合并之前获取，还是在合并中获取
        //  => 本题计算有效区间和，目的想通过一次性计算得到，故只能在合并之前计算
        //     若是在合并中，只能 i 和 j 两者一一比对，没法直接一次性计算得到
    }

    private void merge(long[] nums, int left, int mid, int right, long[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }
        // 归并排序，i 和 j，两者依依比较，没有一个元素遗漏，纠正归并排序可能遗留情况的思想
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
