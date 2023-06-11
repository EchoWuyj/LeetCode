package alg_03_leetcode_top_zcy.class_11_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 15:38
 * @Version 1.0
 */
public class Problem_0116_Note_Queue {
    /*
           →  4  3  2  1 →
         rear         front
         
        入队列:add(),offer()
            相同:未超出容量,从队尾压入元素,返回压入的那个元素.
            区别:在超出容量时,add()方法会对抛出异常,offer()返回false
        
        出队列:remove(),poll()
            相同:容量大于0的时候,删除并返回队头被删除的那个元素.
            区别:在容量为0的时候,remove()会抛出异常,poll()返回null
        
        获取队头元素(不删除):element(),peek()
            相同:容量大于0的时候,都返回队头元素.但是不删除.
            区别:容量为0的时候,element()会抛出异常,peek()返回null.

     */
}
