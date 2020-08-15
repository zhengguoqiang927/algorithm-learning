package com.zhengguoqiang.offer;

import java.util.Stack;

/**
 * 6.从尾到头打印链表
 * 链表第一个元素最后一个打印，最后一个元素第一个打印，可以联想到借助栈这个数据结构
 */
public class PrintLinkedListReversing {
    static class Node{
        final int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }

    static void printLinkedList(Node head){
        Stack<Node> stack = new Stack<>();
        while (head != null){
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()){
            Node top = stack.pop();
            System.out.print(top.val + " -> ");
        }
    }

    static void printLinkedListRecursive(Node head){
        if (head == null) return;
        printLinkedListRecursive(head.next);
        System.out.print(head.val + " -> ");
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;
        printLinkedList(n1);
        System.out.println();
        printLinkedListRecursive(n1);
    }
}
