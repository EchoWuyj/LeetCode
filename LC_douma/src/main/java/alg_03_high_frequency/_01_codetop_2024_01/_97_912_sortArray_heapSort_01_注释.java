package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:25
 * @Version 1.0
 */
public class _97_912_sortArray_heapSort_01_注释 {

    // KeyPoint 堆排序，简化版本，王道
    // 堆排序：O(n) + O(nlogn) = O(nlogn)
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        int[] heapArray = new int[n + 1];
        // 将 num 数组转成 heapArray
        // 原数组，起始索引，目的数组，起始索引，数组长度
        System.arraycopy(nums, 0, heapArray, 1, n);

        // 基于大顶堆进行排序
        buildMaxHeap(heapArray, n); // O(n)

        // i 指向堆底元素
        // 堆排序: 每一趟将堆顶元素加入有序子序列，即将堆顶元素与待排序序列中的最后一个元素交换
        // 最后一个元素 nums[1] 不需要交换
        for (int i = n; i > 1; i--) { // O(nlogn)
            // 堆顶和堆底元素交换，使用循环变量 i 进行交换，而不是固定值 n
            swap(heapArray, i, 1);
            // 对交换到堆顶元素进行下沉操作，保证满足大顶堆性质
            // 已经确定一个有序元素，故数组长度为 i-1
            adjust(heapArray, 1, i - 1);
        }

        // 调整后的  heapArray 重新赋值给 nums
        System.arraycopy(heapArray, 1, nums, 0, n);

        // 最终将 nums 进行返回
        return nums;
    }

    // 建立大顶堆
    public void buildMaxHeap(int[] nums, int n) {
        // 思路：把所有非终端结点都检查一遍，是否满足大根堆的要求,如果不满足:则进行调整
        // 在顺序存储的完全二叉树中，非终端结点编号 <= ⌊n/2⌋
        for (int i = n / 2; i > 0; i--) {
            adjust(nums, i, n);
        }
    }

    // 将以 k 为根的子树调整为大根堆
    public void adjust(int[] nums, int k, int n) {
        nums[0] = nums[k];
        // 小元素不断下沉的过程
        for (int i = 2 * k; i <= n; i *= 2) {
            // 注意：i < n 为了保证 i + 1 不越界
            if (i < n && nums[i] < nums[i + 1]) {
                i++;
            }
            // 根 >= 左右子节点
            if (nums[0] >= nums[i]) {
                break;
            } else {
                // KeyPoint 两行代码都是针对 i 赋值给 k，分别是：数组值 和 索引
                // 使用 左/右子节点 替换 根节点
                nums[k] = nums[i];
                // 更新 k 位置索引，用于后续 nums[k] 和 nums[i] 的比较
                k = i;
            }
        }
        // 最终 k 位置，即为最开始 nums[0] 的位置
        // KeyPoint 记忆：首尾相接 nums[0] = nums[k] 到 nums[k] = nums[0]
        nums[k] = nums[0];
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

