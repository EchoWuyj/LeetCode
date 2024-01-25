package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 15:22
 * @Version 1.0
 */
public class _51_165_compareVersion {
    public int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");

        int m = nums1.length;
        int n = nums2.length;

        for (int i = 0; i < Math.max(m, n); i++) {
            // 没有越界，则使用相应数字，否则使用数字 0
            int x = i < m ? Integer.parseInt(nums1[i]) : 0;
            int y = i < n ? Integer.parseInt(nums2[i]) : 0;

            // 只有明确的大小关系，才去返回 1 或者 -1，中间出现的相等进行下轮比较
            if (x > y) {
                return 1;
                // 这里必须使用 else if，不能使用 else ，得留下相等可能，执行后面的 for 循环
            } else if (x < y) {
                return -1;
            }
        }
        // 循环遍历结束后，每个元素都相等，才最终返回 0
        return 0;
    }
}
