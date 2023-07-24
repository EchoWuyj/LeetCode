package alg_02_train_dm._12_day_滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 16:55
 * @Version 1.0
 */
public class _03_3_longest_substring_without_repeating_characters3 {

    // KeyPoint 方法三 优化 滑动窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int lengthOfLongestSubstring(String s) {

        //            right
        //              ↓
        //  p q e d c w w w w w w k e w
        //  ↑           ↑
        // left        left'

        // p 和 w 之间无重复字符很多，则 left 需要一个一个向前移动，从 left 到 left'，效率低下

        // 优化思路：
        // 方法二中的滑动窗口，当在窗口中存在重复字符，是一个一个字符缩小窗口，速度太慢
        // => 可以通过记住每个字符在字符串中的索引，当遇到重复字符时，就可以直接跳到重复字符的后面，实现跳跃移动

        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;
        int left = 0, right = 0;

        // Map 映射
        // key => 字符
        // value => 索引
        Map<Character, Integer> window = new HashMap<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            //
            int rightCharIndex = window.getOrDefault(rightChar, -1);

            // 不是一个字符，一个字符移动，而是通过 index 跳着移动

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
}
