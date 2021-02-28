//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。 
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学 
// 👍 279 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
* 有且仅有一个二进制位是1
* */
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0; // 最后一个1 打掉之后，就是0
    }
}

// 可以调用 [191-位1的个数] 看是否个数为1
/*
class Solution {
    public boolean isPowerOfTwo(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= n - 1;
        }
        if (count == 1) {
            return true;
        }
        return false;
    }
}
*/

/////////////////
class Solution {
  public boolean isPowerOfTwo(int n) {
    if (n == 0) return false;
    long x = (long) n;
    return (x & (-x)) == x;
  }
}
//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1) == 0);
    }
}