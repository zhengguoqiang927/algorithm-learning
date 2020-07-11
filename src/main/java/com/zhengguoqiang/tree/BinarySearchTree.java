package com.zhengguoqiang.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树
 */
public class BinarySearchTree {
    static class Node{
        int val;
        Node left;
        Node right;
        int height;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 先序遍历
     * @param root
     */
    public static void printByBefore(Node root){
        if (root == null) return;
        System.out.print(root.val + " -> ");
        printByBefore(root.left);
        printByBefore(root.right);
    }

    /**
     * 按层遍历二叉树
     * @param root
     */
    public static void printByLevel(Node root){
        if (root == null) return;
        Node last = root,nlast = null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(last);
        int level = 0;
        System.out.print("Level " + (++level) + " : ");
        while (!queue.isEmpty()){
            Node head = queue.poll();
            System.out.print(head.val + " ");
            if (head.left != null){
                queue.offer(head.left);
                nlast = head.left;
            }
            if (head.right != null){
                queue.offer(head.right);
                nlast = head.right;
            }
            if (head == last && !queue.isEmpty()){
                System.out.print("\nLevel " + (++level) + " : ");
                last = nlast;
            }
        }
    }

    /**
     * 二叉树深度
     * @param root
     * @return
     */
    public static int deep(Node root){
        if (root == null) return 0;
        int left = deep(root.left);
        int right = deep(root.right);
        return Math.max(left,right) + 1;
    }

    /**
     * 判断二叉树是否为平衡二叉树
     * 平衡二叉树的特点：左右子树的高度相差不超过1
      * @param root
     * @return
     */
    public static boolean isBalance(Node root){
        /* 判断每个结点的左右子结点的高度差是否小于1，这种方法每个结点都会被重复访问多次，所以效率比较低
        if (root == null) return true;
        int left = deep(root.left);
        int right = deep(root.right);
        int diff = left - right;
        if (diff > 1 || diff < -1) return false;
        return isBalance(root.left) && isBalance(root.right);
        */

        //高效的方式是采用后序遍历，先求出该结点的左右子树的高度，在判断该结点是否是平衡的，这种方式是自下而上的判断，所以结点不会被重复访问
        return calculateHeight(root) != -1;
    }

    public static int calculateHeight(Node root){
        if (root == null) return 0;
        int left = calculateHeight(root.left);
        if (left == -1) return -1;
        int right = calculateHeight(root.right);
        if (right == -1) return -1;

        return  Math.abs(left-right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node night = new Node(9);
        root.left = two;
        root.right =three;
        two.left = four;
        two.right = five;
        three.left = six;
        three.right = seven;
        four.left = eight;
        eight.left = night;
        printByLevel(root);
        System.out.println("\n树的深度：" + deep(root));
        System.out.println("树是否平衡：" + isBalance(root));
        printByBefore(root);
    }
}
