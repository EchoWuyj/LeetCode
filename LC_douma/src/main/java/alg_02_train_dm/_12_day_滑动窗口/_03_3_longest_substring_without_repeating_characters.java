package alg_02_train_dm._12_day_滑动窗口;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 16:44
 * @Version 1.0
 */
public class _03_3_longest_substring_without_repeating_characters {
    /*
       3. 无重复字符的最长子串 => KeyPoint 求最大值

       给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

       KeyPoint 子串和子序列区别：
       子串 <=> 连续子数组
       子序列是"不必要求连续"，而子串则是"连续"的

       示例 1:
       输入: s = "abcabcbb"
       输出: 3
       解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

       示例 2:
       输入: s = "bbbbb"
       输出: 1
       解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

       示例 3:
       输入: s = "pwwkew"
       输出: 3
       解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
             请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

       示例 4:
       输入: s = ""
       输出: 0

       提示：
       0 <= s.length <= 5 * 10^4
       s 由英文字母、数字、符号和空格组成

    */

    // KeyPoint 方法一 暴力解法 超时
    // 遍历数组的所有的区间，然后找到最长没有重复字符的区间
    // 时间复杂度：O(n^3)
    // 空间复杂度：O(n)
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;

        for (int i = 0; i < n; i++) { // O(n)
            for (int j = i + 1; j < n; j++) { // O(n)
                // 辅助方法 => 判断区间中字符是否唯一
                // 若该区间没有重复字符，更新 maxLen
                if (allUnique(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }

                // 通过暴力枚举区间，区间与区间之间存在重叠
                // 即存在重复计算 => 子串重叠部分会进行多次判断是否存在重复字符
                // 优化：若 s[i, j) 子串没有重复字符，如果想要判断 s[i, j] 没有重复字符
                //      则只需要判断 s[i, j) 中是否存在 s[j] 即可 => 滑动窗口思路
            }
        }
        return maxLen;
    }

    // 辅助方法 => 判断区间中字符是否唯一
    private boolean allUnique(String s, int start, int end) {
        // set 判重
        Set<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) { // O(n)
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }

    // KeyPoint 方法二 滑动窗口
    // 不断移动 right，为了保证窗口最大，若 right 字符在窗口中已经出现，移动左边界，为了缩减窗口，为了踢出重复字符
    // 时间复杂度：O(2n) = O(n)，最坏的情况是 left 和 right 都遍历了一遍字符串
    // 空间复杂度：O(n)
    public int lengthOfLongestSubstring2(String s) {
        if (s == null) return 0;
        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;

        int left = 0, right = 0;
        // 使用 set 作为窗口，用于判重
        Set<Character> window = new HashSet<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            // 先判断 right 位置字符是否存在 set 中
            // 若是重复字符，缩小窗口，从而将重复字符踢出窗口
            // 使用 while 重复判断
            while (window.contains(rightChar)) {
                window.remove(s.charAt(left));
                left++;
            }
            // 没有重复字符，更新 maxLen
            maxLen = Math.max(maxLen, right - left + 1);
            window.add(rightChar);
            right++;
        }
        return maxLen;
    }

    // KeyPoint 方法三 优化 滑动窗口
    // 思路：
    // 1.以上当在窗口中存在重复字符，是一个一个字符的缩小窗口
    // 2.可以通过记住每个字符在字符串中的索引，当遇到重复字符的时候，就可以直接跳到重复字符的后面
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;
        int left = 0, right = 0;
        // key 字符
        // value 索引
        Map<Character, Integer> window = new HashMap<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            // 不是一个字符，一个字符移动，而是通过 index 跳着移动
            int rightCharIndex = window.getOrDefault(rightChar, -1);

//            if (rightCharIndex == -1 || rightCharIndex < left) {
//                left = left;
//            } else {
//                left = rightCharIndex + 1;
//            }

            // KeyPoint 解释为什么需要 rightCharIndex < left ?
            // 移动 left 指针，并没有去删除 window 中的 left 对应的字符
            // 当从窗口中查找 rightChar 对应的元素的时候，可能找到之前出现过的元素，
            // 执行 left = rightCharIndex + 1，反而使得 left 左移，导致结果不正确

            // 索引  0 1 2 3
            // 字符  a b b a
            //          ↑
            //        left
            //        right

            // 索引  0 1 2 3
            // 字符  a b b a
            //         ↑   ↑
            //       left
            //           right
            // left 原来是索引 2 位置，现在却左移到索引 1 位置，从而导致结果不正确
            // 故 rightCharIndex < left 了，实际上这种情况，我们也不应该移动 left 的了

            // KeyPoint 将上面 if 判断，通过 Math.max 来简化
            // 因为上面的代码段中 rightCharIndex < left 时，也是取 left，等价于该下面代码
            left = Math.max(left, rightCharIndex + 1);
            // 注意：rightCharIndex + 1，表示其后一个位置，是出现重复字符，left 该移动到的位置

            // 详细解释请见：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4JB1P

            maxLen = Math.max(maxLen, right - left + 1);
            // rightChar，对应索引 right
            // KeyPoint 后移一位操作，应该是和 left 最相关，故将其放到 left 处
            window.put(rightChar, right);
            right++;
        }

        return maxLen;
    }

    // KeyPoint 方法四 追求程序的极致性能 => 最优解
    // 充分利用题目给的条件 => s 由英文字母、数字、符号和空格组成
    // => 即 ASCII 前 128 个字符组成，使用数组代替 Map
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        // " "，len = 1
        // ""，len = 0
        if (n <= 1) return n;
        int maxLen = 1;

        int left = 0, right = 0;
        // Map 底层是：数组 + 链表，需要的空间更大，有可能出现哈希碰撞
        int[] window = new int[128];
        while (right < n) {
            char rightChar = s.charAt(right);
            int rightCharIndex = window[rightChar];
            left = Math.max(left, rightCharIndex);

            // KeyPoint 细节注意
            // s 字符索引从 0 开始，而 window 不存在 rightChar，返回也是 0
            // 导致判断条件重合，无法区分 rightChar 是否存在，导致有些测试用例通过不了
            // 通过 window[rightChar] = right + 1 将其区别

            // KeyPoint 详细代码见下面
//            if (rightCharIndex == 0 || rightCharIndex < left) {
//            } else {
//                left = rightCharIndex;
//            }

            maxLen = Math.max(maxLen, right - left + 1);
            // 直接使用字符，没有转化成数字，直接将 char 编码为整数
            // 表示字符对应的 Unicode 值，'a' => 97，在数组 window[97] = s 中的 index
            window[rightChar] = right + 1;

            right++;
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring4(String s) {
        if (s == null) return 0;
        int left = 0, right = 0;
        int n = s.length();
        int maxLen = 0;
        int[] map = new int[128];
        while (right < n) {
            char rightChar = s.charAt(right);
            int rightIndex = map[rightChar];
            if (rightIndex == 0 || rightIndex < left) {
            } else {
                left = rightIndex;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            map[rightChar] = right + 1;
            right++;
        }
        return maxLen;
    }
}
