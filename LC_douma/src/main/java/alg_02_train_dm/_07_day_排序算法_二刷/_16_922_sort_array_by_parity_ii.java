package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 12:03
 * @Version 1.0
 */
public class _16_922_sort_array_by_parity_ii {
    /*
        922. 按奇偶排序数组 II
        给定一个 非负整数 数组 nums，nums 中一半整数是 奇数，一半整数是 偶数 。
        对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。

        示例 1：
        输入：nums = [4,2,5,7]
        输出：[4,5,2,7]
        解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。

        示例 2：
        输入：nums = [2,3]
        输出：[2,3]
        
        提示：
        2 <= nums.length <= 2 * 104
        nums.length 是偶数
        nums 中一半是偶数
        0 <= nums[i] <= 1000
     */

    // KeyPoint 方法一 两次遍历 + 额外数组
    public int[] sortArrayByParityII1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int i = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                // 不能在原 nums 上修改，否则存在索引越界，i 每次加 2，即为 i += 2
                res[i] = num;
                i += 2;
            }
        }
        // 新的一轮遍历，i 从 1 开始
        i = 1;
        for (int num : nums) {
            if (num % 2 == 1) {
                res[i] = num;
                i += 2;
            }
        }
        return res;
    }

    // KeyPoint 方法二 一次遍历 + 交错指针
    //                 => 没有使用额外空间，直接在原数组 nums 上修改
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        // 交错指针 => 双指针 => 减少一次循环
        // i 指向偶数位，j 指向奇数位
        int i = 0, j = 1;
        while (i < n) {
            // 若当前偶数位置是奇数
            if (nums[i] % 2 == 1) {
                // 奇数位置为奇数，则 j 一直往前找，直到在奇数位置上找到一个偶数，与之交换
                while (nums[j] % 2 == 1) j += 2;
                swap(nums, i, j);
            }
            // nums[i] % 2 == 0，则 [i] 为偶数，往前走 2 步
            // 执行 while 循环，若一直成立，则一直执行
            i += 2;
        }
        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
