package alg_03_leetcode_top_zcy.class_10;

/**
 * @Author Wuyj
 * @DateTime 2023-02-27 11:46
 * @Version 1.0
 */

// 单词搜索
public class Problem_0079_WordSearch {

    /*
        给定一个m*n二维字符网格board和一个字符串单词word,如果word存在于网格中,返回true;否则,返回false
        单词必须按照字母顺序,通过相邻的单元格内的字母构成,其中"相邻"单元格是那些水平相邻或垂直相邻的单元格
        注意:同一个单元格内的字母不允许被重复使用
        
        输入:"board = [["A","B","C","E"],
			           ["S","F","C","S"],
			           ["A","D","E","E"]],
			  word = "ABCCED"
        输出:"true"

        KeyPoint 小技巧:深度优先,增加现场痕迹

        暴力递归范围  > 动态规划范围
        有些题目只需要暴力尝试就能足以拿下了,不需要改成动态规划的,通过递归函数形参来确定
        本题递归是不用修改成dp的,因为本题中可变参数char[][]b复杂程度超过整型以上,若是修改dp非常麻烦

        KeyPoint 面试场遇到的所有dp题都可以从递归入手转dp的,没有例外
             但是面试遇到的一些递归函数是不需转dp的,事实上也修改不出来

     */
    public static boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        // 行优先,从上往下,遍历每个点进行判断
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 因为多有多个for循环,这里不能直接return,否则后面for循环就执行不了
                // 这里使用if判断来解决这个问题,只有在true条件下,才会return
                if (process(board, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 目前到达了b[i][j],word[index....]
    // 递归函数含义:从b[i][j]出发,能不能搞定word[index....]能返回true,不能返回false
    public static boolean process(char[][] b, int i, int j, char[] w, int index) {

        // index越界,没有字符,word已经找成功了
        if (index == w.length) {
            return true;
        }

        // index有字符,但是i和j到达边界
        // KeyPoint 等不到i>b.length,其实在==时就已经越界了
        if (i < 0 || i == b.length || j < 0 || j == b[0].length) {
            return false;
        }

        // 当前字符匹配不上,不用考虑后续的字符匹配
        if (b[i][j] != w[index]) {
            return false;
        }

        // KeyPoint 深度优先,增加现场痕迹,这样避免走回头路
        char tmp = b[i][j];
        // 将已经走过的位置修改成0,
        b[i][j] = 0;

        // KeyPoint 避免直接return,使用boolean变量存储,最后再去返回
        boolean ans = process(b, i - 1, j, w, index + 1)
                || process(b, i + 1, j, w, index + 1)
                || process(b, i, j - 1, w, index + 1)
                || process(b, i, j + 1, w, index + 1);

        // 返回之前,在将字符修改回去,避免影响其他路径求解
        b[i][j] = tmp;
        return ans;
    }
}
