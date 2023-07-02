package alg_01_ds_wyj._01_line._05_algo._04_bs.train;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 15:41
 * @Version 1.0
 */
public class _07_1095_FindInMountainArray1 {

    interface MountainArray {
        int get(int index);

        int length();
    }

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

        int peekIndex = findPeek(nums);
        System.out.println(peekIndex);

        int res = findTargetFromFrontPart(target, nums, peekIndex);
        System.out.println("first res " + res);
        if (res != -1) return res;
        res = findTargetFromLatterPart(target, nums, peekIndex);
        System.out.println("second res " + res);
        return res;
    }

    private static int findTargetFromFrontPart(int target, MountainArray nums, int peekIndex) {
        int left = 0, right = peekIndex;
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

    private static int findTargetFromLatterPart(int target, MountainArray nums, int peekIndex) {
        int left = peekIndex + 1, right = nums.length() - 1;
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

    private static int findPeek(MountainArray nums) {
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

    // 输入：
    //    // [0,5,3,1]
    //    // 1
    //    // 输出：
    //    // -1
    //    // 预期结果：
    // 3
}
