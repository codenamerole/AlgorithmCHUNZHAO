//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法 
// 👍 328 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    String word = null;
    public TrieNode() {}
}

class Solution {
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}

/*class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        //构建字典树
        wordTrie myTrie=new wordTrie();
        trieNode root=myTrie.root;
        for(String s:words)
            myTrie.insert(s);

        //使用set防止重复
        Set<String> result =new HashSet<>();
        int m=board.length;
        int n=board[0].length;
        boolean [][]visited=new boolean[m][n];
        //遍历整个二维数组
        for(int i=0;i<board.length; i++){
            for (int j = 0; j < board [0].length; j++){
                find(board,visited,i,j,m,n,result,root);
            }
        }
        System.out.print(result);
        return new LinkedList<String>(result);
    }
    private void find(char [] [] board, boolean [][]visited,int i,int j,int m,int n,Set<String> result,trieNode cur){
        //边界以及是否已经访问判断
        if(i<0||i>=m||j<0||j>=n||visited[i][j])
            return;
        cur=cur.child[board[i][j]-'a'];
        visited[i][j]=true;
        if(cur==null)
        {
            //如果单词不匹配，回退
            visited[i][j]=false;
            return;
        }
        //找到单词加入
        if(cur.isLeaf)
        {
            result.add(cur.val);
            //找到单词后不能回退，因为可能是“ad” “addd”这样的单词得继续回溯
//            visited[i][j]=false;
//            return;
        }
        find(board,visited,i+1,j,m,n,result,cur);
        find(board,visited,i,j+1,m,n,result,cur);
        find(board,visited,i,j-1,m,n,result,cur);
        find(board,visited,i-1,j,m,n,result,cur);
        //最后要回退，因为下一个起点可能会用到上一个起点的字符
        visited[i][j]=false;
    }


}

//字典树
class wordTrie{
    public trieNode root=new trieNode();
    public void insert(String s){
        trieNode cur=root;
        for(char c:s.toCharArray()){
            if(cur.child[c-'a']==null){
                cur.child [c-'a'] = new trieNode();
                cur=cur.child[c-'a'];
            }else
                cur=cur.child [c-'a'];
        }
        cur.isLeaf=true;
        cur.val=s;
    }
}
//字典树结点
class trieNode{
    public String val;
    public trieNode[] child=new trieNode[26];
    public boolean isLeaf=false;

    trieNode(){

    }
}*/
//leetcode submit region end(Prohibit modification and deletion)