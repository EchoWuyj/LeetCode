package alg_02_train_dm._13_day_综合应用一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-01 17:28
 * @Version 1.0
 */
public class _13_14_longest_common_prefix3 {

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
        // 递的过程 => 切分开过程
        String leftLcp = lcp(strs, left, mid);
        String rightLcp = lcp(strs, mid + 1, right);
        // 归的过程 => 合并过程
        return process(leftLcp, rightLcp);
    }

    // 递归实现
    // 递归形参：左右区间：left 和 right
    private String process(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }
}
