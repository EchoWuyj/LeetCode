package alg_02_train_dm._22_day_回溯算法一_2刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 23:53
 * @Version 1.0
 */
public class _14_51_NQueens {

    /*
        51. N 皇后
        按照国际象棋的规则，皇后可以攻击与之处在 同一行 或 同一列 或 同一斜线上 的棋子。
        n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
        给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
        每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

        输入：n = 4
        输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        解释：如上图所示，4 皇后问题存在两个不同的解法。

        提示：
        1 <= n <= 9

     */

    // 注意：因为本题中，抽取的模块化的代码比较多
    //       将变量定义为成员变量，方便不同方法间的调用

    // n 皇后的 n
    private int n;

    // rows 存储皇后的位置
    // index 表示：行
    // value 表示：皇后所在列
    // 注意：rows 不是标记是否被行方向的皇后攻击，dfs 过程是一行一行放置皇后的，不同行之间必然不会攻击
    private int[] rows;

    // 标记是否被列方向的皇后攻击
    // index 表示：列
    // value：0 表示：未被攻击 => 无皇后
    //        1 表示：已被攻击 => 有皇后
    private int[] cols;

    // 标记是否被 主对角线方向 的皇后攻击
    // 主对角线：row - col = 固定值，唯一标识一条主对角线
    //           为了保证 row - col >= 0，故 row - col + n - 1
    // 主对角线个数：2*n-1
    // value：0 表示：未被攻击 => 无皇后
    //        1 表示：已被攻击 => 无皇后
    private int[] mains;

    // 标记是否被 次对角线方向 的皇后攻击
    // 主对角线：row + col = 固定值，唯一标识一条主对角线
    //          因为 row + col 始终是正数，故不需要加 n - 1
    // 主对角线个数：2*n-1
    private int[] secondary;

    private List<List<String>> output;

    public List<List<String>> solveNQueens(int n) {
        // 初始化
        this.n = n;
        this.rows = new int[n];
        this.cols = new int[n];
        this.mains = new int[2 * n - 1];
        this.secondary = new int[2 * n - 1];
        this.output = new ArrayList<>();
        dfs(0);
        return output;
    }

    // KeyPoint 总结
    // 1.对于复杂回溯问题，先屏蔽细节，将回溯过程抽象出来
    //   将其用代码实现后，再去实现屏蔽细节处的代码
    // 2.本题抽取的方法比较多，设置全局变量方便，不同方法间进行调用

    // 回溯，考虑在每一行，如何放置一个皇后，故递归形参为 row
    // 思考方向：抽象成树形结构
    // => 对于 n×n 棋盘，每行有 n 种不同的放法
    // => 树形结构中每个节点的子节点有 n 种不同的分支
    //    先不考剪枝，先将回溯代码框架搭建，后续再考虑剪枝
    // 返回值类型，void 类型，因为需要将所有方案都找到，而不是找到就返回
    private void dfs(int row) {
        // 递归边界
        if (row >= n) return;
        // 分别尝试在当前行，每一列中放置皇后
        for (int col = 0; col < n; col++) {
            // KeyPoint 先写伪代码，具体实现细节先不考虑
            // 当前位置 (row, col) ，没有被攻击
            // 注意：方法名的命名方式，isXxx
            if (isNotUnderAttack(row, col)) {
                // 选择在当前的位置，放置皇后
                placeQueen(row, col);
                // 区别：数独，返回类型不是 boolean 类型
                // 若当前行是最后一行，则找到了一个解决方案
                if (row == n - 1) addSolution();
                // 在下一行中放置皇后
                dfs(row + 1);
                // 回溯，撤销，即将当前位置的皇后去掉
                removeQueen(row, col);
            }
        }
    }

    // 在指定的位置上放置皇后
    private void placeQueen(int row, int col) {
        // 在 row 行，col 列 放置皇后
        rows[row] = col;
        // 当前位置的列方向已经有皇后了
        cols[col] = 1;
        // 当前位置的 主对角线方向 已经有皇后了
        // 加上 n-1，避免数组索引出现负数
        mains[row - col + n - 1] = 1;
        // 当前位置的 次对角线方向 已经有皇后了
        secondary[row + col] = 1;
    }

    // 在指定的位置上删除皇后
    private void removeQueen(int row, int col) {
        // 移除 row 行上的皇后
        // row 表示行，rows[row] = col 表示放置皇后的位置
        rows[row] = 0;
        // 当前位置的列方向没有皇后了
        cols[col] = 0;
        // 当前位置的主对角线方向没有皇后了
        mains[row - col + n - 1] = 0;
        // 当前位置的次对角线方向没有皇后了
        secondary[row + col] = 0;
    }

    // 判断 row 行，col 列这个位置有没有被其他方向的皇后攻击
    private boolean isNotUnderAttack(int row, int col) {
        // 判断逻辑：
        // 注意：没有判断同一行，是否有皇后攻击，因为 dfs 中同一时刻 row 行只会有一个皇后，故不存在其他皇后
        //  1.当前位置的这一列方向没有皇后攻击
        //  2.当前位置的主对角线方向没有皇后攻击
        //  3.当前位置的次对角线方向没有皇后攻击
        int res = cols[col] + mains[row - col + n - 1] + secondary[row + col];
        // 如果三个方向都没有攻击的话，则 res = 0，即当前位置不被任何的皇后攻击
        return res == 0;
    }

    private void addSolution() {
        List<String> solution = new ArrayList<String>();
        // 所有 Q 位置都存储在 rows 数组中，可以从中获取
        // 即遍历每行，确定 Q 的列数，拼接成 ..Q.. 形式
        for (int i = 0; i < n; i++) {
            // 每行皇后的列数
            int col = rows[i];
            // ..Q..
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < col; ++j) sb.append(".");
            sb.append("Q");
            for (int j = col + 1; j < n; j++) sb.append(".");
            // ArrayList 集合，add 元素， 默认是通过 , 分隔 (HashSet，HashMap 同理)
            solution.add(sb.toString());
        }
        output.add(solution);
    }

    public static void main(String[] args) {
        System.out.println(new _14_51_NQueens().solveNQueens(4));
        // [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
    }
}
