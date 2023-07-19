package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 10:34
 * @Version 1.0
 */
public class _13_238_product_of_array_except_self {
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int leftProduct = 1;
            for (int j = 0; j < i; j++) {
                leftProduct *= nums[j];
            }
            int rightProduct = 1;
            for (int j = i + 1; j < n; j++) {
                rightProduct *= nums[j];
            }
            res[i] = leftProduct * rightProduct;
        }
        return res;
    }

    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] leftProducts = new int[n];
        leftProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
        }

        int[] rightProducts = new int[n];
        rightProducts[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = leftProducts[i] * rightProducts[i];
        }
        return res;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }
        return res;
    }
}
