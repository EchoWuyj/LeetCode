package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-24 20:00
 * @Version 1.0
 */
public class _01_20_valid_parentheses1 {

    public static boolean isValid1(String s) {
        if (s.length() % 2 == 1) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValid2(String s) {
        if (s.length() % 2 == 1) return false;
        int leftBraceCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftBraceCnt++;
            } else {
                if (leftBraceCnt < 0) return false;
                leftBraceCnt--;
            }
        }
        return leftBraceCnt == 0;
    }

    // test
    public static void main(String[] args) {
        String str1 = "(()))";
        String str2 = "(()";
        String str3 = "(())";
        System.out.println(isValid1(str1)); // false
        System.out.println(isValid1(str2)); // false
        System.out.println(isValid1(str3)); // true
        System.out.println("=========");
        System.out.println(isValid2(str1)); // false
        System.out.println(isValid2(str2)); // false
        System.out.println(isValid2(str3)); // true
    }
}
