package algorithm._10_greedy;

/**
 * @Author Wuyj
 * @DateTime 2022-03-25 23:06
 * @Version 1.0
 */
public class LeetCode_55_JumpGame {
    //贪心实现
    public boolean canJump(int[] nums) {
        //定义一个变量,保存当前最远能跳到的位置
        int farthest = 0;
        //遍历数组,更新farthest
        //数组元素对应的索引就是该元素的起始点位置
        for (int i = 0; i < nums.length; i++) {
            //判断当前i在可以到达的范围内,更新farthest
            //即i元素所在的位置通过farthest可以达到
            if (i <= farthest) {
                farthest = Math.max(farthest, i + nums[i]);
                if (farthest >= nums.length - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};

        LeetCode_55_JumpGame jumpGame = new LeetCode_55_JumpGame();
        System.out.println(jumpGame.canJump(nums1));
        System.out.println(jumpGame.canJump(nums2));
    }
}
