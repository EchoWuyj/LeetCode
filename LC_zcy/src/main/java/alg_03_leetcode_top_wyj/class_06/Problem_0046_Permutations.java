package alg_03_leetcode_top_wyj.class_06;

import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-28 11:03
 * @Version 1.0
 */
public class Problem_0046_Permutations {

    // 方法一(推荐)
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0 || nums == null) {
            return ans;
        }
        process(nums, 0, ans);
        return ans;
    }

    public static void process(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            ans.add(list);
        } else {
            for (int i = index; i < nums.length; i++) {
                swap(nums, index, i);
                process(nums, index + 1, ans);
                swap(nums, index, i);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 方法二(暴力)
    public static List<List<Integer>> onClass(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<Integer> rest = new HashSet<>();
        for (int num : nums) {
            rest.add(num);
        }
        ArrayList<Integer> path = new ArrayList<>();
        process1(rest, path, ans);
        return ans;
    }

    public static void process1(HashSet<Integer> rest, ArrayList<Integer> path, List<List<Integer>> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            for (Integer num : rest) {
                ArrayList<Integer> curPath = new ArrayList<>(path);
                curPath.add(num);
                HashSet<Integer> clone = cloneExceptNum(rest, num);
                process1(clone, curPath, ans);
            }
        }
    }

    public static HashSet<Integer> cloneExceptNum(HashSet<Integer> rest, int num) {
        HashSet<Integer> clone = new HashSet<>(rest);
        clone.remove(num);
        return clone;
    }
}
