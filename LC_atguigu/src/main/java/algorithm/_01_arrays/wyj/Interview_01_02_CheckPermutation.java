package algorithm._01_arrays.wyj;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-09-27 12:36
 * @Version 1.0
 */
public class Interview_01_02_CheckPermutation {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        char[] str1Chars = s1.toCharArray();
        char[] str2Chars = s2.toCharArray();

        Arrays.sort(str1Chars);
        Arrays.sort(str2Chars);

        for (int i = 0; i < str1Chars.length; i++) {
            if (str1Chars[i] != str2Chars[i]) {
                return false;
            }
        }

        return true;
    }
}
