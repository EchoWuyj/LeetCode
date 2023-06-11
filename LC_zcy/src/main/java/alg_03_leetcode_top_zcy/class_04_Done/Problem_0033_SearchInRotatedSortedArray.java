package alg_03_leetcode_top_zcy.class_04_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-17 17:21
 * @Version 1.0
 */
public class Problem_0033_SearchInRotatedSortedArray {
    /*
        [0,1,2,3,4,5,6,7] 数组中的值互不相同
        数组经过旋转(将部分前面连续的元素移动到后面),如:将0,1,2移动到3,4,5,6,7后面
        [3,4,5,6,7,|0,1,2]

        KeyPoint 该数组的特点
             1) 两部分数组都有序
             2) A数组的开头>=B数组的结尾

        给你旋转后的数组nums和一个整数target,如果nums中存在这个目标值target,则返回它的下标,否则返回-1
        注意:该数组可能旋转过(不告诉你旋转范围),也有可能没有旋转,你必须设计一个时间复杂度为O(logN)的算法解决此问题

        KeyPoint 考察点:条件分类,如何将条件分类最好?
                   在条件分类的基础上,能二分就二分
        结论:在arr[L...R]上找num(这里arr是两个有序数组的组合)
            中点的值获取[M],只要[L],[M],[R]三个数不是都相等,就可以不等范围上([L][M],[M][R],[L][R])使用二分
            若[L][M][R]都相等,则无法二分
     */
    public int search(int[] nums, int target) {
        // KeyPoint 以后定义变量最好不要使用大写字母,写代码不方便
        int L = 0;
        int R = nums.length - 1;
        int M = 0;

        // 只要在数组范围搜索没搜到就会退出while循环,返回-1
        while (L <= R) {

            // mid=low+（high-low）/2
            // 这个写法是更加合适的,因为在两个数都很大的情况下,low+high有存在数组越界的可能性,这种写法则不会越界
            M = (L + R) / 2;

            // while循环执行到最后,L和R重合之后,如果该元素是target
            // (L+R)/2=L/R,即为需要找的点,否则L和R错过,表示在该数组没有找到target
            if (nums[M] == target) {
                return M;
            }

            // 经过上个if判断,则暗含arr[M] != target

            // KeyPoint 情况一:[L][M][R]完全一样的情况
            // 因为nums[L]==nums[M]==nums[R] ,且这3个数都是不等于num的
            // 此时没法直接二分的,因为target位置可以在数组中任意变化
            // [2 2 2 2 2 1 2]  target = 1
            //  L     M     R
            // [2 2 2 2 1 2 2]  target = 1
            //  L     M     R
            // 故先进行预处理,有两种情况
            //     1) 若L不停往下移动,若下个位置还是2,则继续移动,直到下个位置不是2,则在该位置到数组末尾上使用二分
            //          [2 3 1 2 2 2 2]  target = 1
            //             L   M     R
            //        在[3...2]上使用二分
            //     2) 若L不停往下移动,若下个位置还是2,则继续移动,若L移动到M位置,则在M+1位置到数组末尾上使用二分
            //          [2 2 2 2 1 2 2]  target = 1
            //           L     M     R
            if (nums[L] == nums[M] && nums[M] == nums[R]) {
                while (L != M && nums[L] == nums[M]) {
                    L++;
                }
                // L撞上M,则说明L到M都是一种数,且不是target
                if (L == M) {
                    L = M + 1;
                    // 再去执行二分的过程,即再去执行while循环
                    continue;
                }

                // L和M没撞上,[L]!=[M],则跳出while循环,执行下面的if判断
            }

            // KeyPoint 情况二:[L][M][R]不都一样的情况
            // arr[M] != target为前提
            // 若[L]!=[M]
            //    [3 1 2 2 2 2]  target = 1
            //     L   M     R
            if (nums[L] != nums[M]) {
                if (nums[M] > nums[L]) {
                    // KeyPoint 核心:判断数组中那一段是有序的
                    // [M] > [L]
                    //  5     3
                    // 说明数组旋转过程的断点在该M到R区域中,则M以左是有序的
                    // 12|3456 -> 3 4 5 6 1 2
                    //            L   M     R
                    // 因为右侧已经有序,判断target是否在有序区[L,M]内,如果在该范围上,则去右侧进行二分
                    // 否则则去左侧进行二分
                    // 注意: nums[M]已经!=target,所以这里是严格小于的关系
                    if (target >= nums[L] && target < nums[M]) {
                        R = M - 1;
                    } else {
                        L = M + 1;
                    }
                } else {
                    // [L] > [M]
                    //  5     1
                    // 说明数组旋转过程的断点在该L到M区域中,则M以右是有序的
                    // 1234|56 -> 5 6 | 1 2 3 4
                    //            L     M     R
                    // 因为右侧已经有序,判断target是否在有序区[M,R]内,如果在该范围上,则去右侧进行二分
                    // 否则则去左侧进行二分
                    // 注意: nums[M]已经!=target,所以这里是严格大于的关系
                    if (target > nums[M] && target <= nums[R]) {
                        L = M + 1;
                    } else {
                        R = M - 1;
                    }
                }
            } else {
                // 若[L]==[M]
                // 且[L][M][R]不都一样,则[M]!=[R]
                // 在此基础上进行细分
                if (nums[M] < nums[R]) {

                    if (target > nums[M] && target <= nums[R]) {
                        L = M + 1;
                    } else {
                        R = M - 1;
                    }
                } else {
                    // [L,M]之间是有序的
                    if (target >= nums[L] && target < nums[M]) {
                        R = M - 1;
                    } else {
                        L = M + 1;
                    }
                }
            }
        }
        return -1;
    }
}
