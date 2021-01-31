//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 131 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
//Round1 递归
//同二叉树主要区别在于 for-i / for-each循环取出所有孩子结点 而不限定左右
/*class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<Integer>();
        nPreOrder(root, list);
        return list;
    }
    public void nPreOrder(Node root,List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        *//*这里用 for-i 比 for-each快*//*
        for(int i=0; i<root.children.size(); i++)
            nPreOrder(root.children.get(i), list);
        *//* for(Node n : root.children) {
            nPreOrder(n, list);
        }*//*
    }
}*/

//Round1 迭代
/*class Solution {
    public List<Integer> preorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }
}*/

//leetcode submit region end(Prohibit modification and deletion)
//Round2 递归
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<Integer>();
        nPreOrder(root, list);
        return list;
    }
    public void nPreOrder(Node root,List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            nPreOrder(root.children.get(i),list);      //注意 get(i)
        }
    }
}
