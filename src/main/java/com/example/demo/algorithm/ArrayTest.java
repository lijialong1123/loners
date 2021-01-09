package com.example.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author : coder
 * @create 2020/10/28 20:54
 */
public class ArrayTest {


    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int count = map.get(nums[i]);
            map.put(nums[i], ++count);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) > 1) {
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 统计一个数字在排序数组中出现的次数。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
            return 0;
        }
        return helper(nums, target) - helper(nums, target - 1);
    }

    private int helper(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            list1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (list1.contains(nums2[i])) {
                list2.add(nums2[i]);
            }
        }
        if (list2.size() <= 0) {
            return new int[0];
        }
        int[] res = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            int integer = list2.get(i);
            res[i] = integer;
        }
        return res;
    }

    public int[] intersect1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int k = 0;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] == nums2[j]) {
                nums1[k++] = nums1[i++];
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 2, 1};
        int[] b = new int[]{2, 2};

        int[] intersect = intersect(a, b);
        for (int i = 0; i < intersect.length; i++) {
            System.out.println(intersect[i]);
        }
    }

}
