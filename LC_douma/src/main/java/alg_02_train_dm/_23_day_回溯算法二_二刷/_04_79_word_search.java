package alg_02_train_dm._23_day_回溯算法二_二刷;

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
        其中 "相邻" 单元格是那些 水平相邻 或 垂直相邻 的单元格。
        同一个单元格内的字母不允许被重复使用。

        输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
        word = "ABCCED"
        输出：true

        提示：
        m == board.length
        n = board[i].length
        1 <= m, n <= 6
        1 <= word.length <= 15
        board 和 word 仅由大小写英文字母组成

     */

    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        this.visited = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // 1.若 第一个字符匹配，即 [row][col] == charAt(0)，再去通过 dfs 判断本字符及其后续字符
                // 2.若 [row][col] 和 charAt(0) 匹配，后续就不用 dfs 进行遍历了，必然不匹配，从而提高算法性能
                if (board[row][col] == word.charAt(0)) {
                    if (dfs(board, word, m, n, row, col, 0)) return true;
                }
            }
        }
        // 只有在字符网格中，完整找到字符串单词，这样才返回 true，否则返回 false
        return false;
    }

    // 判断在格子中是否存在单词 word
    // 存在返回 true，不存在返回 false，返回值类型为 boolean
    private boolean dfs(char[][] board, String word,
                        int m, int n,
                        int row, int col, int index) {

        // 在调用 dfs 之前，if 判断条件中，先对 word 中 index + 1 索引进行了判断
        // 故 index == word.length()-1，就已经是递归边界了，而不是之前的 index == word.length()
        // KeyPoint 关键：判断条件在 dfs 之前，还是在 dfs 之后( dfs 函数最开始位置)，导致递归边界不同
        if (index == word.length() - 1) return true;

        // 该节点已经访问过了
        visited[row][col] = true;

        // 从 4 个方向遍历
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            // 经过 dir 更新之后，使用的是更新之后的坐标 (nextRow,nextCol)
            // 在边界范围内，执行 dfs
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n
                    && !visited[nextRow][nextCol]
                    // 还要附加一个判断条件
                    && board[nextRow][nextCol] == word.charAt(index + 1)) {
                if (dfs(board, word, m, n, nextRow, nextCol, index + 1)) return true;
            }
        }

        // KeyPoint
        // 图章节中的 floodFill 算法，回溯过程没有将已经遍历过的节点还原"未标记状态"，
        // 即没有执行清除现场操作，而本题中需要执行'清除现场操作'
        visited[row][col] = false;

        // 没有找到，返回 false
        return false;
    }

    // KeyPoint 另外一种 dfs 写法，注意两者写法上的区别
    public boolean dfs1(char[][] board, String word,
                        int m, int n, int row, int col, int index) {

        // 递归边界到 word.length()
        if (index == word.length()) return true;
        // 判断条件在 dfs 之后
        if (row < 0 || row >= m || col < 0 || col >= n ||
                visited[row][col] || board[row][col] != word.charAt(index))
            return false;

        visited[row][col] = true;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (dfs1(board, word, m, n, nextRow, nextCol, index + 1)) return true;
        }

        visited[row][col] = false;
        return false;
    }

    // 总结：一个是在递归之前判断，一个是在到递归边界再判断
}
