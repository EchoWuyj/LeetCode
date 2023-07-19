package alg_02_train_dm._01_day_数组技巧_二刷._01_技巧一_元素作为索引下标;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 14:42
 * @Version 1.0
 */
public class _03_1002_find_common_characters {

    /*
        1002. 查找共用字符
        给你一个字符串数组 words，请你找出所有在 words 的每个字符串中都出现的共用字符
        (包括重复字符)，并以数组形式返回。你可以按 任意顺序 返回答案。

        输入：words = ["bella","label","roller"]
        输出：["e","l","l"]

        输入：words = ["cool","lock","cook"]
        输出：["c","o"]

        注意：
        words[i] 由小写英文字母组成
        1 <= words.length <= 100
        1 <= words[i].length <= 100
     */

    // 时间复杂度：O(m*n)
    // 其中 m 表示数组的长度，n 表示每个字符串的平均长度
    // 1 <= words.length <= 100
    // 1 <= words[i].length <= 100
    // => O(mn) 10^4
    public List<String> commonChars(String[] words) {
        // 用于存储每个字母在所有字符串中出现的最小次数
        // => 通过最小次数，判断最后是否输出
        int[] minCount = new int[26];

        // KeyPoint length 方法与属性
        // 1.String 对应 length() 是方法，不是属性
        // 2.minCount.length 数组 对应 length，属性，不是方法

        // 计算第一个单词中每个字母出现的次数
        // 先默认使用第一个单词每个字母出现的次数作为 minCount，后续比较再更新 minCount
        for (char c : words[0].toCharArray()) {
            minCount[c - 'a']++;
        }

        // 计算第二个单词往后每个字母出现的最小次数
        for (int i = 1; i < words.length; i++) {
            int[] count = new int[26];
            for (char c : words[i].toCharArray()) {
                count[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                // 比较大小，更新最小次数
                // KeyPoint 直接使用 Math.min，优化取较小值
                minCount[j] = Math.min(minCount[j], count[j]);
            }
        }

        // 将字符出现最小次数大于 0 的字符输出到结果中
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            // 内层 for 根据 minCount[i] 值进行重复输出，即次数为 2，则输出 2 个相同字符
            // 若 minCount[i] = 0，则 j 跳过
            // KeyPoint 易错点
            // 不能单纯通过 if (minCount[i] > 0) 判断，if 只是决定有无，无法决定次数
            for (int j = 0; j < minCount[i]; j++) {
                // KeyPoint 易错点
                // 1.数字转字符需要括起来，作为一个整体
                // 2.String.valueOf 底层也是 new String，故也是可以直接 new String
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }

    // KeyPoint 存在 bug 代码
    public List<String> commonChars1(String[] words) {
        List<String> res = new ArrayList<>();
        int n = words.length;
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            String str = words[i];
            int len = str.length();
            for (int j = 0; j < len; j++) {
                count[str.charAt(i) - 'a']++;
            }
        }
        System.out.println(Arrays.toString(count));
        for (int i = 0; i < 26; i++) {
            // KeyPoint 易错点
            // 1.数字 i + 'a' => 数字 => 需要强制类型转换
            // 2.单纯统计词频，判断其个数，这样不行
            //    => 有的单词某个字母没有，有的单词某个字母出现多次
            //    => 单纯统计词频不能保证某个字母，所有单词都有
            if (count[i] >= n) res.add((char) (i + 'a') + "");
        }
        return res;
    }
}
