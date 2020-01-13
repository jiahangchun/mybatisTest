//package com.test.leetCode;
//
//import com.alipay.sofa.rpc.common.json.JSON;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import io.swagger.models.auth.In;
//import org.apache.logging.log4j.core.util.JsonUtils;
//
//import java.io.File;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * @author jiahangchun
// */
//public class Solution {
//    /**
//     * NO.1
//     *
//     * @param nums
//     * @param target
//     * @return
//     */
//    public int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (target - (nums[i] + nums[j]) == 0) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return null;
//    }
//
//
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        if (null == l1) {
//            return l2;
//        }
//        if (null == l2) {
//            return l1;
//        }
//
//        List<Integer> l1Vector = new LinkedList<>(), l2Vector = new LinkedList<>();
//        int lenA = 0, lenB = 0;
//        while (null != l1) {
//            l1Vector.add(l1.val);
//            l1 = l1.next;
//            lenA++;
//        }
//        while (null != l2) {
//            l2Vector.add(l2.val);
//            l2 = l2.next;
//            lenB++;
//        }
//        int len = lenA > lenB ? lenA : lenB;
//
//        int carry = 0;
//        ListNode head = null, tmp = null;
//        for (int i = 0; i <= len; i++) {
//            int l1Val = 0, l2Val = 0;
//            try {
//                l1Val = l1Vector.get(i);
//            } catch (Exception e) {
//
//            }
//            try {
//                l2Val = l2Vector.get(i);
//            } catch (Exception e) {
//
//            }
//            int sum = l1Val + l2Val + carry;
//            ListNode listNode = new ListNode(sum % 10);
//            carry = sum / 10;
//            if (null == head && null == tmp) {
//                head = listNode;
//                tmp = listNode;
//            } else {
//                if (i == len) {
//                    if (0 == listNode.val) {
//                        continue;
//                    }
//                }
//
//                tmp.next = listNode;
//                tmp = listNode;
//            }
//
//        }
//        return head;
//    }
//
//    public static class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int x) {
//            val = x;
//        }
//    }
//
//
//    public String longestPalindrome(String s) {
//        if (s == null || s.length() <= 0) {
//            return "";
//        }
//        int start = 0, end = 0;
//        for (int i = 0; i < s.length(); i++) {
//            int a = expendAroundCenter(s, i, i);
//            int b = expendAroundCenter(s, i, i + 1);
//            int len = Integer.max(a, b);
//            if (len > (end - start)) {
//                start = i - (len - 1) / 2;
//                end = i + len / 2;
//            }
//        }
//        return s.substring(start, end + 1);
//    }
//
//    private int expendAroundCenter(String s, int left, int right) {
//        int L = left, R = right;
//        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
//            L--;
//            R++;
//        }
//        return R - L - 1;
//    }
//
//
//    public int reverse(int x) {
//        int rev = 0, a = 0;
//        while (Math.abs(x) > 0) {
//            if (Math.abs(rev) <= Integer.MAX_VALUE / 10) {
//                a = x % 10;
//                x /= 10;
//                rev = rev * 10 + a;
//            } else {
//                return 0;
//            }
//
//        }
//        return rev;
//    }
//
//    public boolean isPalindrome(int x) {
//        if (x < 0) {
//            return Boolean.FALSE;
//        }
//        int rev = 0, a = 0, origin = x;
//        while (x > 0) {
//            if (Math.abs(rev) <= Integer.MAX_VALUE / 10) {
//                a = x % 10;
//                x /= 10;
//                rev = rev * 10 + a;
//            } else {
//                return Boolean.FALSE;
//            }
//        }
//        if (rev == 0) {
//            return Boolean.TRUE;
//        }
//        if (origin / rev == 1) {
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//    }
//
//
//    public int romanToInt(String s) {
//        if (s == null || s.length() <= 0) {
//            return 0;
//        }
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < s.length(); i++) {
//            String valueStr = String.valueOf(s.charAt(i));
//            list.add(convert(valueStr));
//        }
//        list.add(0);
//        int val = 0;
//        for (int k = 0; k < list.size() - 1; k++) {
//            int cur = list.get(k);
//            int next = list.get(k + 1);
//            if (cur < next) {
//                val = val - cur + next;
//                k++;
//            } else {
//                val += cur;
//            }
//        }
//
//
//        return val;
//    }
//
//    private int convert(String lnumber) {
//        switch (lnumber) {
//            case "I":
//                return 1;
//            case "V":
//                return 5;
//            case "X":
//                return 10;
//            case "L":
//                return 50;
//            case "C":
//                return 100;
//            case "D":
//                return 500;
//            case "M":
//                return 1000;
//            default:
//                return 0;
//        }
//    }
//
//
//    public String longestCommonPrefix(String[] strs) {
//        if (null == strs || strs.length == 0) {
//            return "";
//        }
//        String prefix = strs[0];
//        if (strs.length == 1) {
//            return prefix;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < prefix.length(); i++) {
//            char prefixT = prefix.charAt(i);
//            char temp = 0;
//            for (int j = 1; j < strs.length; j++) {
//                char t;
//                try {
//                    t = strs[j].charAt(i);
//                } catch (Exception e) {
//                    return sb.toString();
//                }
//                if (prefixT == t) {
//                    temp = t;
//                } else {
//                    return sb.toString();
//                }
//            }
//            if (temp != 0) {
//                sb.append(temp);
//            }
//        }
//        return sb.toString();
//    }
//
//
//    public boolean isValid(String s) {
//        if (null == s || s.length() <= 0) {
//            return true;
//        }
//        List<String> leftTarget = new ArrayList<>(),
//                rightTarget = new ArrayList<>();
//        leftTarget.add("[");
//        leftTarget.add("{");
//        leftTarget.add("(");
//
//        rightTarget.add("]");
//        rightTarget.add("}");
//        rightTarget.add(")");
//
//
//        Stack<String> stringStack = new Stack<>();
//        for (int i = 0; i < s.length(); i++) {
//            String currentChar = String.valueOf(s.charAt(i));
//            if (rightTarget.indexOf(currentChar) >= 0) {
//                if (stringStack.size() <= 0) {
//                    return false;
//                }
//                String beforeChar = stringStack.pop();
//                if (!beforeChar.equals(reverse(currentChar))) {
//                    return false;
//                }
//            } else {
//                stringStack.push(currentChar);
//            }
//        }
//        boolean remainValue = stringStack.size() > 0;
//        return !remainValue;
//    }
//
//
//    private String reverse(String n) {
//        switch (n) {
//            case "{":
//                return "}";
//            case "}":
//                return "{";
//            case "[":
//                return "]";
//            case "]":
//                return "[";
//            case "(":
//                return ")";
//            case ")":
//                return "(";
//            default:
//                return "";
//        }
//    }
//
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        //基础参数校验
//        if (l1 == null) {
//            return l2;
//        }
//        if (l2 == null) {
//            return l1;
//        }
//
//        //赋值操作
//        List<Integer> list = new ArrayList<>();
//        pushValue(list, l1);
//        pushValue(list, l2);
//
//        //排序
//        list.sort(Comparator.comparing(Integer::intValue));
//
//        //组合参数
//        List<ListNode> listNodes = list.stream().map(Solution::convert2Obj).collect(Collectors.toList());
//
//
//        return combine(listNodes);
//    }
//
//    public static ListNode convert2Obj(Integer val) {
//        return new ListNode(val);
//    }
//
//    public ListNode combine(List<ListNode> list) {
//        ListNode firstNode = null, latestNode = null;
//        for (int i = 0; i < list.size(); i++) {
//            ListNode tmp = list.get(i);
//            if (firstNode == null) {
//                firstNode = tmp;
//                latestNode = tmp;
//            } else {
//                latestNode.next = tmp;
//                latestNode = tmp;
//            }
//        }
//        return firstNode;
//    }
//
//    public void pushValue(List<Integer> list, ListNode l1) {
//        ListNode temp = l1;
//        while (temp != null) {
//            list.add(temp.val);
//            ListNode next = temp.next;
//            temp = next;
//        }
//    }
//
//    public int removeDuplicates(int[] nums) {
//        if (nums == null) {
//            return 0;
//        }
//        if (nums.length == 0) {
//            return 0;
//        }
//        if (nums.length == 1) {
//            return 1;
//        }
//        int i = 0, j = 1, count = 1;
//        for (; j < nums.length; j++) {
//            int leftVal = nums[i];
//            int rightVal = nums[j];
//            if (leftVal != rightVal) {
//                count++;
//                i++;
//                nums[i] = rightVal;
//            }
//        }
//        return count;
//    }
//
//    public int removeElement(int[] nums, int val) {
//        if (nums == null) {
//            return 0;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            int tmp = nums[i];
//            if (val == tmp) {
//                for (int j = i + 1; j < nums.length; j++) {
//                    if (nums[j] == val) {
//                    } else {
//                        nums[i] = nums[j];
//                        nums[j] = val;
//                        break;
//                    }
//                }
//            }
//        }
//
//        int count = 0;
//        for (int k = 0; k < nums.length; k++) {
//            int tmp = nums[k];
//            if (val != tmp) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public int strStr(String haystack, String needle) {
//        if (haystack == null || needle == null) {
//            return 0;
//        }
//        if (needle.length() <= 0) {
//            return 0;
//        }
//        if (haystack.equals(needle)) {
//            return 0;
//        }
//        for (int i = 0; i < haystack.length(); i++) {
//            int tmp = i;
//            for (int j = 0; j < needle.length(); j++) {
//                if (tmp >= haystack.length()) {
//                    break;
//                }
//                if (haystack.charAt(tmp) != needle.charAt(j)) {
//                    break;
//                }
//                tmp++;
//                if (j == needle.length() - 1) {
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }
//
//
//    public int searchInsert(int[] nums, int target) {
//        if (nums == null) {
//            return 0;
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            int value = nums[i];
//            if (value >= target) {
//                return i > 0 ? i : 0;
//            }
//            if (i == nums.length - 1) {
//                return nums.length;
//            }
//        }
//        return 0;
//    }
//
//    public String countAndSay(int n) {
//        if (n == 1) {
//            return "1";
//        }
//        String preVal = countAndSay(n - 1);
//        List<Integer> sortStr = new ArrayList<>();
//        Map<Integer, Integer/*list.index,count*/> map = new HashMap<>();
//        for (int i = 0; i < preVal.length(); i++) {
//            String tmpStr = String.valueOf(preVal.charAt(i));
//            Integer tmp = Integer.valueOf(tmpStr);
//            sortStr.add(tmp);
//            int count = 0;
//            for (int j = i; j < preVal.length(); j++) {
//                String jTmpStr = String.valueOf(preVal.charAt(j));
//                Integer jTmp = Integer.valueOf(jTmpStr);
//                if (!tmp.equals(jTmp)) {
//                    break;
//                }
//                count++;
//                i = j;
//            }
//            map.put(sortStr.size() - 1, count);
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int k = 0; k < sortStr.size(); k++) {
//            Integer cur = sortStr.get(k);
//            Integer count = map.get(k);
//            sb.append(count + "" + cur);
//        }
//        return sb.toString();
//    }
//
//
//    public int maxSubArray(int[] nums) {
//        if (nums == null || nums.length <= 0) {
//            return 0;
//        }
//        int sum = nums[0], ans = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            int num = nums[i];
//            if (sum > 0) {
//                sum += num;
//            } else {
//                sum = num;
//            }
//            ans = Math.max(ans, sum);
//        }
//        return ans;
//    }
//
//    public int lengthOfLastWord(String s) {
//        if (s == null || s.length() <= 0) {
//            return 0;
//        }
//        String[] words = s.split(" ");
//        if (words.length <= 0) {
//            return 0;
//        }
//        String lastWord = words[words.length - 1];
//        if (null == lastWord) {
//            return 0;
//        }
//        if (lastWord.length() <= 0) {
//            return 0;
//        }
//        return lastWord.length();
//    }
//
//    public int[] plusOne(int[] digits) {
//        if (null == digits) {
//            return null;
//        }
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < digits.length; i++) {
//            stack.add(digits[i]);
//        }
//        int carry = 0;
//        List<Integer> newList = new ArrayList<>();
//        for (int k = 0; !stack.isEmpty(); k++) {
//            Integer index = stack.pop();
//            //原数字进位
//            index += carry;
//            //业务逻辑+1
//            if (k == 0) {
//                index++;
//            }
//            carry = index / 10;
//            int rest = index % 10;
//            newList.add(rest);
//        }
//
//        int[] result = null;
//        if (carry == 0) {
//            result = new int[digits.length];
//        } else {
//            result = new int[digits.length + 1];
//        }
//        int index = 0;
//        if (carry != 0) {
//            result[index] = carry;
//            index++;
//        }
//        for (int j = newList.size() - 1; j >= 0; j--) {
//            result[index] = newList.get(j);
//            index++;
//        }
//        return result;
//    }
//
//    public String addBinary(String a, String b) {
//        if (a == null) {
//            return b;
//        }
//        if (b == null) {
//            return a;
//        }
//        int alen = a.length(), blen = b.length();
//        int max = Math.max(alen, blen);
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; max - alen > 0 && i < max - alen; i++) {
//            sb.append("0");
//        }
//        a = sb.toString() + a;
//
//        StringBuilder bBuilder = new StringBuilder();
//        for (int i = 0; max - blen > 0 && i < max - blen; i++) {
//            bBuilder.append("0");
//        }
//        b = bBuilder.toString() + b;
//
//
//        int carry = 0;
//        List<String> resultList = new ArrayList<>();
//        for (int k = b.length() - 1; k >= 0; k--) {
//            int resultIndex = (a.charAt(k) - '0' + b.charAt(k) - '0') + carry;
//            if (resultIndex <= 1) {
//                carry = 0;
//            } else {
//                carry = resultIndex / 2;
//                resultIndex = resultIndex % 2;
//            }
//            resultList.add(resultIndex + "");
//        }
//        if (carry != 0) {
//            resultList.add("1");
//        }
//
//        String result = "";
//        if (null != resultList) {
//            Collections.reverse(resultList);
//            result = resultList.stream().reduce((i, j) -> i + "" + j).orElse("");
//        }
//
//        return result;
//    }
//
//    //46340?
//    public int mySqrt(int x) {
//        if (x <= 0) {
//            return 0;
//        }
//        if (x == 1) {
//            return 1;
//        }
//
//        //数组越界问题
//        Long mid = Long.valueOf(x / 2 + 1);
//        Long min = 1L,count=0L;
//        for (Long i = 1L; i <= mid; i++) {
//            Long result=i*i;
//            if (result<=x) {
//                min = i;
//                count++;
//            } else {
//                return min.intValue();
//            }
//        }
//        return min.intValue();
//    }
//
////    public int climbStairs(int n) {
////
////    }
//
//
//
//    public static void main(String[] args) {
//        System.out.println(File.separator);
//    }
//
//
//
////    public static void main(String[] args) {
//////        String result = new Solution().addBinary("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
//////                "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011");
//////        String result2 = new Solution().addBinary("1010", "1011");
//////        int result3 = new Solution().mySqrt(4);
//////        int result4 = new Solution().mySqrt(8);
////        int result5 = new Solution().mySqrt(2147395600);
////        System.out.println(JSON.toJSONString("++++++++" + result5));
////
////        System.out.println(Integer.MAX_VALUE);
////        System.out.println(Integer.MIN_VALUE);
////        System.out.println(Math.abs(-2147483648));
////    }
//}
