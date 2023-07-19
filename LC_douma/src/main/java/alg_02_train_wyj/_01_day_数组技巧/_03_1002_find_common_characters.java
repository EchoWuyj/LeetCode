package alg_02_train_wyj._01_day_数组技巧;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 14:54
 * @Version 1.0
 */
public class _03_1002_find_common_characters {
    public List<String> commonChars(String[] words) {
        int[] minCount = new int[26];

        int n = words.length;
        for (char c : words[0].toCharArray()) {
            minCount[c - 'a']++;
        }

        for (int i = 1; i < n; i++) {
            String str = words[i];
            int len = str.length();
            int[] count = new int[26];
            for (int j = 0; j < len; j++) {
                count[str.charAt(j) - 'a']++;
            }

            for (int k = 0; k < 26; k++) {
                minCount[k] = Math.min(minCount[k], count[k]);
            }
        }

        List<String> res = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < minCount[i]; j++) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }
}
