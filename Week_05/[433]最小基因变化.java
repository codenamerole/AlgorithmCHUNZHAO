//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意： 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 
//
// 示例 1： 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2： 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3： 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
// 👍 66 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 DFS
/*遍历基因库，找到库中与当前基因相差一个碱基的就是下一步变化的基因，这时步数+1并进入下一层*/
/*
class Solution {
    int minStepCount = Integer.MAX_VALUE;
    public int minMutation(String start, String end, String[] bank) {
        dfs(new HashSet<String>(), 0, start, end, bank);
        return (minStepCount == Integer.MAX_VALUE) ? -1 : minStepCount;
    }
    public void dfs (HashSet<String> step, int stepCount,
                      String current, String end, String[] bank) {
        if (current.equals(end))
            minStepCount = Math.min(stepCount, minStepCount);
        for (String str: bank) {
            int diff = 0;
            for (int i = 0; i < str.length(); i++)
                if (current.charAt(i) != str.charAt(i))
                    if (++diff > 1) break;
            if (diff == 1 && !step.contains(str)) {
                step.add(str);
                dfs(step, stepCount + 1, str, end, bank);
                step.remove(str);
            }
        }
    }
}
*/

//Round1 BFS 单向
/*
* 1 把start放入队列中
* 2 出队一个元素，修改这个元素上第一字母，修改值在这四个字母中选择'A', 'C', 'G', 'T'，
*       四个字母都遍历一遍，如果和最后一个元素匹配，那么就退出，返回当前的层级（step）
*       如果修改后元素的在bank中出现，那么就放入队列中，同时删除bank中的相同的元素。
* 3 然后把第一个元素还原原先的字母，然后开始修改第二个字母。执行和第2步一致
* */
/*
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(end)) {
            return -1;
        }
        char[] four = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        set.remove(start);
        int step = 0;
        while (queue.size() > 0) {
            step++;
            for (int count = queue.size(); count > 0; --count) {
                char[] temStringChars = queue.poll().toCharArray(); //取出第一个碱基
                for (int i = 0, len = temStringChars.length; i < len; ++i) {
                    char oldChar = temStringChars[i];
                    //替换
                    for (int j = 0; j < 4; ++j) {
                        temStringChars[i] = four[j];
                        String newGenetic = new String(temStringChars); //转变为string类型
                        if (end.equals(newGenetic)) { //满足条件 返回步数
                            return step;
                        } else if (set.contains(newGenetic)) { // 基因库中存在，从库中取出放入队列，下一步处理
                            set.remove(newGenetic);
                            queue.offer(newGenetic);
                        }
                    }
                    temStringChars[i] = oldChar; //恢复到对当前这一碱基操作之前的状态
                }
            }
        }
        return -1;
    }
}
*/

//Round1 BFS 双向
/*class Solution {
    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(end)) {
            return -1;
        }
        char[] four = {'A', 'C', 'G', 'T'};
        HashSet<String> positive = new HashSet<String>(){{add(start);}};
        HashSet<String> negative = new HashSet<String>(){{add(end);}};
        HashSet<String> tempNewSet = new HashSet<>();
        int step = 0;
        while (positive.size() > 0 && negative.size() > 0) {
            step++;
            // 每次判断正向是否比负向的元素更多。我们选择元素更小的那个继续更新。
            if (positive.size() > negative.size()) {
                HashSet<String> temp = new HashSet<>(positive);
                positive = negative;
                negative = temp;
            }

            // 遍历每个正向的元素。
            for (String item : positive) {
                String str;
                char[] tempStringChars = item.toCharArray();
                for (int i = tempStringChars.length - 1; i >= 0; --i) {
                    char oldChar = tempStringChars[i];
                    for (int j = 0; j < 4; ++j) {
                        tempStringChars[i] = four[j];
                        String newString = new String(tempStringChars);
                        if (negative.contains(newString)) {
                            return step;
                        } else if (set.contains(newString)) {
                            set.remove(newString);
                            tempNewSet.add(newString);
                        }
                    }
                    tempStringChars[i] = oldChar;
                }
            }
            positive = new HashSet<>(tempNewSet);
            tempNewSet.clear();
        }
        return -1;
    }
}*/
//leetcode submit region end(Prohibit modification and deletion)
//Round2 BFS 单向
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(end)) {
            return -1;
        }
        char[] gene = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        set.remove(start);
        int step = 0;
        while (queue.size() > 0) {
            step++;
            for (int count = queue.size(); count > 0; --count) {
                char[] temGenes= queue.poll().toCharArray();
                for (int i = 0, len = temGenes.length; i < len; ++i) {
                    char oldChar = temGenes[i];
                    for (int j = 0; j < 4; j++) {
                        temGenes[i] = gene[j];
                        String newGenes = new String(temGenes);
                        if (end.equals(newGenes)) {
                            return step;
                        }else if (set.contains(newGenes)){
                            set.remove(newGenes);
                            queue.offer(newGenes);
                        }
                    }
                    temGenes[i] = oldChar;
                }
            }
        }
        return -1;
    }
}