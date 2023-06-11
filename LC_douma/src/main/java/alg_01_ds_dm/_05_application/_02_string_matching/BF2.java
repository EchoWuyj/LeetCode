package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 21:05
 * @Version 1.0
 */

// java.lang.String.indexOf 实现就是这种方法
public class BF2 {
    // 时间复杂度：O(mn)
    // 空间复杂度：O(1)
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 模式串的第一字符
        char first = pattern.charAt(0);
        for (int i = 0; i < m; i++) {
            // 1. 找到等于模式串中第一个字符的位置
            if (mainStr.charAt(i) != first) {
                // i++ 先操作，再自增
                // ++i 先自增，再操作
                // KeyPoint while 循环中 ++i，i++ 都是可以的，但是需要牢记 i++ 和 ++i 的区别
                while (++i < m && mainStr.charAt(i) != first) ;
            }

            // mainStr 和 pattern 首个字符相等之后，分别从后面一个字符开始比较
            if (i < m) {
                // 2. 比对后面的字符，如果相等的话，一直比对下去
                int k = i + 1;
                int j = 1;
                // bug 修复，如果 j 已经超出了 pattern 范围，说明已经找到了，则直接返回
                if (j == n) return i;
                for (; j < n && k < m; j++, k++) {
                    if (mainStr.charAt(k) == pattern.charAt(j)) {
                        // 3. 如果 j 是模式串的最后一个字符，说明匹配到了模式串
                        if (j == n - 1) return i;
                    } else {
                        // 如果字符不相等，则退出循环
                        break;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BF2 b = new BF2();

        String mainStr = "a";
        String patternStr = "a";
        System.out.println(b.indexOf(mainStr, patternStr)); // 0

//        String mainStr = "    your code";
//        String patternStr = "your";
//        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }
}
