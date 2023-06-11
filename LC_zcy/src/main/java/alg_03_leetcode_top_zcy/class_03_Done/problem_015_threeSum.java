package alg_03_leetcode_top_zcy.class_03_Done;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-15 21:57
 * @Version 1.0
 */

// 三数之和
public class problem_015_threeSum {
    public List<List<Integer>> threeSum(int[] nums) {

        /*
            两数之和,保证为k,且要保证二元组不重复
            无序 [3,-2,0,4,2,-1] (使用HashMap来处理是之前的做法)

            KeyPoint 双指针
            思路:先对数组排序,使用双指针L,R从两端往中间移动
            有序 [-2,-1,0,2,3,4]

            [L]+[R]=res
            1) res<k L++
            2) res>k R--
            3) res=k 收集,L++
               L++为了后续判断,同时收集时需要保证L数不等于左边L-1位置的数,目的为了避免收集的结果重复
               例如:[-1,-1,-1,4,4]  k=3
                      L          R
                         L       R
                    -1+4=3   [-1,4]
                    -1+4=3   [-1,4]
               因而这两个二元组是重复的
         */


        /*
            三数之和,保证为k,且要保证三元组不重复
            若是无序数组则先排序,得到的结果如下
            有序 [-3,-3,-2,-1,-1,0,0,1,1,1]  k=5

             一个指针p从左往右移动
             [-3,-3,-2,-1,-1,0,0,1,1,1]
               p

             将p位置的数作为三元组的第一个数(-3,?,?),则等价于在剩余数组中找累加和为8的二元组(-3+?=5,?=8)
             同样p到一个位置,若该数和上个数字一样,直接跳过,以此类推

              [-3,-3,-2,-1,-1,0,0,1,1,1]
                      p
         */

        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);

        // 第一个数选了i位置的数,i到倒数第3个数就已经停止了,留够两个数找二元组
        for (int i = 0; i < nums.length - 2; i++) {
            // i == 0 || nums[i - 1] != nums[i]
            // 执行的逻辑:先判断i=0的情况,在i!=0的情况下再去判断nums[i - 1] != nums[i]
            // KeyPoint 注意一定是nums[i-1]!=nums[i]
            if (i == 0 || nums[i - 1] != nums[i]) {
                // target + nums[i] =0, target = -nums[i]
                List<List<Integer>> nexts = twoSum(nums, i + 1, -nums[i]);
                // 在返回结果的二元组中,在其0位置添加num[i]元素
                for (List<Integer> cur : nexts) {
                    cur.add(0, nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
        // KeyPoint 时间复杂度 O(N^2) 数据集10^5 代码也是能通过的,但是效率不高
        //      执行用时36ms,在所有Java提交中击败了20.78%的用户

    }

    // nums已经有序了
    // nums[begin ...]范围上,找到累加和为target的所有二元组
    public List<List<Integer>> twoSum(int[] nums, int begin, int target) {

        int L = begin;
        int R = nums.length - 1;
        List<List<Integer>> ans = new ArrayList<>();

        // 题目要求i,j,k互不相等
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                // L==begin则认为L为开始位置,左边没有数,则收集答案
                // [-1,-1,-1,4,4]
                //  L          R
                // 最开始-1需要,但后面的-1就不需要了,避免重复
                // KeyPoint ||后的表达式是在L!=begin下执行
                if (L == begin || nums[L - 1] != nums[L]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[L]);
                    cur.add(nums[R]);
                    ans.add(cur);
                }
                // L还是需要移动的
                L++;
            }
        }
        return ans;
    }

    /*
      KeyPoint 优化常数时间,有空可以看
      存在问题:ArrayList 0位置插入元素,性能不好
      如:-3[-1,4]
      将-3放入[-1,4]列表中,这种方式不太好
      因为ArrayList中,将-3插入到0位置,代价是很高的,使用LinkedList插入没有什么代价,
      但是使用LinkedList,不知道程序是怎么调用验证的方法,若使用get(0),get(1)的方式,还是会拖慢时间

      推荐做法:倒着来
      [.....5] 从右侧5开始,找对应的二元组[-7,2],再将5加入[-7,2]的最后,这样比加到开头要好,[-7,2,5]
      算法时间复杂度没有变化,优化了常数时间
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = N - 1; i > 1; i--) {
            if (i == N - 1 || nums[i] != nums[i + 1]) {
                List<List<Integer>> nexts = twoSum2(nums, i - 1, -nums[i]);
                for (List<Integer> cur : nexts) {
                    cur.add(nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> twoSum2(int[] nums, int end, int target) {
        int L = 0;
        int R = end;
        List<List<Integer>> ans = new ArrayList<>();
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                if (L == 0 || nums[L - 1] != nums[L]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[L]);
                    cur.add(nums[R]);
                    ans.add(cur);
                }
                L++;
            }
        }
        return ans;
    }
}
