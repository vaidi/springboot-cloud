package xxh.leetcode.recursion;

/**
 * @Author: elyuan
 * @Date: 2020/12/14 3:51 下午
 */
public class Solution {



    public static int fib3(int n){
       if(n ==2 || n ==1){
           return 1;
       }
       int prev =1,curr =1;
       for(int i =3;i<=n;i++){
           int sum = prev +curr;
           prev = curr;
           curr = sum;
       }
       return curr;
    }



    public static int fib2(int n){
        int[] dp = new int[n+1];
        dp[1] = dp[2] =1;
        for(int i= 3;i<= n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }


    public static int fib1(int n){
        if(n < 1){
            return 0;
        }
        int[] memo = new int[n+1];
        return helper(memo,n);
    }

    private static int helper(int[] memo, int n) {
        if(n ==1 || n == 2){
            return 1;
        }
        if(memo[n] != 0){
            return memo[n];
        }
        memo[n] = helper(memo,n-1)+helper(memo,n-2);
        return memo[n];
    }


    public static int fib(int n){
        if(n ==1 || n== 2){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(fib(30));
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println(fib1(30));
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println(fib2(30));
        System.out.println(System.currentTimeMillis()-startTime);
    }

}
