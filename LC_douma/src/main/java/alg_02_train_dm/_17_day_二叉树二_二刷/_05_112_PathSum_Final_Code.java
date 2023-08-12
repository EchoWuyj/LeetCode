package alg_02_train_dm._17_day_二叉树二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:53
 * @Version 1.0
 */
public class _05_112_PathSum_Final_Code {

    // 优化：剪枝
    public boolean hasPathSum(TreeNode root, int target) {
        if (root == null) return false;

        // 若不单独计算 curTargetSum，则需要保证使用到 curTargetSum 位置
        // 都替换成 target - root.val，见 hasPathSum1 代码
        int curTargetSum = target - root.val;

        if (root.left == null && root.right == null) {
            return curTargetSum == 0;
        }

        boolean left = hasPathSum(root.left, curTargetSum);
        // 提前退出 => 剪枝操作，提高性能
        // 注意 返回值 boolean 处理方式
        if (left) return true;
        boolean right = hasPathSum(root.right, curTargetSum);

        return left || right;
    }

    public boolean hasPathSum1(TreeNode root, int target) {

        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return target - root.val == 0;
        }

        boolean left = hasPathSum(root.left, target - root.val);
        if (left) return true;
        boolean right = hasPathSum(root.right, target - root.val);

        return left || right;
    }

    // 简化
    public boolean hasPathSum2(TreeNode root, int target) {
        if (root == null) return false;
        target -= root.val;
        if (root.left == null && root.right == null) {
            return target == 0;
        }

        // 区别：res 添加路径 path，没有 return 语句
//        if (node.left == null && node.right == null) {
//            res.add(new ArrayList<>(path);
//        }

        boolean left = hasPathSum(root.left, target);
        if (left) return true;
        boolean right = hasPathSum(root.right, target);
        return left || right;

        //             1
        //           /  \
        //          2    3   target = 10
        //         /\   / \
        // node → 4  5  6  7
        //
        // 叶子节点 node(4)，if 判断中有 return，不会递归往下遍历空子树
        // 但是 if (root == null) return false 还得正常写，不能将其省略，保证 root.left 和 root.right 不空指针异常

        //            1[T]
        //           /    \
        //         2[F]    3[T]     target = 10
        //         / \     /  \
        //      4[F] 5[F] 6[T] 7[F]

    }
}
