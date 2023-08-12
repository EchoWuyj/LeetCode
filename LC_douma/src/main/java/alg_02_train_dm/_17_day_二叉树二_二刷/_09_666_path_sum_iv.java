package alg_02_train_dm._17_day_二叉树二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:55
 * @Version 1.0
 */
public class _09_666_path_sum_iv {
    /*
        666. 路径总和 IV
        对于一棵深度小于 5 的树，可以用一组三位十进制整数来表示。
        对于每个整数：
         1.百位上的数字表示：这个节点的深度 D，1 <= D <= 4。
         2.十位上的数字表示：这个节点在当前层所在的位置 P，1 <= P <= 8
           => 说明：二叉树第 4 层有 8 个节点
           位置编号与一棵满二叉树的位置编号相同
         3.个位上的数字表示这个节点的权值 V，0 <= V <= 9。
        给定一个包含三位整数的升序数组，表示一棵深度小于 5 的二叉树，
        请你返回从根到所有叶子结点的路径之和。

        输入: [113, 215, 221]
        输出: 12
        解释:
        1.先通过 [113, 215, 221] 构建二叉树
        2.再去计算：从根到所有叶子结点的路径之和

        113 => 第一层，第一个，数值 3
        215 => 第二层，第一个，数值 5
        221 => 第二层，第二个，数值 3

            3
           / \
          5   1
        路径和 = (3 + 5) + (3 + 1) = 12
 */
    private static int res = 0;

    public static int pathSum(int[] nums) {

        //            1[0]
        //          /     \
        //         2[1]    3[2]
        //       /  \      / \
        //     4[]  5[]   6[]  7[]
        //    / \   / \   / \  / \
        //   8  9 10 11 12 13 14 15 => 二叉树第 4 层有 8 个节点

        // 若索引从 0 开始，有索引关系
        // 父节点索引 index
        // => 左子索引：2*index+1
        // => 左子索引：2*index+2
        // 满二叉树(完全二叉树) => 使用数组存储，比较方便

        // 1.根节点存储：data[0]
        // 2.左子节点存储：data[2*i+ 1]
        // 3.右子节点存储：data[2i+ 2]

        // 构建二叉树，使用数组存储，比较方便
        // 4层，满二叉树一共 15 个节点
        Integer[] tree = new Integer[15];
        for (int num : nums) {
            // 获取个十百位上的数字
            // 1.百位数字表示：层数
            // 2.十位数字表示：节点在当前层所在的位置(序号)
            // 3.个位数字表示：数值本身
            int bai = num / 100;       // 321 / 100 = 3
            int shi = num % 100 / 10;  // 236 % 100 = 36，36 / 10 = 3
            int ge = num % 10;         // 25 % 10 = 5

            // KeyPoint 注意事项
            // 1.2^(level-1) => 每层第一元素序号
            //   数组索引从 0 开始，故需要 -1，减 1 不能丢
            // 2.shi => 节点在当前层所在的位置(序号)
            //   减去重复计算每层第一元素，故还得减 1
            //   => (2^level-1)+shi-1
            // 3.凡是涉及位运算，注意使用 ()，凡是涉及混合运算，就使用 () 提高优先级
            int index = ((1 << (bai - 1)) - 1) + shi - 1;
            // 个位数字表示节点值
            tree[index] = ge;
        }

        // DFS 遍历树
        dfs(tree, 0, 0);
        return res;
    }

    // pathSum：当前递归调用时，路径的累加和
    private static void dfs(Integer[] tree, int index, int pathSum) {
        // Integer 构建的数组，判空是用 null
        if (tree[index] == null) return;
        pathSum += tree[index];

        // KeyPoint 累加 pathSum 到 res 的两种情况
        // 1.最后一层叶子节点
        //   当 index >= 7 时，则为二叉树第 4 层第一个节点及其往后节点，该层节点是叶子节点，
        //   直接累加到 res，不用再去访问其左右子节点，否则，导致索引越界
        // 2.中间层叶子节点
        //   左右子节点为 null
        //   注意：加上括号，表示一个整体
        if (index >= 7 || (tree[2 * index + 1] == null && tree[2 * index + 2] == null)) {
            // 递归形参 res 传入 + dfs 后序遍历，否则累加的 res 无法传出去
            res += pathSum;
            return;
        }
        dfs(tree, 2 * index + 1, pathSum);
        dfs(tree, 2 * index + 2, pathSum);
    }

    public static void main(String[] args) {
        int[] arr = {113, 215, 221};
        System.out.println(pathSum(arr)); // 12
    }
}
