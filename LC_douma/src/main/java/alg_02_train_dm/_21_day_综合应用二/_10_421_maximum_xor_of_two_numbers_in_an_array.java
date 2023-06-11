package alg_02_train_dm._21_day_综合应用二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 20:37
 * @Version 1.0
 */
public class _10_421_maximum_xor_of_two_numbers_in_an_array {
     /*
        421. 数组中两个数的最大异或值
        给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，
        其中 0 ≤ i ≤ j < n 。

        进阶：你可以在 O(n) 的时间解决这个问题吗？

        示例 1：
        输入：nums = [3,10,5,25,2,8]
        输出：28
        解释：最大运算结果是 5 XOR 25 = 28.

        提示：
        1 <= nums.length <= 2 * 10^5
        0 <= nums[i] <= 2^31 - 1

        KeyPoint 补充说明：
        在 Java 中，两个大于等于0的数异或的结果是一个正数，除非这两个数相等，此时异或的结果为0。

        异或运算符（^）用于比较两个二进制数的每一位，如果相同则为0，不同则为1。
        因此，对于两个大于等于0的二进制数，至少有一个位不同，所以它们的异或结果是一个正数。

        例如，对于两个正整数 10 和 25，它们的异或结果为：10 ^ 25 = 31
        这是因为它们的二进制表示分别为：1010 和 10101001
              0000 1010
            ^ 1010 1001
            ------------
              1010 0011
        至少有一个位不同，因此异或结果为31（二进制表示为 1010|1011）

     */

    // KeyPoint 方法一 暴力解法
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public int findMaximumXOR1(int[] nums) {
        // 数组只有一个元素，默认异或结果为 0
        int n = nums.length;
        if (n < 2) return 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                maxValue = Math.max(maxValue, nums[i] ^ nums[j]);
            }
        }
        return maxValue;
    }

    // KeyPoint 方法二 二进制前缀树
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        Trie trie = new Trie();
        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            trie.add(nums[i - 1]);
            // 将 [0] ... [i-1] 加入前缀树中，比较得到最大值 maxValue
            // 最后使用 [i] 和 maxValue 比较大小，得到真正的最大值
            maxValue = Math.max(maxValue, trie.maxXor(nums[i]));
        }
        return maxValue;
    }

    // 定义前缀树
    class Trie {
        // 定义前缀树节点
        private class Node {
            // 只有两个子节点 => 同一层只有这两个分支
            Node zero;
            Node one;

            Node() {
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        // 将 num 的二进制从高位到低位添加到前缀树中
        public void add(int num) {
            Node curr = root;

            //      0100 0000 0000 0000 0000 0000 0000 0000 >> 30
            //   => 0000 0000 0000 0000 0000 0000 0000 0001
            // & 1  0000 0000 0000 0000 0000 0000 0000 0001
            // ---------------------------------------------
            //      0000 0000 0000 0000 0000 0000 0000 0001

            // 从最高位开始获取该位值
            // 整数的最高一位是符号位，对于正数的话是 0，所有数都是一样的，故最高位可以跳过
            for (int k = 30; k >= 0; k--) {
                int bit = (num >> k) & 1;
                if (bit == 0) {
                    if (curr.zero == null) curr.zero = new Node();
                    curr = curr.zero;
                } else {
                    if (curr.one == null) curr.one = new Node();
                    curr = curr.one;
                }
            }
        }

        // 在前缀树中查找和 num 进行异或，得到的最大的异或值
        // 要使得异或值最大，则需要：在有的选的情况下，尽量选择不同二进制，这样异或结果才最大
        public int maxXor(int num) {
            Node curr = root;
            // 记录异或过程中的值
            int x = 0;
            for (int k = 30; k >= 0; k--) {
                int bit = (num >> k) & 1;
                if (bit == 0) {
                    if (curr.one != null) {
                        curr = curr.one;
                        // 先左移一位，再加 1
                        // 假设 x=5，在二进制表示为 101。如果我们将 x 乘以 2（或者说将 x 左移一位）
                        // 先执行 x = 2 * x，就得到 1010，在十进制中表示为 10。
                        // 然后执行 + 1，就得到 1011，在十进制中表示为 11。
                        x = 2 * x + 1;
                    } else {
                        curr = curr.zero;
                        // 在二进制中，将一个数乘以 2 等价于将它左移一位
                        // 对于一个二进制数来说，左移操作会在其右侧添加一个 0
                        // x = 2 * x => 左移一位
                        // 假设 x=5，在二进制表示为 101。如果我们将 x 乘以 2（或者说将 x 左移一位）
                        // 我们会得到 1010，在十进制中表示为 10
                        x = 2 * x;
                    }
                } else {
                    if (curr.zero != null) {
                        curr = curr.zero;
                        x = 2 * x + 1;
                    } else {
                        curr = curr.one;
                        x = 2 * x;
                    }
                }
            }
            return x;
        }
    }
}


