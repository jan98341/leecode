package com.jan.title100.title70;

import com.alibaba.fastjson.JSON;

/**
 * 66. 加一
 * 给定一个表示 大整数 的整数数组 digits，其中 digits[i] 是整数的第 i 位数字。这些数字按从左到右，从最高位到最低位排列。这个大整数不包含任何前导 0。
 * 将大整数加 1，并返回结果的数字数组。
 *
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 加 1 后得到 123 + 1 = 124。
 * 因此，结果应该是 [1,2,4]。
 *
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 加 1 后得到 4321 + 1 = 4322。
 * 因此，结果应该是 [4,3,2,2]。
 * 示例 3：
 *
 * 输入：digits = [9]
 * 输出：[1,0]
 * 解释：输入数组表示数字 9。
 * 加 1 得到了 9 + 1 = 10。
 * 因此，结果应该是 [1,0]。
 *
 * 提示：
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * digits 不包含任何前导 0。
 */
public class Title66 {
    public static void main(String[] args) {
        int[] digits1 = {1, 2, 3};
        int[] digits2 = {4, 3, 2, 1};
        int[] digits3 = {9};

        System.out.println(JSON.toJSONString(plusOne(digits1)));
        System.out.println(JSON.toJSONString(plusOne(digits2)));
        System.out.println(JSON.toJSONString(plusOne(digits3)));
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        // 从末尾开始加1，如果当前数字小于9，加1并返回，如果等于9则当前数字更新为0，并且进行下一个数字计算
        for(int i = n - 1; i >= 0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        // 计算到这里表示所有数据均为9，那么数组需要增加一位并设置首元素为1
        digits = new int[n + 1];
        digits[0] = 1;

        return digits;
    }
}
