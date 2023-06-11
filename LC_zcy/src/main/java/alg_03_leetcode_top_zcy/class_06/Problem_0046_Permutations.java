package alg_03_leetcode_top_zcy.class_06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 11:51
 * @Version 1.0
 */
public class Problem_0046_Permutations {

    /*
           给定一个不含重复数字的数组nums,返回其所有可能的全排列
           你可以按任意顺序返回答案

           输入:nums=[1,2,3]
           输出:[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */

    //  KeyPoint 方法一(暴力)
    public static List<List<Integer>> onClass(int[] nums) {

        // KeyPoint 等号右侧直接写子类即可,不需要再加泛型,泛型在父类中已经申明好了
        List<List<Integer>> ans = new ArrayList<>();

        // rest中有剩余数字
        HashSet<Integer> rest = new HashSet<>();
        for (int num : nums) {
            rest.add(num);
        }
        // 选过的数字在path里
        ArrayList<Integer> path = new ArrayList<>();
        f(rest, path, ans);
        return ans;
    }

    // 将原数组nums中的数字已经加入rest中
    // 使用path来记录递归过程num[i]
    public static void f(HashSet<Integer> rest, ArrayList<Integer> path, List<List<Integer>> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            for (int num : rest) {
                // 克隆一份path(每次都是创建一个新path)
                ArrayList<Integer> curPath = new ArrayList<>(path);
                curPath.add(num);
                // 将rest中刚添加的num移除
                HashSet<Integer> clone = cloneExceptNum(rest, num);
                f(clone, curPath, ans);
            }
        }
    }

    public static HashSet<Integer> cloneExceptNum(HashSet<Integer> rest, int num) {
        // 克隆rest为clone
        HashSet<Integer> clone = new HashSet<>(rest);
        // 从clone移除num
        clone.remove(num);
        return clone;
    }

    // KeyPoint 方法二(在原数组上交换)
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(nums, 0, ans);
        return ans;
    }

    // 没有使用path记录,只是在nums上修改
    public static void process(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            // 每次到递归边界都临时创建一个ArrayList用来装收集好的nums
            // 保证每次创建的ArrayList都是新的,和之前没有关系.
            ArrayList<Integer> cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
            ans.add(cur);
        } else {
            // index不回退,保证不走回头路
            for (int j = index; j < nums.length; j++) {
                swap(nums, index, j);
                process(nums, index + 1, ans);
                swap(nums, index, j);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
