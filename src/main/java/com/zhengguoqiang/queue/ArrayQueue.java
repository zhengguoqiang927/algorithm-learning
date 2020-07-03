package com.zhengguoqiang.queue;

/**
 * 顺序队列
 * @param <Item>
 */
public class ArrayQueue<Item> {
    private Item[] items;//存放元素的数组
    private int head;//队头下标
    private int tail;//队尾下标

    public ArrayQueue(int n) {
        this.items = (Item[]) new Object[n];
    }

    public boolean enqueue(Item item){
        if (tail == items.length) {
            //当head = 0时，表示数组是真的满了
            if (head == 0) return false;
            //数据拷贝
            //if (tail - head >= 0) System.arraycopy(items, head, items, 0, tail - head);
            for (int i = head;i< tail;i++){
                items[i-head] = items[i];
            }
            tail  = tail - head;
            head = 0;
        }
        items[tail++] = item;
        return true;
    }

    public Item dequeue(){
        if (head == tail) return null;
        return items[head++];
    }
}
