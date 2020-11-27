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
        return process2(arr,0,arr.length - 1);
    }

    //遍历
    private Node process1(int[] arr, int left, int right) {
        if (left > right) return null;

        Node head = new Node(arr[right]);
        if (left == right) return head;

        //考虑最差情况时间复杂度，当树只有左子树没有右子树时，比如[1,2,3,4,5,6]这样的树，每次只能拿走一个根结点，那么循环的次数总和：N + N-1 + N-2 +...+1 = O(N^2)
        int mid = left - 1;
        for (int i=left;i<right;i++){
            if (arr[i] < head.value) mid = i;
        }
        head.left = process1(arr,left,mid);
        head.right = process1(arr,mid+1,right-1);
        return head;
    }

    //二分
    private Node process2(int[] arr, int left, int right) {
        if (left > right) return null;

        Node head = new Node(arr[right]);
        if (left == right) return head;

        //同样是最差情况，即只有左子树没有右子树时，[1,2,3,4,5,6] 同样需要N次根结点的过程，根结点定好后剩下的元素进行二分，总的循环次数：N*logN
        int mid = left - 1;
        int L = left;
        int R = right - 1;
        while (L <= R){
            int tempMid = L + ((R - L) >> 1);
            if (arr[tempMid] < head.value){
                mid = tempMid;
                L = tempMid + 1;
            }else {
                R = tempMid - 1;
            }
        }

        head.left = process2(arr,left,mid);
        head.right = process2(arr,mid+1,right-1);
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
