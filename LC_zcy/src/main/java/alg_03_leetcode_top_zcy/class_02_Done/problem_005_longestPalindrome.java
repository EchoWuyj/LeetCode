package alg_03_leetcode_top_zcy.class_02_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-14 14:04
 * @Version 1.0
 */

// 最长回文子串
public class problem_005_longestPalindrome {

    /*
        给你一个字符串s,找到s中最长的回文子串

        输入:s = "cbbd"  输出:"bb"
        输入:s = "babad" 输出:"bab" 解释:"aba" 同样是符合题意的答案
     */

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);

        // 回文半径的大小
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中:R代表最右的扩成功的位置
        // coding:最右的扩成功位置的,再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        int centerIndex = -1;
        for (int i = 0; i < str.length; i++) {
            // 将i在R外,i在R内中的a,b,c中,一共的4种情况(全部),将不需要验证的情况单独抽取
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            /*
             R第一个违规的位置,i>=R,表示R在i外 []|R,i>=R,表示i在R外,只有自己一个回文字符
             2*C-i为i的对称点i'位置,由(i'+i)/2 = C推到
             i'的回文半径和 R-i谁小就取谁
             */

            // 右不越界,并且左侧不越界,在str[]中使用了这两个索引
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                // 考虑i在R内的c子情况的右侧的位置,不确定是否和左侧是否相等,故需要验证
                // 能左右扩,则回文半径+1
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    // 回文半径+1
                    pArr[i]++;
                } else {
                    // 停止扩
                    break;
                }
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            // 这里是求的是包含"#"的回文半径
            // "121" -> "#1#2#1#" 回文半径为4,对应原始串长度为4-1=3
            // "1221" -> "#1#2#2#1#" 回文半径为5,对应原始串长度为5-1=4
            // max = Math.max(max, pArr[i]);
            if (pArr[i] > max) {
                max = pArr[i];
                // 记录最长回文子串的对称中心的位置
                centerIndex = i;
            }
        }
        // KeyPoint 通过回文长度数组找出最长的回文串 => 方式一
        // 本来centerIndex+max-1,但是substring缘故需要再+1,故为centerIndex+max
        // substring(start,end) end是不包括的,若想要截取的字符串包括end,则得输入end+1
        StringBuilder res = new StringBuilder();
        for (int i = centerIndex - (max - 1); i <= centerIndex + max - 1; i++) {
            if (str[i] != '#') {
                res.append(str[i]);
            }
        }
        return res.toString();
    }

    public static char[] manacherString(String string) {
        char[] chars = string.toCharArray();
        char[] res = new char[string.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            // 在偶数位置赋值为'#'
            // 注意是在chars数组中进行赋值操作
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String s = longestPalindrome("121");
        System.out.println(s);
    }
}





