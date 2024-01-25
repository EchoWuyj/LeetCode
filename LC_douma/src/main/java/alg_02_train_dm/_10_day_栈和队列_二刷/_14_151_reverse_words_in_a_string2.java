package alg_02_train_dm._10_day_栈和队列_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-08 0:53
 * @Version 1.0
 */
public class _14_151_reverse_words_in_a_string2 {

    // KeyPoint 方法二 不使用内置 API
    // 一般情况，自己实现 API，比系统内置的 API 要高效
    // 因为系统 API 会做很多判断，从而影响性能，即使时间复杂度相同
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public String reverseWords2(String s) {

        // 输入：s = "  Bob    Loves  Alice   "
        // 1."Bob Loves Alice"
        // 2."ecilA sevoL boB"
        // 3."Alice Loves Bob"
        // 输出："Alice Loves Bob"

        // 总结：通过每一步操作步骤，将其抽取方法，再去单独实现

        // 去掉字符数组中的空格
        // s = trimSpaces1(s);

        s = trimSpaces(s.toCharArray());
        // KeyPoint 注意事项
        // 每次对字符串操作，具有连续性，上次结果[消除头尾空格后的字符串]为这次的输入
        // 最好将 s 转成 chars 数组，给后面 reverse 和 reverseEachWord 使用
        char[] chars = s.toCharArray();
        // 整体反转
        reverse(chars, 0, chars.length - 1);
        // 每个字符反转
        s = reverseEachWord(chars);

        return s;
    }

    // KeyPoint 去掉字符数组中的空格 => 对撞指针 => 实现一
    // 空间复杂度O(n)
    private String trimSpaces1(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉开头的空格
        // ' ' => 空格
        // '' => 空字符
        while (left <= right && s.charAt(left) == ' ') left++;
        // 去掉结尾的空格
        while (left <= right && s.charAt(right) == ' ') right--;

        // 去掉中间的空格
        StringBuilder sb = new StringBuilder();
        // left <= right 需要取等，从而判断 right 索引对应字符，否则会丢字符
        while (left <= right) {
            // sb 只能正向一个一个追加字符，故正向遍历
            char c = s.charAt(left);
            if (c != ' ') {
                sb.append(c);
            } else {
                // c == ' ' 且 sb 添加一个单词的最后一个字符不为空格，则 sb 追加上空格
                // => 保证单词之间需要留一个空格
                if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(' ');
                }
            }
            // 只是移动 left，right 并不移动
            left++;
        }
        return sb.toString();
    }

    // KeyPoint 去掉字符数组中的空格 => 快慢指针
    //          => 实现二 => 实现效率更高，没有进行字符串拼接
    // 类似于：移动零
    // 空间复杂度为 O(1)
    private String trimSpaces(char[] chars) {

        // fast 和 slow 指针，划分区间

        int n = chars.length;
        int slow = 0, fast = 0;
        while (fast < n) {

            // 1.fast先移动，遇到空格跳过，从而去掉字符串开头空格
            // "  Bob    Loves  Alice   "
            // "Bob    Loves  Alice   "
            //  ↑
            // fast
            while (fast < n && chars[fast] == ' ') fast++;

            // 2.保留非空格字符
            // "Bob"
            // KeyPoint slow 指针将原始 chars 覆盖了
            while (fast < n && chars[fast] != ' ') chars[slow++] = chars[fast++];

            // 去掉字符串中间或者结尾空格
            // "Bob    Loves  Alice   "
            //         ↑
            //        fast
            while (fast < n && chars[fast] == ' ') fast++;

            // 一个单词结束，添加一个空格
            // "Bob Loves Alice"
            if (fast < n) chars[slow++] = ' ';
        }

        // KeyPoint 最后边界情况
        // 1.chars[slow++] = chars[fast++] 两个指针同步移动
        // 2.while 循环结束之后，slow 指针在实际最后一个字符的后一个位置
        //   即 slow -1 才是字符最后一个位置，而 substring(0, slow)，正好没有将 slow 包含进来
        return new String(chars).substring(0, slow);
    }

    // 反转字符数组
    private void reverse(char[] chars, int start, int end) {
        // 将 tmp 定义在外面，避免 while 每次都创建一个 tmp
        char tmp;
        while (start < end) {
            tmp = chars[start];
            // 指针一定移动，且一定是 while 循环里面，否则无法做到整体字符串的字符反转
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
    }

    // 反转每个单词字符数组
    private String reverseEachWord(char[] chars) {

        // "ecilA sevoL boB"
        //         ↓
        // "Alice Loves Bob"

        int left = 0;
        int n = chars.length;
        // 使用 left 指针，正向遍历
        // => 核心：指针用法
        while (left < n) {
            if (chars[left] != ' ') {
                // 使用 right 指针代替 left 指针，避免 right 指针移动
                int right = left;
                while (right < n - 1 && chars[right + 1] != ' ') right++;
                // while 循环结束，right + 1 在空格位置
                // left 到 right 就是一个单词 start 和 end
                reverse(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        // 将反转每个单词字符数组后的 chars，包裹一层 String，将其返回
        return new String(chars);
    }
}
