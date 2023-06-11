package _06_backtrack;

/**
 * @Author Wuyj
 * @DateTime 2022-08-22 14:26
 * @Version 1.0
 */
public class Offer_12_Exist {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "ABCCED";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        // 先将字符串进行拆分，一个一个元素进行匹配
        char[] words = word.toCharArray();
        // 通过两层嵌套，覆盖矩阵中所有节点的情况，针对每个节点进行上左下右的判断
        // 只是从左上方顶点开始判断，但是不一定是从矩阵的左上方顶点开始就得找到一条路径
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 以该元素为起始点，递归检查是否符合要求
                if (dfs(board, words, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, char[] word, int i, int j, int k) {

         /*
            KeyPoint 补充  && 和 ||
            &&具有短路效果,当运算符前面的表达式结果为false时,后面的表达式不会被执行(偷懒)
            ||具有短路效果,当运算符前面的表达式结果为true时,后面的表达式不会被执行;

            && 双与(短路与) 	-> 结论:有false则false (f和&)

                false && false
                false && true
                true && false
                true && true -> 要求高，两个为 true 才为 true

            || 双或(短路或)	-> 结论:有true则true (t和|)

                false || false -> 要求低，两个为 false 才为 false
                false || true
                true || false
                true || true
       */

        // 边界情况判断
        // 行越界，列越界，矩阵元素已访问过
        // board.length 行长
        // board[0].length 列长(因为是 m*n 的矩阵，所以 board[i].length 都是相同的)
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k])
            return false;

        // 之前已经和目标字符串匹配成功了 length - 1 个字符，此时又匹配成功了最后一个元素，直接返回结果
        if (k == word.length - 1)
            return true;

        // 标记当前矩阵元素，将其修改为特殊字符 #
        // 代表此元素已访问过，防止之后搜索时重复访问。
        board[i][j] = '#';

        // 检查元素的四个方向 上 左 下 右
        boolean res
                = dfs(board, word, i, j - 1, k + 1)
                || dfs(board, word, i - 1, j, k + 1)
                || dfs(board, word, i + 1, j, k + 1)
                || dfs(board, word, i, j + 1, k + 1);

        // 回退到上一个符合的节点
        board[i][j] = word[k];
        return res;
    }
}
