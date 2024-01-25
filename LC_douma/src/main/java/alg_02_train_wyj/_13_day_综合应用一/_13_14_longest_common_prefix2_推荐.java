package alg_02_train_wyj._13_day_综合应用一;

/**
 * @Author Wuyj
 * @DateTime 2023-11-20 10:54
 * @Version 1.0
 */
public class _13_14_longest_common_prefix2_推荐 {
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        int row = strs.length;
        int col = strs[0].length();
        String res = "";

        for (int j = 0; j < col; j++) {
            char first = strs[0].charAt(j);
            for (int i = 1; i < row; i++) {
                if (j == strs[i].length() || strs[i].charAt(j) != first) {
                    return strs[0].substring(0, j);
                }
            }
        }
        return strs[0];
    }
}
