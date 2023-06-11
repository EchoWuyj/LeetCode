package alg_03_leetcode_top_zcy.class_11_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 11:12
 * @Version 1.0
 */
public class Problem_0102_Note_LinkedList {
      /*

         KeyPoint LinkedList 数据结构
         LinkedList有next和prev指针
         null ← node1 ⇋  node2 ⇋ node3 → null
                first(head)       last(tail)
         注意: first 和 last 有严格左右关系的

         KeyPoint LinkedList 常见API
         void add(int index, E element)  在此列表中指定的位置插入指定的元素
         -----------------------------------------------------------------
         void addFirst(Object o) ⇋ offerFirst ⇋ push 将指定元素插入集合的开头
         void addLast(Object o) ⇋ offerLast ⇋ offer ⇋ add 将指定元素添加到集合的结尾
         -----------------------------------------------------------------
         Object getFirst() 返回集合的第一个元素  => 遇到null,抛出异常
         Object getLast() 返回集合的最后一个元素

         Object peekFirst() ⇋ peek 获取集合的第一个元素  => 遇到null,返回null
         Object peekLast() 获取集合的最后一个元素
         --------------------------------------------------------------------
         Object removeFirst() ⇋ pop 移除并返回集合的第一个元素 => 遇到null,抛出异常
         Object removeLast() 移除并返回集合的最后一个元素

         Object pollFirst() ⇋ poll 移除并返回集合的第一个元素 => 遇到null,返回null
         Object pollLast() 移除并返回集合的最后一个元素

         KeyPoint 双端队列
         进栈/出栈 ⇋ 队头  ...   队尾 ⇋ 进栈/出栈
      */
}
