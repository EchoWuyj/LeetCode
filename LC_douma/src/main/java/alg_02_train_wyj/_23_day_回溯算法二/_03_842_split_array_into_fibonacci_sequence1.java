package alg_02_train_wyj._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-26 14:47
 * @Version 1.0
 */
public class _03_842_split_array_into_fibonacci_sequence1 {
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        dfs(num, 0, new ArrayList<>(), res);
        return res;
    }

    // dfs 若出现超出时间限制 => 需要剪枝
    public void dfs(String num, int index, List<Integer> path, List<Integer> res) {
        if (index == num.length()) {
            if (path.size() <= 2) return;
            for (int i = 2; i < path.size(); i++) {
                if (path.get(i) != path.get(i - 1) + path.get(i - 2)) return;
            }
            // 集合添加集合
            if (res.isEmpty()) {
                res.addAll(path);
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {
            String subStr = num.substring(index, i + 1);
            if (subStr.charAt(0) == '0' && subStr.length() != 1) continue;


            // java.lang.NumberFormatException: For input string: "5511816597"
            // Integer.MAX_VALUE 2147483647
            try {
                path.add(Integer.parseInt(subStr));
            } catch (Exception e) {
                continue;
            }
            dfs(num, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }


}
