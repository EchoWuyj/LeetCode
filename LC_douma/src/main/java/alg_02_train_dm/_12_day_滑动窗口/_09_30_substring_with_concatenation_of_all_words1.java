package alg_02_train_dm._12_day_滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:12
 * @Version 1.0
 */
public class _09_30_substring_with_concatenation_of_all_words1 {
      /* 
           30. 串联所有单词的子串
           给定一个字符串 s 和一些 长度相同 的单词 words 。
           找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
    
           注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，
           但不需要考虑 words 中单词串联的顺序。
    
           示例 1：
           输入：s = "barfoothefoobarman", words = ["foo","bar"]
           输出：[0,9]
           解释：
           从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
           输出的顺序不重要, [9,0] 也是有效答案。
    
           示例 2：
           输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
           输出：[]
    
           示例 3：
           输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
           输出：[6,9,12]
    
           提示：
           1 <= s.length <= 10^4
           s 由小写英文字母组成
           1 <= words.length <= 5000
           1 <= words[i].length <= 30
           words[i] 由小写英文字母组成
     */

    // KeyPoint 方法一 哈希查找 => 暴力解法
    // => 充分使用 '长度相同' 这一信息 => 滑动窗口
    // => 将 words 字符个数作为窗口大小，统计窗口内字符个数，和 words 字符个数是否相同
    // 时间辅助度 O(n^2)
    public List<Integer> findSubstring1(String s, String[] words) {

        // map 统计每个单词出现的次数
        // => 将每个单词作为 key，value 为出现次数，然后按照 Entry 键值对进行比较
        // key -> word
        // value -> 次数
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int num = words.length;
        // 子串总长度
        int totalWordLen = oneWordLen * num;
        List<Integer> res = new ArrayList<>();

        // 遍历子串个数 => 字符串长度 - 窗口大小 + 1 => 规律将其记住
        // i 是从 s 的每个位置，每次向后移动一位，进行循环判断
        for (int i = 0; i < s.length() - totalWordLen + 1; i++) { // O(n)
            // 获取子串
            String subStr = s.substring(i, i + totalWordLen);
            // 子串分单词，构建 subMap
            Map<String, Integer> subMap = new HashMap<>();

            // 子串分单词
            for (int j = 0; j < totalWordLen; j += oneWordLen) { // O(n)
                String oneWord = subStr.substring(j, j + oneWordLen);
                // 统计每个单词出现次数
                subMap.put(oneWord, subMap.getOrDefault(oneWord, 0) + 1);
            }
            // 通过比较 map 和 subMap 从而判断是否一致
            // => 若 subMap 单词出现次数 和 map 单词出现次数保持一致，则符合条件
            if (map.equals(subMap)) res.add(i);
        }
        return res;
    }
}
