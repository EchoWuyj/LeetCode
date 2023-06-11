package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 18:27
 * @Version 1.0
 */
public class _12_93_RestoreIpAddresses {

    /*
        有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0）
        整数之间用 '.' 分隔。

        例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
        但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。

        给定一个只包含数字的字符串 s ，用以表示一个 IP 地址
        返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
        你 不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。

        输入：s = "25525511135"
        输出：["255.255.11.135","255.255.111.35"]

        输入：s = "0000"
        输出：["0.0.0.0"]

        输入：s = "101023"
        输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

     */

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty())
            return res;
        restoreIp(s, 0, "", 0, res);
        return res;
    }

    // index 字符串切分位置索引
    // count 记录 ip 段个数
    private void restoreIp(String s, int index, String restored, int count, List<String> res) {

        // 通过 count 来限制 ip 段的个数
        if (count > 4) return;
        // index == s.length() 越界，表示已经遍历完 String s 了
        if (count == 4 && index == s.length()) {
            res.add(restored);
            return;
        }
        // 每个 ip segment 的长度，最短为 1 个字符，最长为 3 个字符，即 0-255
        for (int segmentLen = 1; segmentLen < 4; segmentLen++) {
            // index 在不断后移过程中，每个 segmentLen 都进行 1~4 判断
            // 可能出现索引越界，所以需要 if 判断。若出现越界，则跳出循环
            // KeyPoint 凡是涉及 >，>=，<，<= 仔细考虑边界情况
            if (index + segmentLen > s.length()) break;
            // index 为最后一位，segmentLen = 1，则 index, index + segmentLen 没有越界
            // 故上面的 if 判断是不能包括等于的，所以只需要 > 即可
            String segment = s.substring(index, index + segmentLen);
            // 判断 ip 是否合法，不合法直接跳过
            if (!isValidIpSegment(segment)) continue;
            // count 从 0 开始，若 count = 3，则表示最后一个 ip 段，后面不用加上 "."
            String suffix = count == 3 ? "" : ".";
            // 更新 index，restored，count
            // 每一段 ip 的最后是以 suffix 结尾，后续再拼接时接着前面部分
            restoreIp(s, index + segmentLen, restored + segment + suffix, count + 1, res);
        }
    }

    // 判断 ip segment 是否为合法 => 单独功能抽取 => 解耦
    // 判断 ip segment 长度， 前导 0，255 范围
    private boolean isValidIpSegment(String segment) {
        // 长度不能大于 3
        int len = segment.length();
        if (len > 3) return false;

        // 1. ip 段如果是以 0 开始的话，那么这个 ip 段只能是 0
        // 2. ip 段需要小于等于 255
        return (segment.charAt(0) == '0') ?
                (len == 1) : (Integer.parseInt(segment) <= 255);
    }

    public static void main(String[] args) {
        System.out.println(new _12_93_RestoreIpAddresses().restoreIpAddresses("101023"));
        // [1.0.10.23, 1.0.102.3, 10.1.0.23, 10.10.2.3, 101.0.2.3]
    }
}
