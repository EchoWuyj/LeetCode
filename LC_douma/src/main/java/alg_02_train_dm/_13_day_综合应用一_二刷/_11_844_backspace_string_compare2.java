package alg_02_train_dm._13_day_综合应用一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-01 13:57
 * @Version 1.0
 */
public class _11_844_backspace_string_compare2 {

    public boolean backspaceCompare1(String s, String t) {
        return build(s).equals(build(t));
    }

    // KeyPoint 进阶
    // 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
    // => 想要不使用额外空间，且时间复杂度为 O(N) => 双指针

    // KeyPoint 方法三 双指针 => 快慢指针 => 从前往后遍历
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    private String build(String str) {

        //    fast
        //     ↓
        //  s：a b # # # c # d
        //  ↑
        // slow

        // KeyPoint 双指针
        // 0 ~ slow：存放不是'#'且不用删除字符
        // => 注意：闭区间 [0,slow]
        // slow ~ fast：存放需要删除字符，及'#'
        // => 注意：左开右闭区间 (slow,fast]

        // => 本质：划分指针区间，每个区间进行怎样操作

        char[] chars = str.toCharArray();
        int n = chars.length;
        int slow = -1, fast = 0;
        while (fast < n) {
            if (chars[fast] != '#') {
                // 先移动 slow，扩充一个位置
                slow++;
                // 字符交换，为了保留需要的字符
                if (slow != fast) swap(chars, slow, fast);
            } else { // chars[fast] == '#'
                // slow 左移，表示该位置元素删除
                // 但是在 slow 左移前需要判断下，slow 左最多为 -1，不能再左移
                if (slow > -1) slow--;
            }
            fast++;
        }
        // [0,slow] 不是'#'且不用删除字符的区间
        // count 表示：length 长度，[0,slow] 长度为 slow + 1
        // KeyPoint 注意：slow == -1，返回 ""，而不是返回 null，否则上游需要处理
        //               null 是不能调用 equals 方法的，但是 "" 可以
        return slow == -1 ? "" : new String(chars, 0, slow + 1);
    }

    // 交换
    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    // KeyPoint 方法四 双指针 => 从后往前遍历
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    public boolean backspaceCompare(String s, String t) {

        // "ab##" 遇到两个'#'，则需要删除两个字符

        //          i
        //          ↓
        // s：a b # c d c # #
        // t：a c # # c c #
        //            ↑
        //            j

        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;

        // KeyPoint 补充说明
        // 1.若s 或 t 只要有字符未处理，则指针前移，循环遍历
        // 2.若 s 和 t 字符串都处理完了，则循环就会停止
        // 这种方式对于处理两个可能长度不一致的字符串特别有效，不论哪个字符串先处理完
        // 都可以确保另一个字符串的剩余部分会被正确处理
        while (i >= 0 || j >= 0) {

            // 回退 S 字符串的字符
            while (i >= 0) {
                // 遇到 '#'，记录需要跳过字符个数
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else {
                    if (skipS > 0) {
                        // 跳过字符，移动指针
                        skipS--;
                        i--;
                    } else {
                        // KeyPoint 二选一关系，故使用 if...else
                        // i 为第一不需要删除的字符
                        // 结束 while 循环
                        break;
                    }
                }
            }

            // 回退 T 字符串的字符
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else {
                    if (skipT > 0) {
                        skipT--;
                        j--;
                    } else {
                        break;
                    }
                }
            }

            // KeyPoint 经过上面两个 while 循环，i 指针 和 j 指针 分别指向 s 和 t 第一不需要删除的字符
            // => 针对 i 和 j 进行判断

            // 1.判断 S 和 T 是否相等
            // 若当前的 i 和 j 对应的字符不相等，提前返回 false
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false;

            // 2.若有一个指针到头了，还有一个不到头，则两者必然不相等，提前返回 false
            if ((i >= 0) != (j >= 0)) return false;

            // 别忘记，指针前移
            i--;
            j--;
        }
        // 若没有提前返回 false，则最终是 true
        return true;
    }
}
