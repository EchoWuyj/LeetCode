package alg_02_体系班_zcy.class11;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:10
 * @Version 1.0
 */
public class Code07_PaperFolding {

    // 请把一段纸条竖着放在桌子上,然后从纸条的下边向上方对折1次,压出折痕后展开
    // 此时折痕是凹下去的,即折痕凸起的方向指向纸条的背面
    // 如果从纸条的下边向上方对折2次,压出折痕后展开,此时有三条折痕,从上到下依次是凹折痕,凹折痕和凸折痕

    // 给定一个输入参数N,代表纸条都从下边向上方连续对折N次,请从上到下打印所有的折痕的方向
    // 例如 N=1时,打印: down
    //     N=2时,打印：down down up

    // 分析:
    // 规律,大于一次后,每次折痕出现的位置都是在上次折痕的上方出现凹折痕,下方出现凸折痕
    // 所以我们没必要构建这颗树,就可以用递归思维解决,从上到下打印整张纸的凹凸情况就是整棵树的中序遍历。

    // 对应的树结构按层输出为：
    //             1凹
    //     2凹             2凸
    // 3凹     3凸     3凹     3凸

    // 这颗二叉树有着明确规则:
    // 1)头节点是凹的
    // 2)所有左子树的头节点都是凹的
    // 3)所有右子树的头节点都是凸的

    public static void printAllFolds(int N) {
        process(1, N, true);
        System.out.println();
    }

    /**
     * 中序打印以你想象的节点为头的整棵树！
     * 分析:
     * 1)节点数2^N-1个,但是空间占用O(N),其中N是层数,使用递归行为模拟了想象,从始至终,这颗树都没有真实的建立
     * 2)垃圾做法:折痕数量是2^N-1个,使用2^N-1大小数组,再去分析数组怎么填好值,再去遍历数组打印出来
     *
     * @param i    当前你来了一个节点,该节点在第i层
     * @param N    一共有N层,N固定不变的
     * @param down 该节点若是凹的话,down=T;若是凸的话,down=F
     */

    public static void process(int i, int N, boolean down) {
        // 递归的边界,
        if (i > N) {
            return;
        }
        // 想象左边凹节点,作为左树
        process(i + 1, N, true);
        System.out.print(down ? "凹 " : "凸 ");
        // 想象右边凸节点,作为右树
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);
    }
}
