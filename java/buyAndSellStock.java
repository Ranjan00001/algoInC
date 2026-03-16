/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
*/

class Solution {
    public int maxProfit(int[] prices) {
        // traverse backward and find the maximum price
        int len = prices.length;
        int maxProfitSellPrice = prices[len - 1];
        int maxProfitBuyPrice = 0; // 0 will indicate that this hasn't been bought yet
        int result = 0;

        for (int i = len - 2; i >= 0; i--) {
            maxProfitBuyPrice = prices[i];
            int profit = maxProfitSellPrice - maxProfitBuyPrice;
            if (profit > result) {
                result = profit;
            }
            maxProfitSellPrice = Integer.max(maxProfitSellPrice, prices[i]);
        }

        /*
        Dry run: prices = [7,1,5,3,6,4]

        */

        return result;
        
    }
}