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
        findSubSeq(s, 0, "", res);
        return res;
    }

    public void findSubSeq(String s, int start, String subSeq, List<String> res) {
        if (start != 0) res.add(subSeq);
        for (int i = start; i < s.length(); i++) {
            findSubSeq(s, i + 1, subSeq + s.charAt(i), res);
        }
    }
}
