package alg_03_leetcode_top_wyj.class_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 23:17
 * @Version 1.0
 */
public class Problem_0022_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n < 0) {
            return res;
        }

        char[] path = new char[n << 1];
        process(path, 0, n, 0, res);
        return res;
    }

    public void process(char[] path, int leftMinusRight, int leftRest, int index, List<String> res) {
        if (index == path.length) {
            res.add(String.valueOf(path));
        } else {
            if (leftRest > 0) {
                path[index] = '(';
                process(path, leftMinusRight + 1, leftRest - 1, index + 1, res);
            }
            if (leftMinusRight > 0) {
                path[index] = ')';
                process(path, leftMinusRight - 1, leftRest, index + 1, res);
            }
        }
    }

    // 没有剪枝深度优先
    public void process1(char[] path, int index, List<String> res) {
        if (index == path.length) {
            if (isValid(path)) {
                res.add(String.valueOf(path));
            }
        } else {
            path[index] = '(';
            process1(path, index + 1, res);
            path[index] = ')';
            process1(path, index + 1, res);
        }
    }

    private boolean isValid(char[] path) {
        int count = 0;
        for (char c : path) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}
