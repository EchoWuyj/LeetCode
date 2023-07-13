package alg_02_train_dm._07_day_排序算法_二刷;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-07-10 23:16
 * @Version 1.0
 */
public class _12_179_Note_Comparator_Generic {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};

        // KeyPoint 比较器 Comparator 泛型限制
        // 比较器 Comparator 泛型无法传入 int 数据类型，而 nums 是 int 类型
        // 即使将 Comparator<int> 修改成 <Integer>，也和 nums 数据类型不匹配

//        Arrays.sort(nums, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return 0;
//            }
//        });

        int n = nums.length;
        Integer[] nums1 = new Integer[n];

        for (int i = 0; i < n; i++) {
            nums1[i] = nums[i];
        }
        // 将 int[] nums 转成 Integer[] nums1，可以调用 Comparator 比较器
        Arrays.sort(nums1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

        System.out.println(Arrays.toString(nums1));
    }
}
