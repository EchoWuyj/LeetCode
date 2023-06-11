package alg_03_leetcode_top_zcy.class_06;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 9:31
 * @Version 1.0
 */

// 跳跃游戏 II
public class Problem_0045_JumpGameII {
    /*
       题目描述和55题一样,只是返回为到达nums[n-1]的最小跳跃次数

       思路:
        step: 当前至少跳几步才能到i位置
              一开始i位置在0位置,所以至少跳0步,就能到i(0)位置,所以step初值为0
        cur: 跳跃步数不超过step(只跳step)情况下的最右位置
        next:跳跃步数不超过step+1(在step基础上多跳一步)情况下的最右位置(next只是维持最大值)

        KeyPoint 案例一
        [ 3,1,7 ...]
          0 1 2
          i
        i=0时,step:不需要增加,也能到i(0),step=0
              cur:step(0)步内,最右只能到0位置,cur=0
              next:因为i位置数值为3,从0位置再跳1步(step=1),故next=3

        i=1时, step:当i在1位置,但跳跃步数不超过0情况下的最右是0位置,
                    此时必须增加step,step=1
               cur:step=1,利用i=0时,next值,故cur=next=3
               next:1位置再跳1步的最右位置是2,即跳跃步数不超过step(1)+1=2,
                    但是2<3(之前next值),则不更新

        KeyPoint 案例二
        [ 3,2,1,4,2,1..]
          0 1 2 3 4
          i
        i=0时,step:不需要增加,也能到i(0),step=0
              cur:step(0)步内,最右只能到0位置,cur=0
              next:因为i位置数值为3,从0位置再跳1步(step=1),故next=3

        1) 分支1
           i=1时
                i>cur,step步不足以到i,需要增加步数,step+1,step=1
                cur需要随着step更新而更新,即1步以内最右位置,利用i=0时,next值,故cur=next=3

        2) 分支2
           i=2时
                i<=cur,step步能到i,则step不需要增加步数
                next,在2位置再跳1步,最右位置3,3<=next,则next不需要更新

           i=3时
                i<=cur,step步能到i,则step不需要增加步数
                next,在3位置再跳1步(i=1和2时,step没有增加步数,step=1,step+1=2)的最右位置:i+nums[i]=7
                7>next,则next=7
        ...
        最终step就是所求ans

     */
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            // 直接不用跳跃
            return 0;
        }
        int step = 0;
        int cur = 0;
        int next = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 再跳1步能到n-1位置,则返回step+1
            if (next >= nums.length - 1) {
                return step + 1;
            }

            // 说明只跳step步不能到达位置i,需要多跳一步才行
            if (cur < i) {
                step++;
                //cur更新成跳step+1步能够达到的位置即next
                cur = next;
            }
            // 表示下一次多跳一步到达的最远位置
            next = Math.max(next, i + nums[i]);
        }
        return step;
    }
}
