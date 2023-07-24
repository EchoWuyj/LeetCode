package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:10
 * @Version 1.0
 */
public class _06_487_max_consecutive_ones_ii {

    /*
       487. 最大连续 1 的个数 II
       给定一个二进制数组，你可以最多将 1 个 0 翻转为 1，找出其中最大连续 1 的个数。

       示例 1：
       输入：[1,0,1,1,0]
       输出：4
       解释：翻转第一个 0 可以得到最长的连续 1。
            当翻转以后，最大连续 1 的个数为 4。

       注：
       输入数组只包含 0 和 1.
       输入数组的长度为正整数，且不超过 10,000

       进阶：
       如果输入的数字是作为 无限流 逐个输入如何处理？
       换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
    */

    // 思路：将窗口中第一个 0 当做 1，且窗口中最多只有一个 1
    public static int findMaxConsecutiveOnes1(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        // 记录窗口中 0 个数
        int windowZeroCnt = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                windowZeroCnt++;
                // windowZeroCnt > 1
                if (windowZeroCnt == 2) {
                    // right 为 0，不是 1，所以直接 right - left
                    ans = Math.max(ans, right - left);
                }
            }

            // 若窗口中有 2 个 0，则 left 不断右移动，保证窗口中只能有一个 1
            while (windowZeroCnt == 2) {
                // KeyPoint 无法处理'无限流'，因为内存不能存储下所有从流中输入的数字
                // 需要将 left 到 right 之间所有元素都存在数组中，后续需要一一判断
                if (nums[left] == 0) windowZeroCnt--;
                left++;
            }

            right++;
        }
        // ans 是在 nums[right] == 0 更新，故返回 ans 前，还得套一层 Math.max
        return Math.max(ans, right - left);
    }

    // 进阶
    public static int findMaxConsecutiveOnes2(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;

        // zeroIndex 记录当前窗口中 0 出现的位置，而不是将 left 到 right 之间所有元素
        // 都存储下来，从而解决了：内存不能存储下所有从流中输入的数字

        // -1 表示之前还没有出现过 0
        int zeroIndex = -1;
        while (right < nums.length) {
            if (nums[right] == 0) {
                // 说明当前窗口已经有 0，且此时 nums[right] 为 0，需要计算 ans
                if (zeroIndex >= 0) {
                    ans = Math.max(ans, right - left);
                    // 直接跳到 zeroIndex 后面一个位置，避免从 left 位置往后一个位置一个位置判断，效率低下
                    left = zeroIndex + 1;
                }
                // 更新 zeroIndex
                zeroIndex = right;
            }
            right++;
        }
        return Math.max(ans, right - left);
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 0, 1, 1, 0};
        System.out.println(findMaxConsecutiveOnes1(array)); // 4
        System.out.println(findMaxConsecutiveOnes2(array)); // 4

        int[] array1 = new int[]{1, 0, 1, 0, 1, 0, 1, 0};
        System.out.println(findMaxConsecutiveOnes1(array1)); // 3
        System.out.println(findMaxConsecutiveOnes2(array1)); // 3
    }
}
