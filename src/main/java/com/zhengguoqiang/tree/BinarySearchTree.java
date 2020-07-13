package com.zhengguoqiang.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
     * 先序遍历递归方式
     * @param root
     */
    public static void printByBefore(Node root){
        if (root == null) return;
        System.out.print(root.val + " -> ");
        printByBefore(root.left);
        printByBefore(root.right);
    }

    /**
     * 先序遍历非递归方式
     * @param root
     */
    public static void preOrder(Node root){
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            Node node = stack.pop();
            System.out.print(node.val + " -> ");
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    /**
     * 中序遍历非递归
     * @param root
     */
    public static void midOrder(Node root){
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        while (!stack.empty() || root != null){
            if (root != null){
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                System.out.print(root.val + " -> ");
                root = root.right;
            }
        }
        System.out.println();
    }

    public static void postOrder(Node head){
        if (head == null) return;
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(head);
        while (!s1.empty()){
            head = s1.pop();
            s2.push(head);
            if (head.left != null){
                s1.push(head.left);
            }
            if (head.right != null){
                s1.push(head.right);
            }
        }
        while (!s2.empty()){
            head = s2.pop();
            System.out.print(head.val + " -> ");
        }
        System.out.println();
    }


    /**
     * 二叉树的神级遍历方法（时间复杂度O(N),空间复杂度O(1)）
     * @param head
     */
    public static void morrisIn(Node head){
        if (head == null) return;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null){
                //查找左子树中最右边的结点
                while ( cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                //找到后指向根结点（中序遍历）
                if (cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else {
                    cur2.right = null;
                }
            }
            System.out.print(cur1.val + " ");
            cur1 = cur1.right;
        }
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
        System.out.println();
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
        System.out.println();
        preOrder(root);
        System.out.print("中序遍历：");
        midOrder(root);
        System.out.print("后序遍历：");
        postOrder(root);
        System.out.print("神级中序遍历：");
        morrisIn(root);
        String s = "             BARCODE  INDEX     DATE     S.TIME    E.TIME   CYCLE             JOB        RESULT       USER          LOTINFO        MACHINE        \n" +
                "          11113024469094 55011 2020/7/13   13:17:37  13:17:46      9 309000124208B3_Kenobi_MB_BOT     GOOD     KohYoung              KOHYOUNG\n" +
                "PadID\tComponent ID\t  Size X\t  Size Y\t T\tVolume(%)\tHeight(um)\tOffsetX(mm)\tOffsetY(mm)\t Area(%)\tVolume(um3)\tArea(um2)\t  Result\tPinNumber\t Barcode\t  PCB ID\t     Job\t  Date  \t Time \t   Shape\tArray\t   Panel\tPosX(mm)\tPosY(mm)\t    Spec\tPad Verification\tTolerance\tOffsetX(%)\tOffsetY(%)\tLibrary_Name\tHeight(%)\t\n" +
                "\n";
        String y = "\tBARCODE\tINDEX\tDATE\tS.TIME\tE.TIME\tCYCLE\tJOB\tRESULT\tUSER\tLOTINFO\tMACHINE\n" +
                "\tD33333333\t2\t2020-07-12\t14:39:45\t14:39:50\t5\t309000124208T4-KENOBI-TOP\tPASS\tSV\tKOHYOUNG\n" +
                "PadID\tComponent ID\t  Size X\t  Size Y\t T\tVolume(%)\tHeight(um)\tOffsetX(mm)\tOffsetY(mm)\t Area(%)\tVolume(um3)\tArea(um2)\t  Result\tPinNumber\t Barcode\t  PCB ID\t     Job\t  Date  \t Time \t   Shape\tArray\t   Panel\tPosX(mm)\tPosY(mm)\t    Spec\tPad Verification\tTolerance\tOffsetX(%)\tOffsetY(%)\tLibrary_Name\tHeight(%)";
    }
}
