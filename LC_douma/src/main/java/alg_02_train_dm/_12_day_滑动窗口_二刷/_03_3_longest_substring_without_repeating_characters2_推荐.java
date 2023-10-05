package alg_02_train_dm._12_day_滑动窗口_二刷;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 16:54
 * @Version 1.0
 */
public class _03_3_longest_substring_without_repeating_characters2_推荐 {

    // 优化思路 => 核心：分析重复计算代码逻辑，对重复计算的部分进行优化
    // 重复计算：通过暴力枚举区间，区间与区间之间存在重叠，区间重叠部分会进行多次判断是否存在重复字符

    // 具体优化
    // 若 s[i, j) 子串没有重复字符，如果想要判断 s[i, j] 没有重复字符，则只需要判断 s[i, j) 中是否存在 s[j] 即可
    // => 只需针对新增字符 s[j] 判断是否为重复元素，而不是再去判断 s[i, j] 整体是否存在重复字符，从而减少重复计算
    // => 滑动窗口思路

    // KeyPoint 方法二 滑动窗口
    // 时间复杂度：O(n)，
    // => 最坏的情况是 left 和 right 都遍历了一遍字符串，O(2n) -> O(n)
    // 空间复杂度：O(n)
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        // s 为 ""，返回 0
        if (n <= 1) return n;
        int maxLen = 1;

        int left = 0, right = 0;
        // 使用 set 作为窗口，用于判重
        Set<Character> window = new HashSet<>();

        // 滑动窗口逻辑：
        // 1.不断移动 right，为了保证窗口最大
        // 2.若 right 字符在窗口中已经出现，移动左边界，为了缩减窗口，为了踢出重复字符
        while (right < n) {
            char rightChar = s.charAt(right);

            // 1.先判断 right 位置字符是否存在 set 中
            // 2.若是重复字符，右移 left，缩小窗口，从而将重复字符踢出窗口
            // 3.计算 maxLen 后，将 rightChar 加入到 set 集合

            // 注意：可能存在多个重复字符，故使用 while 判断
            while (window.contains(rightChar)) {
                // KeyPoint 关键
                // 先将窗口左侧元素移除，再去移动 left 指针，不要遗忘，否则陷入死循环
                // 死循环 => 超时多半时 while 循环出现问题，针对 while 循环代码进行修改
                window.remove(s.charAt(left));
                left++;
            }
            // 没有重复字符，更新 maxLen
            maxLen = Math.max(maxLen, right - left + 1);
            // 同理： 先将窗口右侧元素添加，再去移动 right 指针
            window.add(rightChar);
            right++;
        }
        return maxLen;
    }
}
