package alg_02_train_wyj._23_day_回溯算法二;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 21:31
 * @Version 1.0
 */
public class _05_301_remove_invalid_parentheses {

    public List<String> removeInvalidParentheses1(String s) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        HashSet<String> visited = new HashSet<>();
        visited.add(s);
        boolean nextLevel = true;
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curStr = queue.remove();
                if (isValid(curStr)) {
                    res.add(curStr);
                    nextLevel = false;
                    found = true;
                    continue;
                }
                if (nextLevel) {
                    int curStrLen = curStr.length();
                    for (int j = 0; j < curStrLen; j++) {
                        if (curStr.charAt(j) != '(' && curStr.charAt(j) != ')') continue;
                        String leftStr = curStr.substring(0, j);
                        String rightStr = (j == curStrLen - 1) ? "" : curStr.substring(j + 1, curStrLen);
                        String next = leftStr + rightStr;
                        if (!visited.contains(next)) {
                            queue.add(next);
                            visited.add(next);
                        }
                    }
                }
            }
            if (found) break;
        }
        return res;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    private String s;
    private Set<String> res;

    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        this.res = new HashSet<>();

        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftRemove++;
            } else if (s.charAt(i) == ')') {
                if (leftRemove == 0) {
                    rightRemove++;
                } else {
                    leftRemove--;
                }
            }
        }

        dfs(0, leftRemove, rightRemove, 0, 0, new StringBuilder());
        return new ArrayList<>(res);
    }

    private void dfs(int index,
                     int leftRemove, int rightRemove,
                     int leftCount, int rightCount,
                     StringBuilder path) {

        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0) {
                res.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);
        if (c == '(' && leftRemove > 0) {
            dfs(index + 1, leftRemove - 1, rightRemove, leftCount, rightCount, path);
        }
        if (c == ')' && rightRemove > 0) {
            dfs(index + 1, leftRemove, rightRemove - 1, leftCount, rightCount, path);
        }

        path.append(c);

        if (c != '(' && c != ')') {
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount, path);
        } else if (c == '(') {
            dfs(index + 1, leftRemove, rightRemove, leftCount + 1, rightCount, path);
        } else if (rightCount < leftCount) {
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount + 1, path);
        }

        path.deleteCharAt(path.length() - 1);
    }
}
