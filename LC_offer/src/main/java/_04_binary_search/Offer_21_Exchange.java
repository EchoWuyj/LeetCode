package _04_binary_search;

/**
 * @Author Wuyj
 * @DateTime 2022-08-22 20:15
 * @Version 1.0
 */
public class Offer_21_Exchange {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        int[] result = exchanage(array);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public static int[] exchanage(int[] nums) {

        // KeyPoint 双指针

        // 定义头指针 left
        int left = 0;
        // 定义尾指针 right
        int right = nums.length - 1;
        // 定义临时变量 tmp ，用于交换这个两个元素
        int temp;

        // 移动 left 和 right ，直到 left 在 right 右侧或者相遇为止
        while (left < right) {

            // 如果 left 指针指向的元素值是奇数，那么说明该元素在左侧了，观察其它的元素，即让 left 向右移动
            // KeyPoint a&1 == 1 奇数  a&1 == 0 偶数
            while (left < right && (nums[left] & 1) == 1) left++;

            // 如果 right 指针指向的元素值是偶数，那么说明该元素在右侧了，观察其它的元素，即让 right 向左移动
            while (left < right && (nums[right] & 1) == 0) right--;

            // 否则就说明，此时要么 left 指向的元素值为偶数，要么 right 指向的元素值为奇数，交换这两个位置的元素
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

        return nums;
    }
}
