package xxh.leetcode;

import lombok.val;
import org.apache.dubbo.qos.command.impl.Ls;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: elyuan
 * @Date: 2020/12/3 4:46 下午
 */
public class Solution3 {
    public static ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        int node1Length = 0;
        ListNode listNodeLength1 = node1;
        while (listNodeLength1 != null) {
            listNodeLength1 = listNodeLength1.next;
            node1Length++;
        }
        int node2Length = 0;
        ListNode listNodeLength2 = node2;
        while (listNodeLength2 != null) {
            listNodeLength2 = listNodeLength2.next;
            node2Length++;
        }
        int addNum = 0;
        StringBuilder sb = new StringBuilder();
        if (node1Length < node2Length) {
            ListNode swapNode = node2;
            node2 = node1;
            node1 = swapNode;
        }
        while (node1 != null) {
            int val1 = node1.val;
            node1 = node1.next;
            int val2 = 0;
            while (node2 != null) {
                val2 = node2.val;
                node2 = node2.next;
                break;
            }
            int num = val1 + val2 + addNum;
            if (addNum != 0) {
                addNum = 0;
            }
            addNum = num / 10;
            if (addNum > 0) {
                num = num - 10;
            }
            sb.append(num);
        }
        if(addNum !=0){
            sb.append(addNum);
        }
        String str = sb.toString();
        ListNode listNode = new ListNode(0);
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            ListNode addNode = new ListNode(Integer.parseInt(String.valueOf(array[i])));
            ListNode cur = listNode;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = addNode;
        }
        return listNode.next;
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(3);
        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);
        listNode2.next.next.next = new ListNode(4);
        ListNode node = addTwoNumbers(listNode1, listNode2);
        System.out.println(node);


    }

    public static ListNode reversalListNode(ListNode listNode) {
        ListNode nodeList = new ListNode(-1);
        ListNode p = listNode;
        while (p != null) {
            ListNode swapNodes = p.next;
            p.next = nodeList.next;
            nodeList.next = p;
            p = swapNodes;
        }
        return nodeList.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    /**
     * 别人写的感觉很棒
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;//节点后移

            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }

        }

        return root.next;
    }

}
