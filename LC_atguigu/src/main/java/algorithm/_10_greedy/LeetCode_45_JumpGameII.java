package algorithm._10_greedy;

/**
 * @Author Wuyj
 * @DateTime 2022-03-26 12:38
 * @Version 1.0
 */
public class LeetCode_45_JumpGameII {
    //方法一:反向跳跃
    public int jump01(int[] nums) {
        //定义一个变量保存跳跃步数
        int steps = 0;

        //定义循环变量
        //跳跃的当前的位置(从末尾开始跳跃)
        int curPosition = nums.length - 1;

        //不停地向前跳跃,以最远距离,并且跳跃到初始位置0的话就结束循环
        //因为当前不知道到底需要跳几步,所以使用while循环更加合适
        while (curPosition > 0) {
            //从前到后遍历数组元素,找到从当前末尾位置往前跳跃最远的“上一步位置”
            for (int i = 0; i < curPosition; i++) {
                //基于i的位置能够往后跳跃的最大距离,若该距离>=curPosition,表示能够跳过来
                //因为这里是for循环是正向从前往后遍历,即找到的第一个i位置就是最远的位置
                if (i + nums[i] >= curPosition) {
                    curPosition = i;
                    steps++;

                    //找到之后退出for循环,再去执行更新后的curPosition,直到curPosition=0
                    break;
                }
            }
        }

        return steps;
    }

    //方法二:正向跳跃,考虑能够跳到最远的两步
    public int jump02(int[] nums) {

        //KeyPoint 直接在力扣上提交是不能通过的,因为力扣上的一些测试用例比较特殊
        // 一个数组里面就一个数[2],这种情况则是不需要步数,直接返回0即可,但是
        // return ++steps返回值却是1,这样测试用例是通过不了的,特殊情况需要单独判断
        if (nums.length == 1) return 0;

        //定义一个变量保存跳跃步数
        int steps = 0;
        //跳跃的当前的位置
        int curPosition = 0;

        //在往后跳的过程中,通过定义双指针farthest和nextFarthest
        //指向当前位置跳一步的最远位置
        int farthest = nums[0];
        //第二步最远至少应该在第一步最远的基础上,所以赋值为farthest
        int nextFarthest = farthest;

        //不停贪心寻找下一步的合适位置
        while (farthest < nums.length - 1) {
            //遍历currPosition~farthest范围内所有元素,选择第二步跳跃最远的作为当前一步的选择
            for (int i = curPosition; i <= farthest; i++) {
                //如果比之前第二步最远距离大,更新nextFarthest和当前跳跃位置curPosition
                if (i + nums[i] > nextFarthest) {
                    nextFarthest = i + nums[i];
                    curPosition = i;
                }
            }
            //当前一步已经确定,step加1
            steps++;
            //之前的第二步变成现在的第一步
            farthest = nextFarthest;
        }
        //因为while中是判断farthest,当while循环结束之后
        //最后一步curPosition还没有跳到nums.length-1,故需要先加1,之后再去return
        return ++steps;
    }

    //方法三:正向跳跃,考虑能够跳到最远的两步,优化
    public int jump03(int[] nums) {

        //定义一个变量保存跳跃步数
        int steps = 0;

        //以初始起跳为准
        int farthest = 0;
        //第二步最远至少应该在第一步最远的基础上,所以赋值为farthest
        int nextFarthest = farthest;

        //通过一趟遍历就可以实现,不再需要起始位置,所以将curPosition直接删除了
        for (int i = 0; i < nums.length - 1; i++) {
            //如果比之前第二步最远距离大,更新nextFarthest和当前跳跃位置curPosition
            if (i + nums[i] > nextFarthest) {
                nextFarthest = i + nums[i];
            }
            //当前循环一轮结束,即i将当前farthest处理完,即为步数增长条件
            if (i == farthest) {
                //当前一步完成
                steps++;
                farthest = nextFarthest;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 2, 3};

        LeetCode_45_JumpGameII jumpGameII = new LeetCode_45_JumpGameII();
        System.out.println(jumpGameII.jump03(nums));
    }
}
