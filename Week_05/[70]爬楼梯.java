//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1421 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 Fibonacci 递推
/*class Solution {
    public int climbStairs(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }
}*/

//递归


//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public int climbStairs(int n) {
        int res[] = new int[n + 1];
        res[0] = 1; // 注意写的是 0,1项，不是1,2项
        res[1] = 1;
        for (int i = 2; i <= n; i++) { // 注意 循环变量初始为2，最后要包含 <=n
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }
}
//Round3
class Solution {
    public int climbStairs(int n) {
        int res[] = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }
}