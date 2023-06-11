package alg_01_ds_wyj._01_line._05_algo._01_twopoint;

/**
 * @Author Wuyj
 * @DateTime 2023-05-08 21:28
 * @Version 1.0
 */
public class _02_344_ReverseString {

    public static String reverseString1(String s) {
        if (s == null) return null;
        int length = s.length();
        char[] tmp = new char[length];
        int j = length - 1;
        for (int i = 0; i < length; i++) {
            tmp[j] = s.charAt(i);
            j--;
        }
        return new String(tmp);
    }

    public static String reverseString(String s) {
        if (s == null) return null;
        int left = 0;
        int right = s.length() - 1;
        char[] strArr = s.toCharArray();
        while (left < right) {
            char tmp = strArr[right];
            strArr[right] = strArr[left];
            strArr[left] = tmp;

            right--;
            left++;
        }
        return new String(strArr);
    }

    public static void main(String[] args) {
        String s = "hello";
        System.out.println(reverseString1(s));
        System.out.println(reverseString(s));
    }
}
