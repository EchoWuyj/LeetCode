package alg_02_train_wyj._13_day_综合应用一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 21:14
 * @Version 1.0
 */
public class _13_14_longest_common_prefix {

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        int n = strs.length;
        for (int i = 1; i < n; i++) {
            prefix = process(prefix, strs[i]);
            if (prefix.length() == 0) return "";
        }
        return prefix;
    }

    public String process(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return lcp(strs, 0, strs.length - 1);
    }

    public String lcp(String[] strs, int left, int right) {
        if (left == right) return strs[left];
        int mid = left + (right - left) / 2;
        String leftStr = lcp(strs, left, mid);
        String rightStr = lcp(strs, mid + 1, right);
        return process(leftStr, rightStr);
    }
}
