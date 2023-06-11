package _10_others;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2022-09-18 19:04
 * @Version 1.0
 */
public class Offer_17_PrintNumbers {
    // 输入数字n,按顺序打印出从1到最大的n位十进制数
    // 比如输入3,则打印出1、2、3 一直到最大的3位数999
    //输入: n = 1
    //输出: [1,2,3,4,5,6,7,8,9]
    public int[] printNumbers(int n) {

        // 10^2代码中无法直接计算,需要调用Math.pow方法
        // Math.pow(x,y) x是底数,y是幂数,返回值类型是double需要强转
        int temp = (int) Math.pow(10, n);
        int ans = temp - 1;

        // 其实中间可以不用集合过度,增加额外的开销
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= temp - 1; i++) {
            list.add(i);
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    // 不使用额外的集合
    public int[] printNumbers2(int n) {
        int temp = (int) Math.pow(10, n);
        int size = temp - 1;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            // 里面i+1并不影响for循环的i
            res[i] = i + 1;
        }
        return res;
    }
}
