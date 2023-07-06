package alg_02_train_dm._26_day_动态规划一_二刷._10_516_LongestPalindromeSubseq;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 15:28
 * @Version 1.0
 */
public class _10_516_LongestPalindromeSubseq2 {

    private int len = Integer.MIN_VALUE;

    // 1 <= s.length <= 1000
    // dfs => 时间复杂度是指数级别，必然超时
    // => 必须将回溯给优化掉，否则单纯使用 dp 判断回文，还是超时
    public int longestPalindromeSubseq(String s) {
        findSubSeq(s, 0, "");
        return len;
    }

    private void findSubSeq(String s, int start, String subSeq) {
        if (start != 0) {
            if (isPalindrome(subSeq)) {
                len = Math.max(len, subSeq.length());
            }
        }
        for (int i = start; i < s.length(); i++) {
            findSubSeq(s, i + 1, subSeq + s.charAt(i));
        }
    }

    // 判断子串是否是回文
    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
