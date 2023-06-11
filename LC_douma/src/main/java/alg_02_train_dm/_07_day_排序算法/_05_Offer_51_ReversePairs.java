package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 10:46
 * @Version 1.0
 */

public class _05_Offer_51_ReversePairs {

      /*
        剑指 Offer 51. 数组中的逆序对
        在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
        输入一个数组，求出这个数组中的逆序对的总数。

        示例 1:
        输入: [7,5,6,4]
        输出: 5

        解释： 5 个逆序对
            7,5
            7,6
            7,4
            5,4
            6,4

        限制：
        0 <= 数组长度 <= 50000

     */

    // KeyPoint 方法一 暴力解 => 超出时间限制
    // 时间复杂度 O(n^2)
    public int reversePairs1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // j 直接从 i+1 开始，避免从 i 开始，提高一点效率
            for (int j = i + 1; j < nums.length; j++) {
                // 逆序，则 count++
                if (nums[i] > nums[j]) count++;
            }
        }
        return count;
    }

    // KeyPoint 方法二 归并排序 => 在归并排序的合并操作中，从而计算逆序对
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // 为了不改变原数组，这里先将原数组拷贝一份
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }
        // 归并排序中临时数组
        int[] temp = new int[nums.length];
        return mergeSort(copy, 0, nums.length - 1, temp);
    }

    // 归并排序
    private int mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left >= right) return 0;

        int mid = (left + right) / 2;
        // 左边归并
        int leftCount = mergeSort(nums, left, mid, temp);
        // 右边归并
        int rightCount = mergeSort(nums, mid + 1, right, temp);

        // 计算逆序对个数
        int mergeCount = mergeAndCountReversePairs(nums, left, mid, right, temp);

        // countSum
        return leftCount + rightCount + mergeCount;
    }

    // 合并且计算逆序对
    private int mergeAndCountReversePairs(int[] data, int left, int mid, int right, int[] tmp) {
        // 将 data 数组，拷贝到 tmp 中
        // KeyPoint 错误代码 =>  for (int i = 0; i < tmp.length; i++)
        // 1.拷贝范围从 left 到 right，而不是 0 到 tmp.length
        // 2.不一定每次都能使用完整的一个数组，多数情况只是使用一部分
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }
        // 记录逆序对个数
        int count = 0;

        // i 和 j 分别是 tmp，前后两端逐一比较的起始位置
        int i = left;
        int j = mid + 1;

        // k 遍历 data 数组指针，进行赋值操作
        // i 和 j 指向元素比较大小，赋值给 data[index]
        for (int k = left; k <= right; k++) {

            // 左边没有元素，右边有元素
            if (i == mid + 1) {
                // KeyPoint k 不需要自增，即 k++，for 循环执行一轮后，有自增操作，但是 i 和 j 是需要自增的
                data[k] = tmp[j++];
                // 左边有元素，右边没有元素
            } else if (j == right + 1) {
                data[k] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                data[k] = tmp[i++];
            } else { // tmp[i] > tmp[j]
                data[k] = tmp[j++];
                // 计算 temp[j] 的逆序对
                count += mid - i + 1;

                // left     mid       right
                //  ↓        ↓          ↓
                // 34 51 78 88 9 11 32 37
                //  ↑          ↑
                //  i          j

                // 当 tmp[i] > tmp[j] 时，由于 i 到 mid 都是递增序列，则 i ~ mid 数组元素 > [j]
                // 都是满足逆序对的，所以 count += mid - i + 1，mid 和 i 两端都包括在内，故还需要加 1
            }
        }
        return count;
    }
}
