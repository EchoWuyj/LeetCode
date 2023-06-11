package alg_02_train_dm._10_day_栈和队列;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 16:37
 * @Version 1.0
 */
public class _14_151_reverse_words_in_a_string {

    /*
        151 号算法题：翻转字符串里的单词
        给定一个字符串，逐个翻转字符串中的每个单词。

        输入："the sky is blue"
        输出："blue is sky the"

        输入："  hello world!  "
        输出："world! hello"

        输入："a good   example"
        输出："example good a"

        输入：s = "  Bob    Loves  Alice   "
        输出："Alice Loves Bob"

        1. 无空格字符构成一个单词 。
        2. 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
        3. 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

        请尝试使用 O(1) 额外空间复杂度的原地解法。
     */

    // KeyPoint 方法一 使用内置 API
    public String reverseWords1(String s) {

        // 输入：s = "  Bob    Loves  Alice   "
        // 1. "Bob    Loves  Alice"
        // 2. ["Bob", "Loves", "Alice"]
        // 3. ["Alice", "Loves", "Bob"]
        // 4. "Alice Loves Bob"
        // 输出："Alice Loves Bob"

        s = s.trim();
        // \\s ==\s 表示转义字符 ,\s表示匹配任意空格(包括空格，制表符，换页符)
        // \\s+中的'+'表示多次匹配
        // s.split("\\s+") 将字符串 s 按照一个或多个连续的空白字符进行分割，并返回一个字符串数组
        // ["Bob", "Loves", "Alice"]
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    // KeyPoint 方法二 不使用内置 API
    // 一般情况，自己实现 API，比系统内置的 API 要高效，因为系统 API 会做很多判断，从而影响性能，即使时间复杂度相同
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public String reverseWords2(String s) {

        // 输入：s = "  Bob    Loves  Alice   "
        // 1. "Bob Loves Alice"
        // 2. "ecilA sevoL boB"
        // 3. "Alice Loves Bob"
        // 输出："Alice Loves Bob"
        // 消除头尾空格
        s = trimSpaces(s.toCharArray());
        // KeyPoint 每次对字符串操作，具有连续性，上次结果(消除头尾空格后的字符串)为这次的输入
        // 将 s 转成 chars 数组，给后面使用
        char[] chars = s.toCharArray();
        // 整体反转
        reverse(chars, 0, chars.length - 1);
        // 每个字符反转
        s = reverseEachWord(chars);

        return s;
    }

    // 对撞指针
    // 空间复杂度O(n)
    private String trimSpaces1(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉开头的空格
        while (left <= right && s.charAt(left) == ' ') left++;
        // 去掉结尾的空格
        while (left <= right && s.charAt(right) == ' ') right--;
        // 去掉中间的空格
        StringBuilder sb = new StringBuilder();
        // KeyPoint left <= right 需要取等，否则会丢字符
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                sb.append(c);
                // sb 添加一个单词的最后一个字符加上空格 => 保证单词之间需要留一个空格
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(' ');
            }
            // 只是移动 left，right 并不移动
            left++;
        }
        return sb.toString();
    }

    // 快慢指针
    // 空间复杂度为 O(1)
    private String trimSpaces(char[] chars) {

        int n = chars.length;
        int slow = 0, fast = 0;
        while (fast < n) {

            // 去掉字符串开头空格
            // "  Bob    Loves  Alice   "
            // "Bob    Loves  Alice   "
            //  ↑
            // fast
            while (fast < n && chars[fast] == ' ') fast++;

            // 保留非空格字符
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

        // chars[slow++] = chars[fast++] 两个指针同步移动，while 循环结束之后，slow 也是在后面一个位置
        // slow -1 才是字符最后一个位置，而 substring(0, slow)，正好没有将 slow 包含进来
        return new String(chars).substring(0, slow);
    }

    private void reverse(char[] cArr, int start, int end) {
        // 将 temp 定义在外面，避免 while 每次都创建一个 tmp
        char temp;
        while (start < end) {
            temp = cArr[start];
            // 指针一定移动，且一定是 while 循环里面，否则无法做到整体字符串的字符反转
            cArr[start++] = cArr[end];
            cArr[end--] = temp;
        }
    }

    private String reverseEachWord(char[] chars) {
        int left = 0;
        int n = chars.length;
        // 2. "ecilA sevoL boB"
        // 3. "Alice Loves Bob"
        while (left < n) {
            if (chars[left] != ' ') {
                int right = left;
                while (right + 1 < n && chars[right + 1] != ' ') right++;
                // while 循环结束，right + 1 在空格位置
                // left 到 right 就是一个单词 start 和 end
                reverse(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        return new String(chars);
    }

    // KeyPoint 方法三 使用双端队列(栈)
    public String reverseWords(String s) {

        // 输入：s = "  Bob    Loves  Alice   "
        // 1. "Bob    Loves  Alice"
        // 2. Deque
        //   ----------------------------
        //         Alice Loves Bob
        //   ----------------------------
        // 输出："Alice Loves Bob"

        // left 和 right 都是索引，故 right 从 s.length() - 1 开始
        int left = 0, right = s.length() - 1;
        // 去掉开头的空格
        while (left <= right && s.charAt(left) == ' ') left++;
        // 去掉结尾的空格
        // KeyPoint right 从右往左移动，right--
        while (left <= right && s.charAt(right) == ' ') right--;

        // 双端队列 => 使用额外空间
        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                word.append(c);
            } else if (word.length() != 0) {
                deque.offerFirst(word.toString());
                // 清空
                word.setLength(0);
            }
            // 指针前移，不能忘记！
            left++;
        }
        // 若 s 最后没有空格，即没有执行 deque.offerFirst(word.toString());
        // 需要将最后一个单词加入队列中
        deque.offerFirst(word.toString());
        return String.join(" ", deque);
    }
}
