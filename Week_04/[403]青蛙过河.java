//一只青蛙想要过河。 假定河流被等分为 x 个单元格，并且在每一个单元格内都有可能放有一石子（也有可能没有）。 青蛙可以跳上石头，但是不可以跳入水中。 
//
// 给定石子的位置列表（用单元格序号升序表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一个石子上）。 开始时， 青蛙默认已站在第一个石子上，并可以
//假定它第一步只能跳跃一个单位（即只能从单元格1跳至单元格2）。 
//
// 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。 
//
//
// 请注意： 
//
// 
// 石子的数量 ≥ 2 且 < 1100； 
// 每一个石子的位置序号都是一个非负整数，且其 < 231； 
// 第一个石子的位置永远是0。 
// 
//
// 示例 1: 
//
// 
//[0,1,3,5,6,8,12,17]
//
//总共有8个石子。
//第一个石子处于序号为0的单元格的位置, 第二个石子处于序号为1的单元格的位置,
//第三个石子在序号为3的单元格的位置， 以此定义整个数组...
//最后一个石子处于序号为17的单元格的位置。
//
//返回 true。即青蛙可以成功过河，按照如下方案跳跃： 
//跳1个单位到第2块石子, 然后跳2个单位到第3块石子, 接着 
//跳2个单位到第4块石子, 然后跳3个单位到第6块石子, 
//跳4个单位到第7块石子, 最后，跳5个单位到第8个石子（即最后一块石子）。
// 
//
// 示例 2: 
//
// 
//[0,1,2,3,4,8,9,11]
//
//返回 false。青蛙没有办法过河。 
//这是因为第5和第6个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
// 
// Related Topics 动态规划 
// 👍 144 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 类似跳跃游戏吧
/*
* key 表示当前石头的位置，valuevalue 是一个包含 jumpsizejumpsize 的集合，
*                其中每个 jumpsizejumpsize 代表可以通过大小为 jumpysizejumpysize 的一跳到达当前位置
* 首先我们对散列表初始化，keykey 为所有石头的位置，除了位置 0 对应的 valuevalue 为包含一个值 0 的集合以外，其余都初始化为空集。
* 接下来，依次遍历每个位置上的石头。对于每个currentPosition，遍历 value中每个 jumpsize，判断位置 currentPosition + newjumpsize 是否存在于map 中，
*    对于每个 jumpsize ，newjumpsize 分别为 jumpsize-1，jumpsize，jumpsize+1。
*    如果找到了，就在对应的 value 集合里新增 newjumpsize。重复这个过程直到结束。
* 如果在结束的时候，最后一个位置对应的集合非空，那也就意味着我们可以到达终点，如果还是空集那就意味着不能到达终点
* */
class Solution {
    public boolean canCross(int[] stones) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        map.get(0).add(0);
        for (int i = 0; i < stones.length; i++) {
            for (int k : map.get(stones[i])) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }
}

/*
从后向前，最后一步跳了L步，到了a[n-1]，该步肯定从a[i]跳过来 a[i] = a[n-1]-L
接着考虑倒数第二步是否能跳到a[i] 且这一步的范围是 L-1 L L+1
* dp[i][j] 代表能否最后一跳长度为 j 跳到 a[i]
* dp[i][j]=dp[k][j−1]∣∣dp[k][j]∣∣dp[k][j+1]
* */
class Solution {
    public boolean canCross(int[] A) {
        int n = A.length;
        //每一块石头作为下标
        HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();
        int i, j;

        //初始化
        for (i = 0; i < n; i++) {
            dp.put(A[i], new HashSet<>());
        }
        //一开始就在初始位置
        dp.get(A[0]).add(0);

        //看每一块石头
        for (i = 0; i < n; i++) {
            //获取这块石头对应的Si
            HashSet<Integer> Si = dp.get(A[i]);
            //k = previous jump
            for (Integer k : Si) {
                //j = current jump
                for (j = k - 1; j <= k + 1; j++) {
                    //只能往右跳
                    if (j <= 0) {
                        continue;
                    }
                    //如果有这块石头，需要更新
                    if (dp.containsKey(A[i] + j)) {
                        dp.get(A[i] + j).add(j);
                    }
                }
            }
        }
        return !dp.get(A[n - 1]).isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
