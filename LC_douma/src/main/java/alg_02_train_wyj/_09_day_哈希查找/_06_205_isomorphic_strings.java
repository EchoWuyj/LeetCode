package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 20:14
 * @Version 1.0
 */
public class _06_205_isomorphic_strings {

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> s2t = new HashMap<>();
        HashMap<Character, Character> t2s = new HashMap<>();

        int n = s.length();
        for (int i = 0; i < n; i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);

            if (s2t.containsKey(cs) && s2t.get(cs) != ct
                    || t2s.containsKey(ct) && t2s.get(ct) != cs) {
                return false;
            }
            s2t.put(cs, ct);
            t2s.put(ct, cs);
        }
        return true;
    }
}
