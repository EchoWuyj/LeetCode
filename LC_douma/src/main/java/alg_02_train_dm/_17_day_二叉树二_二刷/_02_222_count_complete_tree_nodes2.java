package alg_02_train_dm._17_day_二叉树二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-30 19:18
 * @Version 1.0
 */
public class _02_222_count_complete_tree_nodes2 {

    // KeyPoint 方法二 二分查找
    // 时间复杂度：O((logn)^2) < O(n)
    // KeyPoint 注意事项
    // 若输入规模 n 比较小，O(n) 算法可能比 O((logn)^2) 算法更快
    // 但随着n的增大，O((logn)^2) 算法会比 O(n) 算法更快
    public int countNode1(TreeNode root) {
        if (root == null) return 0;

        // 计算完全二叉树的最大层数
        // 层数从 0 开始，2^0 =1，表示第 0 层的节点数
        // KeyPoint 通过画图，找规律，从而确定 level 从 0 开始
        int level = 0;
        TreeNode node = root;
        // 因为是完全二叉树，所有的子节点都在最后一层的左边
        // 故通过一直向左遍历，可以到最后一层，从而确定完全二叉树的层次
        // KeyPoint 循环条件
        // 先判断 node.left 否为 null，再去移动 node 指针
        while (node.left != null) {
            level++;
            node = node.left;
        }

        // 通过 level 信息来计算完全二叉树的节点数的范围： start 和 end
        // 1.每层第一个节点序号：2^level
        // 2.最后一层 start 节点和 end 节点序号：[2^level, 2^(level + 1) - 1]
        // => 在最后一层上(有序数列)上使用二分查找

        //                  level   节点个数
        //        1           0      2^0 = 1
        //       / \
        //      2   3         1      2^1 = 2
        //     / \ / \
        //    4  5 6  7       2      2^2 = 4  start：2^level，end：2^(level+1)-1
        //   / \
        //  8  9              3

        // 左移 level 位，相当于乘以 2^level 次方
        // 代码中 ^ 表示位运算，不是次方，次方使用 Math.pow
        // 1 << 6 <=> 1 × 64(2^6) = 64
        // 3 << 4 <=> 3 × 16(2^4) = 48
        // => 位运算通过加 () 提高与运算优先级
        int left = 1 << level;
        int right = (1 << (level + 1)) - 1;

        // while 循环结束，left = right，找到完全二叉树 '最后一个' 节点
        while (left < right) { // O(logn)
            // 注意：这里 right - left 需要 + 1，详细看二分查找那里
            int mid = left + (right - left + 1) / 2;

            // 判断以 root 为根节点的树是否存在 mid 的节点
            // 标准格式写法
            if (!exists(root, level, mid)) { // O(logn)
                right = mid - 1;
            } else {
                // KeyPoint 规律 => 从左往右逼近
                // left 赋值操作中没有 -1，表示从左往右不断逼近
                left = mid;
            }

            // KeyPoint 从左往右逼近
//            while (left < right) {
//                // 计算中间值 mid 需要靠右，否则会有死循环
//                int mid = left + (right - left + 1) / 2;
//                if (target < nums[mid]) {
//                    right = mid - 1;
//                } else {
//                    left = mid;
//                }
//            }

            // KeyPoint 从右往左逼近
//            while (left < right) {
//                int mid = left + (right - left) / 2;
//                if (target > nums[mid]) {
//                    left = mid + 1;
//                } else {
//                    right = mid;
//                }
//            }
//            return left;

        }
        return left;
    }

    // 函数功能：判断在 root 为根节点的树中，是否存在节点 mid
    // 时间复杂度 O(logn)
    private boolean exists(TreeNode root, int level, int mid) {

        // 根据完全二叉树，最后一层节点序号的二进制，通过二进制 0 和 1 来判断其左右

        //         1
        //       /   \
        //      2     3
        //     / \   / \
        //    4  5   6  7
        //   100    110     => 序号对应二进制
        //      101    111

        // 最后一层所有节点序号，二进制第三位都是 1，表示该节点在第三层
        // 确定节点位置，即判断左右：先除去最高位，剩余的 2 位，0 表示在左边，1 表示在右边
        // 1|01 => 先左再右，节点 5 ，从而找到相应的节点

        // mask = 1 << (level-1) => mask 最后一层的上一层第一个数字(2)
        // 其二进制，最高位(第三位)为 0，次高位为 1，其余位全为 0 => 0|10
        // => 用于按位比较节点 mid，除最高位(第三位)外的剩余位数，从而确定其左右位置
        int mask = 1 << (level - 1);
        // node 从根节点开始
        TreeNode node = root;

        // 1.node 自身保证不为 null，否则结束 while 循环
        // 2.mask = 0，则说明 node 指针已经是叶子节点那一层了，结束 while 循环
        while (node != null && mask > 0) {
            // mask 和 mid 进行按位与操作
            // => 0|10 & 101 = 0
            // mid 对应位 0
            if ((mask & mid) == 0) {
                // 最开始从 root 左移，到左子节点 2
                node = node.left;
            } else {
                // mid 对应位 1
                node = node.right;
            }
            // 判断 mid 的下一位，对应 mask 右移一位，根据与的结果判断左右
            // 因为 mask 为正数，正数右移补 0 ，故不存在越界
            // KeyPoint 不要忘了 =，经常遗忘，同时 +=，等号在右边
            mask >>= 1;
        }

        // KeyPoint 判断 node 是否为 null
        // exists 函数默认语义，节点存在，返回 true => 外部调用遵守这个语义
        // 故返回值需要保持一致含义，node 不为 null 返回 true
        // 否则，若 node == null，则是 node 为 null 返回 true
        return node != null;
    }
}
