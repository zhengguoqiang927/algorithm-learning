package com.zhengguoqiang.queue;

/**
 * 基于链表实现的队列
 * @param <Item>
 */
public class LinkedListQueue<Item> {
    private class Node{
        Item item;
        Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    private Node head;//队列头结点
    private Node tail;//队列尾结点
    private int N;//队列中元素的个数

    public void enqueue(Item item){
        Node oldTail = tail;
        tail = new Node(item);
        tail.next = null;
        //队列是空的
        if (head == null){
            head = tail;
        }else {
            oldTail.next = tail;
        }
        N++;
    }

    public Item dequeue(){
        if (head == tail) return null;
        Item item = head.item;
        head = head.next;
        //删除头结点后队列为空的判断
        if (head == null) tail = null;
        N--;
        return item;
    }
}
