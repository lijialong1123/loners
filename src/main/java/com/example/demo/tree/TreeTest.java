package com.example.demo.tree;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author LIJIALONG1
 */
public class TreeTest {

    public static TreeNode createBinaryTree(LinkedList<Integer> list) {
        TreeNode node = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        Integer data = list.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftTree = createBinaryTree(list);
            node.rightTree = createBinaryTree(list);
        }
        return node;
    }


    private void inOrderTraverse(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                System.out.println(node.getVal());
                stack.push(node);
                node = root.getLeftTree();
            } else {
                TreeNode temp = stack.pop();
                System.out.println(temp.getVal());
                node = temp.getRightTree();
            }
        }
    }

    /**
     * 两个树是否相等
     */
    public boolean isSameTree(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        } else if (tree1 != null && tree2 != null) {
            return tree1.val == tree2.val && isSameTree(tree1.leftTree, tree2.leftTree) && isSameTree(tree1.rightTree, tree2.rightTree);
        }
        return false;
    }

    /**
     * 两个树是否相等 （非递归）
     */
    public boolean isSameTree1(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        } else if (tree1 == null || tree2 == null) {
            return false;
        }
        Stack<TreeNode> treeStack1 = new Stack<>();
        treeStack1.push(tree1);
        Stack<TreeNode> treeStack2 = new Stack<>();
        treeStack2.push(tree2);
        TreeNode temp1, temp2;
        while (!treeStack1.isEmpty() && !treeStack2.isEmpty()) {
            temp1 = treeStack1.pop();
            temp2 = treeStack2.pop();
            if (temp1.val != temp2.val) {
                return false;
            }
            if (temp1.leftTree != null) {
                treeStack1.push(temp1.leftTree);
            }
            if (temp1.rightTree != null) {
                treeStack1.push(temp1.rightTree);
            }
            if (temp2.leftTree != null) {
                treeStack2.push(temp2.leftTree);
            }
            if (temp2.rightTree != null) {
                treeStack2.push(temp2.rightTree);
            }

        }
        return true;
    }

    public boolean isSubtree(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null) {
            return false;
        }
        if (isSameTree(tree1, tree2)) {
            return true;
        }
        return isSubtree(tree1.leftTree, tree2) || isSubtree(tree1.rightTree, tree2);
    }

    /**
     * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return insert(nums, 0, nums.length - 1);
    }

    private TreeNode insert(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.leftTree = insert(nums, start, mid - 1);
        node.rightTree = insert(nums, mid + 1, end);
        return node;
    }

    /**
     * 对称的二叉树
     */
    public boolean isSymmetrical(TreeNode tree) {
        if (tree == null) {
            return true;
        }
        return comValue(tree.leftTree, tree.rightTree);
    }

    private boolean comValue(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        return left.val == right.val && comValue(left.leftTree, right.rightTree) && comValue(left.rightTree, right.leftTree);
    }

    /**
     * 对称的二叉树(队列)
     */
    public boolean isSymmetrical1(TreeNode tree) {
        return check(tree, tree);
    }

    public boolean check(TreeNode tree1, TreeNode tree2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree1);
        queue.offer(tree2);
        while (!queue.isEmpty()) {
            tree1 = queue.poll();
            tree2 = queue.poll();
            if (tree1 == null && tree2 == null) {
                continue;
            }
            if ((tree1 == null || tree2 == null) || (tree1.val != tree2.val)) {
                return false;
            }
            queue.offer(tree1.leftTree);
            queue.offer(tree2.rightTree);

            queue.offer(tree1.rightTree);
            queue.offer(tree2.leftTree);

        }
        return true;
    }


    /**
     * 对称的二叉树(栈)
     */
    public boolean isSymmetrical2(TreeNode tree) {
        return false;
    }

    public boolean check2() {

        return false;
    }


    /**
     * 二叉树的深度
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.leftTree), maxDepth(root.rightTree)) + 1;
    }

    /**
     * 平衡二叉树
     */
    public boolean isBalance(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(maxDepth(root.leftTree) - maxDepth(root.rightTree)) <= 1) {
            return isBalance(root.leftTree) && isBalance(root.rightTree);
        }
        return false;
    }

    /**
     * 二叉树的镜像（递归）
     */
    private TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.leftTree;
        root.leftTree = mirrorTree(root.rightTree);
        root.rightTree = mirrorTree(temp);
        return root;
    }

    /**
     * 前序遍历
     */
    private TreeNode mirrorTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.leftTree != null) {
                stack.push(node.leftTree);
            }
            if (node.rightTree != null) {
                stack.push(node.rightTree);
            }
            TreeNode temp = node.leftTree;
            node.leftTree = node.rightTree;
            node.rightTree = temp;
        }
        return root;
    }

    private void BFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode.leftTree != null) {
                queue.offer(treeNode.leftTree);
            }
            if (treeNode.rightTree != null) {
                queue.offer(treeNode.rightTree);
            }
        }
    }

    private void DFS(TreeNode root) {

    }

    /**
     * 翻转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.leftTree;
            root.leftTree = invertTree(root.rightTree);
            root.rightTree = invertTree(temp);
        }
        return root;
    }

    /**
     *
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            double sum = 0;
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    sum += node.val;
                    if (node.leftTree != null) {
                        queue.add(node.leftTree);
                    }
                    if (node.rightTree != null) {
                        queue.add(node.rightTree);
                    }
                }
            }
            res.add(sum / len);
        }
        return res;
    }

    /**
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> data = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode tempNode = queue.poll();
                data.add(tempNode.val);
                if (tempNode.leftTree != null) {
                    queue.add(tempNode.leftTree);
                }
                if (tempNode.rightTree != null) {
                    queue.add(tempNode.rightTree);
                }
            }
            res.add(data);
        }

        return res;
    }

    /**
     * 二叉树的最小深度  dfs
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.leftTree == null && root.rightTree == null) {
            return 1;
        }
        int minDep = Integer.MAX_VALUE;
        if (root.leftTree != null) {
            minDep = Math.min(minDepth(root.leftTree), minDep);
        }
        if (root.rightTree != null) {
            minDep = Math.min(minDepth(root.rightTree), minDep);
        }
        return minDep + 1;
    }

    /**
     * 二叉树的最小深度  bfs
     */
    public int minDepth1(TreeNode root) {
        return 0;
    }

    /**
     * 二叉树前序遍历(递归)
     */
    public static List<Integer> preOrderTraversal(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        preOrderHelper(node, list);
        return list;
    }

    public static void preOrderHelper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrderHelper(root.leftTree, list);
        preOrderHelper(root.rightTree, list);
    }

    /**
     * 二叉树前序遍历（非递归）
     */
    public static List<Integer> preOrderTraversalWithStack(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node.val);
                stack.push(node);
                node = node.leftTree;
            }
            node = stack.pop();
            node = node.rightTree;
        }
        return list;
    }

    /**
     * 二叉树中序遍历(递归)
     */
    public static List<Integer> inOrderTraversal(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        inOrderHelper(node, list);
        return list;
    }

    public static void inOrderHelper(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inOrderHelper(node.leftTree, res);
        res.add(node.val);
        inOrderHelper(node.rightTree, res);
    }

    /**
     * 二叉树中序遍历（非递归）
     */
    public static List<Integer> inOrderTraversalWithStack(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.leftTree;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.rightTree;
        }
        return list;
    }

    /**
     * 二叉树后序遍历(递归)
     */
    public static List<Integer> postOrderTraversal(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        postOrderHelper(node, list);
        return list;
    }

    public static void postOrderHelper(TreeNode node, List<Integer> res) {
        if (node == null) {

            return;
        }
        postOrderHelper(node.leftTree, res);
        postOrderHelper(node.rightTree, res);
        res.add(node.val);
    }

    /**
     * 布隆过滤器
     * 二叉树后序遍历（非递归）
     */
    public static List<Integer> postOrderTraversalWithStack(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.leftTree;
            }
            node = stack.pop();
            //后序遍历的过程中在遍历完左子树跟右子树prev都会回到根结点。所以当前不管是从左子树还是右子树回到根结点都不应该再操作了，应该退回上层。
            //如果是从右边再返回根结点，应该回到上层。
            //主要就是判断出来的是不是右子树，是的话就可以把根节点=加入到list了
            if (node.rightTree == null || node.rightTree == prev) {
                list.add(node.val);
                prev = node;
                node = null;
            } else {
                stack.push(node);
                node = node.rightTree;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.leftTree = new TreeNode(9);
        root.rightTree = new TreeNode(4);
        root.rightTree.leftTree = new TreeNode(5);
        root.rightTree.rightTree = new TreeNode(7);
        List<Integer> integers = postOrderTraversalWithStack(root);
        for (Integer data : integers) {
            System.out.println(data);
        }
    }

}
