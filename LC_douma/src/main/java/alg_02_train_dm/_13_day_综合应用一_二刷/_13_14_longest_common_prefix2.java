package alg_02_train_dm._13_day_综合应用一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-01 17:28
 * @Version 1.0
 */
public class _13_14_longest_common_prefix2 {

    // KeyPoint 方法一 纵向扫描
    // 时间复杂度：O(mn)
    // 其中 m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(1)
    public String longestCommonPrefix1(String[] strs) {

        //  j i → → →
        //  ↓ fl|ower
        //  ↓ fl|ow
        //  ↓ fl|ight

        if (strs.length == 0) return "";
        // 将多个单词看成二维矩阵，纵向扫描每一列
        int rows = strs.length;
        // 最长公共前缀长度 <= 第一单词长度
        int cols = strs[0].length();
        // 先按照列
        // KeyPoint 固定写法，i 和行搭配，j 和列搭配
        for (int j = 0; j < cols; j++) {
            char firstChar = strs[0].charAt(j);
            // 再按照行，逐行比较
            // 从第二行开始 => 表示后面的单词
            for (int i = 1; i < rows; i++) {
                // 若 j 遍历列过程中，已经到某行 strs[i] 结尾，即 strs[i].length() == j
                // 或者 其中某一行，有个字符不相同，对其进行截取
                // => 根据木桶效应，最长公共前缀，只能为最短的字符串
                if (strs[i].length() == j || strs[i].charAt(j) != firstChar) {
                    return strs[0].substring(0, j);
                }
            }
        }
        // 若执行 for 循环后，都没有 return，则说明 strs[0] 为公共前缀
        return strs[0];
    }

    // KeyPoint 方法二 横向扫描
    // 时间复杂度：O(mn)
    // 其中 m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(1)
    public String longestCommonPrefix2(String[] strs) {

        // flower  flow  flight
        //   ↑
        // prefix

        // 将 flower 作为 prefix，依次和后面的单词找公共前缀，将结果赋值给 prefix，依次循环

        if (strs.length == 0) return "";
        String prefix = strs[0];
        int n = strs.length;
        for (int i = 1; i < n; i++) {
            prefix = lcp(prefix, strs[i]);
            // 若 prefix 长度为 0，提前退出，不需要再进行后续 for 循环遍历
            if (prefix.length() == 0) return "";
        }
        return prefix;
    }

    // 原始方法名：longestCommonPrefix => 太长，有点麻烦，使用 lcp 或者 process
    private String lcp(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        // index 最后位置，在 str1.charAt(index) != str2.charAt(index)
        // 截取 [0,index)
        return str1.substring(0, index);
    }
}
