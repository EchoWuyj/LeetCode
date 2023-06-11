package algorithm._12_backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-03-29 14:54
 * @Version 1.0
 */
public class EightQueens {
    //方法一:暴力穷举
    //这里为了直观看到每种解法,通过list将每种解法都保存下来
    public List<int[]> eightQueens01() {
        //定义保存结果的list
        ArrayList<int[]> result = new ArrayList<>();

        //用一个int[8]数组保存一组解
        int[] solution = new int[8];

        //遍历八皇后每种可以摆放的场景,判断是否符合题目限制
        for (solution[0] = 0; solution[0] < 8; solution[0]++) {
            for (solution[1] = 0; solution[1] < 8; solution[1]++) {
                for (solution[2] = 0; solution[2] < 8; solution[2]++) {
                    for (solution[3] = 0; solution[3] < 8; solution[3]++) {
                        for (solution[4] = 0; solution[4] < 8; solution[4]++) {
                            for (solution[5] = 0; solution[5] < 8; solution[5]++) {
                                for (solution[6] = 0; solution[6] < 8; solution[6]++) {
                                    for (solution[7] = 0; solution[7] < 8; solution[7]++) {
                                        //是否符合要求
                                        if (check(solution)) {
                                            //这里不能直接add(solution),因为solution是数组的引用
                                            //若直接add,则solution后续的遍历过程中,导致solution会发生变化
                                            //从而影响add的值,所以使用copy方法,将结果copy到result中
                                            result.add(Arrays.copyOf(solution, 8));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    //定义一个判定当前摆放方式是否有效的方法
    public boolean check(int[] arr) {
        //任意两个皇后进行比较
        //8个皇后,比较7次即可,不用遍历到最后一个
        for (int i = 0; i < 7; i++) {
            //两两比较中后面对象是需要遍历到最后一位的
            for (int j = i + 1; j < 8; j++) {
                //不能在同一列,并且行列索引差不能相等
                //i和j肯定是不同行的皇后,则行号直接不用比较了
                //a数组的值对应是当前皇后的行号,两者不能相等,相等返回false
                //列相减的绝对值=行相减(j>i),返回false
                if (arr[i] == arr[j] || Math.abs(arr[i] - arr[j]) == j - i) {
                    return false;
                }
            }
        }
        return true;
    }

    //方法二:回溯
    HashSet<Integer> cols = new HashSet<>();
    HashSet<Integer> diags1 = new HashSet<>();
    HashSet<Integer> diags2 = new HashSet<>();

    public List<int[]> eightQueens02() {
        //定义保存结果的List
        ArrayList<int[]> result = new ArrayList<>();

        //用一个int[8]数组保存一组解
        int[] solution = new int[8];

        //先对solution做初始填充,表示皇后还没有填充,不能给0-7的值
        Arrays.fill(solution, -1);

        //定义回溯方法,递归调用
        //定义回溯方式是按照一行一行去判断,需要行号0-7
        //同时,需要判断solution,找到一组解,将其保存在result中,同样需要将其传入
        backtrack(result, solution, 0);

        return result;
    }

    //实现回溯方法
    public void backtrack(ArrayList<int[]> result, int[] solution, int row) {
        //首先处理递归调用结束场景
        if (row >= 8) {
            //已经直接得到了所有行的填充结果,构建了一组解
            result.add(Arrays.copyOf(solution, 8));
        } else {
            //对于当前行,考察可能的皇后位置,遍历每一列
            for (int column = 0; column < 8; column++) {
                //1.如果已经和之前的皇后冲突,直接跳过,寻找下一位置,通过辅助的3个HashSet进行判断
                //通过这样的方式,就避免了两两比对,避免了时间复杂度为O(n^2)

                //1.1判断是同一列
                if (cols.contains(column)) {
                    continue;
                }
                //1.2判断两条斜线
                //首先需要将当前皇后处在那两条斜线上,需要计算出来
                int diag1 = row - column;  //行号-列号(左对角)
                int diag2 = row + column;  //行号+列号(右对角)
                if (diags1.contains(diag1)) {
                    continue;
                }
                if (diags2.contains(diag2)) {
                    continue;
                }

                //2.如果不冲突,当前位置就放置皇后
                solution[row] = column;
                cols.add(column);
                diags1.add(diag1);
                diags2.add(diag2);

                //3.递归调用,深度搜索下一行
                //solution是一层一层往下传递的,之前行的对应的皇后的位置还保留在solution中
                //因此每传一层,递归调用深一层,就多填一个皇后的列号
                backtrack(result, solution, row + 1);

                //KeyPoint 等到递归调用,一层一层全部调用结束,如果后面全处理完了
                // 即表示当前皇后位置solution[row] = column这种场景已经处理完了
                // 此时需要进行回溯,向下一个位置移动,此时column++,但是在回溯之前
                // 需要将之前的状态都回滚,方便判断后面一列位置是否符合要求

                //4.回溯,将状态回滚,继续遍历当前行皇后可能的位置

                solution[row] = -1;
                cols.remove(column);
                diags1.remove(diag1);
                diags2.remove(diag2);
            }
        }
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        List<int[]> result = eightQueens.eightQueens02();

        System.out.println("八皇后问题一共有" + result.size() + "种不同的摆法");
        for (int[] ints : result) {
            printQueens(ints);
            System.out.println("===========================================");
        }
    }

    public static void printQueens(int[] queens) {
        int n = queens.length;
        //打印每一行
        for (int i = 0; i < n; i++) {
            //每一行都使用String数组来装
            String[] line = new String[n];
            //将数组中每个元素都替换成□
            Arrays.fill(line, "□");
            line[queens[i]] = "Q";
            //遍历string数组中每个元素,通过\t进行分隔
            for (String s : line) {
                System.out.print(s + "\t");
            }
            System.out.println();
        }
    }
}
