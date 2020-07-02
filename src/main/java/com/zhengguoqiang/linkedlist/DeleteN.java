package com.zhengguoqiang.linkedlist;

public class DeleteN {
    static class Node {
        final int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    //两次遍历
    public static Node deleteByTwice(Node head, int n) {
        int length = 0;
        Node first = head;
        //1.遍历得到链表的长度
        while (first != null) {
            length++;
            first = first.next;
        }
        //计算删除结点的正向位置，正常应该是(L - n + 1)，不加1是因为正好找到其前驱结点
        length -= n;

        //哨兵结点，有两个好处：1.简化删除头结点的判断逻辑；2.简化判断前驱结点位置的逻辑
        Node shard = new Node(0);
        shard.next = head;

        first = shard;
        //2.遍历找到要删除结点的前驱结点
        while (length > 0) {
            length--;
            first = first.next;
        }
        //3.删除结点
        first.next = first.next.next;
        return shard.next;
    }

    //方法二：一次遍历
    public static Node deleteByOne(Node head, int n) {
        //哨兵结点
        Node shard = new Node(0);
        shard.next = head;
        //两个指针，他俩之间的距离为n，所以当第一个指针到达链表末尾时，第二个指针正好指向要删除的结点
        Node first = shard, second = shard;
        int i = 0;
        while (first.next != null) {
            first = first.next;
            i++;
            //控制第二个指针与第一个指针的距离为n
            if (i > n) {
                second = second.next;
            }
        }
        second.next = second.next.next;
        return shard.next;
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
        Node h = deleteByOne(l1, 6);
        while (h != null) {
            System.out.print(h.val + " -> ");
            h = h.next;
        }
        System.out.print("null");
    }
}
