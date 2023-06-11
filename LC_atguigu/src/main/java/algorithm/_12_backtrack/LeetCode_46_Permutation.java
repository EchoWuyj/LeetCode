package algorithm._12_backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-03-29 18:53
 * @Version 1.0
 */
public class LeetCode_46_Permutation {

    /*
    backtrack的公式:
    result = []
    def backtrack(路径, 选择列表):
        if 满足结束条件:
            result.add(路径)
            return

        for 选择 in 选择列表:
            做选择
            backtrack(路径, 选择列表)
            撤销选择
     */

    //回溯实现
    public List<List<Integer>> permute01(int[] nums) {
        //定义保存结果的List
        ArrayList<List<Integer>> result = new ArrayList<>();
        //用一个ArrayList保存一组解
        ArrayList<Integer> solution = new ArrayList<>();
        //从0位置开始填充数
        backtrack(nums, result, solution, 0);
        return result;
    }

    //定义一个辅助集合,保存已经用过的数
    private HashSet<Integer> filledNums = new HashSet<>();

    //实现一个回溯方法,方便递归调用
    private void backtrack(int[] nums, ArrayList<List<Integer>> result, ArrayList<Integer> solution, int i) {
        int n = nums.length;

        //首先判断退出递归调用的场景
        if (i >= n) {//数组是从0开始遍历,范围是0到n-1
            //solution是对象引用,不能直接add,而是需要copy进来
            result.add(new ArrayList<>(solution));
        } else {
            //需要对i位置选数填入,可选的数应该是由遍历数组中所有数而得,并取没有用过的数进行填充
            for (int j = 0; j < n; j++) {
                if (!filledNums.contains(nums[j])) {
                    //没有用过直接填入
                    solution.add(nums[j]);
                    //表示当前数已经使用过了
                    filledNums.add(nums[j]);

                    //递归调用,处理下一个位置
                    backtrack(nums, result, solution, i + 1);

                    //KeyPoint 回溯总结:
                    // 相当于选了某个数i之后的所有可能场景都已经考虑完了,并且放到result中
                    // 此时进行回溯,回到上一层,接下来继续遍历当前位置下个可能的数,再去考察不同的场景

                    //KeyPoint 注意回溯逻辑位置,是在if条件判断里面,而不是在if条件判断的外面进行状态回退

                    //回溯,回退状态
                    //当时修改了什么,现在就回退什么
                    solution.remove(i);//remove中给的是索引的,将之前填入的索引移除
                    filledNums.remove(nums[j]);//remove移除的是元素nums[j]
                }
            }
        }
        //代码结束的位置,即表示递归结束,递归结束之后,放回上层递归的入口继续执行后续的代码
        //此时,变量的值都是上层递归环境中的值,不要和下层递归环境的值相混淆
    }

    //空间优化
    public List<List<Integer>> permute02(int[] nums) {
        //定义保存结果的List
        ArrayList<List<Integer>> result = new ArrayList<>();
        //用一个ArrayList保存一组解
        ArrayList<Integer> solution = new ArrayList<>();

        //把nums复制到solution
        for (int num : nums)
            solution.add(num);

        //从0位置开始填充数
        backtrack(result, solution, 0);

        return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> solution, int i) {
        int n = solution.size();

        //首先判断退出递归调用的场景
        if (i >= n) {
            result.add(new ArrayList<>(solution));
        } else {
            //需要对i位置选数填入,遍历数组中所有数,取没有用过的数进行填充
            //i之前位置都已经填充完成,即只要从i开始遍历即可
            for (int j = i; j < n; j++) {
                //通过索引下标来交换元素位置
                Collections.swap(solution, i, j);
                //递归调用,处理后面的位置
                backtrack(result, solution, i + 1);
                //回溯
                Collections.swap(solution, i, j);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        LeetCode_46_Permutation permutation = new LeetCode_46_Permutation();
        List<List<Integer>> result = permutation.permute02(nums);

        for (List<Integer> solution : result) {
            for (Integer num : solution) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }
}
