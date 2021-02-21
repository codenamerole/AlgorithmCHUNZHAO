//给定一个正整数 n，返回长度为 n 的所有可被视为可奖励的出勤记录的数量。 答案可能非常大，你只需返回结果mod 109 + 7的值。 
//
// 学生出勤记录是只包含以下三个字符的字符串： 
//
// 
// 'A' : Absent，缺勤 
// 'L' : Late，迟到 
// 'P' : Present，到场 
// 
//
// 如果记录不包含多于一个'A'（缺勤）或超过两个连续的'L'（迟到），则该记录被视为可奖励的。 
//
// 示例 1: 
//
// 
//输入: n = 2
//输出: 8 
//解释：
//有8个长度为2的记录将被视为可奖励：
//"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//只有"AA"不会被视为可奖励，因为缺勤次数超过一次。 
//
// 注意：n 的值不会超过100000。 
// Related Topics 动态规划 
// 👍 121 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int checkRecord(int n) {
        int _MOD = 1000000007;
        long[][][] dp = new long[n + 1][2][3]; // i, A:{0:non, 1:contains}, L:{0,1,2}
        dp[1][1][0] = 1; // A
        dp[1][0][1] = 1; // L
        dp[1][0][0] = 1; // P
        for (int i = 2; i <= n; i++) {
            // +P
            // 0A0L = 0A0L + 0A1L + 0A2L
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % _MOD;
            // 1A0L = 1A0L + 1A1L + 1A2L
            dp[i][1][0] = (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % _MOD;
            // +L
            // 0A1L = 0A0L
            dp[i][0][1] = dp[i - 1][0][0];
            // 0A2L = 0A1L
            dp[i][0][2] = dp[i - 1][0][1];
            // 1A1L = 1A0L
            dp[i][1][1] = dp[i - 1][1][0];
            // 1A2L = 1A1L
            dp[i][1][2] = dp[i - 1][1][1];
            // +A
            // 1A0L = 0A0L + 0A1L + 0A2L
            dp[i][1][0] += (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % _MOD;
        }
        return (int) ((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] + dp[n][1][0] + dp[n][1][1] + dp[n][1][2]) % _MOD);
    }
}

//Round1 空间优化
class Solution {
    public int checkRecord(int n) {
        int _MOD = 1000000007;
        long dp00, dp01, dp02, dp10, dp11, dp12;
        dp00 = dp01 = dp10 = 1;
        dp11 = dp02 = dp12 = 0;
        for (int i = 2; i <= n; i++) {
            long t00 = dp00, t10 = dp10;
            dp00 = (t00 + dp01 + dp02) % _MOD;
            dp10 = (t10 + dp11 + dp12 + t00 + dp01 + dp02) % _MOD;
            dp02 = dp01;
            dp01 = t00;
            dp12 = dp11;
            dp11 = t10;
        }
        return (int) ((dp00 + dp01 + dp02 + dp10 + dp11 + dp12) % _MOD);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
