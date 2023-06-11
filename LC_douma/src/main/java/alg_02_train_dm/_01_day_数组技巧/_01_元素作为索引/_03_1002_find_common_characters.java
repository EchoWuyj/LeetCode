package alg_02_train_dm._01_day_数组技巧._01_元素作为索引;

import java.util.ArrayList;
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

        注意：
        words[i] 由小写英文字母组成
        1 <= words.length <= 100  => m
        1 <= words[i].length <= 100 => n => 算法时间复杂度 O(mn) 10^4


        输入：words = ["bella","label","roller"]
        输出：["e","l","l"]

        输入：words = ["cool","lock","cook"]
        输出：["c","o"]

     */

    // 时间复杂度：O(m*n)，m 表示数组的长度，n 表示每个字符串的平均长度
    public List<String> commonChars(String[] words) {
        // 频率 frequency -> freq
        // 用于存储每个字符在所有字符串中出现的最小次数
        int[] minfreq = new int[26];

        // String 对应 length() 是方法，不是属性
        // minfreq.length 数组 对应 length，属性，不是方法

        // 计算第一个单词中每个字符出现的次数
        // 先默认使用第一个单词每个字符出现的次数作为 minfreq，后续比较再更新 minfreq
        for (char c : words[0].toCharArray()) {
            minfreq[c - 'a']++;
        }

        // 计算第二个单词往后每个字符出现的最小次数
        for (int i = 1; i < words.length; i++) {
            int[] freq = new int[26];
            for (char c : words[i].toCharArray()) {
                freq[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                // 比较大小，更新最小次数
                minfreq[j] = Math.min(minfreq[j], freq[j]);
            }
        }

        // 将字符出现最小次数大于 0 的字符输出到结果中
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            // minfreq[i] < 0，则 j 跳过
            // 内层 for 根据 minfreq[i] 值进行重复输出，即次数为 2，则输出2个相同字符
            for (int j = 0; j < minfreq[i]; j++) {
                // KeyPoint 数字转字符需要括起来，作为一个整体
                // String.valueOf 底层也是 new String，故也是可以直接 new String
                ans.add(String.valueOf((char) (i + 'a')));
            }
        }
        return ans;
    }
}
