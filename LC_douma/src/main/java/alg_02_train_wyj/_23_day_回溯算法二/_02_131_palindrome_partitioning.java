package alg_02_train_wyj._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 15:22
 * @Version 1.0
 */
public class _02_131_palindrome_partitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        dfs(s, 0, new ArrayList<>(), res);
        return res;
    }

    public void dfs(String s, int index, List<String> path, List<List<String>> res) {
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            String subStr = s.substring(index, i+1);
            if (!isValid(subStr)) continue;
            path.add(subStr);
            dfs(s, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    public boolean isValid(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
