package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:12
 * @Version 1.0
 */
public class _10_165_compare_version_numbers {

    // 不使用内置函数
    public int compareVersion(String version1, String version2) {
        int i1 = 0, i2 = 0;
        while (i1 < version1.length() || i2 < version2.length()) {
            int v1 = 0, v2 = 0;
            while (i1 < version1.length() && version1.charAt(i1) != '.') {
                v1 = v1 * 10 + (version1.charAt(i1) - '0');
                i1++;
            }
            // bug 修复：这里处理的是 version2
            while (i2 < version2.length() && version2.charAt(i2) != '.') {
                v2 = v2 * 10 + (version2.charAt(i2) - '0');
                i2++;
            }

            if (v1 != v2) {
                return v1 > v2 ? 1 : -1;
            }
            i1++;
            i2++;
        }
        return 0;
    }

    // KeyPoint 使用内置函数 => 推荐
    public int compareVersion1(String version1, String version2) {
        String[] num1 = version1.split("\\.");
        String[] num2 = version2.split("\\.");

        int m = num1.length;
        int n = num2.length;

        int v1, v2;
        for (int i = 0; i < Math.max(m, n); i++) {
            v1 = i < m ? Integer.parseInt(num1[i]) : 0;
            v2 = i < n ? Integer.parseInt(num2[i]) : 0;

            if (v1 != v2) {
                return v1 > v2 ? 1 : -1;
            }
        }
        return 0;
    }
}
