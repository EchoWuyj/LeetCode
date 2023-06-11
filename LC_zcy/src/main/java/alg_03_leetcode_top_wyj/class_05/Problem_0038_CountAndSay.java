package alg_03_leetcode_top_wyj.class_05;

/**
 * @Author Wuyj
 * @DateTime 2023-02-19 13:04
 * @Version 1.0
 */
public class Problem_0038_CountAndSay {

    public static String countAndSay(int n) {
        if (n < 1) {
            return "";
        }
        if (n == 1) {
            return "1";
        }

        char[] chars = countAndSay(n - 1).toCharArray();
        StringBuilder res = new StringBuilder();
        int times = 1;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                times++;
            } else {
                res.append(times);
                res.append(chars[i]);
                times = 1;
            }
        }
        res.append(times);
        res.append(chars[chars.length - 1]);
        return res.toString();
    }
}
