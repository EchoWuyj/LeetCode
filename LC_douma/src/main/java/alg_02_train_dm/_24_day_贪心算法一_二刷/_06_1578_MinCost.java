package alg_02_train_dm._24_day_贪心算法一_二刷;

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
        返回使字符串'任意相邻'两个字母不相同的 最小删除成本
        请注意，删除一个字符后，删除其他字符的成本不会改变

        输入：s = "abaac", cost = [1,2,3,4,5]
        输出：3
        解释：
        s：   a b a a c
        cost：1 2 3 4 5
        其中 a a 相邻，需要删除一个 a，删除成本较小的 3 对应的 a

        s：   a b   a c
        cost：1 2 3 4 5

        如：
        s：   a a b a a
        cost：1 2 3 4 1

        删除两个 a 字符

        s：     a b a
        cost：1 2 3 4 1

        提示：
        n == colors.length == neededTime.length
        1 <= n <= 105
        1 <= neededTime[i] <= 104
        colors 仅由小写英文字母组成

     */

    // KeyPoint 贪心策略：碰到相邻且相同字母时，我们贪心的删除'删除成本最小的字符'，也可以说保留'删除成本最大的字母'
    // 本质：逆向思维
    public int minCost(String s, int[] cost) {

        // s：    a a a b a a
        // cost： 1 3 2 4 1 5
        //          ↑       ↑  => 保留'删除成本最大的字母'

        int res = 0;
        int len = s.length();
        int i = 0;
        // 遍历一遍数组
        while (i < len) {
            char c = s.charAt(i);
            int maxCost = 0;
            int sumCost = 0;
            // 通过 while 循环 => 判断相邻字符是相同字符
            // 从新执行外层 while 循环，变量 maxCost 和 sumCost 都重新置为 0
            while (i < len && s.charAt(i) == c) {
                maxCost = Math.max(maxCost, cost[i]);
                sumCost += cost[i];
                i++;
            }
            // 相邻相同字符累和成本 - 相邻相同字符中最大成本 =  相邻相同字符删除需要的最小成本
            // res 只是统计删除成本
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
