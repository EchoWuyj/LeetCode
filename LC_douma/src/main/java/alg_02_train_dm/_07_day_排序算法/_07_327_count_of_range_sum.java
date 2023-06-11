package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 11:44
 * @Version 1.0
 */
public class _07_327_count_of_range_sum {

    /*
        327. 区间和的个数

        给你一个整数数组 nums 以及两个整数 lower 和 upper 。
        求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
        区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

        示例 1：
        输入：nums = [-2,5,-1], lower = -2, upper = 2
        输出：3
        解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2

        i = 0，子数组 [-2] √，[-2,5] ×，[-2,5,1] √ => s[0,0]，s[0,2]
        i = 1，子数组 [5] ×，[5,-1] ×
        i = 2，子数组 [-1] √ => s[2,2]

        提示：
        1 <= nums.length <= 10^5
        -2^31 <= nums[i] <= 2^31 - 1
        -10^5 <= lower <= upper <= 10^5
        题目数据保证答案是一个 32 位 的整数

     */

    // KeyPoint 方法一 暴力解 超时
    // 时间复杂度 O(n^3)
    public int countRangeSum2(int[] nums, int lower, int upper) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) { // O(n)
            for (int j = i; j < nums.length; j++) {  // O(n)
                // 累加和可能溢出，所以需要使用 long 类型
                long sum = 0;
                // 牢记这种形式，计算时间复杂度是算作 O(n)
                // 计算区间和，存在大量重复计算，可以优化
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum <= upper && sum >= lower) count++;
            }
        }
        return count;
    }

    // KeyPoint 方法二 前缀和 超时
    // 时间复杂度 O(n^2)
    public int countRangeSum3(int[] nums, int lower, int upper) {
        // KeyPoint 代码经验
        // 若测试用例中，数据 [-2147483647,0,-2147483647,2147483647] 出错，
        // 多半是数据范围不够，将 int 修改成 long，再进行测试
        long[] prefixSum = new long[nums.length + 1];
        int n = nums.length;
        prefixSum[0] = 0;
        // i < n，因为 num[i]
        // i = 0， p[i+1] 直接从 p[1] 开始计算
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int count = 0;
        // 使用 prefixSum.length，保证每个区间和都得计算到
        int m = prefixSum.length;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                // p[j] = p[i+1] = p[i] + n[i]
                // p[i]
                // p[j] - p[i] = n[i]
                // 必须是 long，要不然会溢出
                long sum = prefixSum[j] - prefixSum[i];
                if (sum <= upper && sum >= lower) count++;
            }
        }
        return count;
    }

    // KeyPoint 方法三 归并排序 => 通过分治思想来降低时间复杂度
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefixSum = new long[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int m = prefixSum.length;
        long[] tmp = new long[m];
        // 对前缀和归并排序
        return mergeSort(prefixSum, 0, m - 1, tmp, lower, upper);
    }

    private int mergeSort(long[] prefixSum, int left, int right, long[] tmp, int lower, int upper) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;

        // 左侧区间和
        int leftCount = mergeSort(prefixSum, left, mid, tmp, lower, upper);
        // 右侧区间和
        int rightCount = mergeSort(prefixSum, mid + 1, right, tmp, lower, upper);
        int count = 0;

        // KeyPoint 问题转换
        // 查找 [i，j] 对，使得：prefixSum[j] - prefixSum[i]
        // 在 [lower，upper] 范围内，找到所有符合条件的 [i，j]对，其中 i < j

        // KeyPoint 总结
        //  => 本题在合并之前处理，区别于前面在合并中处理题目
        //  => 关键：取决于一些信息，是在合并之前获取，还是在合并中获取

        // 在合并之前，计算有效的区间和个数，即下面代码
        int i = left;
        // lower 和 upper 区间范围指针
        // p[l]-p[i] >= lower => 第一个 >= lower
        // p[u]-p[i] > upper => 第一个 > upper
        int l = mid + 1, u = mid + 1;
        while (i <= mid) {
            // l 和 u 不回退，因为 i++，p[i] 增大，l 只能往右才能找到 p[l]-p[i] < lower
            // l 和 u 不能越界
            while (l <= right && prefixSum[l] - prefixSum[i] < lower) l++;
            while (u <= right && prefixSum[u] - prefixSum[i] <= upper) u++;
            count += (u - l);
            i++;
        }

        // KeyPoint 补充说明：
        // 在合并之后，合并数组已是有序状态，且数组排序之后，不会影响最终的结果
        // 因为一组元素和二组元素之间的相对顺序没有发生改变

        // 归并排序前 15，13 | 12，8 => 比较 4 对
        // 15，12 | 15，8 | 13，12 | 13，8

        // 归并排序后 13，15 | 8，12 => 同样比较 4 对
        //           i       j
        // i 和 j 指针，依次比较，逐一从小往大排序，也是比较 4 对
        // 8，12，13，15

        // 综上：所以数组排序之后，不会影响最终的结果

        // 合并区间和
        merge(prefixSum, left, mid, right, tmp);
        return leftCount + rightCount + count;
    }

    private void merge(long[] nums, int left, int mid, int right, long[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }
        // KeyPoint 归并排序，i 和 j，两者依依比较，没有一个元素遗漏，纠正归并排序可能遗留情况的思想
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) nums[k] = tmp[j++];
            else if (j == right + 1) nums[k] = tmp[i++];
            else {
                if (tmp[i] <= tmp[j]) nums[k] = tmp[i++];
                else nums[k] = tmp[j++];
            }
        }
    }
}
