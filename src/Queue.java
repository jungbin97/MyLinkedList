import java.util.NoSuchElementException;

public class Queue<E> {
    /***************************************************
     * FIFO(Frist_In_First_Out)
     *
     * Queue 기본 명세
     * add(), remove(), peek(), isEmpty(), size() 구현
     *
     * add() : 데이터 추가
     * remove() : 첫번째 데이터 반환 및 삭제, queue가 비어있으면 예외 발생 (NoSuchElementException)
     * peek() : 큐의 첫번째를 반환, queue가 비어있으면 예외 발생 (NoSuchElementException)
     * isEmpty() : 큐가 비어있는지 확인, true, false 반환
     * size() : 큐의 길이를 반환
     ***************************************************/
    private MyLinkedList<E> list;

    // 생성자
    public Queue() {
        this.list = new MyLinkedList<>();
    }

    // add 메서드 (데이터 마지막에 추가)
    public void add(E item){
        list.addLast(item);
    }

    // remove() 메서드 (첫번째 데이터 반환 및 삭제)
    public E remove(){
       if (isEmpty()) {
           throw new NoSuchElementException("Queue is Empty!");
       }
       return list.deleteFirst();
    }

    // peek 메서드 (큐의 첫번째 반환)
    public E peek(){
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is Empty!");
        }
        return list.get(0);
    }

    // isEmpty 메서드 (큐가 비어있는지 확인, 비어있으면 true 반환)
    public boolean isEmpty(){
        return list.getSize() == 0;
    }

    public int size() {
        return list.getSize();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
