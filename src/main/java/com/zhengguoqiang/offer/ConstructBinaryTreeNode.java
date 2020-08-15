package com.zhengguoqiang.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 7.重建二叉树
 * 根据输入的前序遍历序列和中序遍历序列，重建二叉树
 * 前序：1,2,4,7,3,5,6,8
 * 中序：4,7,2,1,5,3,8,6
 */
public class ConstructBinaryTreeNode {
    static class BinaryTreeNode {
        final int val;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(int val) {
            this.val = val;
        }
    }

    public static BinaryTreeNode buildTree(int[] preOrder, int[] midOrder) {
        if (preOrder == null || midOrder == null) return null;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < midOrder.length; i++) {
            indexMap.put(midOrder[i], i);
        }
        BinaryTreeNode root = buildTree(preOrder,0, preOrder.length-1,
                midOrder,0, midOrder.length-1,indexMap);
        return root;
    }

    public static BinaryTreeNode buildTree(int[] preOrder,int preOrderStart,int preOrderEnd,
                                           int[] midOrder,int midOrderStart,int midOrderEnd,Map<Integer,Integer> indexMap){
        if (preOrderStart > preOrderEnd) return null;

        int rootVal = preOrder[preOrderStart];
        BinaryTreeNode root = new BinaryTreeNode(rootVal);
        if (preOrderStart == preOrderEnd){
            return root;
        }else {
            int rootIndex = indexMap.get(rootVal);
            int leftNodes = rootIndex - midOrderStart;
            int rightNodes = midOrderEnd - rootIndex;
            BinaryTreeNode left = buildTree(preOrder, preOrderStart + 1, preOrderStart + leftNodes,
                    midOrder, midOrderStart, rootIndex - 1, indexMap);
            BinaryTreeNode right = buildTree(preOrder, preOrderEnd - rightNodes + 1, preOrderEnd,
                    midOrder, rootIndex + 1, midOrderEnd, indexMap);
            root.left = left;
            root.right = right;
            return root;
        }
    }

    public static void main(String[] args) {
        int[] preOrder = {1,2,4,7,3,5,6,8};
        int[] midOrder = {4,7,2,1,5,3,8,6};
        BinaryTreeNode root = buildTree(preOrder,midOrder);
        printAfter(root);
    }

    public static void printAfter(BinaryTreeNode root){
        if (root == null) return;
        printAfter(root.left);
        printAfter(root.right);
        System.out.print(root.val + " ");
    }
}
