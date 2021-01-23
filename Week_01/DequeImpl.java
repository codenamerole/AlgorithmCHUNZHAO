import java.util.Deque;
import java.util.LinkedList;

/* ****************************
*   Deque Java8的API 实现
* */
public class DequeImpl {
        public static void main(String[] args){
            Deque<String> deque = new LinkedList<String>();
            deque.addFirst("a");  //push
            deque.addFirst("b");
            deque.addFirst("c");
            System.out.println(deque);
            String str = deque.peekFirst(); //peek
            System.out.println(str);
            System.out.println(deque);
            while(deque.size() > 0){
                System.out.println(deque.removeFirst()); //pop
            }
            System.out.println(deque);
        }
}
