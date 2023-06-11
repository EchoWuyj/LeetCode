package alg_02_体系班_zcy.class15;

// 本题为 leetcode 原题
// 测试链接: https://leetcode.cn/problems/bLyHh0/
// 可以直接通过

public class Code01_FriendCircles {

    // 班上有N名学生.其中有些人是朋友,有些则不是.他们的友谊具有是传递性.
    // 如果已知A是B的朋友,B是C的朋友,那么我们可以认为A也是C的朋友.
    // 所谓的朋友圈,是指所有朋友的集合

    // 给定一个N*N的矩阵M,表示班级中学生之间的朋友关系
    // 如果M[i][j]=1,表示已知第i个和j个学生互为朋友关系,否则为不知道
    // 你必须输出所有学生中的已知的朋友圈总数

    // 输入
    //  [[1,1,0],
    //  [1,1,0],
    //  [0,0,1]]
    // 输出:2
    //解释：已知学生0和学生1互为朋友,他们在一个朋友圈
    //     第2个学生自己在一个朋友圈.所以返回 2

    public static int findCircleNum(int[][] M) {
        int N = M.length;
        // {0},{1},{2}..{N-1}
        // 班上有N名学生已经是确定的,所以直接传入N,每个学生是一个小集合
        UnionFind unionFind = new UnionFind(N);
        // 只是遍历矩阵的右上半区域,且不包括对角线元素
        // (0,1),(0,2)...
        // (1,2),(2,3)...
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // i和j互相认识
                if (M[i][j] == 1) {
                    // i,j所在集合合并
                    // 若(0,1)和(0,3)满足,则将该俩合并
                    unionFind.union(i, j);
                }
            }
        }
        // 调用的是方法,而不是属性
        return unionFind.sets();
    }

    // 并查集(数组实现) 推荐
    // 使用HashMap构造的并查集,常数时间会慢,并不是时间复杂度慢,时间复杂度还是O(1)
    public static class UnionFind {
        // parent[i] = k
        // i的父亲是k,索引是子节点,值是父节点
        private int[] parent;
        // size[i] = k
        // 如果i是代表节点,size[i]才有意义,表示i所在的集合大小是多少,否则无意义
        private int[] size;
        // 辅助结构(栈)
        private int[] help;
        // 一共有多少个集合
        private int sets;

        // 形参传入数组大小N
        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            //
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 从i开始一直往上,往上到不能再往上,即为代表节点,此时返回
        // 同时,这个过程要做路径压缩
        public int find(int i) {
            int hi = 0;
            // i不为自己父节点
            while (i != parent[i]) {
                // 使用辅助数组,将沿途遇到的节点都记录下来
                // 数组模拟栈,关于指针的移动需要小心,指针是高于数组元素一格位置的
                help[hi++] = i;
                i = parent[i];
            }

            // 路径压缩
            // KeyPoint 记住这种for循环的书写格式
            // 1)一开始是hi--形式,相当于先减了1,再去执行for循环
            //   for循环中3个;相当于是3个语句,hi--;hi已经减1了
            // 2)因为指针是高于数组元素一格位置的,所以得先修改hi指针
            for (hi--; hi >= 0; hi--) {
                // 里面是索引值,外层是数组值
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            // 找到代表节点
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        // 类中的方法不用static修饰
        public int sets() {
            return sets;
        }
    }
}
