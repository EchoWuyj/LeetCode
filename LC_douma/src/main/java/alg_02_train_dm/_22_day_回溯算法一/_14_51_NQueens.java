package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 23:53
 * @Version 1.0
 */
public class _14_51_NQueens {

    // KeyPoint
    //  1.对于复杂回溯问题，先屏蔽细节，将回溯过程抽象出来
    //    将其用代码实现后，再去实现屏蔽细节部分的代码
    //  2.本题抽取的方法比较多，设置全局变量方便不同的方法之间调用

    // n 皇后的 n
    private int n;
    // 注意:rows 存储皇后的位置，不是标记是否被行方向的皇后攻击
    // 因为 dfs 过程是一行一行放置皇后的，不同行之间必然不会攻击
    private int[] rows;
    // KeyPoint 标记不同的方向是否有皇后攻击
    // 标记是否被列方向的皇后攻击
    // cols[index] 0 或 1，表示:未被攻击 或 已被攻击
    private int[] cols;
    // 标记是否被主对角线方向的皇后攻击
    private int[] mains;
    // 标记是否被次对角线方向的皇后攻击
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

    // 回溯，在每一行中放置一个皇后
    // 返回值类型，void 类型
    private void dfs(int row) {
        if (row >= n) return;
        // 分别尝试在当前行的每一列中放置皇后
        for (int col = 0; col < n; col++) {
            // (row, col)位置，没有被攻击
            // KeyPoint 先写伪代码，具体实现细节先不考虑
            if (isNotUnderAttack(row, col)) {
                // 选择在当前的位置上放置皇后
                placeQueen(row, col);
                // 若当前行是最后一行，则找到了一个解决方案
                // KeyPoint 区别于数独，返回类型不是 boolean 类型
                if (row == n - 1) addSolution();
                // 在下一行中放置皇后
                dfs(row + 1);
                // 撤销，回溯，即将当前位置的皇后去掉
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
        // 当前位置的主对角线方向已经有皇后了
        // 加上 n-1，避免数组索引出现负数
        mains[row - col + n - 1] = 1;
        // 当前位置的次对角线方向已经有皇后了
        secondary[row + col] = 1;
    }

    // 在指定的位置上删除皇后
    private void removeQueen(int row, int col) {
        // 移除 row 行上的皇后
        // row 表示行，rows[row] = col 表示放置皇后的位置
        rows[row] = 0;

        // 其实 row 行上的皇后可以不移除的，因为我们是一行一行存储皇后的，
        // 所以每一行肯定会有一个皇后的，而且在 isNotUnderAttack 这个方法中都没有用到 rows[row] 这个值
        // 所以上面的代码可以注释掉的

        // 当前位置的列方向没有皇后了
        cols[col] = 0;
        // 当前位置的主对角线方向没有皇后了
        mains[row - col + n - 1] = 0;
        // 当前位置的次对角线方向没有皇后了
        secondary[row + col] = 0;
    }

    // 判断 row 行，col 列这个位置有没有被其他方向的皇后攻击
    private boolean isNotUnderAttack(int row, int col) {
        // 判断的逻辑是：
        // 1.当前位置的这一列方向没有皇后攻击
        // 2.当前位置的主对角线方向没有皇后攻击
        // 3.当前位置的次对角线方向没有皇后攻击
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
            for (int j = 0; j < n - col - 1; ++j) sb.append(".");
//            for (int j = col + 1; j < n; j++) sb.append(".");
            solution.add(sb.toString());
        }
        output.add(solution);
    }

    public static void main(String[] args) {
        System.out.println(new _14_51_NQueens().solveNQueens(4));
        // [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
    }
}
