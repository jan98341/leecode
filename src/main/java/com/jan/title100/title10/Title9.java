package com.jan.title100.title10;

public class Title9 {
    public static void main(String[] args) {

        Title9 t = new Title9();
//        System.out.println("是否回文整数:"+t.isPalindrome1(121));
//        System.out.println("是否回文整数:"+t.isPalindrome1(-121));
//        System.out.println("是否回文整数:"+t.isPalindrome1(10));

//        System.out.println("是否回文整数:"+t.isPalindrome2(121));
//        System.out.println("是否回文整数:"+t.isPalindrome2(-121));
//        System.out.println("是否回文整数:"+t.isPalindrome2(10));
//        System.out.println("是否回文整数:"+t.isPalindrome2(0));
//        System.out.println("是否回文整数:"+t.isPalindrome2(12366321));

        System.out.println("是否回文整数:"+t.isPalindrome3(121));
        System.out.println("是否回文整数:"+t.isPalindrome3(-121));
        System.out.println("是否回文整数:"+t.isPalindrome3(10));
        System.out.println("是否回文整数:"+t.isPalindrome3(0));
        System.out.println("是否回文整数:"+t.isPalindrome3(12366321));
    }

    /**
     * 判断数字是否回文数
     * @param x 需要判断的数字
     * @return 是否回文
     */
    public boolean isPalindrome1(int x) {
        String s = x + "";
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断数字是否回文数
     * @param x 需要判断的数字
     * @return 是否回文
     */
    public boolean isPalindrome2(int x) {
        // 如果数字为负数，并且个数为0的非零数字不是回文数
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int r = 0;
        while( x > r) {
            r = r * 10 + x % 10;
            x /= 10;
        }

        // 需要考虑数字为奇数或者偶数的情况
        return r == x || x == r / 10;
    }

    /**
     * 判断数字是否回文数
     * @param x 需要判断的数字
     * @return 是否回文
     */
    public boolean isPalindrome3(int x) {
        StringBuilder s = new StringBuilder(x+"");
        return s.toString().equals(s.reverse().toString());
    }
}
