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
public class _09_30_substring_with_concatenation_of_all_words {
      /* 
        leetcode 30. 串联所有单词的子串
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
    // 时间辅助度 O(n^2)
    // 将 words 字符个数作为窗口大小，统计窗口内字符个数，和 words 字符个数是否相同
    public List<Integer> findSubstring1(String s, String[] words) {
        // 统计每个单词出现的次数
        // key -> word
        // value -> 次数
        // 将每个单词作为 key，value 为出现次数，然后按照 Entry 键值对进行比较
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int oneWordLen = words[0].length();
        int wordNum = words.length;
        int totalLen = oneWordLen * wordNum;
        // 结果集
        List<Integer> res = new ArrayList<>();

        // 遍历子串个数 => 字符串长度 - 窗口大小 + 1
        // i 是从 s 的每个位置，每次向后移动一位，进行循环判断
        for (int i = 0; i < s.length() - totalLen + 1; i++) { // O(n)
            // 拿到等于所有单词长度之和的子串
            String subStr = s.substring(i, i + totalLen);
            // 统计子串中单词出现的次数
            Map<String, Integer> tmpMap = new HashMap<>();

            // 以每个单词长度为单位进行统计
            for (int j = 0; j < totalLen; j += oneWordLen) { // O(n)
                String oneWord = subStr.substring(j, j + oneWordLen);
                // 统计每个单词出现次数
                tmpMap.put(oneWord, tmpMap.getOrDefault(oneWord, 0) + 1);
            }

            // 如果单词出现的次数和原始 words 中单词出现的次数相同，则符合条件
            if (map.equals(tmpMap)) res.add(i);
        }
        return res;
    }

    // KeyPoint 方法二 滑动窗口 O(n)
    // 本题是按照每个单词去看，而不是按照每个字符去看 => 即比较的是单词,不是每个字符
    public List<Integer> findSubstring(String s, String[] words) {
        // 统计每个单词出现的次数
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int wordNum = words.length;

        List<Integer> res = new ArrayList<>();
        // i 从单个单词中所有可能的字符位置，依次进行判断
        // i 不一定只是首个字符位置，可以从单个单词第 2 位置开始往后
        for (int i = 0; i < oneWordLen; i++) {
            int left = i, right = i;
            int matchedWords = 0;
            Map<String, Integer> windowMap = new HashMap<>();

            int len = s.length();
            // right 快到最后的位置，若有一个单词，则处理，否则就不需要处理了
            while (right <= len - oneWordLen) {

                // KeyPoint 解释 right <= len - oneWordLen
                // 索引 0 1 2 | 3 4 5 | 6 7 8 | 9
                // 元素 1 2 3   4 5 6   7 8 9  10
                // len = 10
                // oneWordLen = 3
                // 最极限位置 10-3=7，倒数 3 个元素位置，即为 7 号索引
                // => right <= 7，最后一组能构成一个单词
                // => right > 7，最后一组不能构成一个单词

                String currWord = s.substring(right, right + oneWordLen);
                windowMap.put(currWord, windowMap.getOrDefault(currWord, 0) + 1);
                // 不管是否和 map 匹配，都加 1
                // 在后续 while 代码中，将不匹配的单词消除
                matchedWords++;

                // 只要 currWord 的 windowMap 出现次数 > map 出现次数
                // => currWord 不是窗口内需要的单词，右移动 left，缩小窗口，循环执行，使用 while
                // KeyPoint 消除不匹配的单词，直到有匹配为止
                // getOrDefault 避免 key 不存在，返回 null，因为 null 是无法比较的
                while (windowMap.getOrDefault(currWord, 0)
                        > map.getOrDefault(currWord, 0)) {
                    String leftWord = s.substring(left, left + oneWordLen);
                    // 更新 windowMap
                    windowMap.put(leftWord, windowMap.getOrDefault(leftWord, 0) - 1);
                    // 移动 left
                    left += oneWordLen;
                    matchedWords--;
                }
                // matchedWords 和 words 中单词个数匹配，将此时 left 加入 res
                if (matchedWords == wordNum) res.add(left);

                // right 以每个单词字符长度往前移动
                right += oneWordLen;
            }
        }
        return res;
    }
}
