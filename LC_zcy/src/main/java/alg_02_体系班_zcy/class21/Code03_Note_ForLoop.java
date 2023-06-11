package alg_02_体系班_zcy.class21;

/**
 * @Author Wuyj
 * @DateTime 2023-03-07 20:51
 * @Version 1.0
 */
public class Code03_Note_ForLoop {
    public static void main(String[] args) {
        int k = 0;
        // 这种结构下，i < 10 则循环 10 次
        for (int i = 0; i < 10; i++) {
            k += i;
        }
        System.out.println(k);

        /*
            for循环的运算机制
            1 首先执行int i=0
            2 之后执行i<10,结果为true则执行{}中的代码,反之结束循环
            3 运行完{}中的代码后执行i++
            4 第三步结束后进入下一次循环
         */

    }
}
