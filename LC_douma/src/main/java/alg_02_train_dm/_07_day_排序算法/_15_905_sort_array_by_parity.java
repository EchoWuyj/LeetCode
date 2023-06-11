package alg_02_train_dm._07_day_排序算法;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 12:03
 * @Version 1.0
 */
public class _15_905_sort_array_by_parity {

    /*
        给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，
        后跟所有奇数元素。返回满足此条件的 任一数组 作为答案。

        示例 1：
        输入：nums = [3,1,2,4]
        输出：[2,4,3,1]
        解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。

        示例 2：
        输入：nums = [0]
        输出：[0]

        提示：
        1 <= nums.length <= 5000
        0 <= nums[i] <= 5000

     */

    // KeyPoint 方法一 两次遍历 + 额外数组
    public int[] sortArrayByParity1(int[] nums) {
        int[] res = new int[nums.length];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                // nums 和 res 奇偶位置不对应，故不能共用一个 i
                // res 需要使用自己遍历索引 count
                res[count++] = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                res[count++] = nums[i];
            }
        }
        return res;
    }

    // KeyPoint 方法二 一次遍历 + 额外数组
    public int[] sortArrayByParity2(int[] nums) {
        int[] ans = new int[nums.length];

        int left = 0;
        int right = nums.length - 1;
        // 指针从数组两端开始遍历
        // => left 负责 偶数
        // => right 负责 奇数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                ans[left] = nums[i];
                left++;
            } else {
                ans[right] = nums[i];
                right--;
            }
        }
        return ans;
    }

    // KeyPoint 方法三 排序过程
    // 将取模 2 等于 0 的放在前面，将取模 2 等于 1 的放在后面 => 排序过程
    // 将本题看成排序的题目，按照奇偶进行排序
    public int[] sortArrayByParity3(int[] nums) {
        int n = nums.length;
        // 排序只能是基本数据类型包装类
        Integer[] tmp = new Integer[n];
        for (int i = 0; i < n; i++) tmp[i] = nums[i];
        // tmp 为排序对象，Lambda 表达式为排序规则
        // 自定义排序，使用奇偶进行排序 => 偶数在前，奇数在后
        // o1 为偶数，o2 为奇数，o1 % 2 - o2 % 2 < 0，即：将 o1 放在 o2 前面
        Arrays.sort(tmp, (o1, o2) -> o1 % 2 - o2 % 2);
        for (int i = 0; i < n; i++) nums[i] = tmp[i];
        return nums;
    }

    // KeyPoint 方法四 二向切分，快排分区逻辑 => 本质：快慢指针
    public int[] sortArrayByParity4(int[] nums) {
        int less = 0, great = 0;
        // 并非严格意义的二向切分，分区条件有所改变
        for (; great < nums.length; great++) {
            // 奇数在前，偶数在后，则交换
            if (nums[less] % 2 > nums[great] % 2) {
                swap(nums, less, great);
            }
            // 保证 less 左边都是偶数
            if (nums[less] % 2 == 0) less++;
        }
        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 快排分区的逻辑优化 => 对撞指针
    // 避免 less 和 great 都遍历一遍数组
    public int[] sortArrayByParity(int[] nums) {
        int less = 0, great = nums.length - 1;
        while (less < great) {
            // 奇数在前，偶数在后，则交换
            if (nums[less] % 2 > nums[great] % 2) {
                swap(nums, less, great);
            }
            if (nums[less] % 2 == 0) less++;
            if (nums[great] % 2 == 1) great--;
        }
        return nums;
    }
}
