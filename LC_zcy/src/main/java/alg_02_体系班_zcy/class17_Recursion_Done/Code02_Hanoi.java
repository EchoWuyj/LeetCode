package alg_02_体系班_zcy.class17_Recursion_Done;

import java.util.Stack;

public class Code02_Hanoi {

	 /*
	 暴力递归(暴力递归就是尝试)
	   1)把问题转化为规模缩小了的同类问题的子问题
	   2)有明确的不需要继续进行递归的条件base case
	   3)有当得到了子问题的结果之后的决策过程
	   4)不记录每一个子问题的解

	 KeyPoint 设计递归思想
	  1) 递归需要宏观认识(不要一开始就纠结细节),边界条件写对,后面的执行过程就是自身调用的过程
	  2) 递归决策图画出来,类似二叉树形式
	  3) 黑盒思维,递归函数F(),关注输入和输出,base case,以及需要满足什么样的条件(显示,隐藏),然后想怎么使用这个黑盒
     */

    // 题目描述:把A柱子上的盘子借助B柱子移动到C柱子上面
    // 移动的要求:每一步只能移动一个盘子,同时必须满足大盘子在小盘子的下面
    // 打印n层汉诺塔从最左边移动到最右边的全部过程

    // KeyPoint 方法一(原始)
    // 假设有3跟柱子左中右,我们细分为如下三个步骤
    //  1)将1~N-1层圆盘从左->中(大问题,需要继续划分为小问题)
    //  2)将第N层圆盘从左->右(base case)
    //  3)将1~N-1层圆盘从中->右(大问题,需要继续划分为小问题)

    public static void hanoi1(int n) {
        leftToRight(n);
    }

    // 请把1~N层圆盘 从左->右
    public static void leftToRight(int n) {
        // KeyPoint 只有一个圆盘,该圆盘就是第一层圆盘,可以直接操作
        if (n == 1) { // base case
            System.out.println("Move 1 from left to right");
            // KeyPoint 关于return说民
            //  1)这里必须加上return是因为在if判断之后,还有其他语句并不是程序的结尾,
            //    执行打印之后,程序没有停止,因此需要加上return,否就会出现反复调用,导致堆栈溢出
            //  2)一般函数返回值类型为void的情况下,是可以省略return,但是在递归函数可能因为
            //   没有return导致,自身反复调用导致程序无法停止,从而导致堆栈溢出
            return;
        }
        // 将1~N-1层圆盘从左->中
        leftToMid(n - 1);
        // KeyPoint 想要N层圆盘移动,则需要N-1个圆盘已经移走了,此时就剩N层一个圆盘,这样也是能移动的
        // 将第N层圆盘从左->右
        System.out.println("Move " + n + " from left to right");
        // 将1~N-1层圆盘从中->右
        midToRight(n - 1);
    }

    // 请把1~N层圆盘 从左 -> 中
    public static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    public static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    public static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    public static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    public static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to left");
        midToLeft(n - 1);
    }

    // KeyPoint 方法二(改进)
    /*
     如果我们忘记柱子的左中右,将三根柱子标记为from,to,other
     我们实现的是,将所有的圆盘(一共N层)从from->to,同样细分为3个步骤：
       1) 将1~N-1层圆盘从from->other(大问题,需要继续划分为小问题)
       2) 将第N层圆盘从from->to(base case,可以直接打印)
       3) 将1~N-1层圆盘从other->to(大问题,需要继续划分为小问题)

     KeyPoint 貌似没什么改进,因为还是细分这三步,同样要递归
          但关键在于忘掉柱子的左中右顺序,只需要三个变量,代码可以少很多

     */
    public static void hanoi2(int n) {
        if (n > 0) {
            func(n, "left", "right", "mid");
        }
    }

    /**
     * @param N     1-N个圆盘
     * @param from  在哪(源头)
     * @param to    去哪(目标)
     * @param other 另外的位置(中间过度)
     */

    // KeyPoint 技巧:递归函数可以通过增加参数的方式表达更多的可能性
    //           从而使得递归函数更加强大
    //      原来形参只是层数n,现在又增加了在哪,去哪,中间过度
    //      => 原来6个递归函数整合成一个递归函数
    public static void func(int N, String from, String to, String other) {
        // 最小圆盘可以自由移动
        if (N == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            // 1~n-1,从from(在哪)到other(目标),to成为另一个
            func(N - 1, from, other, to);
            System.out.println("Move " + N + " from " + from + " to " + to);
            // 1~n-1,从other(在哪)到to(目标),from成为另一个
            func(N - 1, other, to, from);
        }

        /*
        KeyPoint 时间复杂度分析
             操作流程:
             1)在移动n个盘子的过程中,我们需要先将n-1个盘子从起始柱移动到辅助柱,
             2)然后将第n个盘子从起始柱移动到目标柱,最后将n-1个盘子从辅助柱移动到目标柱
             移动n个盘子的步数可以表示为:T(n)=2T(n-1)+1,其中T(n-1)表示移动n-1个盘子所需的步数
             n层汉诺塔问题,一共有2^n-1移动步数
             即需要打印的步骤就是2^n-1步,时间复杂度O(2^n-1)
         */
    }

    // KeyPoint 方法三:非递归的解法(任何递归都可以改成非递归)
    public static class Record {
        public boolean finish1;
        public int base;
        public String from;
        public String to;
        public String other;

        public Record(boolean f1, int b, String f, String t, String o) {
            finish1 = false;
            base = b;
            from = f;
            to = t;
            other = o;
        }
    }

    public static void hanoi3(int N) {
        if (N < 1) {
            return;
        }
        Stack<Record> stack = new Stack<>();
        stack.add(new Record(false, N, "left", "right", "mid"));
        while (!stack.isEmpty()) {
            Record cur = stack.pop();
            if (cur.base == 1) {
                System.out.println("Move 1 from " + cur.from + " to " + cur.to);
                if (!stack.isEmpty()) {
                    stack.peek().finish1 = true;
                }
            } else {
                if (!cur.finish1) {
                    stack.push(cur);
                    stack.push(new Record(false, cur.base - 1, cur.from, cur.other, cur.to));
                } else {
                    System.out.println("Move " + cur.base + " from " + cur.from + " to " + cur.to);
                    stack.push(new Record(false, cur.base - 1, cur.other, cur.to, cur.from));
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi1(n);
        System.out.println("============");
        hanoi2(n);
        System.out.println("============");
        //hanoi3(n);
    }
}
