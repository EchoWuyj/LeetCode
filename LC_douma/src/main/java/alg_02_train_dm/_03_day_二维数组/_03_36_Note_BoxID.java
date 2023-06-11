package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-18 22:51
 * @Version 1.0
 */
public class _03_36_Note_BoxID {
   /*
        boxIndex 是如何推导出来的呢？

        补充知识：二维数组转一维数组
        二维数组中，有个元素坐标索引 (row,col)
        => 一维数组中索引 index = col + row * n，(n 二维数组列数)

        求解 boxIndex 过程
        先将二维数组元素 (row,col)，转成这个元素所在的 box 的坐标索引为 (row/3,col/3)
        然后将二维的 3×3 的 box 数组转成一维的，boxIndex = col / 3 + (row / 3) * 3
        一般是通过'行优先方式计算'

    */
}
