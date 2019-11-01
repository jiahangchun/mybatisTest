package com.test.leetCode;

import com.alipay.sofa.rpc.common.utils.JSONUtils;

import java.util.*;

/**
 * @author jiahangchun
 */
public class Solution {
    /**
     * NO.1
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target - (nums[i] + nums[j]) == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }

        List<Integer> l1Vector = new LinkedList<>(), l2Vector = new LinkedList<>();
        int lenA = 0, lenB = 0;
        while (null != l1) {
            l1Vector.add(l1.val);
            l1 = l1.next;
            lenA++;
        }
        while (null != l2) {
            l2Vector.add(l2.val);
            l2 = l2.next;
            lenB++;
        }
        int len = lenA > lenB ? lenA : lenB;

        int carry = 0;
        ListNode head = null, tmp = null;
        for (int i = 0; i <= len; i++) {
            int l1Val = 0, l2Val = 0;
            try {
                l1Val = l1Vector.get(i);
            } catch (Exception e) {

            }
            try {
                l2Val = l2Vector.get(i);
            } catch (Exception e) {

            }
            int sum = l1Val + l2Val + carry;
            ListNode listNode = new ListNode(sum % 10);
            carry = sum / 10;
            if (null == head && null == tmp) {
                head = listNode;
                tmp = listNode;
            } else {
                if (i == len) {
                    if (0 == listNode.val) {
                        continue;
                    }
                }

                tmp.next = listNode;
                tmp = listNode;
            }

        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {


        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;

        System.out.println(JSONUtils.toJSONString(new Solution().addTwoNumbers(a1, b1)));
//
//        ListNode c = new ListNode(5);
//        ListNode d = new ListNode(5);
//
//        System.out.println(JSONUtils.toJSONString(new Solution().addTwoNumbers(c, d)));

//        ListNode f = new ListNode(9);
//        ListNode h = new ListNode(1);
//        ListNode h1 = new ListNode(9);
//        ListNode h2 = new ListNode(9);
//        ListNode h3 = new ListNode(9);
//        ListNode h4 = new ListNode(9);
//        ListNode h5 = new ListNode(9);
//        ListNode h6 = new ListNode(9);
//        ListNode h7 = new ListNode(9);
//        ListNode h8 = new ListNode(9);
//        ListNode h9 = new ListNode(9);
//        h.next=h1;
//        h1.next=h2;
//        h2.next=h3;
//        h3.next=h4;
//        h4.next=h5;
//        h5.next=h6;
//        h6.next=h7;
//        h7.next=h8;
//        h8.next=h9;
//
//        System.out.println(JSONUtils.toJSONString(new Solution().addTwoNumbers(f, h)));


        ListNode c = new ListNode(1);
        ListNode c1 = new ListNode(8);
        c.next=c1;
        ListNode d = new ListNode(0);

        System.out.println(JSONUtils.toJSONString(new Solution().addTwoNumbers(c, d)));
    }
}
