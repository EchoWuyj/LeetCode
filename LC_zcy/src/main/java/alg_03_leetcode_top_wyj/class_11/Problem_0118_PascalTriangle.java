package alg_03_leetcode_top_wyj.class_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 16:48
 * @Version 1.0
 */
public class Problem_0118_PascalTriangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) {
            return ans;
        }

        for (int i = 0; i < numRows; i++) {
            ans.add(new ArrayList<Integer>());
            ans.get(i).add(1);
        }

        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < i; j++) {
                ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }
            ans.get(i).add(1);
        }
        return ans;
    }
}
