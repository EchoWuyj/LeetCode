package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 1:27
 * @Version 1.0
 */
public class _42_93_restoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty()) return res;
        dfs(s, "", 0, 0, res);
        return res;
    }

    public void dfs(String s, String ip, int index, int count, List<String> res) {
        if (count > 4) return;
        // ip 地址 4 部分，使用 count 来计数
        if (count == 4 && index == s.length()) {
            res.add(ip);
            return;
        }

        // 拼接字符个数最多为 3，通过 i 来标记拼接的位置
        for (int i = 1; i < 4; i++) {
            // 超出长度，结束本轮循环，使用 break 后续循环不需要再去执行了
            if (index + i > s.length()) break;
            String segment = s.substring(index, index + i);
            // 对截取 segment 进行判断
            if (!isValid(segment)) continue;
            // 拼接部分：后缀，对应在 segment 的后面
            String suffix = count == 3 ? "" : ".";
            // 传入的 index，是基于之前的 index，故为 index + i，而不是单纯的 index
            dfs(s, ip + segment + suffix, index + i, count + 1, res);
        }
    }

    public boolean isValid(String str) {
        int n = str.length();
        if (n > 3) return false;
        return str.charAt(0) == '0' ? n == 1 : Integer.parseInt(str) <= 255;
    }
}
