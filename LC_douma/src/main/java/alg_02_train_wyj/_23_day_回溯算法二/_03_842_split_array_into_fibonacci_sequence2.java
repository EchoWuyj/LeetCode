package alg_02_train_wyj._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-26 15:59
 * @Version 1.0
 */
public class _03_842_split_array_into_fibonacci_sequence2 {
    public static List<Integer> splitIntoFibonacci(String num) {
        List<Integer> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        dfs(num, 0, 0, 0, res);
        return res;
    }

    public static boolean dfs(String num, int index, int preTwoNumSum, int preNum, List<Integer> res) {
        if (index == num.length()) {
            return res.size() >= 3;
        }
        long curLongNum = 0;
        for (int i = index; i < num.length(); i++) {
            if (i > index && num.charAt(index) == '0') continue;
            curLongNum = curLongNum * 10 + (num.charAt(i) - '0');
            if (curLongNum > Integer.MAX_VALUE) break;
            int curIntNum = (int) curLongNum;

            if (res.size() >= 2) {
                if (curIntNum < preTwoNumSum) {
                    continue;
                } else if (curIntNum > preTwoNumSum) {
                    break;
                }
            }

            res.add(curIntNum);
            if (dfs(num, i + 1, preNum + curIntNum, curIntNum, res)) return true;
            res.remove(res.size() - 1);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(splitIntoFibonacci("1101111"));
    }
}
