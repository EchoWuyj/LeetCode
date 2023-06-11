package alg_02_train_wyj._13_day_综合应用一;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 19:20
 * @Version 1.0
 */
public class _11_844_backspace_string_compare {
    public boolean backspaceCompare1(String s, String t) {
        return build(s).equals(build(t));
    }

    public String build(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                sb.append(c);
            } else {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    public static String build1(String str) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        return stack.toString();
    }

    public String build2(String str) {
        char[] chars = str.toCharArray();
        int slow = -1, fast = 0;
        while (fast < str.length()) {
            if (chars[fast] != '#') {
                slow++;
                if (fast != slow) swap(chars, slow, fast);
            } else if (slow > -1) {
                slow--;
            }
            fast++;
        }
        return slow == -1 ? "" : new String(chars, 0, slow + 1);
    }

    public void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            if ((i >= 0 && j >= 0) && s.charAt(i) != t.charAt(j)) return false;
            if ((i >= 0) != (j >= 0)) return false;
            i--;
            j--;
        }
        return true;
    }
}
