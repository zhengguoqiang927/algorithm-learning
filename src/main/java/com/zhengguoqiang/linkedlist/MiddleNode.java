package com.zhengguoqiang.linkedlist;

public class MiddleNode {
    static class Node{
        final int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node middleNode(Node head){
        Node slow=head,fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node l1 = new Node(1);
        Node l2 = new Node(2);
        Node l3 = new Node(3);
        Node l4 = new Node(4);
        Node l5 = new Node(5);
        Node l6 = new Node(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = null;
        Node m = middleNode(l5);
        System.out.println(m.val);
    }
}
