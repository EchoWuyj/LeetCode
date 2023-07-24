package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 16:57
 * @Version 1.0
 */
public class _03_3_longest_substring_without_repeating_characters4 {

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
