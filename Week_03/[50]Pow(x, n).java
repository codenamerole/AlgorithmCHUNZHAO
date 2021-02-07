//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
// Related Topics 数学 二分查找 
// 👍 572 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
* 1. 库函数； for-i 累乘 O(n)
* 2. 分治  2^10 = 2^5 * 2^5
*          2^5 = 2^2 * 2^2   根据奇偶判断是否需要再补一个
* */
//Round1 快速幂
/*class Solution {
    public double myPow(double x, int n) {
        double res = 1.0;
        // subproblem myPow(x, n/2)
        for (int i = n; i != 0; i/= 2) {
            if (i % 2 != 0) {
                //merge
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }
}*/

//Round1 调用函数
/*class Solution {
    public double myPow(double x, int n) {
        return n >= 0 ? fastPow(x,n) : 1.0 / fastPow(x, -n);
    }

    public double fastPow(double x, int n){
        double half = 1.0;
        //terminator
        if (n == 0) return 1.0;
        if (n == 1) return x;
        // subproblems
        half = fastPow(x, n / 2);
        //merge
        return n % 2 == 0 ? half * half : half * half * x;
        //revert
    }
}*/
//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public double myPow(double x, int n) {
        return n >= 0 ? fastPow(x, n) : 1 / fastPow(x, n);
    }
    public double fastPow(double x, int n) {
        double half = 1.0;
        if (n == 0) return 1.0;
        if (n == 1) return x;
        half = fastPow(x, n / 2);
        return n % 2 == 0 ? half * half : half * half * x;
    }
}