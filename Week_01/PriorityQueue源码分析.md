一、全局变量

这里底层实现用的是数组，初始容量设为11

    //默认的初始化容量
        private static final int DEFAULT_INITIAL_CAPACITY = 11;
    //实际保存元素的数组
    transient Object[] queue; 
    
    //记录元素个数
    private int size = 0;
    
    //排序用的比较器
    private final Comparator<? super E> comparator;
     
    //记录修改次数
    transient int modCount = 0; 
    
    //数组的最大大小
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
二、构造方法

 PriorityQueue的构造函数有多种，参数可用来设置容量和设置优先级

  （1）PriorytyQueue ():创建一个默认容量(11)和自然顺序的优先级队列;

  （2）PriorityQueue (int initialCapacity):创建一个指定容量和自然顺序的优先级队列；

  （3）PriorityQueue (int initialCapacity,Comparator<? super E> comparator)：创建一个指定容量和指定比较器的优先级队列；

  （4）PriorityQueue (Collection<? extends E> c)：创建一个包含指定集合元素的优先级队列，它分为三种情况：

   第一种：如果是一个已经排好序的集合，首先根据集合获取到他的比较器方法，然后将集合中的元素添加到队列中；

   第二种：如果是一个优先级队列，首先获取比较器方法，然后将指定优先级队列中的元素赋值到该队列中；

   第三种：其他情况默认使用自然比较器，将集合中的元素添加到队列中；

  （5）PriorityQueue (PriorityQueue<? extends E> c)：创建一个包含指定优先级队列的队列，他的实现逻辑与（4）中的第二种情况相同；

  （6）PriorityQueue (SortedSet<? extends E> c)：创建一个包含指定排序集合中元素的优先级队列，他的实现逻辑与（4）中的第一种情况相同

三、其他方法

还提供了grow() 和 hugeCapacity() 来实现扩容，如果当前数组的长度小于64，那么将扩大一半的长度，否则扩大一倍，同时需要对扩容后的数组的容量进行检查，不能超过最大的限制容量

四、数据操作方法

1. add & offer

   ​	确实add报异常，而offer返回错误值

   主要操作逻辑如下：

    （1）首先需要判断插入的元素是否是null，因为在优先级队列中不允许存在空元素，这是因为在队列中是根据null来判断某个位置是否存在值；

     （2）如果当前队列中的元素的个数大于或等于存储数组的长度，那么需要对数组进行扩容，之后再进行操作

     （3）如果当前的队列为空，那么插入的是第一个元素，直接赋值到数组的第[0]位置，结束操作；

     （4）如果当前队列不为空，默认是将新插入的元素添加到数组的尾部，但是由于优先级队列要求及父节点与子节点之间的值有比较的关系，所以可能需要进行调整，主要是在siftUp方法中实现，根据有无比较器决定最终的实现方法，将插入的元素与其父节点元素比较，如果大于其父节点，那么完成插入操作，不需要调整，如果小于其父节点，那么需要与其父节点调换位置，然后在一层层的往上比较，使其满足优先级队列的特性。

其他操作逻辑类似，先看到这里，完成后面任务再来写完