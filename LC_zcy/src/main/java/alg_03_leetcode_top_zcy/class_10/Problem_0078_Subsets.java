package alg_03_leetcode_top_zcy.class_10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-27 11:45
 * @Version 1.0
 */

// 子集
public class Problem_0078_Subsets {

    /*
        给你一个整数数组nums,数组中的元素互不相同,返回该数组所有可能的子集(幂集)
        解集不能包含重复的子集.你可以按任意顺序返回解集

        输入:nums=[1,2,3]
        输出:[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

        KeyPoint 本质:考察递归怎么写 => 刷题越多,就会发现大部分是重温经典
                                    => 将过往经典的题目掌握之后,再去结合新的题目将其练熟

        KeyPoint 本质:生成所有子序列(递归实现)

        数值  3 1 5
        索引  0 1 2
                                 3
                            ×      √
                              1
                         ×      √ [1]
                         5          5
                     ×[]  √[5]  ×[1,0] √[1,5]
     */

    public static List<List<Integer>> subsets(int[] nums) {
        // KeyPoint 返回值类型直接定义主函数要求的形式
        //          不要定义ArrayList<ArrayList<Integer>>,避免返回值出现问题
        //          反正使用List<List<Integer>>都是可以接受的
        List<List<Integer>> ans = new ArrayList<>();

        // KeyPoint path使用LinkedList,可以使用最后添加,最后删除,比较好操作
        LinkedList<Integer> path = new LinkedList<>();
        process(nums, 0, path, ans);
        return ans;
    }

    // 当前来到index位置做决定
    // 1)不要当前位置的数 => 如果不要当前位置的数,不把该数字放入到path中去
    // 2)要当前位置的数   => 如果要当前位置的数,则把该数字放入到path中去
    public static void process(int[] nums, int index, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(copy(path));
        } else {
            process(nums, index + 1, path, ans);
            // 加入结尾
            path.addLast(nums[index]);
            process(nums, index + 1, path, ans);
            // 清除现场(从结尾移除)
            path.removeLast();
            // KeyPoint 注意区别
            // process(str, index + 1, ans, path + str[index]),这种是在递归函数中拼接字符串,所以就没有涉及清除现场
            //  要是在递归代码之前进行的拼接操作,则递归操作后要清除现场
        }
    }

    public static ArrayList<Integer> copy(LinkedList<Integer> path) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer num : path) {
            ans.add(num);
        }
        return ans;
    }
}
