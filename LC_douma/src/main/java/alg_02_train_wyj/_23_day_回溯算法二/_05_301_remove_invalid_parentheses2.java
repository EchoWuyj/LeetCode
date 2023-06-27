package alg_02_train_wyj._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-27 15:35
 * @Version 1.0
 */
public class _05_301_remove_invalid_parentheses2 {

    private static List<String> removeInvalidParentheses1(String s) {

        HashSet<String> set = new HashSet<>();
        int[] removeArr = countRemoveNums(s);
//        System.out.println(Arrays.toString(removeArr));
        int leftRemove = removeArr[0];
        int rightRemove = removeArr[1];

        dfs(s, 0, leftRemove, rightRemove, 0, 0, new StringBuilder(), set);
        return new ArrayList<>(set);
    }

    private static int[] countRemoveNums(String s) {
        int[] res = new int[2];
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                if (left == 0) {
                    right++;
                }
                if (left > 0) {
                    left--;
                }
            }
        }
        res[0] = left;
        res[1] = right;
        return res;
    }

    public static void dfs(String s, int index,
                           int leftRemove, int rightRemove,
                           int leftCount, int rightCount,
                           StringBuilder path, HashSet<String> set) {

        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0) {
                set.add(path.toString());
            }
            return;
        }

        char cur = s.charAt(index);

        if (cur == '(' && leftRemove > 0) {
            dfs(s, index + 1, leftRemove - 1, rightRemove, leftCount, rightCount, path, set);
        } else if (cur == ')' && rightRemove > 0) {
            dfs(s, index + 1, leftRemove, rightRemove - 1, leftCount, rightCount, path, set);
        }

        path.append(cur);

        if (cur != '(' && cur != ')') {
            dfs(s, index + 1, leftRemove, rightRemove, leftCount, rightCount, path, set);
        } else if (cur == '(') {
            dfs(s, index + 1, leftRemove, rightRemove, leftCount + 1, rightCount, path, set);
        } else if (rightCount < leftCount) {
            dfs(s, index + 1, leftRemove, rightRemove, leftCount, rightCount + 1, path, set);
        }
        path.deleteCharAt(path.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses1("(a)())()("));
    }
}
