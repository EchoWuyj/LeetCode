package alg_02_train_dm._12_day_滑动窗口_二刷;

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
        int res = 0;
        int left = 0, right = 0;
        // 记录窗口中 0 个数
        int count = 0;
        int n = nums.length;
        while (right < n) {
            if (nums[right] == 0) {
                count++;
                // count > 1
                if (count == 2) {
                    // 因为 right 位置对应为 0 不是 1，所以直接 right - left
                    res = Math.max(res, right - left);
                }
            }
            // 若窗口中有 2 个 0，则 left 不断右移动，保证窗口中只能有一个
            // => 能使用 while 尽量使用 while，必然不会出错！
            while (count == 2) {
                if (nums[left] == 0) count--;
                left++;
            }
            right++;
        }
        // res 是在 nums[right] == 0 更新，数组可能是 1结尾
        // 故返回 res 前，还得套一层 Math.max
        return Math.max(res, right - left);
    }

    // KeyPoint 进阶问题
    // 如果输入的数字是作为 无限流 逐个输入如何处理？
    // 换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
    // KeyPoint 解决措施
    // 使用 zeroIndex 记录当前窗口中 0 出现索引位置，通过索引坐标之间相减来计算元素个数
    // 而不是将 left 到 right 之间所有元素都存储下来，从而解决进阶问题
    public static int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0;
        int left = 0, right = 0;
        int n = nums.length;

        // -1 表示之前还没有出现过 0
        int zeroIndex = -1;
        while (right < n) {
            if (nums[right] == 0) {
                // 最开始 zeroIndex == -1 不满足题目条件，不执行 if 判断
                // 说明当前窗口已经有一个 0，且此时 right 位置，数组元素为 0，故计算 res
                if (zeroIndex >= 0) {
                    res = Math.max(res, right - left);
                    // 直接跳到 zeroIndex 后面一个位置
                    // 避免从 left 位置往后一个位置一个位置判断，效率低下
                    left = zeroIndex + 1;
                }
                // 更新 zeroIndex 值
                zeroIndex = right;
            }
            right++;
        }
        return Math.max(res, right - left);
    }

    //  for test => 需要力扣会员
    public static void main(String[] args) {
        int[] array = new int[]{1, 0, 1, 1, 0};
        System.out.println(findMaxConsecutiveOnes1(array)); // 4
        System.out.println(findMaxConsecutiveOnes2(array)); // 4

        int[] array1 = new int[]{1, 0, 1, 0, 1, 0, 1, 0};
        System.out.println(findMaxConsecutiveOnes1(array1)); // 3
        System.out.println(findMaxConsecutiveOnes2(array1)); // 3
    }
}
