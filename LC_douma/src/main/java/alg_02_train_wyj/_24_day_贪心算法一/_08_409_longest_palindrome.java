package alg_02_train_wyj._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 15:38
 * @Version 1.0
 */
public class _08_409_longest_palindrome {

    public int longestPalindrome(String s) {
        int[] counts = new int[128];
        for (char c : s.toCharArray()) {
            counts[c]++;
        }
        int res = 0;
        for (int count : counts) {
            res += (count / 2) * 2;
            if (count % 2 == 1 && res % 2 == 0) {
                res++;
            }
        }
        return res;
    }
}
