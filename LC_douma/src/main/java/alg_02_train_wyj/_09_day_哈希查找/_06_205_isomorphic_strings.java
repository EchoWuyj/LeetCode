package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 20:14
 * @Version 1.0
 */
public class _06_205_isomorphic_strings {

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2tMap = new HashMap<>();
        Map<Character, Character> t2sMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if ((s2tMap.containsKey(sc) && s2tMap.get(sc) != tc)
                    || (t2sMap.containsKey(tc) && t2sMap.get(tc) != sc)) {
                return false;
            } else {
                s2tMap.put(sc, tc);
                t2sMap.put(tc, sc);
            }
        }

        return true;
    }
}
