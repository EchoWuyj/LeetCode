package alg_03_leetcode_top_zcy.class_04_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-17 9:44
 * @Version 1.0
 */
public class Problem_0026_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {

        // KeyPoint 方法一
        // [1,1,2,2,2,3,4,4,4] 在原数组上操作将所有不同的数字放到前面
        // [1,2,3,4,...] 右边剩下的元素不用管
        // 思路:
        // 1) 第一个数留着,使用两个指针,第一个指针done,表示已经放好的区域的最后一个数
        //                            第二个指针cur,表示正在处理的数
        //    [ 1,  1,2,2,2,3,4,4,4]
        //    done cur

        // 2) cur若和done指针指的数一样,则cur后移
        //    [ 1,  1,2,2,2,3,4,4,4]
        //    done   cur

        // 3) cur若和done指针指的数不一样,则将cur数赋值到done指针下个位置的数,done指针后移1位,cur后移1位
        //    [ 1,  2,  1, 2, 2,3,4,4,4]
        //        done    cur
        //
        // 4) 即done指针之前的区域为放好的区域,cur为当前来到区域,done与cur之间是垃圾区域
        //    依次循环,直到cur指针到数据最后越界的位置
        //    本质:cur遇到有用的数,将其放到有效区里面,让垃圾区推着cur往前走

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length < 2) {
            return 1;
        }

        int done = 0;
        // cur若和done指针指的数一样,则cur后移
        // 注意:cur后移是for循环中体现
        for (int cur = 1; cur < nums.length; cur++) {
            if (nums[cur] != nums[done]) {
                // 赋操操作 int a = 3; 让 a 的值为3
                // nums[++done] 实现了两步操作
                //   1)nums[++done]确定done指针下个位置的数
                //   2)++done也实现了自增
                nums[++done] = nums[cur];
            }
        }

        // 题目要求返回长度,故需要索引+1
        return done + 1;
    }

    public int removeDuplicates2(int[] nums) {

        // KeyPoint 方法二
        // [ 1, 1,2,2,2,3,4,4,4]
        // index
        //   i
        // 1) i和左边的数不一样,则该i位置的元素是需要的,将该位置的数copy到index位置,i++,
        //    同时index++,因为index左边的东西是放好的区域,也是要随着不断扩大的
        // 2) i和左边的数一样,则该i位置的元素是不需要的,i++,index不变

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length < 2) {
            return 1;
        }

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // i==0是第1个元素位置,是符合条件的
            //
            if (i == 0 || nums[i - 1] != nums[i]) {
                nums[index++] = nums[i];
            }
        }

        // 因为index++最后总是会自增1,正好是索引+1变成长度
        return index;
    }
}
