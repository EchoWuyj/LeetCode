package alg_02_体系班_zcy.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

// 本题为leetcode原题
// 测试链接：https://leetcode.cn/problems/number-of-islands/
// 所有方法都可以直接通过
public class Code02_NumberOfIslands {

    // 一个矩阵中只有0和1两种值,每个位置都可以和自己的上下左右四个位置相连
    // 如果有一片1连在一起(上下左右),这个部分叫做一个岛,求一个矩阵上有多少个岛

    // 举例：
    // 001010
    // 111010
    // 100100
    // 000000
    // 这个矩阵有3个岛

    // 方法三(递归实现) 最优解
    // 时间复杂度分析:
    //  1)双重for循环,将所有节点都遍历一遍
    //  2)infect函数,每个节点只有被上下左右调用,最多4遍
    //  3)总的最多遍历的次数为5遍,忽略常数系数O(M*N)
    public static int numIslands3(char[][] board) {
        int islands = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    islands++;
                    infect(board, i, j);
                }
            }
        }
        return islands;
    }

    // KeyPoint 二维数组与矩阵
    // 1)二维数组的每一行都是一个一维数组,并且这些一维数组的长度不一定相等,所以每一行的列数,要单独的求
    // 2)注意:二维数组不一定是矩阵！矩阵的列数是固定的,每一行的列数都相同
    // 3)如一定义二维数组array[][],则获得该数组的长度(即行数)的代码为array.length,
    //   int arr[3][4]={{1,2,3,4},{5,6,7,8},{9,10,11,12}} 3行4列
    // 4)对于每一行的宽度可以循环获得,如获得第i行的宽度(i的范围是0~array.length-1)的代码为array[i].length

    // 从(i,j)这个位置出发,把所有练成一片的'1'字符,变成0
    public static void infect(char[][] board, int i, int j) {
        // {{},{}}
        // board.length 为行数
        // board[0].length 为列数(因为矩阵,所以每行的列数都一样)
        // i表示行,边界是上下
        // j表示列,边界是左右
        if (i < 0 || i == board.length || j < 0
                || j == board[0].length || board[i][j] != '1') {
            return;
        }
        // 一定先要将其修改成不为1的,避免成环
        board[i][j] = 0;
        // 上
        infect(board, i - 1, j);
        // 下
        infect(board, i + 1, j);
        // 左
        infect(board, i, j - 1);
        // 右
        infect(board, i, j + 1);
    }

    // 方法一(并查集:HashMap)不是很推荐
    public static int numIslands1(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        Dot[][] dots = new Dot[row][col];
        // 记录new来多少个Dot,为后面的并查集初始化准备
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1') {
                    // 每次new出来的Dot的内存地址都是不同的,使用Dot来标记不同的1
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }

        // 初始化
        UnionFind1<Dot> uf = new UnionFind1<>(dotList);

        // 使用多个for循环,针对0行,0列,一般情况分别处理
        // 减少了判断是否有左,是否有上的,其实这样反而是更高效的

        // 0行没有上面,使用单独for循环解决
        for (int j = 1; j < col; j++) {
            // (0,j):(0,1),(0,2),(0,3),自己是1,左边也是1,则合并
            // (0,0)既没左,又没有上,直接有跳过了
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                uf.union(dots[0][j - 1], dots[0][j]);
            }
        }

        // 0列没有左面,使用单独for循环解决
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(dots[i - 1][0], dots[i][0]);
            }
        }

        // 剩下的位置,既有左又有上,使用for循环解决
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // 自己是1
                if (board[i][j] == '1') {
                    // 左边也是1
                    if (board[i][j - 1] == '1') {
                        uf.union(dots[i][j - 1], dots[i][j]);
                    }
                    // 上边也是1
                    if (board[i - 1][j] == '1') {
                        uf.union(dots[i - 1][j], dots[i][j]);
                    }
                }
            }
        }
        // 返回多少个独立区域
        return uf.sets();
    }

    // 每个实例的内存地址不同,来区分相同值1的不同的节点
    public static class Dot {

    }

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind1<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind1(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }

    // 方法二(并查集:数组)
    public static int numIslands2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionFind2 uf = new UnionFind2(board);

        // 使用多个for循环,针对0行,0列,一般情况分别处理
        // 减少了判断是否有左,是否有上的,其实这样反而是更高效的

        // 0行没有上面,使用单独for循环解决
        for (int j = 1; j < col; j++) {
            // (0,j):(0,1),(0,2),(0,3),自己是1,左边也是1,则合并
            // (0,0)既没左,又没有上,直接有跳过了
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                uf.union(0, j - 1, 0, j);
            }
        }
        // 0列没有左面,使用单独for循环解决
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(i - 1, 0, i, 0);
            }
        }
        // 剩下的位置,既有左又有上,使用for循环解决
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // 自己是1
                if (board[i][j] == '1') {
                    // 左边也是1
                    if (board[i][j - 1] == '1') {
                        uf.union(i, j - 1, i, j);
                    }
                    // 上边也是1
                    if (board[i - 1][j] == '1') {
                        uf.union(i - 1, j, i, j);
                    }
                }
            }
        }
        // 返回多少个独立区域
        return uf.sets();
    }

    // 并查集(数组实现)
    public static class UnionFind2 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int col;
        private int row;
        private int sets;

        // 形参需要将二维数组(矩阵)传入
        public UnionFind2(char[][] board) {
            row = board.length;
            col = board[0].length;
            sets = 0;
            // row * col存在溢出的风险,数组的长度的个数只能是整数的长度,即最大为Integer.MAX_VALUE
            // 数组的长度和数据类型没有关系,即使将数据类型定义long,即long[][])也是不行的
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];

            // 将二维矩阵在一维数组上初始化标记
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        // 定义全局变量sets,遍历过程中进行累加
                        // 这样才能确定样本量,因为不是每个位置都是1
                        sets++;
                    }
                }
            }
        }

        // 矩阵按照行编排,对应一维数组索引
        // 0行编排
        // (0,0) -> 0
        // (0,1) -> 1
        // (0,2) -> 2
        // ...
        // 纵向将二维数组拉成一位数组:(i,j) -> i*列数+j
        // (r,c) -> i
        private int index(int r, int c) {
            return r * col + c;
        }

//        除了构造方法外,避免形参和属性参数一样,否则需要加上this.col
//            public int index(int row, int col) {
//                return row * this.col + col;
//            }

        // 原始位置 -> 下标
        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                // if条件满足时sets减1
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }

    // for test
    public static char[][] generateRandomMatrix(int row, int col) {
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Math.random() < 0.5 ? '1' : '0';
            }
        }
        return board;
    }

    // for test
    public static char[][] copy(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        char[][] ans = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[i][j] = board[i][j];
            }
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int row = 0;
        int col = 0;
        char[][] board1 = null;
        char[][] board2 = null;
        char[][] board3 = null;
        long start = 0;
        long end = 0;

        row = 1000;
        col = 1000;
        board1 = generateRandomMatrix(row, col);
        board2 = copy(board1);
        board3 = copy(board1);

        System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numIslands3(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行结果: " + numIslands1(board2));
        end = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

        System.out.println();

        row = 10000;
        col = 10000;
        board1 = generateRandomMatrix(row, col);
        board3 = copy(board1);
        System.out.println("感染方法、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numIslands3(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");
    }
}
