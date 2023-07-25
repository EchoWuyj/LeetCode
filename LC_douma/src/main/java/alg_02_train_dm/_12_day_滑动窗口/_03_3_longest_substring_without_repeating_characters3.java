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

        //               right
        //                 ↓
        //  0 1 2 3    4 5 6
        //  p o w w    k e w
        //  ↑     ↑    ↑
        // left left' left''

        // right 移动到 3 索引对应的 w 位置
        // 此时，窗口中存在重复字符 w，通过 map 获取之前 w 出现的位置 2
        // 并将 left 移动到 2 索引后面 w 位置，从而实现快速将重复字符从窗口中踢出

        // right 移动到 6 索引对应的 w 位置
        // 此时，窗口中存在重复字符 w，通过 map 获取之前 w 出现的位置 3
        // 并将 left 移动到 3 索引后面 k 位置，从而实现快速将重复字符从窗口中踢出

        // p → 0
        // o → 1
        // w → 2
        // -----
        // w → 3
        // k → 4
        // e → 5
        // -----
        // w → 6

        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;
        // KeyPoint 两个变量 left 和 right 同时初始化 => 正确写法
        int left = 0, right = 0;

        // KeyPoint 两个变量 left 和 right 同时初始化 => 错误写法
        // left 没有进行初始化
        // int left, right = 0;

        // Map 映射
        // key => 字符
        // value => 索引
        Map<Character, Integer> window = new HashMap<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            // KeyPoint 注意：使用 getOrDefault，设置默认值 -1
            int rightCharIndex = window.getOrDefault(rightChar, -1);

            // rightCharIndex == -1 说明该字符之前没有出现过
            // 或者 rightCharIndex < left，left 指针都不移动
            if (rightCharIndex == -1 || rightCharIndex < left) {
                // 也可以将 left = left 注释掉
                left = left;
            } else {

                // KeyPoint left 不是一个字符，一个字符移动，而是通过 index 跳着移动
                // rightCharIndex 上一轮重复字符对应索引，rightCharIndex + 1 表示其后一个位置
                // 本轮 right 对应 Index 还没有更新
                // => 执行下面代码 window.put(rightChar, right) 才算真正更新
                left = rightCharIndex + 1;
            }

            // KeyPoint 使用 Math.max 简化 if else 判断
            // 1.rightCharIndex == -1 || rightCharIndex < left，选择较大值 left
            // 2.否则 rightCharIndex != -1 && rightCharIndex >= left，选择较大值 rightCharIndex + 1
            // left = Math.max(left, rightCharIndex + 1);

            // KeyPoint 解释为什么需要 rightCharIndex < left ?

            // index  0 1 2 3
            // char   a b b a
            //            ↑
            //          left
            //          right

            // right 移动到 2 索引对应的 b 位置
            // 此时，窗口中存在重复字符 b，通过 map 获取之前 b 出现的位置 1
            // 并将 left 移动到 1 索引后面 b 位置

            // index  0 1 2 3
            // char   a b b a
            //            ↑ ↑
            //          left
            //             right

            // right 移动到 3 索引对应的 a 位置
            // 由于移动 left 指针，并没有去删除 window 中的 left 对应的字符
            // 即：最开始 index 为 0 位置，对应 a 字符

            // map(a) => 0，rightCharIndex < left
            // 若不执行 rightCharIndex < left，left = left，
            // 而执行 left = rightCharIndex + 1，反而使得 left 左移

            // index 0 1 2 3
            // char  a b b a
            //         ↑   ↑
            //       left
            //           right

            // 即将 left 原来是索引 2 位置，现在却左移到索引 1 位置，从而导致结果不正确
            // 故 rightCharIndex < left 了，实际上这种情况，我们也不应该移动 left 的了

            // 详细解释请见
            // https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4JB1P

            // 更新 maxLen
            maxLen = Math.max(maxLen, right - left + 1);

            // 更新 rightChar 对应 right 索引
            window.put(rightChar, right);
            // right 后移
            right++;
        }
        return maxLen;
    }
}
