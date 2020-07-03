package com.zhengguoqiang.stack;

/**
 * 链式栈
 * @param <Item>
 */
public class LinkedListStack<Item> {
    private class Node{
        Item item;
        Node next;

        public Node() {
        }

        public Node(Item item) {
            this.item = item;
        }
    }

    private Node top;//栈顶结点
    private int N;//元素个数

    public void push(Item item){
        Node oldTop = top;
        top = new Node(item);
        top.next = oldTop;
        N++;
    }

    public Item pop(){
        Item item = top.item;
        top = top.next;
        N--;
        return item;
    }
}
