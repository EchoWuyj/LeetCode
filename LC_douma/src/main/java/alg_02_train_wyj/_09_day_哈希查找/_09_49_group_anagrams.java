package alg_02_train_wyj._09_day_哈希查找;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 11:45
 * @Version 1.0
 */
public class _09_49_group_anagrams {
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            List<String> list = map.get(key);
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            int[] count = new int[26];
            for (char c : chars) count[c - 'a']++;
            String key = Arrays.toString(count);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
