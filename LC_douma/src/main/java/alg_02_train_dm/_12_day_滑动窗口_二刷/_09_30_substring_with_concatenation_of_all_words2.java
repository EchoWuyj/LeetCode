package alg_02_train_dm._12_day_滑动窗口_二刷;

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

    // KeyPoint 方法二 滑动窗口
    // 时间复杂度 O(30n) => 去掉系数 时间复杂度 O(n)
    public List<Integer> findSubstring(String s, String[] words) {

        // KeyPoint 优化
        // 本题：按照每个单词去分析，而不是按照每个字符去看
        // =>故 left 和 right 指针每次移动一个单词的长度，而不是一个字符的长度，从而提高效率

        // 统计每个单词出现次数
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int totalWordLen = words.length;
        List<Integer> res = new ArrayList<>();

        // words：["foo","bar"]

        // s：b a r f o o t h e f o o b a r m a n
        //    ↑
        //  left
        //    |---|

        // s：b a r f o o t h e f o o b a r m a n
        //      ↑
        //     left
        //      |---|

        // s：b a r f o o t h e f o o b a r m a n
        //        ↑
        //       left
        //        |---|

        // 首个单词，滑动窗口的起始位置，不一定只是首个字符位置，可以从单词的每个位置
        // => 故需要从单词中所有可能的字符位置，依次进行判断
        for (int i = 0; i < oneWordLen; i++) { // O(30)
            int left = i, right = i;
            // 匹配单词的个数
            int matched = 0;
            // 窗口中计算单词出现次数
            Map<String, Integer> windowMap = new HashMap<>();
            int len = s.length();
            // KeyPoint 解释说明 right <= len - oneWordLen
            // 若字符串 s 长度 len = 10，oneWordLen = 3
            // 最极限位置 10 - 3 = 7，倒数 3 个元素位置，即为 7 号索引
            // 索引 0 1 2 | 3 4 5 | 6 7 8 | 9
            // 元素 1 2 3   4 5 6   7 8 9  10
            //                        ↑
            //                     极限状态
            // 1.若 right <= 7，最后一组能构成一个单词，执行 while 循环
            // 2.若 right > 7，最后一组不能构成一个单词，不执行 while 循环
            // => right 快到最后的位置，若有一个单词则处理，否则就不需要处理了
            while (right <= len - oneWordLen) {
                String rightWord = s.substring(right, right + oneWordLen);
                windowMap.put(rightWord, windowMap.getOrDefault(rightWord, 0) + 1);

                // 只要 rightWord 加入窗口中，不管是否和 wordsMap 匹配都加 1
                // 在后续 while 代码中，通过移动 left 指针，将窗口中不匹配的单词移除，matched--
                matched++;

                // KeyPoint 消除不匹配的单词，直到有匹配为止
                // 若 windowMap 中 rightWord 出现次数 > wordsMap 中 rightWord 出现次数
                // => rightWord 不是窗口内需要的单词，右移动 left 指针，缩小窗口
                // => 多次判断，使用 while 循环执行，不断移动 left 指针，直到出现次数相等，或者 left 和 right 在同一位置

                // KeyPoint 注意事项
                // 使用 getOrDefault，为了避免 key 不存在，返回 null，因为 null 是无法比较的
                while (windowMap.getOrDefault(rightWord, 0)
                        > wordsMap.getOrDefault(rightWord, 0)) {
                    // 获取 leftWord
                    String leftWord = s.substring(left, left + oneWordLen);
                    // 将 leftWord 从 windowMap 中移除
                    windowMap.put(leftWord, windowMap.getOrDefault(leftWord, 0) - 1);
                    // left 移动一个单词距离
                    left += oneWordLen;
                    // 通过 left 指针，将窗口中不匹配的单词移除，matched--
                    matched--;
                }
                // matched 匹配个数，达到 words 中单词个数，将此时 left 加入 res
                if (matched == totalWordLen) res.add(left);
                // right 以每个单词字符长度往前移动，而不是一个字符一个字符移动
                right += oneWordLen;
            }
        }
        return res;
    }
}
