package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 23:04
 * @Version 1.0
 */
public class _60_151_reverseWords {
    public String reverseWords(String str) {

        // KeyPoint 补充说明

//        // 初始赋值，不同值可以写在同一行
//        int left = 0, right = str.length() - 1;

//        // left 和 right 不相等，则需要分开赋值
//        left = 0;
//        right = trimStr.length() - 1;

        String trimStr = trim(str);
        // 使用处理过的 trimStr
        // KeyPoint 针对反转操作，最好传入字符数组，而不是字符串
        String reverseStr = reverse(trimStr.toCharArray(), 0, trimStr.length() - 1);
        String res = charReverse(reverseStr.toCharArray());
        return res;
    }

    // 清除字符串空格
    public String trim(String str) {
        int left = 0, right = str.length() - 1;
        // 两端空格
        while (left <= right && str.charAt(left) == ' ') left++;
        while (left <= right && str.charAt(right) == ' ') right--;
        StringBuilder sb = new StringBuilder();

        // 中间多余空格
        while (left <= right) {
            if (str.charAt(left) != ' ') {
                sb.append(str.charAt(left));
            } else {
                // 只是保留一个空格，后续多余空格都跳过
                if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(' ');
                }
            }
            // 移动 left 指针
            left++;
        }
        return sb.toString();
    }

    // 字符串整体反转
    public String reverse(char[] chars, int left, int right) {

        // KeyPoint 经典错误
        // 不能直接这样赋值，交换元素得通过数组实现
        // trimStr.charAt(left) = trimStr.charAt(right);
        // 类似错误形式：int a = int b;

        while (left < right) {
            char tmpChar = chars[right];
            chars[right] = chars[left];
            chars[left] = tmpChar;
            left++;
            right--;
        }
        return new String(chars);
    }

    // 单个字符反转
    public String charReverse(char[] chars) {

        // KeyPoint 经典错误
        // 不能直接在字符串上进行修改，因为修改的结果没有保存，需要将其转成数组
//            right = left;
//            while (right < n - 1 && reverseStr.charAt(right) != ' ') right++;
//            reverse(reverseStr, left, right);
//            left = right + 1;

        // 注意：n 为 chars 的 length，并没有减一
        int left = 0, n = chars.length;
        while (left < n) {
            // 需要加一层 if 判断，因为 left 有可能在 ' ' 位置
            if (chars[left] != ' ') {
                // right 从 left 位置开始往后遍历
                int right = left;
                // right 最后在空格位置的前一个位置
                // right < n-1，保证 right + 1 不越界
                while (right < n - 1 && chars[right + 1] != ' ') right++;
                //

                reverse(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        return new String(chars);
    }
}
