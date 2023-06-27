package alg_02_train_wyj._23_day_回溯算法二;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 21:31
 * @Version 1.0
 */
public class _05_301_remove_invalid_parentheses1 {

    public List<String> removeInvalidParentheses1(String s) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);

        HashSet<String> set = new HashSet<>();
        set.add(s);

        boolean found = false;
        boolean nextLevel = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curStr = queue.poll();
                if (isValid(curStr)) {
                    res.add(curStr);
                    found = true;
                    nextLevel = false;
                    continue;
                }
                if (nextLevel) {
                    int len = curStr.length();
                    for (int j = 0; j < len; j++) {
                        if (curStr.charAt(j) != '(' && curStr.charAt(j) != ')') continue;
                        String leftStr = curStr.substring(0, j);
                        String rightStr = (j == len - 1) ? "" : curStr.substring(j + 1, len);
                        String subStr = leftStr + rightStr;
                        if (!set.contains(subStr)) {
                            queue.add(subStr);
                            set.add(subStr);
                        }
                    }
                }
            }
            if (found) break;
        }
        return res;
    }

    private boolean isValid(String str) {
        int left = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0) return false;
                left--;
            }
        }
        return left == 0;
    }
}
