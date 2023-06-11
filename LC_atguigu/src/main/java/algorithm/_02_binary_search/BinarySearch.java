package algorithm._02_binary_search;

/**
 * @Author Wuyj
 * @DateTime 2022-02-27 12:31
 * @Version 1.0
 */
public class BinarySearch {
    public static int binarySearch01(int[] nums, int key) {
        // 定义初始查找范围,双指针
        int low = 0;
        int high = nums.length - 1;

        // 排除特殊情况
        // 比第一元素要小或者比最后一个元素要大,直接返回-1结束
        if (key < nums[low] || key > nums[high]) {
            return -1;
        }

        // 当low和high相等也是能看下两者相等值是多少
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < key)
                // 取右半部分
                low = mid + 1;
                // KeyPoint 注意if的逻辑关系,这里是else if 而不是if,否则下面的else就会和该if组合
                //  形成一个if...else... 即改变了原来的if的逻辑关系
            else if (nums[mid] > key)
                // 取左半部分
                high = mid - 1;
            else
                // 相等的情况表示已经找到了
                return mid;
        }

        // 当前循环还是没有找到,则返回-1结束
        return -1;
    }

    // 递归实现二分查找,增加搜索的上下界作为参数
    // 因为是递归实现,所以函数的形参列表还需要传入搜索范围的上下界
    public static int binarySearch02(int[] nums, int key, int fromIndex, int toIndex) {
        // 特殊情况超出最大最小值,直接返回-1
        if (key < nums[fromIndex] || key > nums[toIndex]) {
            return -1;
        }

        // 递归的边界:当起始位置大于结束位置时,直接返回-1
        if (fromIndex > toIndex) {
            return -1;
        }

        // 计算中间位置
        // 是否能整除不是很重,是否取左边一点,还是右边一点,其实没有关系
        // 二分查找并不是严格意义上的二分,只要取到一个值将其分成两个部分即可
        int mid = (fromIndex + toIndex) / 2;

        // 判断中间位置元素和key的大小关系,更改搜索范围,递归调用
        if (nums[mid] < key) {
            // KeyPoint 递归调用需要return进行返回
            return binarySearch02(nums, key, mid + 1, toIndex);
        } else if (nums[mid] > key) {
            return binarySearch02(nums, key, fromIndex, mid - 1);
        } else {
            return mid;
        }
        // return -1;不能再有了
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int key = 6;
        int index01 = binarySearch01(arr, key);
        int index02 = binarySearch02(arr, key, 0, arr.length - 1);

        System.out.println(index01);
        System.out.println(index02);
    }
}
