package alg_02_train_wyj._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 15:22
 * @Version 1.0
 */
public class _02_131_palindrome_partitioning {
    private String s;
    private List<List<String>> res;

    public List<List<String>> partition(String s) {
        this.s = s;
        this.res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(0, path);
        return res;
    }

    private void dfs(int startIndex, List<String> path) {
        if (startIndex == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            int endIndex = i;
            if (!checkPalindrome(s, startIndex, endIndex)) continue;
            path.add(s.substring(startIndex, endIndex + 1));
            dfs(i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    private boolean checkPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
