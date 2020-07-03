package com.zhengguoqiang.stack;

/**
 * 顺序栈
 */
public class ArrayStack<Item> {
    private Item[] items;//存放元素的数组
    private int count;//元素个数

    public ArrayStack(int n) {
        this.items = (Item[]) new Object[n];
        this.count = 0;
    }

    public void push(Item item){
        if (size() == items.length) resize(2*items.length);
        items[count++] = item;
    }

    public Item pop(){
        if (isEmpty()) return null;
        Item item = items[--count];
        //避免游离
        items[count] = null;
        //缩容
        if (count > 0 && count == items.length/4) resize(items.length/2);
        return item;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public int size(){
        return count;
    }

    public void resize(int max){
        Item[] tmp = (Item[]) new Object[max];
        for (int i=0;i<count;i++){
            tmp[i] = items[i];
        }
        //if (count >= 0) System.arraycopy(items, 0, tmp, 0, count);
        items = tmp;
    }
}
