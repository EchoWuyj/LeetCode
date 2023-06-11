package algorithm._08_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2022-03-23 19:56
 * @Version 1.0
 */
public class LeetCode_215_KthLargestElement {
    // 方法一:直接排序(调库)
    public int findKthLargest01(int[] nums, int k) {
        Arrays.sort(nums);
        // 倒数第一元素对应索引为nums.length-1
        // 同样倒数第k个元素对应索引为nums.length-k
        return nums[nums.length - k];
    }

    //  方法二:基于快排的选择
    public int findKthLargest02(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    //  实现快速选择方法
    public int quickSelect(int[] nums, int start, int end, int index) {
        // 找到pivot的位置返回
        int position = randomPartition(nums, start, end);

        // 判断当前pivot位置是否为index
        // 找到直接返回,不会继续去寻找,别的元素是否排好序并不关心
        if (position == index) {
            return nums[position];
        } else {
            // 每次分区之后,再去递归调用只要找一半即可,在原来快排的基础上进行优化
            return position > index ? quickSelect(nums, start, position - 1, index) :
                    quickSelect(nums, position + 1, end, index);
        }
    }

    //  实现一个随机分区方法
    private int randomPartition(int[] nums, int start, int end) {
        Random random = new Random();
        // 随机生成pivot的位置,该索引的范围在start和end之间
        // nextInt(num)其中num是不包括在内,如nextInt(10),返回的结果为0-9之间的整数
        int randIndex = start + random.nextInt(end - start + 1);

        // 交换nums[start]和nums[randIndex]的值
        swap(nums, start, randIndex);

        // 调用partition()方法
        return partition(nums, start, end);
    }

    public int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start, right = end;

        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];

            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //  方法三:基于堆排序的选择
    public int findKthLargest03(int[] nums, int k) {
        int n = nums.length;

        // 通过定义当前堆的大小,方便在数组构成的大顶堆中删除元素
        // 即不是物理上将元素删除,而是将堆顶元素和最后一个元素进行交换
        // 后面遍历时,只需要遍历当前堆的大小即可

        // 保存堆的大小,初始就是n
        int heapSize = n;

        // 1.构建大顶堆
        buildMaxHeap(nums, heapSize);

        // 2.执行k-1次删除堆顶元素操作
        // 倒序的for(int i = n ; i > n-1 ; i--) 执行的次数:n-(n-1)=1
        for (int i = n - 1; i > n - k; i--) {
            // 将堆顶元素交换到当前堆的末尾
            swap(nums, 0, i);
            heapSize--;
            // 大顶堆的堆调,传入当前根节点的索引和堆的大小
            maxHeapify(nums, 0, heapSize);
        }

        // 3.返回当前堆顶元素
        return nums[0];
    }

    //  定义一个调整成大顶堆的方法
    private void maxHeapify(int[] nums, int top, int heapSize) {
        // 定义左右子节点
        int left = top * 2 + 1;
        int right = top * 2 + 2;

        // 保存当前最大元素的索引位置
        int largest = top;

        // 比较左右子节点,记录最大元素索引位置
        // right<heapSize保证右节点在树索引范围内
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }
        if (left < heapSize && nums[left] > nums[largest])
            largest = left;

        // 将最大元素换到堆顶(本身最大元素就是top就不用调换)
        if (largest != top) {
            //  将堆顶替换成largest元素
            swap(nums, top, largest);

            //  递归调用,继续下沉
            maxHeapify(nums, largest, heapSize);
        }
    }

    // 实现一个构建大顶堆的方法,且不用到额外的存储空间
    public void buildMaxHeap(int[] nums, int heapSize) {
        // 数组中已经有所有元素,即不用插入再上浮的算法来实现
        // 自底向上依次调用maxHeapify来构建大顶堆,叶子节点不用处理,直接从非叶子节点开始处理

        // KeyPoint 一棵完全二叉树各节点的编号为0到n,最后一个叶子节点的索引值是n-1,
        //  假定父节点为i,则其左叶子为2i+1,右叶子为2i+2,它的父节点索引值是[(n-1)-1]/2 = n/2 -1
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            // 以i为根进行调整,将其以下的都进行调整
            maxHeapify(nums, i, heapSize);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        LeetCode_215_KthLargestElement kthLargestElement = new LeetCode_215_KthLargestElement();
        System.out.println(kthLargestElement.findKthLargest03(nums1, 2));
        System.out.println(kthLargestElement.findKthLargest03(nums2, 4));
    }
}
