package com.jan.title200.title130;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 提示：
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 */
public class Title121 {
    public static void main(String[] args) {
        int[] prices1 = {7,1,5,3,6,4};
        int[] prices2 = {7,6,4,3,1};

        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit(prices2));

        System.out.println(maxProfit2(prices1));
        System.out.println(maxProfit2(prices2));
    }

    /**
     * 暴力获取最大利润，时间复杂度O(n^2)
     */
    public static int maxProfit(int[] prices) {
        if(prices.length < 2) {
            return 0;
        }

        int n = prices.length;
        int maxProfit = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                maxProfit = Math.max(maxProfit, prices[i] - prices[j]);
            }
        }

        return maxProfit;
    }

    /**
     * 时间复杂度O(n)
     * 从左到右枚举卖出价格 prices[i]。要想获得最大利润，需要股票价格最低的那天买入。
     * 买入日期必须在卖出日期之前，所以我们求的是从 prices[0] 到 prices[i−1] 的最小值，这可以用一个变量 minPrice 维护。
     */
    public static int maxProfit2(int[] prices) {
        int ans = 0;
        int minPrice = prices[0];
        for(int p : prices) {
            ans = Math.max(ans, p - minPrice);
            minPrice = Math.min(minPrice, p);
        }

        return ans;
    }
}
