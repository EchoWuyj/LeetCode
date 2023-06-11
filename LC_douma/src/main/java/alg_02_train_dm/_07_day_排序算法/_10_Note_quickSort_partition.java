package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 18:06
 * @Version 1.0
 */
public class _10_Note_quickSort_partition {
    /*
           1.二路快排
                                       great pivot
                                        ↓   ↓
            9 11 13 8 10 20 22 77 45 46 33 34
            ↑             ↑                 ↑
           low           less              high
                         分区点

           抽取模型 => 处理过程中
            |  < pivot   |  > pivot  | 未处理 |
           low         less        great    high
                       分区点

           抽取模型 => 处理完
           low  <= pivot   |  > pivot   high


           => less 和 great 快慢指针
           => 移动零
           => 总结：一般这种题目都可以使用快慢指针，给数组分区，从而将问题解决

                fast
                 ↓
           6 3 0 0 8
             ↑
            slow

           0 ~ slow => 非零
           slow ~ fast => 0
           fast ~ n-1 => 未处理

           处理完，前一段非零，后一段为零


           2.三路快排

           抽取模型 => 处理过程中
            |  < pivot   |  = pivot | 未处理  | > pivot |
           low         less         i      great      high

           抽取模型 => 处理完
            low  <  pivot  | = pivot  | > pivot  high

           循环不变式
           [low,1ess) < pivot
           [less,i) = pivot
           [i,great] 未处理
           (great,high] > pivot

           pivot 分区点 20
                                  i
                                  ↓
           9 10 11 8 20 20 20 20 45 22 33 34 46
           ↑             ↑     ↑              ↑
          low           less  great          high


     */
}
