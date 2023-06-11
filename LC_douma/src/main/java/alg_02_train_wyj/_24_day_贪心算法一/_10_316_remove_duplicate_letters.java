package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 16:45
 * @Version 1.0
 */
public class _10_316_remove_duplicate_letters {
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();
        boolean[] exists = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (exists[c - 'a']) continue;
            while (!stack.isEmpty() && stack.peek() > c
                    && lastIndex[stack.peek() - 'a'] > i) {
                char top = stack.pop();
                exists[top - 'a'] = false;
            }

            stack.push(c);
            exists[c - 'a'] = true;
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pollLast());
        }
        return res.toString();
    }
}
