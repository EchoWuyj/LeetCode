package alg_02_train_dm._13_day_综合应用一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 21:05
 * @Version 1.0
 */
public class _13_14_longest_common_prefix1 {
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

    // KeyPoint 自己解法 => 存在 bug
    // 类似：03_1002_find_common_characters
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int n = strs.length;
        int[] counts = new int[26];
        for (char c : strs[0].toCharArray()) {
            counts[c - 'a']++;
        }
        // System.out.println(Arrays.toString(counts));

        for (int i = 1; i < n; i++) {
            int[] countsi = new int[26];
            for (char c : strs[i].toCharArray()) {
                countsi[c - 'a']++;
            }

            // System.out.println(Arrays.toString(countsi));
            for (int j = 0; j < 26; j++) {
                // KeyPoint 使用最近 for 循环变量 j，而不是 i
                counts[j] = Math.min(countsi[j], counts[j]);
            }
        }
        // System.out.println(Arrays.toString(counts));

        StringBuilder res = new StringBuilder();
        for (int j = 0; j < 26; j++) {
            // counts[j] > 0 表示在 j 位置上有字符
            // j 表示该字符所在位置，而不是直接 counts[j] + 'a'
            while (counts[j] > 0) {
                // 转 char，最外层需 (char) 表示要强转
                res.append((char) (j + 'a'));
                counts[j]--;
            }
        }
        return res.toString();
    }

    // 输入：
    // ["cir","car"]

    // 输出：
    // "cr"

    // 前缀需要保证字符是连续的，而统计每个字符最小出现频率不能保证字符连续
}
