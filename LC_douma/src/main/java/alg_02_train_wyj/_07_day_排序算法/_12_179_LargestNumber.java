package alg_02_train_wyj._07_day_排序算法;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 16:09
 * @Version 1.0
 */
public class _12_179_LargestNumber {
    public String largestNumber1(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        sort(nums, 0, nums.length - 1);

        if (Arrays.equals(nums, new int[nums.length])) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        for (int num : nums) {
            res.append(num);
        }

        if (res.charAt(0) == '0') return "0";
        return res.toString();
    }

    public void sort(int[] nums, int low, int high) {
        if (low >= high) return;
        int pivot = nums[high];
        int less = low;
        int great = high;
        int i = low;

        while (i <= great) {
            String xy = nums[i] + "" + pivot;
            String yx = pivot + "" + nums[i];
            if (xy.compareTo(yx) > 0) {
                swap(nums, i, less);
                less++;
                i++;
            } else if (xy.compareTo(yx) < 0) {
                swap(nums, i, great);
                great--;
            } else {
                i++;
            }
        }
        sort(nums, low, less - 1);
        sort(nums, great + 1, high);
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static String largestNumber2(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        int n = nums.length;
        String[] strArr = new String[n];
        int index = 0;
        for (int num : nums) {
            strArr[index++] = num + "";
        }

        Arrays.sort(strArr, new LargestNumberComparator());
        if (strArr[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static class LargestNumberComparator implements Comparator<String> {

        @Override
        public int compare(String x, String y) {
            String xy = x + y;
            String yx = y + x;
            return yx.compareTo(xy);
        }
    }

    public static void main(String[] args) {
        System.out.println(largestNumber2(new int[]{10, 2}));
    }
}





