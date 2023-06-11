package alg_02_train_wyj._09_day_哈希查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 16:38
 * @Version 1.0
 */
public class _04_389_find_the_difference {
    public char findTheDifference1(String s, String t) {
        int[] countS = new int[26];
        for (char c : s.toCharArray()) countS[c - 'a']++;
        for (char c : t.toCharArray()) {
            countS[c - 'a']--;
            if (countS[c - 'a'] < 0) return c;
        }
        return ' ';
    }

    public char findTheDifference2(String s, String t) {
        int as = 0, at = 0;
        for (char c : s.toCharArray()) as += c;
        for (char c : t.toCharArray()) at += c;
        return (char) (at - as);
    }

    public char findTheDifference(String s, String t) {
        int res = 0;
        for (char c : s.toCharArray()) res ^= c;
        for (char c : t.toCharArray()) res ^= c;
        return (char) res;
    }
}
