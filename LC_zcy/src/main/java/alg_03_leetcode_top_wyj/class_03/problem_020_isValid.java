package alg_03_leetcode_top_wyj.class_03;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-02-23 11:05
 * @Version 1.0
 */
public class problem_020_isValid {
    public boolean isValid(String string) {
         if (string.length() == 0 || string == null) {
            return true;
        }

        char[] str = string.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character last = stack.pop();
                if ((c == ')' && last != '(')
                        || (c== '}' && last != '{')
                        || (c == ']' && last != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
