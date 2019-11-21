package com.test.leetCode;

import com.alipay.sofa.rpc.common.utils.JSONUtils;
import io.swagger.models.auth.In;

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


    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 0) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int a = expendAroundCenter(s, i, i);
            int b = expendAroundCenter(s, i, i + 1);
            int len = Integer.max(a, b);
            if (len > (end - start)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expendAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    public int reverse(int x) {
        int rev = 0, a = 0;
        while (Math.abs(x) > 0) {
            if (Math.abs(rev) <= Integer.MAX_VALUE / 10) {
                a = x % 10;
                x /= 10;
                rev = rev * 10 + a;
            } else {
                return 0;
            }

        }
        return rev;
    }

    public boolean isPalindrome(int x) {
        if (x<0) {
            return Boolean.FALSE;
        }
        int rev = 0, a = 0, origin = x;
        while (x > 0) {
            if (Math.abs(rev) <= Integer.MAX_VALUE / 10) {
                a = x % 10;
                x /= 10;
                rev = rev * 10 + a;
            } else {
                return Boolean.FALSE;
            }
        }
        if (rev == 0) {
            return Boolean.TRUE;
        }
        if (origin / rev == 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public static void main(String[] args) {
//        Boolean subStr = new Solution().isPalindrome(-2147483648);
//        System.out.println(subStr);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.abs(-2147483648));
    }
}
