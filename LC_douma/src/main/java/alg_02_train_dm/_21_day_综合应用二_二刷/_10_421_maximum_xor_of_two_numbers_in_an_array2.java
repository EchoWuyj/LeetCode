package alg_02_train_dm._21_day_综合应用二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-03 20:50
 * @Version 1.0
 */
public class _10_421_maximum_xor_of_two_numbers_in_an_array2 {

    // 3 10 5 8 25
    //        ↑ ↑
    //        j i

    // 3  | 0 0 0 | 0 | 0 | 0 | 1 1
    // 10 | 0 0 0 | 0 | 1 | 0 | 1 0
    // 5  | 0 0 0 | 0 | 0 | 1 | 0 1 ← j
    // 8  | 0 0 0 | 0 | 1 | 0 | 0 0
    // 25 | 0 0 0 | 1 | 1 | 0 | 0 1 ← i
    //            | ↑ | ↑ | ↑ |

    // 异或值最大，关键找不同
    // 只有不同的位越多 & 最高位不同 => 异或值越大

    // 构造二进制前缀树 => 左 0 右 1

    // KeyPoint 方法二 二进制前缀树
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        Trie trie = new Trie();
        int maxValue = Integer.MIN_VALUE;

        // 遍历数组中每个元素
        // 1.将当前元素 nums[i] 的前一个元素 nums[i-1] 加入前缀树
        // 2.每次计算 maxValue 都是 Math.max(maxValue, trie.search(nums[i])
        for (int i = 1; i < n; i++) {
            trie.add(nums[i - 1]);
            maxValue = Math.max(maxValue, trie.search(nums[i]));
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

        // 函数功能：将 num 的二进制从高位到低位添加到前缀树中
        // O(32) -> O(1)
        public void add(int num) {
            Node cur = root;
            //      0100 0000 0000 0000 0000 0000 0000 0000
            //      >> 30 => 往右移动 30位

            //      0000 0000 0000 0000 0000 0000 0000 0001
            // & 1  0000 0000 0000 0000 0000 0000 0000 0001
            // ---------------------------------------------
            //      0000 0000 0000 0000 0000 0000 0000 0001

            // 从最高位开始获取该位值
            // 整数的最高一位是符号位，对于正数的话是 0，所有数都是一样的，故最高位可以跳过
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (bit == 0) {
                    // 当前节点的左子节点是否为 null
                    // 1.若为 null，则创建 Node 节点
                    // 2.若不为 null，cur 则移动到该位置上
                    if (cur.zero == null) cur.zero = new Node();
                    cur = cur.zero;
                } else { // bit == 1
                    // 当前节点的右子节点是否为 null
                    // 1.若为 null，则创建 Node 节点
                    // 2.若不为 null，cur 则移动到该位置上
                    if (cur.one == null) cur.one = new Node();
                    cur = cur.one;
                }
            }
        }

        // 函数功能：在前缀树中查找和 num 进行异或，得到的最大的异或值
        // 要使得异或值最大，则需要：在有的选的情况下，尽量选择不同二进制，这样异或结果才最大
        // O(32) -> O(1)
        public int search(int num) {
            Node cur = root;
            // 记录异或过程中的最大异或值
            int res = 0;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                // num 的当前 bit 为 0
                if (bit == 0) {
                    // 在前缀树中，为了使得异或值最大，想找 1
                    // 有 1 则往 1 移动，没有 1 则往 0 移动
                    if (cur.one != null) {
                        cur = cur.one;
                        // 先左移一位，再加 1
                        // 假设 res = 5，在二进制表示为 101，如果我们将 res 乘以 2（或者说将 res 左移一位）
                        // 先执行 res = 2 * res，就得到 1010，在十进制中表示为 10。
                        // 然后执行 + 1，就得到 1011，在十进制中表示为 11。
                        res = 2 * res + 1;
                    } else {
                        // cur.one == null
                        cur = cur.zero;
                        // 在二进制中，将一个数乘以 2 等价于将它左移一位
                        // 对于一个二进制数来说，左移操作会在其右侧添加一个 0
                        // res = 2 * res => 左移一位
                        // 假设 res=5，在二进制表示为 101。如果我们将 res 乘以 2（或者说将 res 左移一位）
                        // 我们会得到 1010，在十进制中表示为 10
                        res = 2 * res;
                    }
                } else {// bit == 1
                    if (cur.zero != null) {
                        cur = cur.zero;
                        res = 2 * res + 1;
                    } else {
                        cur = cur.one;
                        res = 2 * res;
                    }
                }
            }
            return res;
        }
    }
}
