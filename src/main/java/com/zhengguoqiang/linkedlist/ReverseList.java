package com.zhengguoqiang.linkedlist;

public class ReverseList {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    //方案一
    public static ListNode solutionOne(ListNode head){
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = prev;
            //步进操作
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    public static ListNode solutionTwo(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode p = solutionTwo(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ListNode ln4 = new ListNode(4);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = null;

        ListNode head = ln1;
        print(head);
        //ListNode p = solutionOne(head);
        ListNode p = solutionTwo(head);
        print(p);
    }

    public static void print(ListNode head){
        ListNode cur = head;
        while (cur != null){
            System.out.printf(cur.val + " -> ");
            cur = cur.next;
        }

        System.out.println();
    }
}
