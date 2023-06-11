package _04_binary_search;

/**
 * @Author Wuyj
 * @DateTime 2022-08-21 19:13
 * @Version 1.0
 */
public class Offer_11_MinArray {
    public static void main(String[] args) {
        int[] array = {4, 5, 6, 1, 2, 3};
        System.out.println(minArray(array)); // 1
    }

    public static int minArray(int[] numbers) {
        // 排序数组的查找问题首先考虑使用二分法解决

        // 设置 left, right 指针分别指向 numbers 数组左右两端
        // left 指向当前区间的最左边位置，所以初始化为 0
        int left = 0;

        //  right 指向当前区间的最右边位置，所以初始化为 nums.length - 1
        int right = numbers.length - 1;

        // 循环进行二分查找，直到左端点位置超过了右端点
        // 或者在循环过程中找到了起始位置
        while (left < right) {

            // mid 为中点（这里向下取整，比如 （ 2 + 7 ）/ 2 = 4 )
            int mid = (left + right) / 2;

            // KeyPoint 关键是找异常点在那个区间段内，即左边一段，还是右边一段内
            // 当 mid 点所在元素大于数组末端的元素时，由于原来的数组是递增有序的，
            // 此时出现了异常，大的数在前面，所以旋转点在 [ mid + 1, end ] 区间里面
            if (numbers[mid] > numbers[right]) {

                // 所以旋转点在 [ mid + 1, end ] 区间里面 ，更新 left 的位置为 mid + 1
                left = mid + 1;

                // 当 mid 点所在元素小于数组末端的元素时，由于原来的数组是递增有序的，故mid 到 right 也是正常递增的
                // ，看不出来 mid 是否为旋转点，故还是需要考虑 mid 是否为旋转点，所以旋转点在 [ left, mid ] 区间里面
                // 比如：[2,3,4,5,6] -> [5,6,2,3,4] mid = 2 就是旋转点
            } else if (numbers[mid] < numbers[right]) {

                // 旋转点在 [ left, mid ] 区间里面 ，更新 right 的位置为 mid
                right = mid;
            } else {

                // 此时，出现了 numbers[mid] = numbers[right] 的情况，无法判断
                // [ start , mid ]  为有序数组区间，还是 [ mid , end ] 为有序数组区间
                // 比如：[1, 0, 1, 1, 1] 和 [1, 1, 1, 0, 1]
                return findMin(numbers, left, right);
            }
        }
        return numbers[left];
    }

    // 从头到尾遍历 numbers ，获取到最小值
    public static int findMin(int[] numbers, int left, int right) {

        // 默认为数组的第一个元素为最小值
        int result = numbers[left];

        // 从头到尾遍历 numbers
        for (int i = left; i <= right; i++) {

            // 当发现此时遍历的元素值小于 result
            if (numbers[i] < result) {
                // 更新 result
                result = numbers[i];
            }
        }

        // 返回 numbers 中的最小值
        return result;
    }
}
