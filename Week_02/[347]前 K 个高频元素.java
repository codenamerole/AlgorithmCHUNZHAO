//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 621 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 数字-频数， 以频数维护小根堆，只需与堆顶比较，最后取出k个
/*class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //统计频数，放入hash表
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {  //小根堆
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();  //取出键-值
            if (heap.size() == k) {  //已足k个
                if (heap.peek()[1] < count) { //当前元素的频数比 目前堆中的最小频数大，即满足条件
                    heap.poll(); //把当前堆顶移除
                    heap.offer(new int[]{num, count}); //当前元素入堆
                }
            } else { //不足k个 直接入堆
                heap.offer(new int[]{num, count});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            res[i] = heap.poll()[0];
        }
        return res;
    }
}*/

//
/*class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //统计频次
        Map<Integer,Integer> occurrences = new HashMap<Integer,Integer>();
        for (int num : nums){
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {  //小根堆
                return m[1] - n[1];
            }
        });

        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
           int num = entry.getKey(), count = entry.getValue();
           if (heap.size() == k) {
               if (heap.peek()[1] < count){
                   heap.poll();
                   heap.offer(new int[]{num, count});
               }
           }else{
               heap.offer(new int[]{num, count});
           }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll()[0];
        }
        return res;
    }
}*/
//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occur = new HashMap<Integer,Integer>();
        for (int num : nums){
            occur.put(num, occurrences.getOrDefault(num, 0) + 1); //注意 API写法
        }

        // 注意 写法
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });

        for (Map.Entry<Integer,Integer> entry: occur.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (heap.size() == k){
                if (heap.peek()[1] < count){
                    heap.poll();
                    heap.offer(new int[]{num,count});
                }
            }else{
                heap.offer(new int[]{num,count});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll()[0];
        }
        return res;
    }
}