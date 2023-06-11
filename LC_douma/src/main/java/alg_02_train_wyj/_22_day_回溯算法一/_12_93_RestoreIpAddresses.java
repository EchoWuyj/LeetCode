package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 19:49
 * @Version 1.0
 */
public class _12_93_RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return res;
        }
        dfs(s, 0, "", 0, res);
        return res;
    }

    private void dfs(String s, int index, String restored, int count, List<String> res) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            res.add(restored);
            return;
        }
        for (int segmentLength = 1; segmentLength <= 3; segmentLength++) {
            if (index + segmentLength > s.length()) break;
            String segment = s.substring(index, index + segmentLength);
            if (!isValidIpSegment(segment)) continue;
            String suffix = count == 3 ? "" : ".";
            dfs(s, index + segmentLength,
                    restored + segment + suffix, count + 1, res);
        }
    }

    private boolean isValidIpSegment(String segment) {
        int length = segment.length();
        if (length > 3) return false;
        return (segment.charAt(0) == '0') ?
                (length == 1) : (Integer.parseInt(segment) <= 255);
    }
}
