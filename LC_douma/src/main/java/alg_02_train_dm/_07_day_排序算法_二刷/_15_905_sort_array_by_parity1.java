package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 12:03
 * @Version 1.0
 */
public class _15_905_sort_array_by_parity1 {

    /*
        905. 按奇偶排序数组
        给你一个整数数组 nums，将 nums 中的的所有 偶数元素 移动到数组的前面，
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
        int n = nums.length;
        int[] res = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                // KeyPoint 特别注意
                // nums 和 res 奇偶位置不对应，故不能共用一个 i
                // res 需要使用自己遍历索引 count
                res[count++] = nums[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 1) {
                res[count++] = nums[i];
            }
        }
        return res;
    }

    // for 循环 改写 while 循环
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int index = 0;
        int i = 0;
        while (i < n) {
            if (nums[i] % 2 == 0) {
                res[index++] = nums[i];
            }
            // 1.while 循环变量，不能放在 if 判断体内 nums[i++];
            //   若 if 条件语句不执行，则 i 不更新，则 while 死循环
            // 2.测试用例 [3,1,2,4]，若 num[0] = 3，不满足 if 判断条件
            //   i 不更新，则一直执行 while 判断条件，从而导致死循环
            i++;
        }

        // 新的一轮，i 指针从 0 开始，经过上面 while 循环，i 已经走到数组 nums 的尾部
        // index 不需要从 0 开始，index 没有走到 res 的尾部
        i = 0;
        while (i < n) {
            if (nums[i] % 2 == 1) {
                res[index++] = nums[i];
            }
            i++;
        }
        return res;
    }

    // KeyPoint 方法二 一次遍历 + 额外数组 => 推荐使用
    public int[] sortArrayByParity2(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        int left = 0;
        int right = n - 1;
        // 指针从数组两端开始遍历
        // => 对撞指针
        // => left 负责 偶数
        // => right 负责 奇数
        for (int i = 0; i < n; i++) {
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


}
