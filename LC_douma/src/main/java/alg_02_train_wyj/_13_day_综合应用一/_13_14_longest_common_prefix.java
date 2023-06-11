package alg_02_train_wyj._13_day_综合应用一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 21:14
 * @Version 1.0
 */
public class _13_14_longest_common_prefix {

    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        int rows = strs.length;
        int cols = strs[0].length();
        for (int i = 0; i < cols; i++) {
            char firstChar = strs[0].charAt(i);
            for (int j = 1; j < rows; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != firstChar) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        int n = strs.length;
        for (int i = 1; i < n; i++) {
            prefix = help(prefix, strs[i]);
            if (prefix.length() == 0) return "";
        }
        return prefix;
    }

    public String help(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }

        return str1.substring(0, index);
    }

    public String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) return "";
        return lcp(strs, 0, strs.length - 1);
    }

    public String lcp(String[] strs, int left, int right) {
        if (left == right) return strs[left];
        int mid = left + (right - left) / 2;
        String leftStr = lcp(strs, left, mid);
        String rightStr = lcp(strs, mid + 1, right);
        return help(leftStr, rightStr);
    }
}
