package alg_01_新手班_wyj.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-09-07 14:13
 * @Version 1.0
 */
public class Code02_RandToRand {
    public static void main(String[] args) {
        //System.out.println(Math.random());

        int testTimes = 10000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < 0.3) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println("==============");

        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() * 8 < 4) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);

        System.out.println("==============");

        int[] counts = new int[9];
        for (int i = 0; i < testTimes; i++) {
            int ans = (int) (Math.random() * 9);
            counts[ans]++;
        }

        for (int i = 0; i < 9; i++) {
            // 每个数字出现的次数都是差不多的,可以认为每个元素出现的概率是等概的
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }

        System.out.println("==============");

        count = 0;
        double x = 0.3;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower2() < 0.3) {
                count++;
            }
        }

        System.out.println((double) count / (double) testTimes);
        System.out.println("==============");

        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (f2() == 0) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);

        System.out.println("==============");

        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (f2() == 0) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);

        counts = new int[8];
        // 索引从1开始,将索引0排除在外
        for (int i = 1; i < testTimes; i++) {
            int num = g();
            counts[num]++;
        }

        for (int i = 1; i < 8; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }

        System.out.println("========================");

        counts = new int[2];
        int ans;
        for (int i = 0; i < testTimes; i++) {
            ans = y();
            counts[ans]++;
        }

        for (int i = 0; i < 2; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }
    }

    //------------------------------------------------

    public static double xToXPower2() {
        return Math.max(Math.random(), Math.random());
    }

    public static int f1() {
        // 实现1-5随机
        return (int) (Math.random() * 5) + 1;
    }

    public static int f2() {
        int ans;
        do {
            ans = f1();
        } while (ans == 3);
        return (ans < 3) ? 0 : 1;
    }

    public static int f3() {
        return (f2() << 2) + (f2() << 1) + f2();
    }

    public static int f4() {
        int ans;
        do {
            ans = f3();
        } while (ans == 7);
        return ans;
    }

    public static int g() {
        return f4() + 1;
    }

    //
    public static int x() {
        // 0概率是,1是
        return (Math.random() < 0.84) ? 0 : 1;
    }

    public static int y() {
        int ans;
        do {
            ans = x();
        } while (ans == x());
        return ans;
    }
}
