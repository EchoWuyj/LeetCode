package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-07-01 17:32
 * @Version 1.0
 */
public class _02_Note_BS_Summary {

    /*


        01_Basic_BinarySearch

                                           迭代 => 不断的在循环体中查找目标元素
          基本二分查找 => 唯一查找 target  ↗     => 以 nums[mid] 为中心
                                        ↘
                                         递归

        02_Advanced_BinarySearch

                进阶二分查找 (迭代) => 解决：有序数组中存在'重复元素'问题
                                  => 不断的在循环体中查找目标元素 => 以 nums[mid] 为中心

                1.查找：第一个'等于'目标元素的下标
                2.查找：最后一个'等于'目标元素的下标

                3.查找：第一个'大于等于'目标元素的下标
                4.查找：最后一个'小于等于'目标元素的下标

                注意：以上代码：以 nums[mid] 为中心


     */

    /*


      01_704_BinarySearch => 基本二分查找 (3种思路) => 唯一查找 target

         思路一：不断的在循环体中查找目标元素 => 以 nums[mid] 为中心

         思路二：在循环体中排除 一定不存在 目标元素的区间 => 以 target 为中心
                                                       => 重点掌握
                唯一查找 target
                1.target > nums[mid]
                  逼近方向：从右往左
                  唯一查找 target => if (nums[left] == target) return left;
                                     return -1;

                2.target < nums[mid]
                  逼近方向：从左往右
                  额外说明：计算 mid 得 +1
                  唯一查找 target => if (nums[left] == target) return left;
                                    return -1;

                推广(一)：第一个等于 和 最后一个等于
                 1.target > nums[mid]
                   逼近方向：从右往左
                   求'第一个'等于 target 元素 => if (nums[left] == target) return left;
                                                return -1;

                 2.target < nums[mid]
                   逼近方向：从左往右
                   求'最后一个'等于 target 元素 => if (nums[left] == target) return left;
                                                 return -1;


                推广(二)：第一个大于等于 和 最后一个小于等于
                 1.target > nums[mid]
                   逼近方向：从右往左 => target <= nums[mid]
                   求第一个大于等于 target 元素 => return left

                   ... i，i+1，target | i+2，i+3，i+4 ...
                                        ↑         ↑
                                       mid   ←   mid  从右往左逼近

                 2.target < nums[mid]
                   逼近方向：从左往右 => target >= nums[mid]
                   额外说明：计算 mid 得 +1
                   求最后一个小于等于 target 元素 => return left

                   ... i-1，i，i+1 | target，i+2，i+3，i+4 ...
                        ↑      ↑
         从左往右逼近   mid  →  mid


         思路三：保证 while 循环结束，有 两个元素在循环体外 进行处理 => 以 target 为中心
                                                                => 了解即可
                1.target > nums[mid]
                2.target < nums[mid]

     */
}
