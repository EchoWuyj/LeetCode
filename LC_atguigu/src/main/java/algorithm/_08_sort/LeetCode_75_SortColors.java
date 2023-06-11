package algorithm._08_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-03-24 11:00
 * @Version 1.0
 */
public class LeetCode_75_SortColors {
    //方法一:调库
    public void sortColors01(int[] nums) {
        Arrays.sort(nums);
    }

    //方法二:基于选择排序
    public void sortColors02(int[] nums) {
        //定义一个指针,指向当前应该填入元素的位置
        int cur = 0;

        //1.遍历数组,将所有0交换到数组头部
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, cur++, i);
            }
        }

        //2.遍历数组,将所有1交换到中间位置,接着之前的curr继续
        //因为上个for循环只有if条件成立时,cur才会加1,这次遍历直接接着最后一个0后面交换1即可
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(nums, cur++, i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //方法三:基于计数排序
    //通过记录数组中有几个0,再去记住有几个1和2,再去将012都填到数组里面
    public void sortColors03(int[] nums) {
        int count0 = 0, count1 = 0;

        //遍历数组,统计0,1,2的个数
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            }
        }

        //将0,1,2按照个数依次填入nums数组
        for (int i = 0; i < nums.length; i++) {
            if (i < count0) {
                nums[i] = 0;
            } else if (i < count0 + count1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    //方法四:基于快速排序(借鉴了快速排序的思路)
    //直接将1当做枢轴,将0移动到1之前,将2移动到1之后
    public void sortColors04(int[] nums) {
        //定义左右指针
        int left = 0, right = nums.length - 1;

        //定义一个遍历所有元素的指针
        int i = left;

        //循环判断,遍历元素
        //i=right也是需要进行判断的,i是当前处理的元素,而right是下一个可以换过来位置,这个位置没有判断过
        while (left < right && i <= right) {
            //1.如果是2,换到末尾,右指针左移
            while (i <= right && nums[i] == 2)
                swap(nums, i, right--);

            //2.如果是0,换到头部,左指针右移
            if (nums[i] == 0)
                swap(nums, i, left++);

            //3.i++,继续遍历
            //故由上面的两个操作可以知道:只是调整0和2,1是保持不动的
            i++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        LeetCode_75_SortColors sortColors = new LeetCode_75_SortColors();
        sortColors.sortColors03(nums);
        printArray(nums);
    }

    public static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }
}
