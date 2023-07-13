package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-09 18:17
 * @Version 1.0
 */
public class _05_Offer_51_ReversePairs2 {

    // KeyPoint 方法二 归并排序 => 在归并排序的合并操作中，从而计算逆序对
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        // 为了不改变原数组，这里先将原数组拷贝一份
        // 注意：本题没有明确说明不能修改原数组
        int[] copy = new int[n];
        for (int i = 0; i < n; i++) {
            copy[i] = nums[i];
        }
        // 归并排序中临时数组
        int[] temp = new int[n];
        return mergeSort(copy, 0, n - 1, temp);
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

        // KeyPoint 核心：分治思想
        // 51 34 88 78 32 11 9 37

        // 一一比较
        // 51 34 | 88 78 | 32 11 | 9 37
        //   1       1       1      0  => count = 3
        // => 调整顺序，保证没有逆序
        // 34 51  | 78 88  | 11 32  | 9 37

        // 二二比较
        // 34 51  78 88 | 11 32 9 37
        //                      2     => count = 2
        // => 调整顺序，保证没有逆序
        // 34 51  78 88 | 9 11 32 37

        // 四四比较
        // 34 51  78 88  9 11 32 37
        //               4 4  4  3    => count = 15
        // => 调整顺序，保证没有逆序
        // 9 11 32 34 37 51 78 88

        // KeyPoint 错误代码
        // for (int i = 0; i < tmp.length; i++)
        // 1.拷贝范围从 left 到 right，而不是 0 到 tmp.length，否则索引越界
        // 2.不一定每次都能使用完整的一个数组，多数情况只是使用一部分
        for (int i = left; i <= right; i++) {
            // 将 data 数组，拷贝到 tmp 中
            tmp[i] = data[i];
        }

        // 记录逆序对个数
        int count = 0;

        // i 和 j 分别是 tmp，前后两端逐一比较的起始位置
        int i = left;
        int j = mid + 1;

        // index 遍历 data 数组指针
        // i 和 j 遍历 tmp 数组元素，并比较大小，将值赋值给 data[index]
        for (int index = left; index <= right; index++) {
            // 左边没有元素，右边有元素
            if (i == mid + 1) {
                // 区别：自增
                // index 不需要自增，即 index++，for 循环执行一轮后，有自增操作
                // i 和 j 是需要自增的
                data[index] = tmp[j++];
                // 左边有元素，右边没有元素
            } else if (j == right + 1) {
                data[index] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                data[index] = tmp[i++];
            } else {
                // tmp[i] > tmp[j]
                // => 正好符合逆序对条件
                // => 计算 temp[j] 的逆序对
                data[index] = tmp[j++];
                count += mid - i + 1;

                // tmp[i] > tmp[j]
                // => i 左侧一半数组，j 右侧一半数组
                // => 正好符合逆序对条件
                // => 计算 temp[j] 的逆序对

                // left     mid        right
                //  ↓        ↓           ↓
                // 34 51 78 88  9 11 32 37
                //  ↑           ↑
                //  i           j

                // KeyPoint 核心分析
                // 当 tmp[i] > tmp[j] 时，由于 i 到 mid 都是递增序列，则 i ~ mid 数组元素 > [j]
                // 都是满足逆序对的，所以 count += mid - i + 1，mid 和 i 两端都包括在内，故还需要加 1
            }
        }
        return count;
    }
}
