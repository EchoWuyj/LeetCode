package alg_03_leetcode_top_wyj.class_08;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-02-24 10:58
 * @Version 1.0
 */
public class AddDivideNum {

    public static int count = 0;

    public static HashMap<Integer, Integer> map = new HashMap<>();

    public final static int[] arr = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    public static int ways(int n) {
        if (map.size() == 0) {
            process(123456789, 8);
        }
        return map.containsKey(n) ? map.get(n) : 0;
    }

    public static void process(int nums, int index) {
        if (index == -1) {
            for (int add = 8; add >= 2; add--) {
                int p1 = nums / arr[add];
                int rest = nums % arr[add];
                for (int dev = (add >> 1); dev >= 1; dev--) {
                    int p2 = rest / arr[dev];
                    int p3 = rest % arr[dev];
                    if (p2 % p3 == 0) {
                        int ans = p1 + (p2 / p3);
                        if (!map.containsKey(ans)) {
                            map.put(ans, 1);
                        } else {
                            map.put(ans, map.get(ans) + 1);
                        }
                    }
                }
            }
        } else {
            for (int swap = index; swap >= 0; swap--) {
                int next = swap(nums, index, swap);
                process(next, index - 1);
            }
        }
    }

    public static int swap(int num, int l, int r) {
        int bitL = (num / arr[l]) % 10;
        int bitR = (num / arr[r]) % 10;
        return num - (bitL - bitR) * arr[l] + (bitL - bitR) * arr[r];
    }

    public static void main(String[] args) {
        int N = 100;
        long start;
        long end;
        start = System.currentTimeMillis();
        System.out.println(N + "用带分数表示的方法数 : " + ways(N));
        end = System.currentTimeMillis();
        System.out.println("运行了(毫秒) : " + (end - start));
        N = 10000;
        start = System.currentTimeMillis();
        System.out.println(N + "用带分数表示的方法数 : " + ways(N));
        end = System.currentTimeMillis();
        System.out.println("运行了(毫秒) : " + (end - start));
    }
}


