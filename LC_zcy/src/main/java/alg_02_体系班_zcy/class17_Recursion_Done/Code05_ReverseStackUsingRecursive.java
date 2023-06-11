package alg_02_体系班_zcy.class17_Recursion_Done;

import java.util.Stack;

public class Code05_ReverseStackUsingRecursive {

	/*
		 给定一个栈,请逆序这个栈
		 不能申请额外的数据结构,只能使用递归函数
	 */

    // KeyPoint 两层递归嵌套(之前微软喜欢考)
    public static void reverse(Stack<Integer> stack) {
        /*
        	|1|
      		|2| -> 原始栈
      		|3|									 实现栈逆序
																  |3|
		  ↓ |1|													  |2|
		    |2|	<- r1:stack不空,f(stack),i=3   ↑  stack.push(3);   |1|

																 |2|
		  ↓ |1|	<- r2:stack不空,f(stack),i=2   ↑  stack.push(2);  |1|

		  ↓ 栈空 <- r3:stack不空,f(stack),i=1   ↑  stack.push(1);  |1|

					r4:栈空,返回r3

         */

        if (stack.isEmpty()) {
            return;
        }
        // 栈底元素移除掉,上面的元素盖下来,返回移除掉的栈底元素
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    // 功能:栈底元素移除掉,上面的元素盖下来,返回移除掉的栈底元素
    // |1|
    // |2| -> |1|  返回值是3
    // |3|    |2|
    // --     --
    public static int f(Stack<Integer> stack) {
        /*
        递归过程

    		|1|
      		|2| -> 原始栈                    最终返回值last=3
      		|3|

      		|2|
      	↓	|3| -> f1:result=1,last=?  ↑  f1:3=last<=f2             |1|
      									     result=1,压栈result=1   |2|
      									     返回last=3

      	↓   |3| -> f2:result=2,last=?  ↑  f2:3=last<=f3
      	                                     result=2,压栈result=2  |2|
									         返回last=3

 		↓   栈空 -> f3:result=3,last=?
 		           栈空 √ return result=3 ↑
 		           返回f2的递归函数出口
         */

        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            // 在从递归函数出口返回前,不会执行后面代码
            // 返回递归函数上层,result还是保留的,可以重新入栈
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
