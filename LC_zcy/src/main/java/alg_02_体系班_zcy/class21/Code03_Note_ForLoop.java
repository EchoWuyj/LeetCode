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
            for 循环的运算机制
            1.首先执行 int i = 0
            2.之后执行 i < 10，结果为true，则执行 {} 中的代码，反之结束循环
            3.运行完 {} 中的代码后，执行 i++
            4.第三步结束后进入下一次循环

            记忆：执行方向类似：逆时针
                    初始化  循环条件      循环变量累加
                       →      →              ←
                                    ↓ 循环体  ↑
         */

    }
}
