package xxh.leetcode.recursion;

/**
 * @Author: elyuan
 * @Date: 2020/12/14 6:05 下午
 */
public class Solution1 {

    private static int coinChange(int[] coins,int amount){
      return dp(coins,amount);
    }


    public static void main(String[] args) {
        int[] array = {2};
        System.out.println(coinChange(array,3));

    }

    private static int dp(int[] coins, int n) {
        if(n == 0){
            return 0;
        }
        if(n<0){
            return -1;
        }
        int res =Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = dp(coins,n-coin);
            if(subProblem==-1){
                continue;
            }
            res = Math.min(res,1+subProblem);
        }
        return res !=Integer.MAX_VALUE?res:-1;
    }


}
