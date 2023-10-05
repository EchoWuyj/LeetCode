package algorithm._13_dfs_bf;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2022-03-30 18:13
 * @Version 1.0
 */
public class LeetCode_297_TreeSerialization {

    //方法一:DFS,先序遍历
    //序列化方法
    public String serialize(TreeNode root) {
        //定义一个StringBuffer来保存序列化结果
        StringBuffer result = new StringBuffer();

        result.append("[");

        //单独定义递归方法,实现深度优先搜索,避免和序列化方法混淆在一起(涉及额外的数据结构定义)
        //否则每次递归调用时,都是会new一个StringBuffer()对象,不是很合适
        dfs_serialize(root, result);

        //将最后一位的,删除
        result.deleteCharAt(result.length() - 1);
        result.append("]");

        return result.toString();
    }

    //递归方法,实现深度优先搜索
    //将根节点传入进去,同时将result传入,为了不停地在后面进行追加
    private void dfs_serialize(TreeNode root, StringBuffer result) {
        //基准情况
        //需要考虑的是,当叶子节点为null时,同样是需要将null加入其中的,不仅仅是针对root节点
        if (root == null) {
            //根节点为null,则在result后面追加null
            result.append("null,");
            return;
        }

        //将当前根节点的值序列化添加到result
        result.append(root.val + ",");

        //递归处理左右子树
        dfs_serialize(root.left, result);
        dfs_serialize(root.right, result);
    }

    //反序列化

    public TreeNode deserialize(String data) {
        //"[1,2,3,null,null,4,5]"

        //首先将数据进行切分,得到每个节点的值,保存成一个链表
        String[] dataArr = data.split(",");
        LinkedList<String> dataList = new LinkedList<>(Arrays.asList(dataArr));

        String first = dataList.getFirst();

        //删除方括号
        //getFirst()方法获取[1这个字符串
        //想在[1基础上截取1,即从1索引开始截取
        String firstElement = dataList.getFirst().substring(1);

        //将原来的头节点移除
        dataList.removeFirst();
        //添加新的头节点
        dataList.addFirst(firstElement);

        //substring方法结束索引是不包含的,所以length()-1就是最后一个索引,且不包含就将其截取了
        String lastElement = dataList.getLast().substring(0, dataList.getLast().length() - 1);
        dataList.removeLast();
        dataList.addLast(lastElement);

        return dfs_deserialize(dataList);
    }

    //实现递归方法,辅助反序列化
    //序列化过程是深度优先遍历,反序列化同样也是深度优先遍历,将树一层一层恢复出来的过程
    private TreeNode dfs_deserialize(LinkedList<String> dataList) {
        //准情况,遇到null,就是叶子节点的子节点,返回null
        if (dataList.getFirst().equals("null")) {
            //处理完一个就删除一个
            dataList.removeFirst();
            return null;
        }

        //获取当前节点
        TreeNode node = new TreeNode(Integer.parseInt(dataList.getFirst()));
        //处理完就删除
        dataList.removeFirst();

        //递归调用,定义当前节点的左右子节点
        //注意递归调用中得是以dataList第一个节点作为当前左子树的根进行处理,这样就得处理完一个节点就得删除removeFirst
        node.left = dfs_deserialize(dataList);
        node.right = dfs_deserialize(dataList);

        return node;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        LeetCode_297_TreeSerialization treeSerialization = new LeetCode_297_TreeSerialization();
        System.out.println(treeSerialization.serialize(node1));

        TreeNode newTree = treeSerialization.deserialize(treeSerialization.serialize(node1));
    }
}
