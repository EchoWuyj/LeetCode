package alg_02_train_dm._13_day_综合应用一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 21:05
 * @Version 1.0
 */
public class _13_14_longest_common_prefix {
      /*
         14. 最长公共前缀
        编写一个函数来查找字符串数组中的最长公共前缀。

        如果不存在公共前缀，返回空字符串 ""。

        示例 1：
        输入：strs = ["flower","flow","flight"]
        输出："fl"

        示例 2：
        输入：strs = ["dog","racecar","car"]
        输出：""

        提示：
        0 <= strs.length <= 200
        0 <= strs[i].length <= 200
        strs[i] 仅由小写英文字母组成

     */

    // KeyPoint 方法一 纵向扫描
    // 时间复杂度：O(mn
    // 其中 m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(1)
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        // 将多个单词看成二维矩阵，纵向扫描每一列
        int rows = strs.length;
        // 最长公共前缀长度 <= 第一单词长度
        int cols = strs[0].length();
        // 先确定列
        for (int i = 0; i < cols; i++) {
            char firstChar = strs[0].charAt(i);
            // 逐行比较，从第二行开始
            for (int j = 1; j < rows; j++) {
                // 若某行 strs[j] 已经遍历到结尾，即 strs[j].length() == i
                // 根据木桶效应，最长公共前缀，只能为最短的字符串
                if (strs[j].length() == i || strs[j].charAt(i) != firstChar) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    // KeyPoint 方法二 横向扫描
    // 时间复杂度：O(mn)
    // 其中 m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(1)
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = help(prefix, strs[i]);
            // prefix 长度为 0，提前退出，不需要再进行后续 for 循环遍历
            if (prefix.length() == 0) return "";
        }
        return prefix;
    }

    // 原始方法名：longestCommonPrefix 太长，有点麻烦，没有 help 方法名简单
    private String help(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    // KeyPoint 方法三 分治思想
    // 分治思想 => 先俩俩求最长公共前缀，之后再俩俩求最长公共前缀
    // 时间复杂度：O(mn)
    // 其中 m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(mlogn) 空间复杂度主要取决于递归调用的层数，层数最大为 logn，每层需要 m 的空间存储返回结果。
    public String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) return "";
        return lcp(strs, 0, strs.length - 1);
    }

    private String lcp(String[] strs, int left, int right) {
        // 区间索引相等，直接返回
        if (left == right) return strs[left];
        int mid = left + (right - left) / 2;
        String leftLcp = lcp(strs, left, mid);
        String rightLcp = lcp(strs, mid + 1, right);

        return help(leftLcp, rightLcp);
    }
}
