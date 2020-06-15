package com.zhengguoqiang.linkedlist;

public class MergeTwoLists {
    static class ListNode{
        final int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    public static ListNode merge(ListNode p1,ListNode p2){
        if (p1 == null) return p2;
        ListNode head = p1;
        while (true){
            if (p1 == null){
                head.next = p2;
                break;
            }
            if (p2 == null){
                head.next = p1;
                break;
            }
            if (p1.val < p2.val){
                head = p1;
                p1 = p1.next;
            }else{

            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(3);
        ListNode ln3 = new ListNode(5);
        ListNode ln4 = new ListNode(7);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = null;

        ListNode rn1 = new ListNode(2);
        ListNode rn2 = new ListNode(4);
        ListNode rn3 = new ListNode(6);
        ListNode rn4 = new ListNode(8);
        rn1.next = rn2;
        rn2.next = rn3;
        rn3.next = rn4;
        rn4.next = null;


    }
}
