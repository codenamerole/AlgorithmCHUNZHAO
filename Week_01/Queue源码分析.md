```
public interface Queue<E> extends Collection<E> {  
  
    boolean add(E e); // 添加元素到队列中，相当于进入队尾排队。  
  
    boolean offer(E e);  //添加元素到队列中，相当于进入队尾排队.  
  
    E remove(); //移除队头元素  
  
    E poll();  //移除队头元素  
  
    E element(); //获取但不移除队列头的元素  
  
    E peek();  //获取但不移除队列头的元素  
} 
```

Queue定义了接口，具体实现交给实现类

