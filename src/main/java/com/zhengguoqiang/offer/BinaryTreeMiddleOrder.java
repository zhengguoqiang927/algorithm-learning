package com.zhengguoqiang.offer;

/**
 * 8.给定二叉树和一个结点，求该二叉树中序遍历的下一个结点
 */
public class BinaryTreeMiddleOrder {
    static class BinaryTreeNode{
        final String value;
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode parent;
        BinaryTreeNode(String value){
            this.value = value;
        }
    }

    static BinaryTreeNode getNext(BinaryTreeNode p){
        if (p == null) return null;
        BinaryTreeNode pNext = null;
        if (p.right != null){
            //该结点有右子结点,则下一个结点为右子树中最左结点
            BinaryTreeNode pRight = p.right;
            while (pRight.left != null){
                pRight = pRight.left;
            }
            pNext = pRight;
        }else if (p.parent != null){
            BinaryTreeNode pCurrent = p;
            BinaryTreeNode pParent = p.parent;
            while (pParent != null && pCurrent == pParent.right){
                pCurrent = pParent;
                pParent = pParent.parent;
            }
            pNext = pParent;
        }

        return pNext;
    }

    public static void main(String[] args) {
        BinaryTreeNode a = new BinaryTreeNode("a");
        BinaryTreeNode b = new BinaryTreeNode("b");
        BinaryTreeNode c = new BinaryTreeNode("c");
        BinaryTreeNode d = new BinaryTreeNode("d");
        BinaryTreeNode e = new BinaryTreeNode("e");
        BinaryTreeNode f = new BinaryTreeNode("f");
        BinaryTreeNode g = new BinaryTreeNode("g");
        BinaryTreeNode h = new BinaryTreeNode("h");
        BinaryTreeNode i = new BinaryTreeNode("i");

        a.left = b;
        a.right = c;
        b.parent = c.parent = a;

        b.left = d;
        b.right = e;
        d.parent = e.parent = b;

        c.left = f;
        c.right = g;
        f.parent = g.parent = c;

        e.left = h;
        e.right = i;
        h.parent = i.parent = e;

        System.out.println(getNext(e).value);
    }
}
