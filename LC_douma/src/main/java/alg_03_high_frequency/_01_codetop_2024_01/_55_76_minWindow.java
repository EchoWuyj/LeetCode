package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 19:52
 * @Version 1.0
 */
public class _55_76_minWindow {
    public String minWindow(String s, String t) {

        // 定义长度 n
        int n = s.length();
        // 对字符串 t 的操作
        int[] cntStrT = new int[128];
        int uniqueCnt = 0;
        for (char c : t.toCharArray()) {
            if (cntStrT[c] == 0) uniqueCnt++;
            cntStrT[c]++;
        }

        // 对字符串 s 的操作
        int[] windowCnt = new int[128];
        int matchCnt = 0;

        // 定义结果集
        int[] res = {-1, 0, 0};

        // 滑动窗口指针
        int left = 0, right = 0;

        // 循环遍历字符串 s
        while (right < n) {
            char rightChar = s.charAt(right);
            windowCnt[rightChar]++;

            // 窗口和目标进行比较判断
            if (windowCnt[rightChar] == cntStrT[rightChar]) {
                matchCnt++;
            }

            // 判断 matchCnt 和 uniqueCnt
            // left 和 right 可以取等，两指针在同一个位置
            while (left <= right && matchCnt == uniqueCnt) {
                // 更新 res
                if (res[0] == -1 || right - left + 1 < res[0]) {
                    res[0] = right - left + 1;
                    res[1] = left;
                    res[2] = right;
                }

                char leftChar = s.charAt(left);
                windowCnt[leftChar]--;
                // 比较过程都是 windowCnt 和 cntStrT
                if (windowCnt[leftChar] < cntStrT[leftChar]) {
                    matchCnt--;
                }
                // 更新 left
                left++;
            }
            // 更新 right
            right++;
        }
        // 不能直接返回，得需要判断，res[0] 是否已经更新
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }
}
