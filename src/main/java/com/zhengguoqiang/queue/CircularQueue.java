package com.zhengguoqiang.queue;

/**
 * 循环队列
 * @param <Item>
 */
public class CircularQueue<Item> {
    private final Item[] items;//存放元素的数组
    private int head;//队头下标
    private int tail;//队尾下标

    public CircularQueue(int capacity) {
        this.items = (Item[]) new Object[capacity];
    }

    public boolean enqueue(Item item) {
        //队满
        if ((tail + 1) % items.length == 0) return false;
        items[tail] = item;
        //注意当tail到达(数组长度 - 1)时要形成环
        tail = (tail + 1) % items.length;
        return true;
    }

    public Item dequeue() {
        //队空
        if (head == tail) return null;
        Item item = items[head];
        //注意当head到达(数组长度 - 1)时要形成环
        head = (head + 1) % items.length;
        return item;
    }
}
