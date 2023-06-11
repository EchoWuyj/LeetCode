package alg_03_leetcode_top_wyj.class_10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-27 15:26
 * @Version 1.0
 */
public class Problem_0078_Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        if (nums.length < 0 || nums == null) {
            return null;
        }

        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        process(nums, 0, path, ans);
        return ans;
    }

    private static void process(int[] nums, int index, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(copy(path));
        } else {
            process(nums, index + 1, path, ans);
            path.addLast(nums[index]);
            process(nums, index + 1, path, ans);
            path.removeLast();
        }
    }

    public static ArrayList<Integer> copy(LinkedList<Integer> path) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (Integer i : path) {
            temp.add(i);
        }
        return temp;
    }
}
