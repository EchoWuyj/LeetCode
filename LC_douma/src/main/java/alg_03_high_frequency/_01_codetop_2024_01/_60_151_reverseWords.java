package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 23:04
 * @Version 1.0
 */
public class _60_151_reverseWords {

    // 反转字符串中的单词
    // 直接模拟
    public String reverseWords(String str) {

        // 输入：s = "the sky is blue"
        // 输出："blue is sky the"

        // KeyPoint 补充说明

//        // 初始赋值，不同值可以写在同一行
//        int left = 0, right = str.length() - 1;

//        // 不是赋初值，则需要分开赋值
//        left = 0;
//        right = trimStr.length() - 1;

        // 1.清除字符串空格
        String trimStr = trim(str);
        // KeyPoint 使用处理过的 trimStr，且针对反转操作，最好传入字符数组，而不是字符串
        // 2.字符串整体反转
        String reverseStr = reverse(trimStr.toCharArray(), 0, trimStr.length() - 1);
        // 3.单个字符反转
        String res = charReverse(reverseStr.toCharArray());
        return res;
    }

    // 1.清除字符串空格
    public String trim(String str) {
        int left = 0, right = str.length() - 1;
        // 两端空格
        while (left <= right && str.charAt(left) == ' ') left++;
        while (left <= right && str.charAt(right) == ' ') right--;
        StringBuilder res = new StringBuilder();

        // 中间多余空格
        while (left <= right) {
            if (str.charAt(left) != ' ') {
                res.append(str.charAt(left));
            } else {
                // 只是保留一个空格，后续多余空格都跳过
                // 通过 res(StringBuilder) 来判断最后一个位置字符是否为空格，而不是使用原始字符串 s 来判断
                if (res.charAt(res.length() - 1) != ' ') {
                    res.append(' ');
                }
            }
            // 移动 left 指针
            left++;
        }
        return res.toString();
    }

    // 2.字符串整体反转
    public String reverse(char[] chars, int left, int right) {

        // KeyPoint 经典错误
        // 不能直接这样赋值，交换元素得通过数组实现
        // 错误代码：trimStr.charAt(left) = trimStr.charAt(right);
        // char c1 = trimStr.charAt(left);
        // char c2 = trimStr.charAt(right);
        // char c1 = char c2 类似错误形式 int a = int b;

        // 错误代码：trimStr.charAt(left) = 'c'; 无法直接赋值

        // 正确代码
        // char c1 = trimStr.charAt(left)
        // c1 = 'c'

        while (left < right) {
            char tmpChar = chars[right];
            chars[right] = chars[left];
            chars[left] = tmpChar;
            left++;
            right--;
        }
        return new String(chars);
    }

    // 3.单个字符反转
    public String charReverse(char[] chars) {

        // KeyPoint 经典错误
        // 不能直接在字符串上进行修改，因为修改的结果没有保存，需要将其转成数组，通过数组索引来实现值的修改

        // KeyPoint 注意 n 为 chars 的 length，并没有减一，处于越界位置
        int left = 0, n = chars.length;
        while (left < n) {
            // 需要加一层 if 判断，判断 char[left]，因为 left 指针有可能在 ' ' 位置
            if (chars[left] != ' ') {
                // right 从 left 位置开始往后遍历
                int right = left;
                // KeyPoint 注意点
                //  1.数组 chars 使用索引 [right+1]，则需要保证索引 right+1 < n，保证其不越界
                //  2.若 right -1 < n，只能保证索引 right-1 不越界，不能保证 right+1 不越界
                // 当 chars[right+1] = ' '，while 循环结束，此时 right 在空格位置的前一个位置
                while (right < n - 1 && chars[right + 1] != ' ') right++;
                reverse(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        return new String(chars);
    }
}
