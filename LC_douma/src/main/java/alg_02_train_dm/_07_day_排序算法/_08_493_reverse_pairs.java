package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 13:04
 * @Version 1.0
 */

// KeyPoint 归并排序应用
public class _08_493_reverse_pairs {

    /*
        493.翻转对
        给定一个数组 nums ，如果 i < j 且 nums[i] > 2 * nums[j]
        则我们就将 (i, j) 称作一个重要翻转对。你需要返回给定数组中的重要翻转对的数量。

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
        => -2^31 <= nums[i] <= 2^31 - 1
        => 存在数据溢出问题，需要使用 long 类型接受

     */

    // KeyPoint 本质：和 327 区间和的个数 一样，只是修改了规则

    // 1.翻转对的条件
    //   给定一个数组 nums ，如果 i < j 且 nums[i] > 2 * nums[j]

    // 2.区间和的个数的条件
    //   查找 [i，j] 对，使得：prefixSum[j] - prefixSum[i] 在 [lower，upper] 范围内
    //   找到所有符合条件的 [i，j]对，其中 i < j，

    public int reversePairs(int[] nums) {
        int n = nums.length;
        int[] tmp = new int[n];
        return mergeSort(nums, 0, n - 1, tmp);
    }

    private int mergeSort(int[] nums, int left, int right, int[] tmp) {

        if (left >= right) return 0;
        int mid = left + (right - left) / 2;

        int leftCount = mergeSort(nums, left, mid, tmp);
        int rightCount = mergeSort(nums, mid + 1, right, tmp);
        int count = 0;
        // 计算当前翻转对的个数
        int i = left;
        int j = mid + 1;
        while (i <= mid) {

            // KeyPoint 数据溢出
            // 输入数组中的所有数字都在 32 位整数的表示范围内
            //  => -2^31 <= nums[i] <= 2^31 - 1
            //  => 2 * nums[j] 可能越界，需要将其转成 long 类型

            // KeyPoint 强制类型转换的位置
            // 强制类型紧跟在数据前面，优先级高于运算符
            // 2*(long) nums[j] => √
            // (long) (2*nums[j]) => ×， 有可能 2 * nums[j] 就已经越界了

            // KeyPoint while 循环逻辑
            // 题目要求：nums[i] > 2 * nums[j]
            // 1.若一开始 [i] > 2*[j] 不满足，说明 [i] 不够大，右移 i
            // 2.[i] < 2*[j]，满足条件，右移 j，直到  [i] <= 2*[j]，此时 j 在不满足条件位置
            //   count += j - (mid+1)，本身就不包括 j 这一端
            // 3.循环变量 i 和 j 都是不回退的
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) j++;
            count += (j - mid - 1);
            i++;
        }

        // left           mid   mid + 1     right
        //  ↓              ↓     ↓             ↓
        //  0 6 9 10 11 23 24 |  2 3 4 5 6 10 11
        //  ↑                    ↑
        //  i                    j

        merge(nums, left, mid, right, tmp);
        // KeyPoint count 计算
        // 返回 totalCount 需要加上：左数组 leftCount 和 右数组 rightCount，本次自身 count
        // 一般 mergeSort 返回值为 int，都是需要加上 leftCount 和 rightCount
        return leftCount + rightCount + count;
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
