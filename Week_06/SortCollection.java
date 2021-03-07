public class SortCollection {
    /*
    * 初级排序
    * 1. 选择排序
    * 2. 插入排序
    * 3. 冒泡排序
    * */

    // 选择排序
    public int[] selectionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIdx]) {
                   minIdx = j;
                }
            }
            int tmp = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = tmp;
        }
        return nums;
    }
    // 插入排序
    public int[] insertionSort(int[] nums) {
        int current;
        int preIdx;
        for (int i = 0; i < nums.length - 1; i++) {
            preIdx = i - 1;
            current = nums[i];
            while (preIdx >= 0 && nums[preIdx] > current) {
                    nums[preIdx + 1] = nums[preIdx];
                    preIdx--;
                }
            nums[preIdx + 1] = current;
        }
        return nums;
    }

    // 冒泡排序
    public int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {   //length-1 也没毛病 length-i-1可提高效率
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
        return nums;
    }

    /*
    * 高级排序
    * 1. 快速排序
    * 2. 堆排序
    * 3. 归并排序
    * */

    // 快速排序


    public static void quickSort(int[] array, int begin, int end) {
        if (end <= begin){
            return;
        }
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }
    static int partition(int[] a, int begin, int end) {
        // pivot: 标杆位置，counter: 小于pivot的元素的个数
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            if (a[i] < a[pivot]) {
                int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
                counter++;
            }
        }
        int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
        return counter;
    }
    // 堆排序
    static void heapify(int[] array, int length, int i) {
        int left = 2 * i + 1, right = 2 * i + 2;
        int largest = i;
        if (left < length && array[left] > array[largest]) {
            largest = left;
        }
        if (right < length && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
            heapify(array, length, largest);
        }
    }
    public static void heapSort(int[] array) {
        if (array.length == 0) {
            return;
        }
        int length = array.length;
        for (int i = length / 2-1; i >= 0; i--){
            heapify(array, length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            int temp = array[0]; array[0] = array[i]; array[i] = temp;
            heapify(array, i, 0);
        }
    }
    // 归并排序
    public static void mergeSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) >> 1; // (left + right) / 2

        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid)  {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
        // 也可以用 System.arraycopy(a, start1, b, start2, length)
    }
}
