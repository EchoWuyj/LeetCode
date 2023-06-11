package alg_03_leetcode_top_wyj.class_10;

import javax.sound.sampled.spi.FormatConversionProvider;

/**
 * @Author Wuyj
 * @DateTime 2023-02-27 15:50
 * @Version 1.0
 */
public class Problem_0079_WordSearch {
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, i, j, 0, words))
                    return true;
            }
        }
        return false;
    }

    public static boolean process(char[][] board, int i, int j, int index, char[] words) {
        if (index == words.length) {
            return true;
        }

        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return false;
        }

        if (words[index] != board[i][j]) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = 0;

        boolean ans = process(board, i + 1, j, index + 1, words)
                || process(board, i - 1, j, index + 1, words)
                || process(board, i, j - 1, index + 1, words)
                || process(board, i, j + 1, index + 1, words);
        board[i][j] = temp;
        return ans;
    }
}
