package alg_02_train_dm._23_day_回溯算法二_2刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 14:15
 * @Version 1.0
 */
public class _02_131_palindrome_partitioning {

    /*
        131. 分割回文串
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
       s 仅由小写英文字母组成

    */

    // KeyPoint 本质：字符串 切割成 子串
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        dfs(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(String s, int index, List<String> path, List<List<String>> res) {
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            // 1.index 为 start 索引，外部传入，不再变化
            // 2.i+1 为 end 索引，因为子串有不同的长度划分，对应循环变量 i
            //   每次 for 循环，i 都是会变化，对应截取长度变化
            // 3.避免 start = end，截取为 ""，故 i+1
            String subStr = s.substring(index, i + 1);
            // 剪枝 => 只要其中一个子串不是回文，则需要进行后续子串的判断
            if (!isValid(subStr)) continue;
            path.add(subStr);
            // 因为字符不可重复使用 => i + 1 => 子节点 > 父节点
            dfs(s, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    // 存在重复计算，后期可以使用动态规划进行优化
    // => 详见 09_131_PalindromePartitioning2
    private boolean isValid(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
