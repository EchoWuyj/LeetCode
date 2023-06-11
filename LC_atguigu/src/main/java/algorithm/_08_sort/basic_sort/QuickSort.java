package algorithm._08_sort.basic_sort;

/**
 * @Author Wuyj
 * @DateTime 2022-03-22 23:42
 * @Version 1.0
 */
public class QuickSort {
    public static void qSort(int[] nums, int start, int end) {
        //基准情况:当前要去处理的数组足够小的时候,当start和end相遇(数组只有一个元素)或者start已经超过end,此时就停止
        if (start >= end) return;

        //1.找到一个pivot,把数组划分成两部分,返回pivot索引位置
        int index = partition2(nums, start, end);

        //2.递归排序左右两部分
        qSort(nums, start, index - 1);
        qSort(nums, index + 1, end);
    }

    //分区方法(不推荐)
    public static int partition(int[] nums, int start, int end) {
        //以第一个元素作为中心点
        int pivot = nums[start];

        //定义双指针
        int left = start, right = end;
        //要返回的pivot位置索引,刚开始pivot在start位置
        int position = start;

        //在左指针没有遇上右指针
        while (left < right) {
            //左指针向右移,找到一个比pivot大的数(相等的情况没有必要移到后面),就停下来
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            //右指针左移,找个一个比pivot小的数,如果while循环卡在这里,则表示nums[right]<pivot
            while (left < right && nums[right] >= pivot) {
                right--;
            }

            //退出while循环的条件不能是left>=right,只有在left<right条件下才是交换两个元素
            //判断左右指针是否相遇,如果没有相遇,交换两个元素
            if (left < right) {
                swap(nums, left, right);
            } else {
                //如果已经相遇,填入pivot
                //要判断当前位置和pivot的大小,确定到底填入哪个位置
                if (nums[left] < pivot) {
                    position = left;
                    swap(nums, start, left);
                } else {
                    position = left - 1;
                    swap(nums, start, left - 1);
                }
            }
        }
        return position;
    }

    public static int partition2(int[] nums, int start, int end) {
        ///第一个元素作为枢轴
        int pivot = nums[start];
        int left = start, right = end;

        while (left < right) {

            //左移右指针,找到一个比pivot小的数,填入空位
            //即先从末尾往前找到第一个比枢轴小的元素
            //left<right条件是为了避免完全顺序的序列出现数组下溢,如:12,24,36,46,60
            while (left < right && nums[right] >= pivot) {
                right--;
            }

            //用right的元素替换left的元素
            nums[left] = nums[right];

            //再从开头往后找到第一个比枢轴大的元素
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            //用left的元素替换right的元素
            nums[right] = nums[left];
        }

        //一趟快速排序将序列分成了两个部分,在枢轴左边的元素都是比枢轴要小的元素,在枢轴右边的元素都是比枢轴要大的元素
        //枢轴元素存放到最终位置
        nums[left] = pivot;

        //返回存放枢轴的最终位置
        //正是通过left位置将序列划分成了两部分
        return left;
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {3, 45, 78, 36, 52, 11, 39, 36, 52};
        qSort(nums, 0, nums.length - 1);
        printArray(nums);
    }
}
