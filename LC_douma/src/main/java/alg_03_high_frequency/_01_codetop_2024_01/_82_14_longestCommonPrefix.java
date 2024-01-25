package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:24
 * @Version 1.0
 */
public class _82_14_longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        // 分别定义列和行
        int row = strs.length;
        int col = strs[0].length();
        String res = "";

        for (int j = 0; j < col; j++) {
            char first = strs[0].charAt(j);
            for (int i = 1; i < row; i++) {
                if (strs[i].length() == j || strs[i].charAt(j) != first) {
                    return strs[0].substring(0, j);
                }
            }
        }
        return strs[0];
    }
}
