package alg_01_ds_dm._02_tree._02_bst;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-13 10:15
 * @Version 1.0
 */

// KeyPoint 泛型 E 继承了 Comparable，而不是 BST 继承了 Comparable
//      从而保证:二叉查找树中的节点类型的值是可以比较的
//      Comparable 也是接口，里面传入泛型 E，明确比较的对象
public class BST<E extends Comparable<E>> {

    // 使用链式存储来实现树节点，这里 TreeNode 中没有使用泛型 <>
    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;

        public TreeNode(E data) {
            this.data = data;
            // 没有设置 left 和 right，则默认是 null
        }
    }

    // 根节点
    private TreeNode root;

    // 二叉查找树有多少个节点
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // KeyPoint 1 插入操作
    // 时间复杂度：O(logn)
    public void add(E e) {
        if (root == null) {
            root = new TreeNode(e);
        } else {
            // cur 用来找到新节点应该插入的位置
            TreeNode curr = root;
            // cur 不为 null，cur 一直找合适的位置
            while (curr != null) {
                // 二叉查找树中不会有重复值
                if (e.compareTo(curr.data) == 0) { // 相等
                    return;
                    // e 是新加入节点的值，curr.data 当前 cur 指针指向的节点值
                    // e.compareTo(curr.data) < 0 <=> e - curr.data < 0
                } else if (e.compareTo(curr.data) < 0 && curr.left == null) {
                    curr.left = new TreeNode(e);
                    size++;
                    return; // 结束当前循环

                    // KeyPoint if 判断条件比较复杂，没法直接使用 else 进行替代
                } else if (e.compareTo(curr.data) > 0 && curr.right == null) {
                    curr.right = new TreeNode(e);
                    size++;
                    return;
                }

                // 经过上面的 if 判断之后，此时 curr.left != null 和 curr.right != null => cur 左右移动指针
                if (e.compareTo(curr.data) < 0) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
        }
    }

    // KeyPoint 补充:修改操作 => 存在问题：set 方法会破坏二叉查找树的性质
    // 可以使用先删除再添加的方式，来代替 set 操作，这样的时间复杂度为 O(logn)
    public void set(E src, E target) {
        if (contains(target)) return;
        TreeNode srcNode = find(src);
        srcNode.data = target;
    }

    // KeyPoint 2 查询操作
    public boolean contains(E target) {
        TreeNode node = find(target);
        // node 不为 null，表示表达式为 true
        return node != null;
    }

    // 时间复杂度：O(logn)
    public TreeNode find(E target) {
        if (root == null) return null;
        TreeNode curr = root;
        // cur 不为 null，就一直遍历，直到找到目标元素
        while (curr != null) {
            if (target.compareTo(curr.data) == 0) {
                return curr;
            } else if (target.compareTo(curr.data) < 0) {
                // target - curr.data < 0
                // => target < curr.data
                // => cur 去 cur 的左子树
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        // 最后没有找到，直接返回 null
        return null;
    }

    // KeyPoint 3 遍历
    // 1 前序遍历
    public List<E> preOrder() { // 不用传入 root 节点，直接使用当前的二叉查找树定义的 root 属性
        List<E> res = new ArrayList<>();
        if (root == null) return res;

        // 1. 初始化栈对象
        Stack<TreeNode> stack = new Stack<>();

        // 2. 将根节点压入栈中
        stack.push(root);

        // 3. 当栈不为空的时候，循环遍历
        while (!stack.isEmpty()) {
            // 3.1 取出栈顶节点
            TreeNode curr = stack.pop();
            // 3.2 处理栈顶节点
            res.add(curr.data);

            // 3.3 先将栈顶节点的右子节点压入栈中，再将左子节点压入栈中
            // 压入栈的目的是为了下一次循环的处理
            // 先压入右子节点的目的是先处理左子节点
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }

        return res;
    }

    // 2 中序遍历
    public List<E> inOrder() {
        ArrayList<E> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode node = stack.pop();
            res.add(node.data);

            curr = node.right;
        }
        return res;
    }

    // 3 后续遍历
    public List<E> postOrder() {
        // List<E> res = new LinkedList<>(); 父类指向子类（向上转型），只能调用父类方法
        // 而父类中是没有 addFirst 方法的，故只能使用  LinkedList<E> 接受
        LinkedList<E> res = new LinkedList<>();

        if (root == null)
            return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.addFirst(curr.data);

            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }

        // 返回值类型是父类，可以将其子类返回
        return res;
    }

    // 4 层序遍历
    public List<List<E>> levelOrder() {
        List<List<E>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            List<E> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                levelNodes.add(curr.data);
                // 将遍历后的节点的左右子节点入队，等到下一轮 while 循环的时候遍历处理
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            res.add(levelNodes);
        }

        return res;
    }

    // KeyPoint 4 最值
    // 1 最小值
    // 时间复杂度：O(logn)
    public E minValue() {
        if (root == null) {
            // 抛出异常使用 throw，不使用 return
            throw new RuntimeException("tree is null");
        }
        // 查找最左位置的节点
        TreeNode min = root;
        while (min.left != null) { // 遍历到 min.left 为 null，但此时 min 却不是 null
            // 一直往左子节点走
            min = min.left;
        }
        return min.data;
    }

    // 2 最大值
    // 时间复杂度：O(logn)
    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        // 查找最右位置的节点
        TreeNode max = root;
        while (max.right != null) {
            // 一直往右子节点走
            max = max.right;
        }
        return max.data;
    }

    // KeyPoint 5 删除操作
    // 1 删除最小值
    // 时间复杂度：O(logn)
    public E removeMin() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }

        // KeyPoint 关键
        //  1 删除操作仅仅使用 min 指针是不够的，还需要 parent 指针
        //  2 将 min 找到的节点的父节点的 left 指针设置为 null

        // 找到 min 值的指针
        TreeNode min = root;
        // 记录 min 值的父节点指针
        TreeNode parent = null;

        // min 从 root 节点开始进行遍历
        while (min.left != null) {
            // min 左走之前，将 parent 指向 min
            parent = min;
            min = min.left;
        }

        // 分情况讨论
        // 1 min 为叶子节点
        // 2 min 为内部节点
        // 3 min 为根节点

        // parent = null，则 min 为根节点
        if (parent == null) {
            root = root.right;
        } else {
            // min 为内部节点或者叶子节点
            // 即使 min.right == null，也是没有关系的
            parent.left = min.right;
        }
        // bug 修复：统一释放 min 节点
        // 删除的节点不能再和原来的二叉查找树有关系了
        min.right = null;

        size--;
        return min.data;
    }

    // 2 删除最大值
    // 时间复杂度：O(logn)
    public E removeMax() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }

        TreeNode max = root;
        TreeNode parent = null;
        while (max.right != null) {
            parent = max;
            max = max.right;
        }

        if (parent == null) { // 删除根节点
            root = root.left;
        } else {
            // KeyPoint 代码具有对称结构
            parent.right = max.left;
        }
        // bug 修复：统一释放 max 节点
        max.left = null;

        size--;
        return max.data;
    }

    // 3 删除一般节点 E 的数值 e
    //   因为删除节点 E 的数值 e 已经知道了，所以就不需要返回 e
    public void remove(E e) {
        if (root == null) return;

        TreeNode curr = root;
        TreeNode parent = null;

        // KeyPoint 删除节点前提:先找到该节点

        // 找到要删除的节点
        while (curr != null && e.compareTo(curr.data) != 0) {
            parent = curr;
            if (e.compareTo(curr.data) < 0) curr = curr.left;
            else curr = curr.right;
            // 可能一直没有找到，最终 cur 指向为 null，故需要在 while 循环中加上 cur != null
            // while 循环中判断条件同时成立，只要其中一个不成立，while 循环终止
        }

        // 如果没有找到需要删除的元素，则直接返回(也可以抛异常，关键看需求)
        if (curr == null) return;

        // 经过上面 if判断，此时 cur 找到要删除的节点

        // KeyPoint 核心逻辑
        //  1 删除节点可能在位置
        //    1.1 叶子节点
        //    1.2 只有左子树
        //    1.3 只有右子树
        //    1.4 左右子树都有
        //  2 每个位置判断 cur 和 parent 关系
        //    2.1 根节点
        //    2.2 parent 左子节点
        //    2.3 parent 右子节点

        // KeyPoint 1 删除叶子节点
        if (curr.left == null && curr.right == null) {
            // 1.1 删除根节点
            if (parent == null) {
                root = null;

                // if 判断中的 parent.left，但是 parent 可能为 null，需要将这种情况考虑在内
                // => 所以上面代码进行了 parent 判空

                // 1.2 左子节点 => 关键:判断 cur 在 parent 的左还是右
            } else if (curr == parent.left) {
                parent.left = null;
                // 1.3 右子节点
            } else if (curr == parent.right) {
                parent.right = null;
            }

            // KeyPoint 省略最后一个 else 子句，则如果前面的条件都不满足，则程序将什么也不做，继续执行程序的下一个语句
            //      这意味着，如果所有的条件都不满足，程序将没有任何反应，可能会导致意外行为或错误，这取决于上下文

            // KeyPoint 2 只有左子树
        } else if (curr.left != null && curr.right == null) {
            // 2.1 删除根节点
            if (parent == null) {
                root = curr.left;
                // 注意：删除的是根节点的话，也需要将当前节点的 left 置空
                curr.left = null;
                // 2.2 左子节点
            } else if (curr == parent.left) {
                parent.left = curr.left;
                curr.left = null;
                // 2.3 右子节点
            } else if (curr == parent.right) {
                parent.right = curr.left;
                curr.left = null;
            }
            // KeyPoint 3 只有右子树
        } else if (curr.left == null && curr.right != null) {
            // 3.1 删除根节点
            if (parent == null) {
                root = curr.right;
                // 注意：删除的是根节点的话，也需要将当前节点的 right 置空
                curr.right = null;
                // 3.2 左子节点
            } else if (curr == parent.left) {
                parent.left = curr.right;
                curr.right = null;
                // 3.3 右子节点
            } else if (curr == parent.right) {
                parent.right = curr.right;
                curr.right = null;
            }
            // KeyPoint  4 左右子树都有
        } else if (curr.left != null && curr.right != null) {

            // KeyPoint 删除节点没有和 parent 扯上关系，因为在代码没有涉及 parent 引用调用方法

            // 1. 找到 curr 的右子树的最小值节点(后继节点)，其中 min 为 cur 右子树的 root 节点
            TreeNode min = curr.right;
            TreeNode minParent = curr;
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }

            // 2. 用后继节点的值覆盖需要删除节点的值
            curr.data = min.data;

            // 3. 删除最小值节点(后继节点)
            // => 先拿到要删除 min 节点的右子树，因为 min 虽然是最左，但是还是有可能有右子树的(包含为 null 的情况)
            //    将 min 删除之后，需要将其右子树重新连接到二叉查找树中(类似下面图二的情况)
            TreeNode right = min.right;

            // bug 修复：这里删除 min 节点，需要区分 min 是父亲节点的右子节点还是左子节点

            // KeyPoint 3.1 要删除 min 节点是父亲节点的右子节点
            if (minParent.right == min) {
                /*
                比如我们要删除以下树的节点 66
                        33
                     /      \
                   22        66
                           /    \
                         35      70
                                   \
                                    99
                那么这个时候：minParent = 66，min = 70，即 min 是父亲节点的右子节点
                */
                // 将 min 的右子树挂到父亲节点的右子树中
                minParent.right = right;
                // KeyPoint 3.2 要删除 min 节点是父亲节点的左子节点
            } else {
                /*
                比如我们要删除以下树的节点 66
                        33
                     /      \
                   22        66
                           /    \
                         35      70
                               /   \
                             68     99
                               \
                                69
                那么这个时候：minParent = 70，min = 68，即 min 是父亲节点的左子节点
                */
                // 将 min 的右子树挂到父亲节点的左子树中
                minParent.left = right;
            }
            // 删掉 min 的右子树，这样可以使得 min 节点从树中断开
            min.right = null;
        }

        // bug 修复：需要维护好 size
        // KeyPoint 凡是增删操作都是要维护好 size 的
        size--;
    }

    // KeyPoint 3 remove 方法 => 优化(推荐使用)
    // 时间复杂度：O(logn)
    public void remove1(E e) {
        if (root == null) return;

        TreeNode curr = root;
        TreeNode parent = null;

        // 找到要删除的节点
        while (curr != null && e.compareTo(curr.data) != 0) {
            parent = curr;
            if (e.compareTo(curr.data) < 0) curr = curr.left;
            else curr = curr.right;
        }

        // 如果没有找到需要删除的元素，则直接返回
        if (curr == null) return;

        // KeyPoint 左右子树都有情况，删除 cur 节点 => 删除 cur 右子树的 min => 转化成删除叶子节点
        if (curr.left != null && curr.right != null) {
            // 1. 找到 curr 的右子树的最小值节点
            TreeNode min = curr.right;
            TreeNode minParent = curr;
            // 一直向左找
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }
            // 2. 覆盖需要删除节点的值为最小值
            curr.data = min.data;

            // 3. 移动指针 cur 和 parent 指针，将删除最小值节(后继)点转化叶子节点
            // 将 cur 指向 min (叶子节点);将 parent 指向 minParent
            curr = min;
            parent = minParent;
        }

        // 删除节点可能是:叶子节点或者仅有一个子树

        // child 用于存储需要删除节点的子节点(包含:左子节点，右子节点)
        TreeNode child;
        if (curr.left != null) {
            child = curr.left;
            // 注意：删除的是根节点的话，也需要将当前节点的 left 置空
            curr.left = null;
        } else if (curr.right != null) {
            child = curr.right;
            // 注意：删除的是根节点的话，也需要将当前节点的 right 置空
            curr.right = null;
        } else {
            // KeyPoint 经过前面 if 的判断之后，最后的 else 只能是
            //      curr.left == null && curr.right == null  => 时刻深刻理解 if 逻辑
            child = null;
        }

        // KeyPoint 优化:将 1,2,3,4 种情况共同的部分抽取出来，放在一起进行统一处理
        if (parent == null) {  // 1 根节点
            root = child;
        } else if (curr == parent.left) { // 2 左子节点
            parent.left = child;
        } else if (curr == parent.right) { // 3 右子节点
            parent.right = child;
        }

        // bug 修复：需要维护好 size
        size--;
    }
}
