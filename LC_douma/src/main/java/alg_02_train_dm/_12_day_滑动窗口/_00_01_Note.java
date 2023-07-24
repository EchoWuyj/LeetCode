package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 9:58
 * @Version 1.0
 */
public class _00_01_Note {
        /*
            滑动窗口
            作用对象：一般作用在'数组'这种线性数据结构上
            将元素放入到窗口中 => 本质：对放入元素进行计算

            //            right
            //              ↓
            // nums x x x x x x x
            //      ↑
            //    left

            KeyPoint 应用场景一：查找满足条件的最短窗口 => 伪代码

            // left 和 right 界定滑动窗口左右边界
            int left = 0, right = 0;

            // ans，Set，Map，具体选择看题目要求
            int ans = 0;

            int n = nums.length
            // 整个数组遍历结束
            while (right < n) {
              处理 right 指向的元素，将其加入到当前窗口，将元素加入窗口
              => 可能涉及计算，需要将结果存在变量，或者数据结构中 (ans，Set，Map)

              // 若是单次判断，则将 while 替换成 if
              // 若使用 while 循环，则是 left++ 一直到窗口不满足条件为止
              while (对中间变量，或者数据结构判断 => 窗口满足条件) {
                  KeyPoint 计算结果(可能位置)
                  处理 left 指向的元素，将其从当前窗口中剔除掉
                  left++;
              }
              KeyPoint 计算结果(可能位置)
              // 不满足条件，从 right 侧添加元素，扩大窗口
              right++;
            }

           KeyPoint 应用场景二：查找满足条件的最长窗口 => 伪代码

           int left = 0, right = 0;
           int n = nums.length
           while (right < n) {
               处理 right 指向的元素，将其加入到当前窗口
               while (对中间变量，或者数据结构判断 => 窗口不满足条件) {
                   KeyPoint 计算结果(可能位置)
                   处理 left 指向的元素，将其从当前窗口中剔除掉
                   left++;
               }
               KeyPoint 计算结果(可能位置)
               // 满足条件，从 right 侧添加元素，扩大窗口
               right++;
           }

           12 道高频算法面试题讲解
           10 道高频算法面试题练习

         */
}
