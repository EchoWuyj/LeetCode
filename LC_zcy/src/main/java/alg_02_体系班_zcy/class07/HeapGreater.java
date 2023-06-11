package alg_02_体系班_zcy.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-09-19 12:03
 * @Version 1.0
 */

public class HeapGreater<T> { // 定义泛型

    // 手动改写堆(非常重要)！
    // 加强堆:笔试中大量出现的,面试中也是大量问到!
    // 统提供的堆无法做到的事情
    // 1)已经入堆的元素,如果参与排序的指标方法变化,系统提供的堆无法做到时间复杂度O(logN)调整！
    //   因为没有反向索引表,无法通过元素定位索引,故需要遍历数组找到元素的索引,再去进行堆调,所以
    //   是O(N)时间复杂度
    // 2)系统提供的堆只能弹出堆顶,做不到自由删除任何一个堆中的元素(遍历找到都得O(N))
    //   或者说,无法在时间复杂度O(logN)内完成！一定会高于O(logN)
    // 3)根本原因:无反向索引表

    // 反向索引表,记录元素在堆中的位置
    // [a,b,c]
    // a -> 0
    // b -> 1
    // c -> 2

    //-----------------------------------------------------

    // 动态数组
    private ArrayList<T> heap;

    // T一定要是非基础类型,若直接使用基础类型,相同的值可能存在覆盖的情况
    // 故基础类型需求则要包一层,使用其内存地址值来存到HashMap中
    // private HashMap<Inner<T>, Integer> indexMap;
    private HashMap<T, Integer> indexMap;

    private int heapSize;

    // 比较器
    // Comparator<? super T>表示该比较器中存的T的父类,包括T自己
    // 因为存的都是类型T的父类,所以如果去添加T类或者T类子类的元素,肯定是可以的
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        // 比较器只能从外部传入
        // 通过外部的比较器来确定是大根堆还是小根堆
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    // O(1)
    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        // 获取动态数组0索引位置元素
        return heap.get(0);
    }

    public void push(T obj) {
        // 加到heap最后
        heap.add(obj);
        indexMap.put(obj, heapSize);
        // 堆上移,heapSize+1
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        // heapSize需要-1,否则越界
        swap(0, heapSize - 1);
        // 将反向索引表中元素(key)对应的下标移除
        indexMap.remove(ans);
        // 同步删除
        heap.remove(--heapSize);
        // 堆下沉
        heapify(0);
        return ans;
    }

    // 删除某个元素(任意元素)
    public void remove(T obj) {
        // 获取堆最后一个元素
        T replace = heap.get(heapSize - 1);
        // 获取删除元素索引
        int index = indexMap.get(obj);

        // 反向索引同步删除
        indexMap.remove(obj);
        // 堆减少1个大小
        heap.remove(--heapSize);

        // 删的就是最后一个元素,直接删除即可
        // 删的不是最后一个元素,继续调整
        if (obj != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            // 交换后元素的位置进行堆调,保持原来的堆结构
            resign(replace);
        }
    }

    // 某个元素的值发生变化(变大或变小)
    // 重新调整堆,保证有序
    public void resign(T obj) {
        // 要么堆上移,要么堆下沉,两行代码只执行一个
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    // 请返回堆上的所有元素O(N)
    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }

    private void heapInsert(int index) {
        // 使用定义的比较器进行比较大小
        // 相当于将原来比较的方法进行替换了
        // 这里默认是小根堆,所以使用的<0
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize
                    && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }

    // 堆中i和j位置交换,在反向索引表中同步变动
    private void swap(int i, int j) {
        // 堆交换
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        // 反向索引表同步交换
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }

    public static class Node {
        int value;

        public Node(int value) {
            this.value = value;
        }

        // 想要直接显示,而不是显示内存地址值,需要重写toString方法
        @Override
        public String toString() {
            return value + "";
        }
    }

    public static class NodeUpComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static class NodeDownComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.value - o1.value;
        }
    }

    public static void main(String[] args) {

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        // 比较器o1.value-o2.value,则按照小顶堆方式输出
        HeapGreater<Node> minheap = new HeapGreater<>(new NodeUpComparator());

        System.out.println(minheap.isEmpty());
        System.out.println("========================");
        minheap.push(n1);
        minheap.push(n2);
        minheap.push(n3);
        minheap.push(n4);
        minheap.push(n5);
        minheap.push(n6);
        minheap.push(n7);
        System.out.println(minheap.getAllElements().toString()); // [1, 2, 3, 4, 5, 6, 7]
        System.out.println("========================");
        minheap.remove(n3);
        minheap.remove(n5);
        System.out.println(minheap.getAllElements()); // [1, 2, 6, 4, 7]
        minheap.remove(n2);
        System.out.println(minheap.getAllElements()); // [1, 4, 6, 7]'

        System.out.println("========================");

        // KeyPoint 注意事项
        //  大顶堆和小顶堆并不是指输出结果,按照从小到大或者从大到小进行排序的,这里不是堆排序
        //  而是按照完全二叉树结构画出来,满足大顶堆或者小顶堆的结构而已

        HeapGreater<Node> maxheap = new HeapGreater<>(new NodeDownComparator());

        System.out.println(maxheap.isEmpty());
        System.out.println("========================");
        maxheap.push(n1);
        maxheap.push(n2);
        maxheap.push(n3);
        maxheap.push(n4);
        maxheap.push(n5);
        maxheap.push(n6);
        maxheap.push(n7);
        System.out.println(maxheap.getAllElements().toString()); // [7, 4, 6, 1, 3, 2, 5]
        System.out.println("========================");
        maxheap.remove(n3);
        maxheap.remove(n5);
        System.out.println(maxheap.getAllElements()); // [7, 4, 6, 1, 2]
        maxheap.remove(n2);
        System.out.println(maxheap.getAllElements()); // [7, 4, 6, 1]
    }
}
