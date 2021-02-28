import java.util.HashSet;
import java.util.Set;

public class TwoWayBFS {
    public static void main(String[] args) {
        // 第 1 步：分别构建两个访问集合，并把起点和终点添加进来
        String beginWord, endWord;
        Set<String> visited = new HashSet<>();
        // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> beginVisited = new HashSet<>();
        //beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
       // endVisited.add(endWord);

        // 第 2 步：执行双向 BFS，左右交替扩散的步数之和为所求
        int step = 1;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }
            // process data
        }
    }
}
