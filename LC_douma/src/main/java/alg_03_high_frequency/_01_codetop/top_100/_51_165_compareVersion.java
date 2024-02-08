package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 15:22
 * @Version 1.0
 */
public class _51_165_compareVersion {

    // 比较版本号
    // 直接模拟
    public int compareVersion(String version1, String version2) {

        // 根据 '.' 进行切分，将字符串转成数字进行比较
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");

        // m 和 n 位于越界位置
        int m = nums1.length;
        int n = nums2.length;

        // 以两数组中较长的数组长度为 for 循环基准
        for (int i = 0; i < Math.max(m, n); i++) {
            // 没有越界，则使用相应数字，否则使用数字 0
            int x = i < m ? Integer.parseInt(nums1[i]) : 0;
            int y = i < n ? Integer.parseInt(nums2[i]) : 0;

            // 只有明确的大小关系，才去返回 1 或者 -1，中间出现的相等进行下轮比较
            if (x > y) {
                // version1 > version2 返回 1
                return 1;
                // 这里必须使用 else if，不能使用 else，得留下相等可能，执行后面的 for 循环
            } else if (x < y) {
                // version1 < version2 返回 -1
                return -1;
            } else {
                // x = y，进行下轮比较，执行下轮 for 循环
            }
        }
        // 循环遍历结束后，每个元素都相等，才最终返回 0
        return 0;
    }
}
