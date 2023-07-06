package alg_02_train_dm._22_day_回溯算法一_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 18:27
 * @Version 1.0
 */
public class _12_93_RestoreIpAddresses1 {

    /*
        93. 复原 IP 地址
        有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0）
        整数之间用 '.' 分隔。

        例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
        但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。

        给定一个 只包含数字的字符串 s ，用以表示一个 IP 地址
        返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
        你 不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。

        输入：s = "25525511135"
        输出：["255.255.11.135","255.255.111.35"]

        输入：s = "0000"
        输出：["0.0.0.0"]

        输入：s = "101023"
        输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

        提示：
        1 <= s.length <= 20
        s 仅由数字组成
     */

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty())
            return res;
        // count 从 0 开始，分析调用 dfs 时，count = 1 是否分好 segment，
        // 若分好，则 count = 4，说明 4 个 segment 都分好段了
        dfs(s, 0, "", 0, res);

        return res;
    }

    // index 字符串切分位置索引
    // count 记录 ip 段个数
    private static void dfs(String s, int index, String ip, int count, List<String> res) {

        // 通过 count 来限制 ip 段的个数
        if (count > 4) return;
        // index == s.length() 越界，表示已经遍历完 String s 了
        if (count == 4 && index == s.length()) {
            res.add(ip);
            return;
        }

        // 关键：如何抽象成树形结构 => 朝着这个方向思考
        // 对 ip segment 划分其长度，最短为 1 个字符，最长为 3 个字符，即 0-255
        for (int segmentLen = 1; segmentLen < 4; segmentLen++) {

            // 1.每个 segmentLen 都进行 1-3 长度穷举，需要保证 index + segmentLen 不越界
            //   这样 s 截取时才不会出错，故需要 if 判。若出现越界，则跳出循环。
            // 2.凡是涉及边界条件判断，务必需要仔细考虑，如：> 或者 >=，< 或者 <=
            //   substring 操作取不到 index + segmentLen，故 index + segmentLen 可以取等 s.length()
            if (index + segmentLen > s.length()) break;

            // 截取 segment
            String segment = s.substring(index, index + segmentLen);

            // 判断 ip 是否合法，不合法直接跳过
            if (!isValidIpSegment(segment)) continue;

            // count 从 0 开始，若 count = 3，则表示最后一个 ip 段，后面不用加上 "."
            String suffix = count == 3 ? "" : ".";

            // 更新 index，ip，count 注意几点细节
            // 1.不是 index + 1，而是 index + segmentLen
            // 2.ip + segment + suffix 三者拼接，不是单个 ip，且是以 suffix 结尾
            dfs(s, index + segmentLen, ip + segment + suffix, count + 1, res);
        }
    }

    // 判断 ip segment 是否为合法 => 单独功能抽取 => 解耦
    // 1.判断 ip segment 长度
    // 2.判断 前导 0
    // 3.判断 255 范围
    private static boolean isValidIpSegment(String segment) {
        // 长度不能大于 3
        int len = segment.length();
        if (len > 3) return false;

        // 1.ip 段如果是以 0 开始的话，那么这个 ip 段只能是单个 0，不能是前导 0
        // 2.ip 段需要小于等于 255
        return (segment.charAt(0) == '0') ?
                (len == 1) : (Integer.parseInt(segment) <= 255);
    }



    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("101023"));

        // 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

        // 输入：s = "101023"
        // 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

    }
}
