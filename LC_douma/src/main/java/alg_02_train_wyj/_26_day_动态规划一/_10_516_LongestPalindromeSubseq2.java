package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 14:08
 * @Version 1.0
 */
public class _10_516_LongestPalindromeSubseq2 {

    private int len = Integer.MIN_VALUE;

    public int longestPalindromeSubseq(String s) {
        findSubSeq(s, 0, "");
        return len;
    }

    private void findSubSeq(String s, int start, String subSeq) {
        if (start != 0) {
            if (help(subSeq)) {
                len = Math.max(len, subSeq.length());
            }
        }

        for (int i = start; i < s.length(); i++) {
            findSubSeq(s, i + 1, subSeq + s.charAt(i));
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
