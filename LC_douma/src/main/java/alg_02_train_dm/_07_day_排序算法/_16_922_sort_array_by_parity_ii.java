package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 12:03
 * @Version 1.0
 */
public class _16_922_sort_array_by_parity_ii {
    /*
        922. 按奇偶排序数组 II
        给定一个非负整数数组 nums，nums 中一半整数是 奇数，一半整数是 偶数 。
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
        if (nums == null) return nums;
        int n = nums.length;
        int[] ans = new int[n];
        int i = 0;
        for (int a : nums) {
            if (a % 2 == 0) {
                // 不能在原 nums 上修改，否则存在索引越界，nums[i]，而 i 是 i += 2
                ans[i] = a;
                i += 2;
            }
        }
        i = 1;
        for (int a : nums) {
            if (a % 2 == 1) {
                ans[i] = a;
                i += 2;
            }
        }
        return ans;
    }

    // KeyPoint 方法二 一次遍历 + 交错指针
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        // i 指向偶数位，j 指向奇数位
        int i = 0, j = 1;
        while (i < n) {
            // 如果当前偶数位置是奇数元素的话
            if (nums[i] % 2 == 1) {
                // 奇数位置为奇数，则 j 一直往前找，直到在奇数位置上找到一个偶数，与之交换
                while (nums[j] % 2 == 1) j += 2;
                swap(nums, i, j);
            }
            // nums[i] % 2 == 0 若 [i] 偶数，往前走 2 步 => 若一直成立，则一直执行
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
