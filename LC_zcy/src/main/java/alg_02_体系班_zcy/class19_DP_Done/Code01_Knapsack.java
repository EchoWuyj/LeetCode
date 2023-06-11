package alg_02_体系班_zcy.class19_DP_Done;

public class Code01_Knapsack {
	
	/*
		背包问题
		给定两个长度都为N的数组weights和values
		weights[i]和values[i]分别代表i号物品的重量和价值
		给定一个正数bag(bag>0),表示一个载重bag的袋子,
		装的物品不能超过这个重量,返回能装下的最大价值

	      w   [   ]  重量
	      v   [   ]  价值
		索引  0 1 2

		KeyPoint 尝试模型:从左往右依次从尝试(要么要,要么不要)
		     依次分析0,1,2位置的情况

                        0
                     ×     √
                   1         1
                ×   √     ×    √     => 递归版本
               2    2     2    2
             ×  √  ×  √ ×  √  ×  √
        每个分支都走,进行暴力枚举,最大价值的策略必在其中
	 */

    /**
     * @param w   所有货的重量(为了方便,其中没有负数)
     * @param v   所有货的价值(为了方便,其中没有负数)
     * @param bag 背包容量,不能超过这个载重
     * @return 不超重的情况下, 能够得到的最大价值
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        // 特殊边界
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数
        return process(w, v, 0, bag);
    }

    // KeyPoint 方法一:暴力递归
    // 在初期尝试就是模仿,照猫画虎,写着写着就会了
    // 功能:当前考虑到了index号货物,index..往后的所有的货物可以自由选择
    //     做的选择不能超过背包容量返回是的最大价值,index之前货物已经处理好了不用管
    // index:用来标记选择到那个货物
    // rest:用来表示bag还剩多少容量
    public static int process(int[] w, int[] v, int index, int rest) {
        // 背包容量小于0,没法装货,直接返回
        if (rest < 0) {
            // -1表示无效状态 => 在写尝试时可能设置无效解,因此在递归调用时需要提前判断
            return -1;
        }

        // 没有货了,没有价值,直接返回
        // w[]数组上每个位置已经确定好相应的数字
        if (index == w.length) {
            return 0;
        }

        // 有货,且为当前index的货,2种处理方式:要与不要
        // 1)不要当前的货
        // process返回为index+1位置,自由选择,不超过背包容量的最大值
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;

        // 2)要当前的货
        // 当前货价值+剩下货最大价值
        // KeyPoint 当rest-w[index]<0,则返回值为0,当前货价值+0=当前货价值,
        //      但其实该解是无效解,因为超过bag的容量,所以需要加上if判断
        //      区别于:Code02_CoinsWayEveryPaperDifferent,这种是可能性,
        //      rest-arr[index]为0,p1+0,还是正确的
        int next = process(w, v, index + 1, rest - w[index]);
        // rest不一定能装下w[index],所以需要加个if判断
        if (next != -1) {
            // v表示value值,不是w,w是weight
            p2 = v[index] + next;
        }

        // 要么要,要么不要,2种情况的最大值 => 递归每层都是比较左右分支,获取最大值返回
        return Math.max(p1, p2);

        //   w   [3,2,4]
        //   v   [5,6,10]
        // 索引   0 1 2

        //               0
        //            ×     √
        //          1         1
        //       ×    √      ×    √
        //      2      2    2      2
        //   ×   √   ×  √  ×  √   ×  √

        //  2 × p1 = 0
        //  2 √ p2 = 10  => Math.max(p1, p2) 返回上层 => 1 × p1 = 10
        //                  因为存在将比较后的较大值返回到上层,所以不需要累加

    }

    /*
         1)判断是否有重复调用
             w [3 2 5 ..]
             v [7 4 6 ..]
                0 1 2
                √ √ ×   p(3,10)
                × × √   p(3,10) => 存在重复过程,使用dp优化有利可图
         2)变化形参不要个数不要搞错!
           index 0~N
           rest  负~bag
         3)列dp表,填表,从边界位置(暴力递归边界条件)->一般位置(假设位置)
            从已知位置推测未知位置
         4)返回最终求解的值
     */

    // KeyPoint 方法二:dp
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        // KeyPoint 根据递归函数的变化形参的个数来决定dp表是一维还是二维的
        //      变化形参是指在递归函数调用中变化的参数:index和bag
        // index 0~N
        // rest 负~bag
        int[][] dp = new int[N + 1][bag + 1];

        // 行优先,从下往上填表,定义循环变量index和rest
        for (int index = N - 1; index >= 0; index--) {
            // 由p1和p2代码中index+1,当前行只是依赖下个行位置的
            // 不依赖同一行的左右位置,因此同一行可以从左往右,也可以从右往左填表
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                // 需要将原来if判断转化下,严格按照递归表达式来写
                // KeyPoint rest - w[index] < 0 反面是 rest - w[index] >= 0
                //     不能仅仅是 rest - w[index] > 0 将取等的情况遗漏了
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        // 主函数的形参
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4};
        int[] values = {5, 6, 10};
        int bag = 6;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
