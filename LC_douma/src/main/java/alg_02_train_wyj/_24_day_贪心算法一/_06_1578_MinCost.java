package alg_02_train_wyj._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 15:59
 * @Version 1.0
 */
public class _06_1578_MinCost {
    public int minCost(String s, int[] cost) {
        int n = s.length();
        int res = 0;
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int maxCost = 0;
            int sumCost = 0;
            while (i < n && c == s.charAt(i)) {
                maxCost = Math.max(maxCost, cost[i]);
                sumCost += cost[i];
                i++;
            }
            res += (sumCost - maxCost);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "aaabaa";
        int[] cost = {1, 3, 2, 4, 1, 5};
        System.out.println(new _06_1578_MinCost().minCost(s, cost));
        //
    }
}
