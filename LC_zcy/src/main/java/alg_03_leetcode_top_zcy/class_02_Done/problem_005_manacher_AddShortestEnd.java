package alg_03_leetcode_top_zcy.class_02_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-14 15:56
 * @Version 1.0
 */

public class problem_005_manacher_AddShortestEnd {

    // 问题:字符串str后面,最少添加几个字符才能使得整体都变成回文串
    // 本题实质是在求"必须包含最后一个字符的情况下最长回文子串的长度"
    // 例如:abc12321,在后面添加cba,整体都变成了回文串abc123321cba
    // 最后一个字符'1'的最长回文子串12321,原字符串剩余abc逆序添加到结尾即可
    // 所以改动一下Manacher算法,当最右回文边界中心C往右扩的位置到了字符串的边界,
    // 那么就可以停了,此时就找到了包含最后一个字符的情况下的最长回文串

    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int maxContainsEnd = -1;
        int flag = -1;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            // KeyPoint 修改Manacher算法
            // 当某个i位置的R已经向右扩到str最后一个字符位置,此时i是最早右扩到str最后一个字符位置的中心
            // 在通过回文半径来求出回文区域,从而得到最后一个字符的情况下最长回文子串,再通过索引范围获取原字符串剩余部分

            // # a # b # c # b # 2 # 2 #
            //   i )
            //           i     )
            //                     i   )  √
            // i位置最早右扩到str最后一个字符位置的中心
            // #a#b#c#b|#2#2#
            // "#2#2#"即为必须包含最后一个字符的情况下最长回文子串的长度
            // 而前面不是的部分"#a#b#c#b",将逆序填到后面即可

            // R已经向右扩到str最后一个字符位置,此时break
            if (R == str.length) {
                maxContainsEnd = pArr[i];
                flag = i;
                break;
            }
        }

        // KeyPoint 推荐(易于理解)
        StringBuilder res = new StringBuilder();
        for (int i = flag - (maxContainsEnd - 1); i >= 0; i--) {
            if (str[i] != '#') {
                res.append(str[i]);
            }
        }
        return res.toString();  // dcba

        // 坐标变化不好看懂
//        char[] res = new char[s.length() - maxContainsEnd + 1];
//        for (int i = 0; i < res.length; i++) {
//            res[res.length - 1 - i] = str[i * 2 + 1];
//        }
//        return String.valueOf(res); // dcba
    }

    public static char[] manacherString(String string) {
        char[] charArray = string.toCharArray();
        char[] res = new char[string.length() * 2 + 1];
        int index = 0;
        // 循环遍历扩展后的数组
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArray[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }
}
