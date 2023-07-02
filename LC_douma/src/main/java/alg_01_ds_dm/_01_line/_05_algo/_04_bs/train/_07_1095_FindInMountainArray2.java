package alg_01_ds_dm._01_line._05_algo._04_bs.train;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-02 15:51
 * @Version 1.0
 */
public class _07_1095_FindInMountainArray2 {

    interface MountainArray {
        int get(int index);

        int length();
    }

    // 找 bug，调试代码
    static class Arr implements MountainArray {
        private List<Integer> list = new ArrayList<>();

        Arr() {
        }

        Arr(int[] nums) {
            for (int num : nums) {
                list.add(num);
            }
        }

        @Override
        public int get(int index) {
            return list.get(index);
        }

        @Override
        public int length() {
            return list.size();
        }
    }

    public static int findInMountainArray(int target, MountainArray nums) {
        if (nums == null || nums.length() == 0) return -1;

        // KeyPoint debug 技巧
        // 1.主函数中，涉及多个子函数调用，先通过打印输出方式，看那个子函数返回值有问题
        //   从而进一步缩小 bug 的范围
        // 2.先通过打印输出方式找 bug，实在不行再 debug 找，因为 debug 比较麻烦

        int peakIndex = findPeak(nums);
        // System.out.println(peakIndex);

        int res = findTargetFromFrontPart(target, nums, peakIndex);
        // System.out.println("first res " + res);
        if (res != -1) return res;
        res = findTargetFromLatterPart(target, nums, peakIndex);
        // System.out.println("second res " + res);
        return res;
    }

    private static int findTargetFromFrontPart(int target, MountainArray nums, int peakIndex) {
        int left = 0, right = peakIndex;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums.get(mid)) {
                return mid;
            } else if (target > nums.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static int findTargetFromLatterPart(int target, MountainArray nums, int peakIndex) {
        int left = peakIndex + 1, right = nums.length() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums.get(mid)) {
                return mid;
            } else if (target > nums.get(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int findPeak(MountainArray nums) {
        int left = 0, right = nums.length() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < nums.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {0, 5, 3, 1};
        //           0  1  2  3
        System.out.println(findInMountainArray(1, new Arr(arr)));
    }

    // 根据测试用例的数据，进行代码调试

    // 输入：
    // [0,5,3,1]
    // 1
    // 输出：
    // -1
    // 预期结果：
    // 3
}
