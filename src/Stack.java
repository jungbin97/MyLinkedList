import java.util.NoSuchElementException;

public class Stack<E> {
    /***************************************************
     * LIFO(Last-In-First-Out)
     *
     * push(), pop(), peek(), isEmpty() 구현
     ***************************************************/
    private MyLinkedList<E> list;

    // 생성자
    public Stack() {
        this.list = new MyLinkedList<>();
    }

    // push 메서드 (항상 맨 위에 추가)
    public void push(E item) {
        list.addLast(item);
    }

    // pop 메서드 (스택 맨위에 있는 값 반환 및 제거)
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is Empty!");
        }
        return list.deleteLast();
    }

    // peek 메서드 (스택의 맨위 데이터 반환, 제거는 하지 않음)
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is Empty!");
        }
        return list.get(list.getSize() - 1);
    }

    // isEmpty (스택이 비어있는지 확인)
    public boolean isEmpty() {
        return size() == 0;
    }

    public int size(){
        return list.getSize();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
