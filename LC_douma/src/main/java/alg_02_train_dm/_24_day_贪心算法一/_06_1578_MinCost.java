package alg_02_train_dm._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 19:36
 * @Version 1.0
 */
public class _06_1578_MinCost {

    /*
        1578 避免重复字母的最小删除成本
        给你一个字符串 s 和一个整数数组 cost
        其中 cost[i] 表示从字符串 s 删除下标等于 i 的字符的代价
        返回使字符串'任意相邻'两个字母不相同的最小删除成本
        请注意，删除一个字符后，删除其他字符的成本不会改变

        输入：s = "abaac", cost = [1,2,3,4,5]
        输出：3

        a b a a c
        1 2 3 4 5
            a a 相邻，删除成本较小的 3 对应的 a

     */

    // 贪心策略：碰到相同字母的时候，我们贪心的删除'删除成本最小的字符'，也可以说保留'删除成本最大的字母'
    public int minCost(String s, int[] cost) {
        int res = 0;
        int len = s.length();
        int i = 0;
        // 遍历一遍数组
        while (i < len) {
            char c = s.charAt(i);
            int maxCost = 0;
            int sumCost = 0;
            // 相邻字符是相同字符，执行 while 循环中代码
            // 从新执行外层 while 循环，变量 maxCost 和 sumCost 都重新置为 0
            while (i < len && s.charAt(i) == c) {
                maxCost = Math.max(maxCost, cost[i]);
                sumCost += cost[i];
                i++;
            }
            // 累和成本 - 最大成本 = 删除需要的最小成本
            res += (sumCost - maxCost);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "aaabaa";
        int[] cost = {1, 3, 2, 4, 1, 5};
        System.out.println(new _06_1578_MinCost().minCost(s, cost));
        // 4
    }
}
