package algorithm._07_stack_and_queue;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-03-21 19:58
 * @Version 1.0
 */
public class LeetCode_84_LargestRectangleInHistorgram {

    // KeyPoint 方法一:暴力法（遍历所有可能的宽度）
    public int largestRectangleArea01(int[] heights) {
        //定义变量保存最大面积
        int largestArea = 0;

        //遍历数组,作为矩形的左边界
        for (int left = 0; left < heights.length; left++) {
            //定义变量保存当前矩形的高度
            int curHeight = heights[left];

            //遍历数组,选取矩形的右边界
            for (int right = left; right < heights.length; right++) {
                //确定当前矩形的高度
                curHeight = Math.min(curHeight, heights[right]);

                //计算当前矩形的面积
                int curArea = (right - left + 1) * curHeight;

                //更新最大面积
                largestArea = Math.max(curArea, largestArea);
            }
        }
        return largestArea;
    }

    // KeyPoint 方法二:双指针法（遍历所有可能的高度）
    public int largestRectangleArea02(int[] heights) {
        //定义变量保存最大面积
        int largestArea = 0;

        //遍历数组,以每个柱子高度作为最终矩形的高度
        for (int i = 0; i < heights.length; i++) {
            //保存当前高度
            int height = heights[i];

            //定义左右指针
            int left = i, right = i;

            //寻找左边界,左指针左移
            while (left >= 0) {
                //因为left=right=i,所以最开始是heights[left]=height,所以左指针必然左移
                if (heights[left] < height) break;
                left--;
            }

            //寻找右边界,右指针右移
            while (right < heights.length) {
                //因为left=right=i,所以最开始是heights[left]=height,所以右指针必然右移
                if (heights[right] < height) break;
                right++;
            }

            //计算当前宽度,需要在左右指针的基础上减1
            int width = right - left - 1;

            //计算面积
            int curArea = height * width;
            largestArea = Math.max(curArea, largestArea);
        }
        return largestArea;
    }

    // KeyPoint 方法三:双指针法改进:省略中间判断
    // 判断左边界时,如果左边的柱子比当前柱子要高的话,则直接拿左边更高的柱子的左边界作为当前可能的左边界
    // 这样话,可以跳过中间更高的柱子,直到找到比当前柱子更矮左边界
    public int largestRectangleArea03(int[] heights) {
        //定义变量保存最大面积
        int largestArea = 0;

        //定义两个数组,保存每个柱字对应的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        //遍历数组,计算左边界
        for (int i = 0; i < n; i++) {
            //保存当前高度
            int height = heights[i];

            //定义左指针
            //当前的左边界不是自己,定义成i-1,使用前一个柱子和当前柱子进行比较
            int left = i - 1;

            //左指针左移,寻找左边界
            while (left >= 0) {
                //heights[left] < height成立的条件下执行break
                if (heights[left] < height) break;
                //如果左边柱子更高,就直接跳到它的左边界柱子再判断
                //跳跃式移动左指针
                left = lefts[left];
            }
            //在while循环退出的话,已经找到了最终的左边界,比当前柱子要矮的值
            lefts[i] = left;
        }

        //遍历数组,计算右边界
        for (int i = n - 1; i >= 0; i--) {
            //保存当前高度
            int height = heights[i];

            //定义右指针
            int right = i + 1;

            //右指针右移,寻找右边界
            while (right < n) {
                if (heights[right] < height) break;
                //如果右边柱子更高,就直接跳到它的右边界柱子再判断
                right = rights[right];
            }
            rights[i] = right;
        }

        //遍历所有柱子,计算面积
        for (int i = 0; i < n; i++) {
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(currArea, largestArea);
        }
        return largestArea;
    }

    // KeyPoint 方法四:单调栈
    public int largestRectangleArea04(int[] heights) {
        //定义变量保存最大面积
        int largestArea = 0;

        //定义两个数组,保存每个柱子对应的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        //定义一个栈
        Stack<Integer> stack = new Stack<>();

        //遍历所有柱子,作为当前高度,先找左边界
        for (int i = 0; i < n; i++) {
            //heights[stack.peek()]:栈中保存是当前的索引
            //栈不为空,栈顶元素比当前元素要大于或等于,则将当前元素弹栈,一定要找个比该元素小且为最近的元素作为其左边界
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            //所有大于等于当前高度的元素全部弹出,找到了左边界,将左边界的下标赋值为当前元素的lefts数组
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();

            //每个元素都要将当前的索引压栈
            stack.push(i);
        }

        stack.clear();

        //遍历所有柱子,作为当前高度,寻找右边界
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            //所有大于等于当前高度的元素全部弹出,找到了左边界
            rights[i] = stack.isEmpty() ? n : stack.peek();

            stack.push(i);
        }

        //遍历所有柱子,计算面积
        for (int i = 0; i < n; i++) {
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(currArea, largestArea);
        }

        return largestArea;
    }

    // KeyPoint 方法五:单调栈优化
    public int largestRectangleArea05(int[] heights) {
        //定义变量保存最大面积
        int largestArea = 0;

        //定义两个数组,保存每个柱子对应的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        //初始化rights为右哨兵n
        for (int i = 0; i < n; i++) rights[i] = n;

        //定义一个栈
        Stack<Integer> stack = new Stack<>();

        //遍历所有柱子,作为当前高度,先找左边界
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                //栈顶元素如果小于当前元素,那么它的右边界就是当前元素
                rights[stack.peek()] = i;
                stack.pop();
            }

            //所有大于等于当前高度的元素全部弹出,找到了左边界
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        //遍历所有柱子,计算面积
        for (int i = 0; i < n; i++) {
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(currArea, largestArea);
        }

        return largestArea;
    }

    public static void main(String[] args) {
        int[] heights = {6, 7, 5, 2, 4, 5, 9, 3};
        LeetCode_84_LargestRectangleInHistorgram largestRectangleInHistorgram = new LeetCode_84_LargestRectangleInHistorgram();
        System.out.println(largestRectangleInHistorgram.largestRectangleArea05(heights));
    }
}
