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
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null) return res;

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int wordCnt = words.length;
        int totalLen = oneWordLen * wordCnt;
        int len = s.length();

        for (int i = 0; i < len - totalLen + 1; i++) {
            String subStr = s.substring(i, i + totalLen);
            Map<String, Integer> subStrMap = new HashMap<>();
            for (int j = 0; j < totalLen; j += oneWordLen) {
                String oneWord = subStr.substring(j, j + oneWordLen);
                subStrMap.put(oneWord, subStrMap.getOrDefault(oneWord, 0) + 1);
            }
            if (map.equals(subStrMap)) res.add(i);
        }
        return res;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null) return res;

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int wordsNum = words.length;

        for (int i = 0; i < oneWordLen; i++) {
            int left = i, right = i;
            int matchedWords = 0;
            int len = s.length();
            Map<String, Integer> windowMap = new HashMap<>();
            while (right <= len - oneWordLen) {
                String curWord = s.substring(right, right + oneWordLen);
                windowMap.put(curWord, windowMap.getOrDefault(curWord, 0) + 1);
                matchedWords++;
                while (windowMap.getOrDefault(curWord, 0)
                        > map.getOrDefault(curWord, 0)) {
                    String leftWord = s.substring(left, left + oneWordLen);
                    windowMap.put(leftWord, windowMap.getOrDefault(leftWord, 0) - 1);
                    matchedWords--;
                    left += oneWordLen;
                }
                if (matchedWords == wordsNum) res.add(left);
                right += oneWordLen;
            }
        }
        return res;
    }
}
