package alg_02_train_dm._09_day_哈希查找_二刷;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 16:41
 * @Version 1.0
 */
public class _08_242_valid_anagram {
      /*
        242 有效的字母异位词
        给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
        注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词

        输入: s = "anagram", t = "nagaram"
        输出: true

        输入: s = "rat", t = "car"
        输出: false

        提示:
        1 <= s.length, t.length <= 5 * 104
        s 和 t 仅包含小写字母

        如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

     */

    // KeyPoint 方法一 哈希查找
    // 限制条件：字符串只包含小写字母 => 字符串有限个字符个数，使用数组替换 HashMap
    // => 使用 HashMap，速度明显比数组慢了很多
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] map = new int[26];
        for (char c : s.toCharArray()) map[c - 'a']++;
        for (char c : t.toCharArray()) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) return false;
        }
        return true;
    }

    // KeyPoint  扩展：字符串包含 unicode 字符 => 哈希查找
    // 字符串法确定字符个数，无法使用数组替换 HashMap
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;

        // 1. 统计字符串 s 中每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);

            // 优化：使用 map.getOrDefault 简化 if else 判断逻辑，代码更加优雅
//            if (map.containsKey(c)) {
//                map.put(c, map.get(c) + 1);
//            } else {
//                map.put(c, 1);
//            }
        }

        // 2. 减去字符串 t 中字符出现的次数，如果出现的次数出现小于 0 的话，则返回 false
        for (char c : t.toCharArray()) {
            // 注意 getOrDefault 这种 API 使用
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0) return false;
        }

        return true;
    }

    // KeyPoint 方法二 排序
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        // 比较两个数组中元素是否相等
        // 调用 数组比较 API Arrays.equals
        return Arrays.equals(sChars, tChars);
    }
}
