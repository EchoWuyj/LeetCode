package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 19:49
 * @Version 1.0
 */
public class _12_93_RestoreIpAddresses {
    private static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        dfs(s, 0, "", 0, res);
        return res;
    }

    private static void dfs(String s, int index, String ip, int count, List<String> res) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            res.add(ip);
            // System.out.println("res");
            return;
        }
        for (int segmentLen = 1; segmentLen < 4; segmentLen++) {
            if (index + segmentLen > s.length()) break;
            String segment = s.substring(index, index + segmentLen);
            if (!isValid(segment)) continue;
            String suffix = count == 3 ? "" : ".";
            dfs(s, index + segmentLen, ip + segment + suffix, count + 1, res);
        }
    }

    private static boolean isValid(String segment) {
        int len = segment.length();
        if (len > 3) return false;
        return segment.charAt(0) == '0' ? len == 1 : (Integer.parseInt(segment) <= 255);
    }
}
