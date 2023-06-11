package alg_03_leetcode_top_zcy.class_04_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-17 10:50
 * @Version 1.0
 */

// 找出字符串中第一个匹配项的下标
// 标准KMP
public class Problem_0028_ImplementStrStr {
    public int strStr(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        int x = 0;
        // str2 中当前比位置(画圈的位置)
        int y = 0;

        // O(M)
        int[] next = getNextArray(str2);

        while (x < str1.length && y < str2.length) {
            // 如果比对成功,x和y共同往各自的下一个位置移动
            if (str1[x] == str2[y]) {
                x++;
                y++;
                // 表示y已经来到了0位置(最开始位置),表示str2已经不能往左跳
            } else if (next[y] == -1) {
                // str1换下一个位置进行比对
                x++;
                // y还可以通过最大前后缀长度往前移动
            } else {
                y = next[y];
            }
        }

        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] str2) {

        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;

        // 目前在哪个位置上求next数组的值
        int i = 2;

        int cn = 0;

        while (i < next.length) {
            // 配成功的时候
            if (str2[i - 1] == str2[cn]) {
                // KeyPoint 赋值使用一个等号=,不是==
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
