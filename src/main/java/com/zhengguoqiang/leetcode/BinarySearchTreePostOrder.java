package com.zhengguoqiang.leetcode;


/**
 * 根据二叉树搜索树的后续遍历结果构造出二叉搜索树
 * 比如 后续遍历结果为[2,4,3,6,8,7,5]
 * 转换为 二叉搜索树 为
 *          5
 *        /    \
 *       3      7
 *     /  \    /  \
 *    2    4  6   8
 */
public class BinarySearchTreePostOrder {
    class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public Node process(int[] arr){
        return process1(arr,0,arr.length - 1);
    }

    private Node process1(int[] arr, int left, int right) {
        if (left > right) return null;

        Node head = new Node(arr[right]);
        if (left == right) return head;

        int mid = left - 1;
        for (int i=left;i<right;i++){
            if (arr[i] < head.value) mid = i;
        }
        head.left = process1(arr,left,mid);
        head.right = process1(arr,mid+1,right-1);
        return head;
    }

    public void postOrder(Node head){
        if (head == null) return;
        postOrder(head.left);
        postOrder(head.right);
        System.out.print(head.value + " -> ");
    }

    public static void main(String[] args) {
        int[] postOrder = {2,4,3,6,8,7,5};
        BinarySearchTreePostOrder instance = new BinarySearchTreePostOrder();
        Node process = instance.process(postOrder);
        instance.postOrder(process);
    }
}
