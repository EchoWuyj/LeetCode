package alg_02_train_wyj._26_day_动态规划一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 11:59
 * @Version 1.0
 */
public class _10_516_LongestPalindromeSubseq1 {
    public List<String> subSeqs(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, 0, "", res);
        return res;
    }

    private void dfs(String s, int index, String subSeq, List<String> res) {
        if (index != 0) {
            res.add(subSeq);
        }

        for (int i = index; i < s.length(); i++) {
            dfs(s, i + 1, subSeq + s.charAt(i), res);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _10_516_LongestPalindromeSubseq1().subSeqs("bbbab"));
    }
}
