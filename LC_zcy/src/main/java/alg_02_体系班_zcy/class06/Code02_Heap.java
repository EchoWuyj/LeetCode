package alg_02_体系班_zcy.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-18 16:09
 * @Version 1.0
 */
public class Code02_Heap {

    // 自定义实现堆(大根堆)
    // 小根堆只要将不等号方向调换即可

    // 利用数组中连续的一段位置,转化成完全二叉树
    // i位置,左孩子位置:2*i+1,右孩子:2*i+2.父节点位置:(i-1)/2

    // 堆概念
    // 1)堆是完全二叉树
    // 2)堆:大根堆or小根堆
    //   大根堆:在一颗完全二叉树中,每一颗子树整体最大值是root值(根节点大于或等于左右节点的值)
    //   小根堆:在一颗完全二叉树中,每一颗子树整体最小值是root值

    public static class MyMaxHeap {
        private int[] heap;
        // 定义数组的大小,之后就不能变化了
        // 因为刚开始定义会报错因为没有使用上
        private final int limit;

        // 动态记录堆的大小
        private int heapSize;

        // 需要通过在外部传入参数
        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            // 初始化过程headSize为0
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        // 加入元素
        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            // 数组大小和索引
            // 1)若数组limit=5,数组大小是5,索引表示范围0-4
            //   此时,若heapSize=5,表示已经将数组使用完了,所以heapSize最大为4
            // 2)若heapSize=3,表示堆大小为3,数组0,1,2已经被使用,则新来元素为heap[3]
            //   故heapSize值比索引值大1(因为索引是从0开始的)
            heap[heapSize] = value;

            // 新来的元素进行堆上移动
            // 同时heapSize还得加1,堆进行扩容
            heapInsert(heap, heapSize++);
        }

        // 弹出元素
        // 用户此时,让你返回最大值,并且在大根堆中,把最大值删掉

        // 剩下的数,依然保持大根堆组织
        public int pop() {
            int ans = heap[0];
            // 使用堆中最后一个数和heap[0]交换
            // 此时heapSize已经减1,堆界限减1,
            // 堆界限以外的数组中的元素不认为是堆结构元素
            // 因为heapSize值比索引值大1,所以是--heapSize
            swap(heap, 0, --heapSize);
            // 堆调,需要保持原来的堆结构
            heapify(heap, 0, heapSize);

            return ans;
        }

        // 堆上移(从叶子)
        // 新加进来的数,现在停在了index位置,请依次往上移动
        // 边界条件:移动到0位置,或者干不掉自己的父亲了,停止！
        // 时间复杂度O(logN),等价于完全二叉树的高度,走的路径从叶节点到root
        private void heapInsert(int[] arr, int index) {
            // 当前数:[index]
            // 父节点:[index-1]/2
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                // 变成父节点的索引,再去往前判断
                index = (index - 1) / 2;
            }
        }

        // 堆下沉(从root)
        // 从index位置,往下看,不断的下沉
        // 停:较大的孩子都不再比index位置的数大or已经没孩子了
        // 时间复杂度O(logN),等价于完全二叉树的高度,走的路径从root往下走
        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            // 如果有左孩子,有没有右孩子,可能有,可能没有！
            // 当左孩子越界之后,直接跳出while循环,表示已经沉到底了
            while (left < heapSize) {

                // 1)左右孩子比较,选左右中大的孩子(largest)
                //   有右孩子,并且右>左,将右给largest
                //   arr[left + 1] == arr[left]则取left也是一样
                int largest = (left + 1) < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;

                // 2)largest和父节点(index)比较,将大值索引给largest
                largest = arr[largest] > arr[index] ? largest : index;

                // 左右孩子都没有父大,则不需要下沉了
                if (largest == index) {
                    break;
                }

                // 左右孩子比父大,index和较大孩子要互换
                // 交换swap只是交换数组值,等价于节点交换了,但没有交换索引
                swap(arr, largest, index);

                // 较大值对应索引(largest)替换成index(父节点)
                // 继续考虑该父节点的左右孩子,依次循环
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    //-------------------------------------------------

    // for test
    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }
    }

    // for test
    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    // for test
    public static void main(String[] args) {

        // PriorityQueue默认是小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        // 5,3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);

        // 只是获取堆顶元素,但是不移除
        System.out.println(heap.peek());
        while (!heap.isEmpty()) {
            // 获取堆顶元素,同时将其移除
            System.out.println(heap.poll());
        }

        //-------------------------------------------------------------------

        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
