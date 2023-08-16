package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 15:25
 * @Version 1.0
 */
public class _03_BM {

    // BF 算法:时间复杂度太高
    // RK 算法:设计一个可以应对各种类型字符的哈希算法并不简单
    // => BF 算法和 RK 算法都不适合：文本文档查找功能

    // BF RK 算法
    // a b c a c a b d c
    // a b d => 直接向后移动 1 位 => 移动一位是很慢的过程
    //     ↑
    //   a b d

    // BM 算法
    //   坏字符
    //     ↓
    // a b c a c a b d c
    // a b d => 直接向后移动 3 位
    //     ↑
    //       a b d

    // pattern 从后往前匹配字符，遇到主串第一个不匹配字符，该字符称之为坏字符 (c)
    // 遇到坏字符之后，在 pattern 中查找是否含有坏字符 (c)
    // 若 pattern 中没有坏字符 (c)，则将 pattern 右移到坏字符 (c) 后面一位即可

    // BM 算法核心思想
    // 根据一定的规则，往后多滑几位，提高性能
    // 关键：寻找模式串往后移动的规则

    // BM 算法包含了两种规则
    // 1.坏字符规则
    // 2.好后缀规则
}
