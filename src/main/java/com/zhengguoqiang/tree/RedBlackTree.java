package com.zhengguoqiang.tree;

public class RedBlackTree<Key extends Comparable<Key>,Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    class Node{
       Key key;
       Value value;
       Node left,right;
       int N;
       boolean color; //父结点指向本结点的链接的颜色

        public Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x){
        if (x == null) return 0;
        else return x.N;
    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Node root;

    public void put(Key key,Value value){
        root = put(root,key,value);
        root.color = BLACK;
    }

    public Node put(Node h,Key key,Value value){
        if (h == null) return new Node(key,value,1,RED);//新结点与父结点用红链接相连
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left,key,value);
        else if (cmp > 0) h.right = put(h.right,key,value);
        else h.value = value;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);//红链接为右链接，进行左旋转
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);//一个结点与两条红链接相连，进行右旋转
        if (isRed(h.left) && isRed(h.right)) flipColors(h);//左右链接均为红链接，进行颜色变换

        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }
}
