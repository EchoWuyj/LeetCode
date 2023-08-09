package alg_02_train_wyj._12_day_滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-08 11:13
 * @Version 1.0
 */
public class _09_30_substring_with_concatenation_of_all_words {

    public List<Integer> findSubstring1(String s, String[] words) {
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int num = words.length;
        int totalLen = oneWordLen * num;
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < s.length() - totalLen + 1; i++) {
            String substr = s.substring(i, i + totalLen);
            Map<String, Integer> subMap = new HashMap<>();
            for (int j = 0; j < totalLen; j += oneWordLen) {
                String oneWord = substr.substring(j, j + oneWordLen);
                subMap.put(oneWord, subMap.getOrDefault(oneWord, 0) + 1);
            }
            if (wordsMap.equals(subMap)) res.add(i);
        }
        return res;
    }

    // 滑动窗口
    public List<Integer> findSubstring2(String s, String[] words) {
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int totalWordLen = words.length;
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < oneWordLen; i++) {
            int left = i, right = i;
            int matched = 0;
            Map<String, Integer> windowMap = new HashMap<>();
            int len = s.length();
            while (right <= len - oneWordLen) {
                String rightWord = s.substring(right, right + oneWordLen);
                windowMap.put(rightWord, windowMap.getOrDefault(rightWord, 0) + 1);
                matched++;
                while (windowMap.getOrDefault(rightWord, 0)
                        > wordMap.getOrDefault(rightWord, 0)) {
                    String leftWord = s.substring(left, left + oneWordLen);
                    windowMap.put(leftWord, windowMap.getOrDefault(leftWord, 0) - 1);
                    left += oneWordLen;
                    matched--;
                }
                if (matched == totalWordLen) res.add(left);
                right += oneWordLen;
            }
        }
        return res;
    }
}
