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
        int n = s.length();
        for (int i = 0; i < n; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        Deque<Character> deque = new ArrayDeque<>();
        boolean[] exists = new boolean[26];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (exists[c - 'a']) continue;
            while (!deque.isEmpty() && c < deque.peekFirst()
                    && lastIndex[deque.peekFirst() - 'a'] > i) {
                char top = deque.pollFirst();
                exists[top - 'a'] = false;
            }
            deque.offerFirst(c);
            exists[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
        }
        return sb.toString();
    }
}
