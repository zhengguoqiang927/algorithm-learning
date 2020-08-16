package com.zhengguoqiang.offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 9.两个队列实现栈
 */
public class TwoQueueImplStack<E> {
    Queue<E> queue1 = new LinkedList<>();
    Queue<E> queue2 = new LinkedList<>();

    void push(E e){
        if (queue1.isEmpty() && queue2.isEmpty()){
            queue1.offer(e);
        }else if (!queue1.isEmpty()){
            queue1.offer(e);
        }else {
            queue2.offer(e);
        }
    }

    E pop(){
        if (queue2.isEmpty()){
            while (queue1.size()>1){
                queue2.offer(queue1.poll());
            }
            return queue1.poll();
        }else {
            while (queue2.size() > 1){
                queue1.offer(queue2.poll());
            }
            return queue2.poll();
        }
    }

    public static void main(String[] args) {
        TwoQueueImplStack<String> stack = new TwoQueueImplStack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
