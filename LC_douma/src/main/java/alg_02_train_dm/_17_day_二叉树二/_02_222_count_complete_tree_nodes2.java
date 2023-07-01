package alg_02_train_dm._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-30 19:18
 * @Version 1.0
 */
public class _02_222_count_complete_tree_nodes2 {

    // KeyPoint 方法二 二分查找
    // 时间复杂度：O((logn)^2) < O(n)
    // 若输入规模 n 比较小，O(n) 算法可能比 O((logn)^2) 算法更快
    // 但随着n的增大，O((logn)^2) 算法会比 O(n) 算法更快
    public int countNode1(TreeNode root) {
        if (root == null) return 0;

        // 计算完全二叉树的最大层数
        // 层数从 0 开始，2^0 =1，表示第 0 层的节点数
        int level = 0;
        TreeNode node = root;
        // 因为是完全二叉树，所有的子节点都在最后一层的左边
        // 故通过一直向左遍历，可以到最后一层，从而确定完全二叉树的层次
        while (node.left != null) {
            level++;
            node = node.left;
        }

        // 通过 level 信息来计算完全二叉树的节点数的范围
        // 每层第一个节点序号：2^level
        // 最后一层 start 节点和 end 节点序号：[2^level, 2^(level + 1) - 1]
        // 在最后一层上(有序数列)上使用二分查找

        //                  level   节点个数
        //        1           0      2^0 = 1
        //       / \
        //      2   3         1      2^1 = 2
        //     / \ / \
        //    4  5 6  7       2      2^2 = 4  start：2^level，end：2^(level+1)-1
        //   / \
        //  8  9              3

        // 左移 level 位，相当于乘以 2 的 level 次方
        // 注意：代码中 ^ 表示位运算，不是次方，次方使用 Math.pow
        // 1 << 6 <=> 1 × 64(2^6) = 64
        // 3 << 4 <=> 3 × 16(2^4) = 48
        // 注意：通过加 () 提高与运算优先级
        int low = 1 << level, high = (1 << (level + 1)) - 1;

        // while 循环结束，low = high，找到完全二叉树 '最后一个' 节点
        // 时间复杂度 O(logn)
        while (low < high) {
            // KeyPoint 注意：这里 high - low 需要 + 1，详细看二分查找那里
            int mid = low + (high - low + 1) / 2;
            // 判断以 root 为根节点的树是否存在 mid 的节点

            // 参考写法
//            if (exists(root, level, mid)) {
//                low = mid;
//            } else {
//                // 不存在严格排除在外
//                high = mid - 1;
//            }

            // 另外一种写法，标准格式写法
            if (!exists(root, level, mid)) { // O(logn)
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }

    // 判断在 root 为根节点的树中是否存在节点 mid
    // 时间复杂度 O(logn)
    private boolean exists(TreeNode root, int level, int mid) {

        // 根据完全二叉树的最后一层节点的二进制的 0，1 来判断其左右
        // 0 表示在左边， 1 表示在右边

        // 最后一层某一节点的二进制 1|0111
        // 最高位 1，表示该节点在第 4 层(从第 0 层开始计算)，判断左右时，最高位不用管
        // 只要处理剩余的 4 位 0111 ，表示左右右右，从而找到相应的节点

        // mask 是除了最高位 1 之外，剩下的位，0|1000，只有 level(4) 为 1，其余位全为 0
        // 用于按位比较节点除高位外的剩余位数，确定其左右位置

        // level = 4，00001 移动 3 位 01000
        int mask = 1 << (level - 1);
        TreeNode node = root;
        // node 自身保证不为 null，否则提前结束 while，return 判断
        // mask = 0，则说明 node 指针已经是叶子节点那一层了，退出 while 循环，return 进行判断
        while (node != null && mask > 0) {
            // mask 和 mid 进行按位与操作，且 mask 只有 level 为 1，其余位全为 0
            if ((mask & mid) == 0) { // mid 对应位 0
                node = node.left;
            } else { // mid 对应位 1
                node = node.right;
            }
            // 判断 mid 的下一位，对应 mask 右移一位，根据与的结果判断左右
            // 因为 mask 为正数，正数右移补 0 ，故不存在越界
            mask >>= 1;
        }
        // 判断 node 是否为 null
        return node != null;
    }
}
