package alg_01_ds_dm._01_line._05_algo._01_twopoint;

/**
 * @Author Wuyj
 * @DateTime 2023-03-10 20:07
 * @Version 1.0
 */
public class _02_344_ReverseString {

    /*
        344. 反转字符串
        编写一个函数，其作用是将输入的字符串反转过来。
        输入字符串以字符数组 s 的形式给出。

        不要给另外的数组分配额外的空间，
        你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

        输入：s = ["h","e","l","l","o"]
        输出：["o","l","l","e","h"]

        输入：s = ["H","a","n","n","a","h"]
        输出：["h","a","n","n","a","H"]

        提示
        1 <= s.length <= 105
        s[i] 都是 ASCII 码表中的可打印字符
     */

    // KeyPoint 方法一:辅助数组
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public static String reverseString1(String s) {
        char[] chars = s.toCharArray();
        char[] tmp = new char[chars.length];

        int j = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            tmp[j] = chars[i];
            j--;
        }
        return new String(tmp);
    }

    // KeyPoint 方法二:对撞指针
    // 时间复杂度 O(n/2) = O(n)
    // 空间复杂度 O(1)
    public static String reverseString(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char tmp = chars[left];
            // 错误写法，不能这样赋值，需要转成字符数组
            // s.charAt(right) = s.charAt(left);
            chars[left] = chars[right];
            chars[right] = tmp;

            // 指针移动一定是在 while 循环中的
            left++;
            right--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String s = "hello";
        System.out.println(reverseString(s)); // olleh
        System.out.println(reverseString1(s)); // olleh
    }
}
