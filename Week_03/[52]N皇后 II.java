//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯算法 
// 👍 230 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int totalNQueens(int n) {
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        return backtrack(n, 0, columns, diagonals1, diagonals2);
    }

    public int backtrack(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backtrack(n, row + 1, columns, diagonals1, diagonals2);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }
}

//
class Solution {

    int res = 0;

    public int totalNQueens(int n) {
        int[] used = new int[n];
        int[] path = new int[n];
        Arrays.fill(path, -1);
        int flag = 0;
        for(int i=0; i<(n+1)/2; i++) { //仅需遍历一半！！！
            if(i==(n/2)) flag = res; //n为奇数时，当遍历到中间时，记录一下之前有多少个res;n为偶数走不到这一步。
            used[i] = 1;
            path[0] = i;
            backTrace(used, i, 1, n, path);
            path[0] = -1;
            used[i] = 0;
        }
        if((n&1)==0) return res*2; //n为偶数时，直接是前一半结果乘2
        return flag*2+(res-flag);//n为奇数时，我们之前记录的flag乘2,加上以中间值为起点的时候回溯的结果(res-flag)

    }
    public void backTrace(int[] used, int last, int rowid, int n, int[] path) {
        if(rowid==n) {
            res++;
            return;
        }
        for(int i=0; i<n; i++) {
            if(used[i]==1) continue; //判断该列是否已经有过了
            if(i==last || (last>0 && i==last-1) || (last<n-1 && i==last+1)) continue; //判断竖向，斜向问题
            if(rowid>1) { //判断是否有跨行的斜向问题
                int j = rowid-2;
                for(; j>=0; j--) {
                    if( i-path[j] == rowid-j || path[j]-i == rowid-j) break;
                }
                if(j>=0) continue;
            }
            used[i] = 1;
            path[rowid] = i;
            backTrace(used, i, rowid+1, n, path);
            path[rowid] = -1;
            used[i] = 0;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
