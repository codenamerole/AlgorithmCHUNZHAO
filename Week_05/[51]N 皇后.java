//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
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
// 👍 740 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 回溯
/*class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                chess[i][j] = '.';
        List<List<String>> res = new ArrayList<>();
        solve(res, chess, 0);
        return res;
    }
    public void solve(List<List<String>> res, char[][] chess, int row) {
        //terminator
        if (row == chess.length) {
            res.add(construct(chess));
            return;
        }
        for (int col = 0; col < chess.length; col++) {
            if (valid(chess, row, col)) {
                chess[row][col] = 'Q';
                //drill down
                solve(res, chess, row + 1);
                //reverse state
                chess[row][col] = '.';
            }
        }
    }
    private boolean valid(char[][] chess, int row, int col) {
        //判断当前列有没有皇后,因为他是一行一行往下走的，
        //我们只需要检查走过的行数即可，通俗一点就是判断当前
        //坐标位置的上面有没有皇后
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        //判断当前坐标的右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        //判断当前坐标的左上角有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    //调整成符合要求的输出样式
    private List<String> construct(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            path.add(new String(chess[i]));
        }
        return path;
    }
}*/
//Round1 位运算
/*
*  列 撇 捺 已经被皇后占据的位置 -> 二进制数
*  (1 << n) - 1 得到对应的二进制位数
*  (cols|pie|na) 所有不能放的位置0  取反 ~(cols|pie|na) 所有能放的位置1
*  放置过程  放在最后一个位置，bits&(bits-1) 把最后一位是1的打掉 0表示不能放
*  DFS过程 撇捺(左移 右移) 实际要分别再往左右下角 多打一位
* */

class Solution {
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(position - 1);
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                queens[row] = -1;
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}


//leetcode submit region end(Prohibit modification and deletion)

//Round2 回溯
/*
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        solve(res, chess,0);
        return res;
    }

    public void solve(List<List<String>> list, char[][] chess, int row) {
        // terminator
        if (row == chess.length) {
            list.add(format(chess));
            return;
        }
        // process
        for (int col = 0; col < chess.length; col++) {
             if (isValid(chess, row ,col)){  //注意 这里不是简单的if-else， 要递归solve才能一直
                 chess[row][col] = 'Q';
                 solve(list, chess,row + 1);
                 chess[row][col] = '.';
             }
        }
    }

    public boolean isValid(char[][] chess, int row, int col) {
        // 正上
        for (int i = 0; i < row ; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        // 左上
        for (int i = row, j = col; i >= 0 && j >= 0 ; i--, j--) {  //注意从 row-1 col-1 当前左上角开始
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        // 右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length ; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public List<String> format(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            path.add(new String(chess[i]));
        }
        return path;
    }
}*/
//Round2 位运算
class Solution {
    public List<List<String>> solveNQueens(int n) {

    }

    public void solve(char[][] board, int ) {

    }
    public List<Integer> _generateBoard() {

    }
}