package alg_02_体系班_wyj.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-09-20 23:17
 * @Version 1.0
 */

public class HeapGreater<T> {
    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<T> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T t) {
        return indexMap.containsKey(t);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T t) {
        heap.add(t);
        indexMap.put(t, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T res = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(res);
        heap.remove(--heapSize);
        heapify(0);
        return res;
    }

    public void remove(T t) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(t);

        indexMap.remove(t);
        heap.remove(--heapSize);

        if (t != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public List<T> getAllElements() {
        List<T> result = new ArrayList<>();
        for (T element : heap) {
            result.add(element);
        }
        return result;
    }

    public void resign(T t) {
        heapInsert(indexMap.get(t));
        heapify(indexMap.get(t));
    }

    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int best = (left + 1) < heapSize
                    && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = 2 * index + 1;
        }
    }

    public void swap(int i, int j) {
        T ti = heap.get(i);
        T tj = heap.get(j);
        heap.set(i, tj);
        heap.set(j, ti);
        indexMap.put(ti, j);
        indexMap.put(tj, i);
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

        // ----------------------------------------

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

