package alg_02_train_wyj._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-08 19:08
 * @Version 1.0
 */
public class _09_666_path_sum_iv {

    private static int res;

    public static int pathSum(int[] nums) {
        Integer[] tree = new Integer[15];
        for (int num : nums) {
            int bai = num / 100;
            int shi = num % 100 / 10;
            int ge = num % 10;
            int index = ((1 << (bai - 1)) - 1) + shi - 1;
            tree[index] = ge;
        }

        dfs(tree, 0, 0);
        return res;
    }

    private static void dfs(Integer[] tree, int index, int pathSum) {
        if (tree[index] == null) return;
        pathSum += tree[index];
        if (index >= 7 || (tree[2 * index + 1] == null && tree[2 * index + 2] == null)) {
            res += pathSum;
            // System.out.println(res);
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
