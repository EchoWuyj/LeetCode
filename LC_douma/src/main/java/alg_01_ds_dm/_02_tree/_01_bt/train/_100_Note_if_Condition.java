package alg_01_ds_dm._02_tree._01_bt.train;

/**
 * @Author Wuyj
 * @DateTime 2023-06-30 15:18
 * @Version 1.0
 */
public class _100_Note_if_Condition {
    /*

        KeyPoint 利用 if 潜在的逻辑，简化代码

        1 没有使用 if 的潜在逻辑的代码，其中有很多无效的判断
          if (p == null && q == null) return true;

          if (p == null && q != null) return false;
          if (p != null && q == null) return false;

        2 经过上面的 if 判断之后，p != null || q != null
          在此基础上，若是 p 和 q 有一个为 null，则 return false
          if (p == null || q == null) return false;

        3 最后只有 p q 都不为 null 的情况，故 p != null && q != null 可以省略
          if ((p != null && q != null) && (p.val == q.val)) return true;
          => if (p.val != q.val) return false;


         KeyPoint 通过列可能性分析 p 和 q

         if 判断中，两个条件，一种 4 种可能

                             ↗ T
                ↗ T  case2
         case1               ↘ F
                ↘ F         ↗ T
                      case2
                             ↘ F

         通过 ×，√ 分别表示 != null，= null

                  q
                  ×      √
          p  × | (×,×)  (×,√)
             √ | (√,×)  (√,√)

         在可能性表格的基础上来写 if 的条件

         KeyPoint 等价写法
         使用 else if 来替换多个独立的 if 语句， 这可以减少条件判断的次数

         if (p == null && q == null) {
             return true;
         } else if (p == null || q == null) {
             return false;
         } else if (p.val != q.val) {
             return false;
         }

         if 语句最终的 else，使用最后一个 return 代替了
         return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);

     */
}
