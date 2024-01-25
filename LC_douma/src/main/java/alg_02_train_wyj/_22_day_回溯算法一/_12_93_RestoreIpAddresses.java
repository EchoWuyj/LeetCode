package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 19:49
 * @Version 1.0
 */
public class _12_93_RestoreIpAddresses {
    private List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty()) return res;
        dfs(s, 0, "", 0, res);
        return res;
    }

    public void dfs(String s, int index, String ip, int count, List<String> res) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            res.add(ip);
            return;
        }

        for (int i = 1; i < 4; i++) {
            if (index + i > s.length()) break;
            String segment = s.substring(index, index + i);
            if (!isValid(segment)) continue;
            String prefix = count == 3 ? "" : ".";
            dfs(s, index + i, ip + segment + prefix, count + 1, res);
        }
    }

    public boolean isValid(String str) {
        int n = str.length();
        if (n > 3) return false;
        return str.charAt(0) == '0' ? n == 1 : Integer.parseInt(str) <= 255;
    }
}
