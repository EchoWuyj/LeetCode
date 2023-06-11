package alg_02_train_dm._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 14:15
 * @Version 1.0
 */
public class _02_131_palindrome_partitioning {
    /* 131. 分割回文串
       给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。
       返回 s 所有可能的分割方案。

       回文串 是正着读和反着读都一样的字符串。

       示例 1：
       输入：s = "aab"
       输出：[["a", "a", "b"], ["aa", "b"]]

       "aab":子集可能
            "a","a","b"
            "aa","b"
            "a","ab"
            "aab"
            切一个字符，切两个字符，切三个字符


       示例 2：
       输入：s = "a"
       输出：[["a"]]

       提示：
       1 <= s.length <= 16
       s

       KeyPoint 本质:求子集(字串)
    */

    private String s;
    private List<List<String>> res;

    public List<List<String>> partition(String s) {
        this.s = s;
        this.res = new ArrayList<>();
        dfs(0, new ArrayList<>());
        return res;
    }

    // 子节点索引 > 父节点索引
    private void dfs(int startIndex, List<String> path) {
        if (startIndex == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            // for 循环中，i 变量会 i++，此时 endIndex 就为新的值了
            // 但是 startIndex 一直是不发生变化的
            int endIndex = i;
            // 获取子节点区间 [startIndex, endIndex]
            // KeyPoint 剪枝 => 只要其中一个子串不是回文，则需要进行后续子串的判断
            if (!checkPalindrome(s, startIndex, endIndex)) continue;
            // 通过上面的 if 判断，该字串已经是回文串了
            path.add(s.substring(startIndex, endIndex + 1));
            // i + 1 => 子节点 > 父节点，严格有序
            dfs(i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    // 存在重复计算，可以使用动态规划优化
    private boolean checkPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
