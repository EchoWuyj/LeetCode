package alg_02_train_wyj._13_day_综合应用一;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 21:14
 * @Version 1.0
 */
public class _12_318_maximum_product_of_word_lengths {

    public int maxProduct1(String[] words) {
        int res = 0;
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word1 = words[i];
            for (int j = i + 1; j < n; j++) {
                String word2 = words[j];
                if (!hasSameChar1(word1, word2)) {
                    res = Math.max(word1.length() * word2.length(), res);
                }
            }
        }
        return res;
    }

    private boolean hasSameChar1(String word1, String word2) {
        for (char c : word1.toCharArray()) {
            if (word2.indexOf(c) != -1) return true;
        }
        return false;
    }

    private boolean hasSameChar2(String word1, String word2) {
        int[] counts = new int[26];
        for (char c : word1.toCharArray()) counts[c - 'a'] = 1;
        for (char c : word2.toCharArray()) {
            if (counts[c - 'a'] == 1) return true;
        }
        return false;
    }

    private boolean hasSameChar3(String word1, String word2) {
        int bitMask1 = 0, bitMask2 = 0;
        for (char c : word1.toCharArray()) bitMask1 |= (1 << (c - 'a'));
        for (char c : word2.toCharArray()) bitMask2 |= (1 << (c - 'a'));
        return (bitMask1 & bitMask2) != 0;
    }

    public int maxProduct2(String[] words) {
        int n = words.length;
        int[] bitMasks = new int[n];
        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                bitMasks[i] |= (1 << (c - 'a'));
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            String word1 = words[i];
            for (int j = i + 1; j < n; j++) {
                String word2 = words[j];
                if ((bitMasks[i] & bitMasks[j]) == 0) {
                    res = Math.max(res, word1.length() * word2.length());
                }
            }
        }
        return res;
    }

    public int maxProduct3(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            int bitMask = 0;
            for (char c : words[i].toCharArray()) {
                bitMask |= (1 << (c - 'a'));
            }
            map.put(bitMask, Math.max(map.getOrDefault(bitMask, 0), words[i].length()));
        }

        int res = 0;
        for (int x : map.keySet()) {
            for (int y : map.keySet()) {
                if ((x & y) == 0) {
                    res = Math.max(res, map.get(x) * map.get(y));
                }
            }
        }
        return res;
    }
}
