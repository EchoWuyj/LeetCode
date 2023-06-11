package alg_02_train_dm._09_day_哈希查找;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 16:41
 * @Version 1.0
 */
public class _06_205_isomorphic_strings {
    /*
       leetcode 205 号算法题：同构字符串
       给定两个字符串 s 和 t，判断它们是否是同构的。
       如果 s 中的字符可以按某种映射关系替换得到 t，那么这两个字符串是同构的。

       映射关系
       - 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
       - 不同字符不能映射到同一个字符上。 如：egf -> add
       - 相同字符只能映射到同一个字符上。 如：egg -> ade
       - 字符可以映射到自己本身

       输入：s = "egg", t = "add"
       输出：true

       输入：s = "foo", t = "bar"
       输出：false

       输入：s = "paper", t = "title"
       输出：true

       - 可以假设 s 和 t 长度相同。
    */
    public boolean isIsomorphic(String s, String t) {
        // 使用 Map 作为映射
        Map<Character, Character> s2tMap = new HashMap<>();
        Map<Character, Character> t2sMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character sc = s.charAt(i);
            Character tc = t.charAt(i);
            // 判断映射关系是否成立 =>保证映射过程是唯一的，需要双向映射判断
            if ((s2tMap.containsKey(sc) && s2tMap.get(sc) != tc) ||
                    t2sMap.containsKey(tc) && t2sMap.get(tc) != sc) {
                return false;
            }
            // 如果 Key 不在 Map 中，则维护映射关系
            s2tMap.put(sc, tc);
            t2sMap.put(tc, sc);
        }

        return true;
    }
}
