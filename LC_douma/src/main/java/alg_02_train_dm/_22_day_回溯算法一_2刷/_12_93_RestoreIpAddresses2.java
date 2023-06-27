package alg_02_train_dm._22_day_回溯算法一_2刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-25 21:14
 * @Version 1.0
 */
public class _12_93_RestoreIpAddresses2 {

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty())
            return res;
        dfs1(s, 0, new StringBuilder(), 0, res);
        return res;
    }

    // 使用 StringBuilder 拼接字符串
    private static void dfs1(String s, int index, StringBuilder ip, int count, List<String> res) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            String ipStr = ip.toString();
            res.add(ipStr.substring(0, ipStr.length() - 1));
            return;
        }

        for (int segmentLen = 1; segmentLen < 4; segmentLen++) {
            if (index + segmentLen > s.length()) break;
            String segment = s.substring(index, index + segmentLen);
            if (!isValidIpSegment(segment)) continue;
            // 最开始ip 是 ""，故先加 segment，再加 "."，两者顺序不要弄反了
            ip.append(segment).append(".");
            dfs1(s, index + segmentLen, ip, count + 1, res);
            ip.delete(ip.length() - segmentLen - 1, ip.length());
        }
    }

    private static boolean isValidIpSegment(String segment) {
        int len = segment.length();
        if (len > 3) return false;
        return (segment.charAt(0) == '0') ?
                (len == 1) : (Integer.parseInt(segment) <= 255);
    }
}
