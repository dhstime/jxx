package com.jxx.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ListTest {
    public static void main(String[] args) {
        int[] nums= {2, 7, 11, 15};
        int[] ints = twoSum(nums, 26);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j] == target){
                    result[0] = nums[i];
                    result[1] = nums[j];
                    break;
                }
            }
        }
        return result;
    }
}
