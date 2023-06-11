package alg_03_leetcode_top_wyj.class_03;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 18:47
 * @Version 1.0
 */
public class problem_014_longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }

        int min = Integer.MAX_VALUE;
        char[] str1 = strs[0].toCharArray();
        for (String str : strs) {
            char[] str2 = str.toCharArray();
            int index = 0;
            while (index < str1.length && index < str2.length) {
                if (str1[index] != str2[index]) {
                    break;
                }
                index++;
            }
            min = Math.min(index, min);
        }
        if (min == 0) {
            return "";
        }
        return strs[0].substring(0, min);
    }
}
