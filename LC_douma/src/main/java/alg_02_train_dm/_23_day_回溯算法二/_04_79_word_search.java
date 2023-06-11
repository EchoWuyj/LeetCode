package alg_02_train_dm._23_day_回溯算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 14:16
 * @Version 1.0
 */
public class _04_79_word_search {
      /*
        79. 单词搜索
        给定一个 m x n 二维字符网格 board 和一个字符串单词 word
        如果 word 存在于网格中，返回 true ；否则，返回 false

        单词必须按照字母顺序，通过相邻的单元格内的字母构成，
        其中"相邻"单元格是那些水平相邻或垂直相邻的单元格。
        同一个单元格内的字母不允许被重复使用。

        输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
        word = "ABCCED"
        输出：true
     */

    private char[][] board;
    private String word;

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
    private int m;
    private int n;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        this.m = board.length;
        this.n = board[0].length;
        this.visited = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // 第一个字符匹配，再去通过 dfs 判断后续字符
                if (board[row][col] == word.charAt(0)) {
                    if (dfs(row, col, 0)) return true;
                }
            }
        }
        // 只有在字符网格中，完整找到字符串单词，这样才返回 true，否则返回 false
        return false;
    }

    // 判断在格子中是否存在单词 word，存在返回 true，不存在返回 false，返回值类型为 boolean
    private boolean dfs(int row, int col, int index) {
        // 每次递归调用，先判断字符是否相等，不能直接 false，不用继续执行了
        if (board[row][col] != word.charAt(index)) return false;
        else if (index == word.length() - 1) return true;

        visited[row][col] = true;
        // 从 4 个方向遍历
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            // 经过 dir 更新之后，使用的是更新之后的坐标 (nextRow,nextCol)
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n
                    && !visited[nextRow][nextCol]) {
                if (dfs(nextRow, nextCol, index + 1)) return true;
            }
        }
        // KeyPoint
        //  1.图章节中的 floodFill，回溯过程没有将已经遍历过的节点还原"未标记状态"
        //    即没有执行清除现场操作
        //  2.本题中需要执行清除现场操作
        visited[row][col] = false;

        // 没有找到，返回 false
        return false;
    }
}
