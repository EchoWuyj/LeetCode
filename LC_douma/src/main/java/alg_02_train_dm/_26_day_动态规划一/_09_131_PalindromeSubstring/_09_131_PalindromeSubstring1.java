package alg_02_train_dm._26_day_动态规划一._09_131_PalindromeSubstring;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 10:32
 * @Version 1.0
 */
public class _09_131_PalindromeSubstring1 {
     /*
        131. 分割回文串
        给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串
        返回 s 所有可能的分割方案。
        回文串 是正着读和反着读都一样的字符串。

        示例 1：
        输入：s = "aab"
        输出：[["a","a","b"],["aa","b"]]

        提示：
        1 <= s.length <= 16  => 使用
        s 仅由小写英文字母组成

        KeyPoint 根据数据规模 => 算法时间复杂度
        O(n^3) => 200 ~ 500
        O(2^n) => 20 ~ 24
        1 <= s.length <= 16  => 使用回溯完全可以求解，不会超时

        KeyPoint 思路分析
        aaba 所有分割子串
        1.每次选择，依次可以选 1,2,3,..., 字符，从而形成多叉树(详见树形图)
        2.从 root 节点到叶子结点没一条路径，都是对应一种分割方案

        字符串  a a b a
        索引    0 1 2 3
                                                 0
                                ↙              ↙         ↘          ↘
                            [0,0]            [0,1]         [0,2]       [0,3]
                       ↙     ↓     ↘         ↓   ↘         ↓
                    [1,1]  [1,2]    [1,3]   [2,2]  [2,3]   [3,3]
                   ↙  ↘     ↓                ↓
               [2,2]  [2,3] [3,3]            [3,3]
                 ↓
               [3,3]

        [a, a, b, a]
        [a, a, ba]
        [a, ab, a]
        [a, aba]
        [aa, b, a]
        [aa, ba]
        [aab, a]
        [aaba]

        分割回文串，且是回文的方案
        [a, a, b, a]
        [a, aba]
        [aa, b, a]

     */

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        dfs(s, 0, new ArrayList<>(), res);
        return res;
    }

    // 找出所有的分割方案 => 使用'回溯'来解
    // 时间复杂度 O(n*2^n)，其中 n 是输入字符串 's' 的长度
    // 1.对于字符串中的每个字符，都有两种选择：包括和不包括，因此可能的分割方案数量为 2^n
    // 2.对于每个分割方案，代码都会检查它是否是回文的，这个检查需要 O(n) 的时间
    private void dfs(String s, int start,
                     List<String> path,
                     List<List<String>> res) {
        // 若 start 在越界位置，则回溯，res 加入 path
        if (start == s.length()) {
            // 需要新创建一个 ArrayList，并将 path 传入
            res.add(new ArrayList<>(path));
            return;
        }

        // i 区间结束索引，i 是从 start 开始，但 i 是循环变量，不是 start
        for (int i = start; i < s.length(); i++) {
            // [start, i]
            String subStr = s.substring(start, i + 1);
            // 不是回文，直接剪枝
            if (!isPalindrome(subStr)) continue;
            path.add(subStr);
            // 子节点，从结束索引 i 下个位置开始
            dfs(s, i + 1, path, res);
            // 恢复现场，将加入的 subStr 移除，回溯重新加入
            path.remove(path.size() - 1);
        }
    }

    // 判断子串是否是回文
    private boolean isPalindrome(String s) {
        int i = 0;
        // 最后一个索引位置需要减 1，不能直接是 s.length()
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> res
                = new _09_131_PalindromeSubstring1().partition("aaba");
        for (List<String> path : res) {
            System.out.println(path);
        }
    }
}
