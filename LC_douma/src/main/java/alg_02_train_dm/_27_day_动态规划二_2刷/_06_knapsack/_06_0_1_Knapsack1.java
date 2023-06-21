package alg_02_train_dm._27_day_动态规划二_2刷._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 20:12
 * @Version 1.0
 */
public class _06_0_1_Knapsack1 {

    /*
        0-1 背包问题
        有一个背包，它的容量为 C
        现在有 n 种不同的物品，他们的编号分别是 0，...，n-1，每一种物品只有一个
        在这 n 种物品中，第 i 个物品的重量是 w[i]，它的价值为 v[i]
        问题是：可以向这个背包中放哪些物品，使得在不超过背包容量的基础上，背包中物品的总价值最大

        KeyPoint 补充：解释 0-1
        每一种物品只有一个，在做选择时，要么选择该物品，要么不选择该物品，从而体现 0-1
     */

    private int[] w;
    private int[] v;
    private int maxValue = 0;

    public int knapsack(int[] w, int[] v, int capacity) {
        this.w = w;
        this.v = v;
        // 索引从 -1 开始，不能从 0 开始，相当于 root 为 -1
        dfs(-1, capacity, 0);
        return maxValue;
    }

    // DFS 先序遍历 => 没法转 dp，需要后序 DFS 遍历
    // 抽象树形结构，节点构成 [物品索引，剩余容量，累计价值]，故 dfs 形参为这三个参数
    private void dfs(int index, int capacity, int currValue) {

        // 通过'剪枝'方式代替了递归边界，即在 dfs 前，先做判断
        // 若能执行 dfs，则表示递归函数中，传递的形参都是有效的，可以更新 maxValue

        // 1.先序遍历，处理当前节点(父节点)
        // 针对遍历到的每个节点都比较是否为最大值
        maxValue = Math.max(maxValue, currValue);

        // 2.处理子节点
        // dfs 存在重复计算，需要剪枝 => 通过 子节点物品索引 > 父节点物品索引 实现
        // => 索引递增，不能回头

        // 如：红蓝，蓝红，只是顺序相反， 其余是一样的，而背包是不考虑顺序的，故需要剪枝
        // 每个物品只有一个，只能放一次

        // KeyPoint 同一层 for 循环中，循环变量 i 变化，相当于同一层的多个分支，互补影响
        //          当调用 dfs 时，下层 for 循环，相当于子节点的多个分支，和父节点分支没有关系
        for (int i = index; i < w.length; i++) {
            // index 只是起始值，循环变量是 i
            // 故 subIndex = i + 1，subIndex 不是 index +1
            int subIndex = i + 1;
            // 剪枝 => 避免无效递归
            // 在 dfs前，先做判断，通过'剪枝'方式代替了递归边界
            if (subIndex == w.length || capacity < w[subIndex]) continue;
            // dfs 中传入更新后的形参
            dfs(subIndex, capacity - w[subIndex], currValue + v[subIndex]);
        }
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack1 k = new _06_0_1_Knapsack1();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};
        System.out.println(k.knapsack(w, v, 10)); // 27
    }


    /*
        KeyPoint 区别：求字符串所有子序列
        public List<String> subSeqs(String s) {
            List<String> res = new ArrayList<>();
            findSubSeq(s, 0, "", res);
            return res;
        }

        // 一个字符串子序列(一个数组子序列)
        // => 本质：求字符串(数组)子集
        private void findSubSeq(String s, int index, String subSeq, List<String> res) {
            // 将 "" 排除在外
            // 此外每次添加一个子节点形成的新组合，都是一个子序列，需要将其添加到 res 中
            if (index != 0) res.add(subSeq);
            // index 参数用于剪枝
            for (int i = index; i < s.length(); i++) {
                // 子节点选择字符，必然是从 i+1 开始的，子节点开始索引必然大于父节点结束索引
                // subSeq + s.charAt(i) 每个字符拼接，都将其加入到 res 中，从而保证子集完整
                findSubSeq(s, i + 1, subSeq + s.charAt(i), res);
            }
        }

     */
}
