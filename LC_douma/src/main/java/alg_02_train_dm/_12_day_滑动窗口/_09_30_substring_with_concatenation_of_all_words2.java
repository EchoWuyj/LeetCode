package alg_02_train_dm._12_day_滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 21:01
 * @Version 1.0
 */
public class _09_30_substring_with_concatenation_of_all_words2 {

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
