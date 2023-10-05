package alg_02_train_dm._06_day_位运算_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 19:37
 * @Version 1.0
 */
public class _00_Note_位运算_基础知识 {
    /*
      =====================================================================

        1.位运算
            & 按位与 => 11 得 1 => 1 和 & 形状不同，故两个 11 才能强制为 1
                     => 10 和 01 和 00 得 0  => 有 0 则为 0

            | 按位或 => 00 得 0 => 0 和 1 形状不同，故两个 00 才能强制为 0
                     => 10、01 和 11 得 1 => 有 1 则为 1

            ^ 异或 => 相同得 0、不同得 1 => 关键：异或看'异'

            ~ 取反

            << 左移

            >> 有符号右移

            >>> 无符号右移

        =====================================================================

        2.有符号 整数-无符号 整数

            有符号整数，即最高位为符号位
                10：0000 1010
                -10：1000 1010

            在 java 虚拟机规范中定义的整数类型有：
                byte(8位)，short(16位)，int(32位)，long(64位)，
                => 它们都是 有符号 整数(有正负)
                => 如：int 范围 -2^31 ~ 2^31-1，有正有负 => 有符号整数
                这些整数 java 中使用补码来表示.那么我们首先来了解一下原码，反码和补码.

       =====================================================================

        3.原码、反码、补码
            32位的整型原码：

                10 原码：00000000 00000000 00000000 00001010 => 将 10 转二进制表示
                        ↑ => 最左边的 0 表示正数

                -10 原码：10000000 00000000 00000000 00001010
                          ↑ => 最左边的 1 表示负数

            反码：正数反码和原码相同
                 针对负数原码计算反码，符号位不变(负不能反)，其他位取反

                10 反码：00000000 00000000 00000000 00001010
                        ↑ => 最左边的 0 表示正数

                -10 反码：11111111 11111111 11111111 11110101
                          ↑ => 最左边的 1 表示负数
                            => 符号位不取反

            正整数补码 = 原码 => KeyPoint 正整数 原反补 相同

                10 补码：00000000 00000000 00000000 00001010

            负整数补码 = 反码 + 1

                -10 原码 10000000 00000000 00000000 00001010
                -10 反码 11111111 11111111 11111111 11110101 => 取反不包括符号位
                                                         + 1
                ------------------------------------------------
                -10 补码 11111111 11111111 11111111 11110110

        =====================================================================

        4.为什么使用补码？

            4.1 统一数字 0 的表示
                => 在使用补码表示时，无论把 0 归入正数 +0 或负数 -0，结果是相同的

                若 0 为正数 +0，则正数的补码为自身，所以 0 的补码为：
                00000000 00000000 00000000 00000000

                若 0 为负数 -0，补码为反码加 1，负数 0
                原码 10000000 00000000 00000000 00000000
                反码 11111111 11111111 11111111 11111111
                补码 00000000 00000000 00000000 00000000
                     => 进位的 1 不用管，已经超过 int 数据类型 32 位范围

            4.2 简化整数的加减法计算，将 减法 看成对 补码 的 加法运算

                   6 + 5 => 使用 原码 进行 加法 运算
                   0000 0000 0000 0000 0000 0000 0000 0110  6
                +  0000 0000 0000 0000 0000 0000 0000 0101  5
                ----------------------------------------------
                   0000 0000 0000 0000 0000 0000 0000 1011 11 => 结果正确 √

                  -6 + 5 => 使用 原码 进行 减法 运算
                   1000 0000 0000 0000 0000 0000 0000 0110 -6
                +  0000 0000 0000 0000 0000 0000 0000 0101 5
                ----------------------------------------------
                   1000 0000 0000 0000 0000 0000 0000 1011 -11 => 结果错误 ×

                => 若使用 原码 进行加减运算，结果不统一，有正确，有错误

                   -6 + 5 => 使用补码运算
                   1111 1111 1111 1111 1111 1111 1111 1010 -6
                +  0000 0000 0000 0000 0000 0000 0000 0101 5
                ----------------------------------------------
                   1111 1111 1111 1111 1111 1111 1111 1111 补码 => 原码，得 10 进制数值
                -1 1111 1111 1111 1111 1111 1111 1111 1110
              取反 1000 0000 0000 0000 0000 0000 0000 0001 => -1 => 结果正确 √

        =====================================================================

        5.有符号整数二进制规律 => 通过 4 位举例

            最高位 0 表示正数
            最高位 1表示正数

            0|000    0
            0|001    1
            0|010    2
            0|011    3
            0|100    4
            0|101    5
            0|110    6
            0|111    7
            1|000   -8  注意：这里 1000 是补码形式，不能直接转 10 进制数字
                              => 需要先转原码，再转 10 进制数字
                        补码 1000 => 减 1 1111 (符号位 不参与 运算) => 取反 1000 => 10 进制 -8
                                                                  => 高位 1，即表示数值，又表示正负
                             0000
                         -      1
                         ---------
                             1111

            1|001   -7  补码 1001 => 减 1 1000 => 取反 1111 => 10 进制 -7
            1|010   -6
            1|011   -5
            1|100   -4
            1|101   -3
            1|110   -2
            1|111   -1
            0|000    0

            5.1 抛开正数和负数，只看二进制 => 负数二进制 比 正数二进制大
                                            -8 => 1|000
                                             7 => 0|111
            5.2 4 位二进制表示正数 [-8，7] => [-2^3，2^3-1]
                => 有符号整数，最高位表示符号，故 2^3-1，
                => 注意：不是 2^4-1
                => 8位二进制表示正数 [-2^7，2^7-1]

                0000 0000   0
                0000 0001   1
                0000 0010   2
                ...
                0111 1111   2^7-1 = 127
                1000 0000   -2^7 = -128
                1000 0001   -127
                ...
                1111 1111   -1
                0000 0000   0

                => 32位二进制表示正数 [-2^31，2^31-1]

        =====================================================================

        6.按位取反 ~
            ~9 = -10 => ~(n) = -(n+1)
            9 二进制 原码  1001 => 正数 原反补 都一样 => 补码 0 ... 1001

                    补码 0 ... 1001 => 0 表示正数，正数补码和原码一样
                    取反 1 ... 0110 => 1 表示负数，取反后变成了负数
                    补码 => 原码 => 10 进制数字
                    补码 10110 => 减1 10101 => 取反 11010 => 10 进制 -10

                    KeyPoint 区别：两种取反
                    1.在 Java 中，对 int 进行 按位取反 包括 最高位的符号位
                    2.一般针对负数(正数原反补都一样) 原码 转换 反码 ，不包括最高位符号位

        =====================================================================

        7. << 左移，>> 有符号右移，>>> 无符号右移

            << 左移
             4       0000 0000 0000 0000 0000 0000 0000 0100          10 进制 4
             4<<2    0000 0000 0000 0000 0000 0000 0001 0000 低位补 0  10 进制 16 => 4 * 2^2
                                                                      => 左边是高位，所以左移变大，而是以 2为递
                                                                      => 所以左移 1 位，等价于 *2

             KeyPoint 左移 n 位，即加 n 个零，扩大 2^n 次方，类比十进制数

            -4      1111 1111 1111 1111 1111 1111 1111 1100
            -4<<2   1111 1111 1111 1111 1111 1111 1111 0000
             减 1   1111 1111 1111 1111 1111 1111 1110 1111
             取反   1000 0000 0000 0000 0000 0000 0001 0000  => 10 进制 -16

            KeyPoint 结论：左移和符号没有关系！！！

            正数 >> 有符号右移

            15       0000 0000 0000 0000 0000 0000 0000 1111
            15 >>2   0000 0000 0000 0000 0000 0000 0000 0011 => 高位补 0
            15 >>>2  0000 0000 0000 0000 0000 0000 0000 0011 => 高位补 0

            正数 '有符号右移' 和 '无符号右移' 是一样的

            负数 >> 有符号右移(负数：高位补1)

            -15      1111 1111 1111 1111 1111 1111 1111 0001
            -15 >>2  1111 1111 1111 1111 1111 1111 1111 1100 => 高位补 1
            减1      1111 1111 1111 1111 1111 1111 1111 1011
            取反     1000 0000 0000 0000 0000 0000 0000 0100 => 10 进制 -4

            负数 >> 无符号右移(负数：高位补0)

            -15      1111 1111 1111 1111 1111 1111 1111 0001
            -15>>>2  0011 1111 1111 1111 1111 1111 1111 1100 => 无符号即不将最高位 1 看成符号，右移 2 位，故高位补 0
             因为该结果是无符号，故将其当做原码计算：2^2 + 2^3 + 2^4 + ... + 2^30 = 1073741820

            负数：有符号右移高位补1，而无符号右移高位补 0


     */
}
