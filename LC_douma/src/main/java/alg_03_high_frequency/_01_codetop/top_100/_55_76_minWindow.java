package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 19:52
 * @Version 1.0
 */
public class _55_76_minWindow {

    // 最小覆盖子串
    // 滑动窗口
    public String minWindow(String s, String t) {

        // s 原始串
        // t 目标串

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
        // 以字符串 s 为窗口进行滑动，定义为 windowCnt
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

            // 针对 rightChar 字符，窗口和目标进行比较判断
            if (windowCnt[rightChar] == cntStrT[rightChar]) {
                matchCnt++;
            }

            //  KeyPoint 回顾代码
            //  1.注意逻辑操作
            //  2.注意逻辑关系[先后，内外]

            // KeyPoint 整体 while 循环在外面
            // 判断 matchCnt 和 uniqueCnt
            // left 和 right 可以取等，两指针在同一个位置，此时只要一个字符也是可以的
            while (left <= right && matchCnt == uniqueCnt) {
                // 更新 res
                if (res[0] == -1 || right - left + 1 < res[0]) {
                    res[0] = right - left + 1;
                    res[1] = left;
                    res[2] = right;
                }

                char leftChar = s.charAt(left);
                // 将 leftChar 移出窗口
                windowCnt[leftChar]--;

                // 针对 leftChar 字符，比较过程都是 windowCnt 和 cntStrT，并且是严格 < 的关系
                if (windowCnt[leftChar] < cntStrT[leftChar]) {
                    matchCnt--;
                }
                // 更新 left
                left++;
            }
            // 更新 right
            right++;
        }
        // 可能结果并存在，故不能直接返回，得需要判断再去返回
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);

        // KeyPoint debug 调试
        // 1.断点 + 打印输出 结合使用
        // 2.通过跳过断点方式，提高 debug 效率
    }
}
