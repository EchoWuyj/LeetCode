package algorithm._01_arrays.atguigu;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-02-25 14:53
 * @Version 1.0
 */
public class LeetCode_01_TwoSum {
    // KeyPoint 方法一:暴力法,穷举所有两数组合
    //  基本思路:把所有数、两两组合在一起,计算它们的和,如果是target,就输出
    public int[] twoSum01(int[] nums, int target) {
        int n = nums.length;
        //  双重for循环
        //  索引为n-1的元素后面就没有元素了,即索引下表为n-1元素不用考虑
        for (int i = 0; i < n - 1; i++) {
            // 从i后面一个元素开始
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    // 记得是new一个数组
                    return new int[]{i, j};
                }
            }
        }
        // 如果找不到,抛出异常(非法的参数异常)
        throw new IllegalArgumentException("no solution");
    }

    // KeyPoint 方法二:哈希表保存所有数的信息
    //  基本思路:在第一次迭代中,我们将每个元素的值和它的索引添加到表中；
    //  然后,在第二次迭代中,我们将检查每个元素所对应的目标元素 (target-nums[i]) 是否存在于表中。
    public int[] twoSum02(int[] nums, int target) {
        int n = nums.length;
        // 定义一个哈希表,保存数值和索引
        HashMap<Integer, Integer> map = new HashMap<>();

        // 1. 遍历数组,将数据全部保存入hash表
        for (int i = 0; i < n; i++) {
            // put操作,按照当前key的hash值,找到对应的位置,将值写入进去,时间复杂度O(1)
            map.put(nums[i], i);
        }

        for (int i = 0; i < n; i++) {
            // 需要查找的数,遍历找的是第一出现的值,但是map.put保存的是后面一次的索引
            int thatNum = target - nums[i];
            // 如果那个数存在,并且不是当前数自身(6-3=3),就直接返回结果
            if (map.containsKey(thatNum) && map.get(thatNum) != i) {
                return new int[]{i, map.get(thatNum)};
            }
        }

        //  如果找不到,抛出异常
        throw new IllegalArgumentException("no solution");
    }

    // KeyPoint 方法三:改进,遍历一次哈希表
    //  基本思路:在上述算法中,我们对哈希表进行了两次扫描,这其实是不必要的。
    //  在进行迭代并将元素插入到表中的同时,我们可以直接检查表中是否已经存在当前元素所对应的目标元素。
    //  如果它存在,那我们已经找到了对应解,并立即将其返回。这样,只需要扫描一次哈希表,就可以完成算法了。
    public int[] twoSum03(int[] nums, int target) {
        int n = nums.length;

        //  定义一个哈希表
        HashMap<Integer, Integer> map = new HashMap<>();

        //  遍历数组中的元素,寻找每个数对应的那个数是否存在（只向前寻找）
        for (int i = 0; i < n; i++) {
            int thatNum = target - nums[i];
            //  如果那个数存在,并且不是当前数自身,就直接返回结果
            if (map.containsKey(thatNum)) {
                // 索引顺序需要修改
                // 刚开始map中是没有元素的,故先是将数组中元素存入map中
                // 之后再去通过for循环后面的nums[i]元素向前寻找满足的元素,同时其索引下标值为小的值
                return new int[]{map.get(thatNum), i};
            }
            // 没有找到那个数,则将值存入map中
            map.put(nums[i], i);
        }

        //  如果找不到,抛出异常
        throw new IllegalArgumentException("no solution");
    }

    public static void main(String[] args) {
        // 测试用例
        int[] input = {2, 7, 11, 15};
        int[] input2 = {3, 1, 3};
        int target = 9;
        int target2 = 6;

        // 定义一个大数组,进行性能测试
        int[] input3 = new int[1000000];
        for (int i = 0; i < input3.length; i++) {
            input3[i] = input3.length - i;
        }

        int target3 = 567890;

        // 为了计算程序运行时间,开始计算和计算完成分别计时
        long startTime = System.currentTimeMillis();
        LeetCode_01_TwoSum twoSum = new LeetCode_01_TwoSum();
        int[] output = twoSum.twoSum01(input3, target3);
        long endTime = System.currentTimeMillis();

        System.out.println("程序运行时间:" + (endTime - startTime) + "ms");

        for (int index : output) {
            System.out.print(index + "\t");
        }
    }
}
