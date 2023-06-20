package alg_02_train_wyj._26_day_动态规划一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 14:08
 * @Version 1.0
 */
public class _10_516_LongestPalindromeSubseq2 {

    private int maxLen = Integer.MIN_VALUE;

    public List<String> subSeqs(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, 0, "", res);
        return res;
    }

    private void dfs(String s, int index, String subSeq, List<String> res) {
        if (index != 0) {

            if (help(subSeq)) {
                maxLen = Math.max(maxLen, subSeq.length());
            }
        }

        for (int i = index; i < s.length(); i++) {
            dfs(s, i + 1, subSeq + s.charAt(i), res);
        }
    }

    private boolean help(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
