package com.zhengguoqiang.offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 9.用两个栈实现队列
 * 思路：一个栈专门用于添加元素s1，另一个栈专门用于移除队头元素s2，当s2没有元素时将s1中的元素弹出并压入s2中
 *
 */
public class TwoStackImplQueue<E> {
//    private final Stack<E> sPush = new Stack<>();
//    private final Stack<E> sPop = new Stack<>();

    private final Deque<E> sPush = new LinkedList<>();
    private final Deque<E> sPop = new LinkedList<>();

    void appendTail(E e){
        sPush.push(e);
    }

    E deleteHead(){
        if (sPop.isEmpty()){
            while (!sPush.isEmpty()){
                sPop.push(sPush.pop());
            }
        }
        if (sPop.isEmpty()) return null;
        return sPop.pop();
    }


    public static void main(String[] args) {
        TwoStackImplQueue<Integer> queue = new TwoStackImplQueue<>();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        queue.appendTail(4);
        queue.appendTail(5);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
}
