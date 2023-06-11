package _03_binary_tree;

/**
 * @Author Wuyj
 * @DateTime 2022-08-24 12:55
 * @Version 1.0
 */
public class Offer_26_IsSubStructure {
    public static void main(String[] args) {

    }

    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        // 一开始如果 A 或者 B 为空，直接返回 false
        // 因为题目约定空树不是任意一个树的子结构
        if (A == null || B == null) {
            return false;
        }

        // 接下来去以下几种情况
        // A 的根节点 VS B 的根节点（ A 的左右子树的节点 VS B 的根节点）

        // 1、A 的根节点和 B 的根节点相同情况，依次比较它们的子节点
        // isSub(A, B)

        // 2、A 的根节点和 B 的根节点不相同情况， A 的左子树 VS B 的根节点
        // isSubStructure(A.left, B)

        // 3、A 的根节点和 B 的根节点不相同情况， A 的右子树 VS B 的根节点
        // isSubStructure(A.right, B)

        // KeyPoint 比较情况是以下几种情况的之一，故使用 || 来表示，而是不是 &&
        return isSub(A, B)
                || isSubStructure(A.left, B)
                || isSubStructure(A.right, B);
    }

    public static boolean isSub(TreeNode A, TreeNode B) {

        // KeyPoint 在递归调用之前，都是需要进行条件判断的,即确定递归边界

        // A 和 B 不匹配的情况有很多，我们需要一开始去找它们完全匹配的情况
        // 即遍历完 B 直到为 null，说明 B 的全部节点都和 A 的子结构匹配上
        if (B == null) {
            return true;
        }

        // A 中的节点为空，但 B 中的节点不为空，说明不匹配
        // KeyPoint 经过上面一个 if 判断，此时 B 节点必然不为 null
        if (A == null) {
            return false;
        }

        //  A 和 B 都不为空，但数值不同，说明不匹配
        if (A.val != B.val) {
            return false;
        }

        // KeyPoint 上面的判断都已经通过了，则说明两个节点是相同的，则需要判断左右子树是否相同
        // 此时，当前这个点是匹配的，继续递归判断左子树和右子树是否「分别匹配」
        return isSub(A.left, B.left) && isSub(A.right, B.right);

    }
}
