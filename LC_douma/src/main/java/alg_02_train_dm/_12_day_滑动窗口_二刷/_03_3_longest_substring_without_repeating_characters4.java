package alg_02_train_dm._12_day_滑动窗口_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 16:57
 * @Version 1.0
 */
public class _03_3_longest_substring_without_repeating_characters4 {

    // KeyPoint 方法四 数组替换 Map => 最优解 => 追求程序的极致性能
    // 充分利用题目给的条件：s 由英文字母、数字、符号和空格组成
    // => 即 ASCII 前 128 个字符组成，使用数组代替 Map
    // => Map 底层是：数组 + 链表，需要的空间更大，有可能出现哈希碰撞
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        // 若 s 为 " "，则 len = 1
        // 若 s 为 ""，则 len = 0
        if (n <= 1) return n;
        int maxLen = 1;

        int left = 0, right = 0;
        // 数组充当 Map
        // index => 字符对应 ASCII
        // value => s.charAt 位置索引
        int[] window = new int[128];
        while (right < n) {
            char rightChar = s.charAt(right);

            // KeyPoint 说明 ASCII
            // window 定义容量 128，范围 0-127，直接将前 128 个 ASCII 都已经包括了
            // 故 window[rightChar] 不需要减 'a' 或 '0'
            // => 主要是因为：s 由英文字母、数字、符号和空格组成，没法确定起始点，故直接定义为 128，将其都包括在内
            // => 关于 ASCII，详见 03_Note_ASCII

            // KeyPoint 区别：s 只包含小写英文字母
            // => int[] counts = new int[26];
            // => counts[c - 'a']++;

            int rightCharIndex = window[rightChar];

            // KeyPoint 细节注意
            // s.charAt 位置索引，从 0 开始
            // window 数组不存在 rightChar 的位置索引返回数组默认值 0
            // => 从而导致判断条件重合，无法区分 rightChar 是存在，还是位置为 0
            // => 通过 window[rightChar] = right + 1 将其区别

            // KeyPoint 详细代码见下面
            if (rightCharIndex == 0 || rightCharIndex < left) {
            } else {
                // window[rightChar] = right + 1
                // => 存储 rightChar 对应索引时，就已经考虑后面一个位置了
                // 故 left 直接移动到 rightCharIndex 即可
                left = rightCharIndex;
            }
            maxLen = Math.max(maxLen, right - left + 1);

            // 直接使用字符 rightChar，没有通过减 'a' 或 '0' 转化成数字
            // [rightChar]，rightChar 对应 ASCII 整数
            // 如：'a' => 97，window[97] = s.charAt(a)
            window[rightChar] = right + 1;
            right++;
        }
        return maxLen;
    }
}
