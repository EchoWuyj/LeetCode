package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 18:06
 * @Version 1.0
 */
public class _10_Note_quickSort_partition {
    /*
           KeyPoint 1.二路快排

                                       great pivot
                                        ↓   ↓
            9 11 13 8 10 20 22 77 45 46 33 34
            ↑             ↑                 ↑
           low           less              high
                         分区点

            处理过程中
            |  < pivot   |  > pivot  | 未处理 |
           low         less        great    high
                       分区点

            处理完
           low  <= pivot   |  > pivot   high


           => less 和 great => 快慢指针应用(常用)
           => 类似：238 移动零
           => 总结：一般这种题目都可以使用快慢指针
                    通过快慢指针，给数组分成不同区域，从而将问题解决

          原始数组
           fast
            ↓
            6 3 0 0 8
            ↑
           slow

          处理过程中
                fast
                 ↓
           6 3 0 0 8
             ↑
            slow

           [0,slow] => 非零
           (slow,fast] => 0
           (fast,n-1] => 未处理

           处理完，前一段非零，后一段为零

                  fast
                   ↓
           6 3 8 0 0
               ↑
              slow

           [0,slow] => 非零
           (slow,fast] => 0

           联系
           快排分区逻辑：小于 pivot 放左边，大于 pivot 放右边
           移动零分区逻辑：非零元素 放左边，零元素 零元素
           => 详细见代码


           KeyPoint 2.三路快排

           处理过程中
            |  < pivot   |  = pivot | 未处理  | > pivot |
           low         less         i      great      high

           处理完
            low  <  pivot  | = pivot  | > pivot  high

           循环不变式
           [low,1ess) < pivot
           [less,i) = pivot
           [i,great] 未处理
           (great,high] > pivot

           pivot 分区点 20
                                  i
                                  ↓
           9 10 11 8 20 20 20 20 45 22 33 34 46
           ↑             ↑     ↑              ↑
          low           less  great          high

          最终 [less,great] 区间 等于 pivot
          下次快速排序从 [low,less-1]，[great+1,high]


     */

    public static void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 提高代码的可读性，修改 i 和 j 变成 fast 和 slow
        // slow 指向最后一个非零元素的下个元素
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                // 减少交换的次数
                if (slow != fast) {
                    // 交换 fast 和 slow 指向的元素
                    // 使用交换，每个元素都会被访问两次
                    // 交换两次会影响性能
                    int tmp = nums[fast];
                    nums[fast] = nums[slow];
                    nums[slow] = tmp;
                }
                // slow == fast 不用交换，但是 slow 得前移，指向最后一个非零元素的下个元素
                slow++;
            }
            // nums[fast] == 0，只要移动 fast 即可，不用移动 slow
        }
    }

    private int partition(int[] data, int low, int high) {
        int pivot = data[high];
        int less = low;
        int great = low;
        for (; great <= high - 1; great++) {
            if (data[great] < pivot) {
                swap(data, less, great);
                // less 右移 => 交换后区间发生变化了，不要遗留了
                less++;
            }
        }

        // 结束 for 循环，分区点位置和 less 交换
        swap(data, less, high);
        // 此时 less 为分区点索引，将其返回，该位置元素已经排好序
        return less;
    }

    public void swap(int[] data, int i, int j) {

    }
}
