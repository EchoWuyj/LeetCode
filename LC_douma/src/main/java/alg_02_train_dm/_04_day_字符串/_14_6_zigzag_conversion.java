package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:15
 * @Version 1.0
 */
public class _14_6_zigzag_conversion {
    // 按行访问
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder sb = new StringBuilder();
        int delta = 2 * numRows - 2;

        int n = s.length();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col + row < n; col += delta) {
                sb.append(s.charAt(col + row));
                if (row != 0 && row != numRows - 1 && col + delta - row < n) {
                    sb.append(s.charAt(col + delta - row));
                }
            }
        }

        return sb.toString();
    }

    public String convert2(String s, int numRows) {
        // bug 修復：如果 numRows == 1 直接返回
        if (numRows == 1) return s;

        StringBuilder[] sbs = new StringBuilder[numRows];
        // bug 修复：需要初始化 StringBuilder 数组
        for (int i = 0; i < numRows; i++) sbs[i] = new StringBuilder();

        int currRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            sbs[currRow].append(c);
            if (currRow == 0 || currRow == numRows - 1) goingDown = !goingDown;
            currRow += (goingDown ? 1 : -1);
        }

        StringBuilder res = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            res.append(sbs[j]);
        }

        return res.toString();
    }

    public String convert1(String s, int numRows) {
        StringBuilder[] sbs = new StringBuilder[numRows];
        // bug 修复：需要初始化 StringBuilder 数组
        for (int i = 0; i < numRows; i++) sbs[i] = new StringBuilder();

        int n = s.length();
        int i = 0;
        while (i < n) {
            // 垂直向下
            for (int idx = 0; idx < numRows && i < n; idx++) {
                sbs[idx].append(s.charAt(i));
                i++;
            }

            // 右前向上
            // bug 修复：idx--
            for (int idx = numRows - 2; idx >= 1 && i < n; idx--) {
                sbs[idx].append(s.charAt(i));
                i++;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            res.append(sbs[j]);
        }

        return res.toString();
    }
}
