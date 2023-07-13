package alg_02_train_dm._07_day_排序算法_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-07-11 14:40
 * @Version 1.0
 */
public class _15_905_sort_array_by_parity2 {

    // KeyPoint 方法三 排序过程
    // 将取模 2 等于 0 的放在前面，将取模 2 等于 1 的放在后面 => 排序过程
    // 将本题看成排序的题目，按照奇偶进行排序
    public int[] sortArrayByParity3(int[] nums) {
        int n = nums.length;
        // 排序只能是基本数据类型包装类，不能直接对基本类型排序
        // int[] ×  Integer[] √
        Integer[] tmp = new Integer[n];
        for (int i = 0; i < n; i++) tmp[i] = nums[i];

        // tmp 为排序对象，Lambda 表达式为排序规则
        // 自定义排序，使用奇偶进行排序 => 偶数在前，奇数在后
        // o1 为偶数，o2 为奇数，
        // o1 % 2 - o2 % 2 < 0，即：将 o1 放在 o2 前面
        Arrays.sort(tmp, (o1, o2) -> o1 % 2 - o2 % 2);
        for (int i = 0; i < n; i++) nums[i] = tmp[i];
        return nums;
    }

    // KeyPoint 方法四 二向切分，快排分区逻辑 => 本质：快慢指针
    public int[] sortArrayByParity4(int[] nums) {
        int n = nums.length;
        int less = 0, great = 0;

        // 基于快排的二向切分进行修改，并非严格意义的二向切分，分区条件有所改变
        for (; great <= n - 1; great++) {
            // 没有定义 pivot，直接 [less] 和 [great] 比较
            // 若奇数在前，偶数在后，则交换
            // nums[less] % 2 => 奇数
            // nums[great] % 2 => 偶数
            if (nums[less] % 2 > nums[great] % 2) {
                swap(nums, less, great);
            }
            // 保证 less 左边都是偶数
            if (nums[less] % 2 == 0) less++;

            // great
            //  ↓
            //  2 3 4 1 1
            //  ↑
            // less

            //    great
            //      ↓
            //  2 3 4 1 1
            //    ↑
            //   less
        }
        return nums;

        // KeyPoint 区别：快排的二向切分
//        for (; great <= high - 1; great++) {
//            if (data[great] < pivot) {
//                swap(data, less, great);
//                less++;
//            }
//        }

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 快排分区的逻辑优化
    // => 对撞指针，避免 less 和 great 都遍历一遍数组
    public int[] sortArrayByParity5(int[] nums) {
        int less = 0, great = nums.length - 1;
        while (less < great) {
            // nums[less] % 2 => 奇数
            // nums[great] % 2 => 偶数
            // 奇数在前，偶数在后，则交换
            if (nums[less] % 2 > nums[great] % 2) {
                swap(nums, less, great);
            }
            // 交换后 => 偶数在前，奇数在后
            //        => 判断是否满足以上条件，若满足，则对撞指针移动
            // 1.nums[less] 偶数，后移
            if (nums[less] % 2 == 0) less++;
            // 2.nums[great] 偶数，前移
            if (nums[great] % 2 == 1) great--;
        }
        return nums;
    }
}
