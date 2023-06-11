package algorithm._06_hashmap;

import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-03-17 18:34
 * @Version 1.0
 */
public class LeetCode_128_LongestConsecutiveSequence {
    //方法一:暴力法
    public int longestConsecutiveSequence01(int[] nums) {
        //定义一个变量,保存当前最长连续序列的长度
        int maxLength = 0;

        //遍历数组,以每个元素作为起点,寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            //保存当前元素作为起始点
            int currNum = nums[i];
            //保存当前连续序列长度
            int currLength = 1;

            //寻找后续数字,组成连续序列
            //一直不停地寻找,所以不能是使用if进行单次判断,需要使用while进行循环判断
            while (contains(nums, currNum + 1)) {
                currLength++;
                currNum++;
            }
            //判断当前连续序列长度是否为最大
            maxLength = Math.max(currLength, maxLength);
        }
        return maxLength;
    }

    //定义一个方法,用于在数组中寻找某个元素
    public boolean contains(int[] nums, int x) {
        for (int num : nums) {
            if (num == x) {
                return true;
            }
        }
        return false;
    }

    //方法二:利用哈希表改进
    public int longestConsecutiveSequence02(int[] nums) {
        //定义一个变量,保存当前最长连续序列的长度
        int maxLength = 0;

        //定义一个HashSet,保存所有出现的数值
        HashSet<Integer> hashSet = new HashSet<>();

        //KeyPoint HashSet不保存kv对,只是保存一个值(通过去重实现),判断是否在里面即可
        //1.遍历所有元素,保存到HashSet
        for (int num : nums) {
            hashSet.add(num);
        }

        //2.遍历数组,以每个元素作为起始点,寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            //保存当前元素作为起始点
            int currNum = nums[i];
            //保存当前连续序列长度
            int currLength = 1;

            //寻找后续数字,组成连续序列
            while (hashSet.contains(currNum + 1)) {
                currLength++;
                currNum++;
            }

            //判断当前连续序列长度是否为最大
            maxLength = Math.max(currLength, maxLength);
        }
        return maxLength;
    }

    //方法三:进一步改进
    //KeyPoint 算法的改进空间,在于之前的算法是否存在重复做的操作,对那些重复性的操作继续优化
    public int longestConsecutiveSequence03(int[] nums) {
        //定义一个变量,保存当前最长连续序列的长度
        int maxLength = 0;

        //定义一个HashSet,保存所有出现的数值
        HashSet<Integer> hashSet = new HashSet<>();

        //1. 遍历所有元素,保存到HashSet
        for (int num : nums) {
            hashSet.add(num);
        }

        //2. 遍历数组,以每个元素作为起始点,寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            //保存当前元素作为起始点
            int currNum = nums[i];
            //保存当前连续序列长度
            int currLength = 1;

            //判断:只有当前元素的前驱不存在的情况下,才去进行寻找连续序列的操作
            //通过这样的方式
            if (!hashSet.contains(currNum - 1)) {
                //寻找后续数字,组成连续序列
                while (hashSet.contains(currNum + 1)) {
                    currLength++;
                    currNum++;
                }
                //判断当前连续序列长度是否为最大
                maxLength = Math.max(currLength, maxLength);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        LeetCode_128_LongestConsecutiveSequence longestConsecutiveSequence = new LeetCode_128_LongestConsecutiveSequence();
        System.out.println(longestConsecutiveSequence.longestConsecutiveSequence03(nums1));
        System.out.println(longestConsecutiveSequence.longestConsecutiveSequence03(nums2));
    }
}
