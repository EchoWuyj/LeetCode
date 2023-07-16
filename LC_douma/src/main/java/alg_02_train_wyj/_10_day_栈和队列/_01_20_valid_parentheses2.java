package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-16 20:35
 * @Version 1.0
 */
public class _01_20_valid_parentheses2 {

    public boolean isValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        if (sb.length() % 2 == 1) return false;
        int len = sb.length();
        int count = len / 2;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < len - 1; j++) {
                char c1 = sb.charAt(j);
                char c2 = sb.charAt(j + 1);
                if (isMatch(c1, c2)) {
                    sb.delete(j, j + 2);
                    break;
                }
            }
        }
        return sb.length() == 0;
    }

    private boolean isMatch(char c1, char c2) {
        if (c1 == '(') {
            return c2 == ')';
        } else if (c1 == '{') {
            return c2 == '}';
        } else if (c1 == '[') {
            return c2 == ']';
        }
        return false;
    }

    public boolean isValid1(String s) {
        if (s.length() % 2 == 1) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char pop = stack.pop();
                if (c == ')' && pop != '(') return false;
                if (c == '}' && pop != '{') return false;
                if (c == ']' && pop != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        if (s.length() % 2 == 1) return false;
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char pop = stack.pop();
                char value = map.get(pop);
                if (c != value) return false;
            }
        }
        return stack.isEmpty();
    }
}
